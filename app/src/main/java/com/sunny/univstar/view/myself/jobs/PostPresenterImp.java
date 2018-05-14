package com.sunny.univstar.view.myself.jobs;

import com.sunny.univstar.model.service.PostService;
import com.sunny.univstar.model.utils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PostPresenterImp implements IPostContract.IPostPresenter {

    private PostService postService;
    private IPostContract.IPostView iPostView;

    public PostPresenterImp(IPostContract.IPostView iPostView) {
        postService = RetrofitUtils.getInstance().getPostService();
        this.iPostView = iPostView;
    }

    @Override
    public void loadPostBean(int loginUserId) {
        postService.getPostBean(loginUserId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostBean postBean) {
                        iPostView.showPostBean(postBean.getData().getArtcircleList().getList());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
