package com.sunny.univstar.view.myself.jobs;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.sunny.univstar.R;
import com.sunny.univstar.app.App;
import com.sunny.univstar.model.service.FollowService;
import com.sunny.univstar.model.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;


public class FollowListAdapter extends BaseAdapter {

    private List<FollowBean.DataBean.ListBean> listBeans;
    private final SharedPreferences user;
    private final SharedPreferences.Editor edit;
    private final FollowService followService;

    public FollowListAdapter(List<FollowBean.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        user = App.context.getSharedPreferences("userState", MODE_PRIVATE);
        edit = user.edit();
        followService = RetrofitUtils.getInstance().getFollowService();
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = App.context.getLayoutInflater().inflate(R.layout.follow_item, parent, false);
            holder.follow_item_img = convertView.findViewById(R.id.follow_item_img);
            holder.follow_item_btn = convertView.findViewById(R.id.follow_item_btn);
            holder.follow_item_nickname = convertView.findViewById(R.id.follow_item_nickname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ViewHolder finalHolder = holder;
        holder.follow_item_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    finalHolder.follow_item_btn.setText("已关注");
                    followService.follow(listBeans.get(position).getAttentionId(), user.getInt("user_id", 0))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<SaveBean>() {
                                @Override
                                public void accept(SaveBean saveBean) throws Exception {
                                    Toast.makeText(App.context, saveBean.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    finalHolder.follow_item_btn.setText("关注");
                    followService.abolishConcern(listBeans.get(position).getAttentionId(), user.getInt("user_id", 0))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<SaveBean>() {
                                @Override
                                public void accept(SaveBean saveBean) throws Exception {
                                    Toast.makeText(App.context, saveBean.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });
        Glide.with(App.context).load(listBeans.get(position).getPhoto()).asBitmap().into(new ImageViewTarget<Bitmap>(finalHolder.follow_item_img) {
            @Override
            protected void setResource(Bitmap bitmap) {
                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(App.context.getResources(), bitmap);
                drawable.setCircular(true);
                finalHolder.follow_item_img.setBackground(drawable);
            }
        });
        holder.follow_item_nickname.setText(listBeans.get(position).getNickname());
        return convertView;
    }

    class ViewHolder {
        public ImageView follow_item_img;
        public CheckBox follow_item_btn;
        public TextView follow_item_nickname;

    }

}
