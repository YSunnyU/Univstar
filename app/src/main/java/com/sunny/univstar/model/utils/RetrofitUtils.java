package com.sunny.univstar.model.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sunny.univstar.app.App;
import com.sunny.univstar.model.entity.AppTokenModel;
import com.sunny.univstar.model.interceptor.AddCookiesInterceptor;
import com.sunny.univstar.model.interceptor.ReceivedCookiesInterceptor;
import com.sunny.univstar.model.service.AlertPasswordService;
import com.sunny.univstar.model.service.FindTeacherService;
import com.sunny.univstar.model.service.HomeMasterService;
import com.sunny.univstar.model.service.HomeNoticeService;
import com.sunny.univstar.model.service.HomeValuableService;
import com.sunny.univstar.model.service.IAppToken;
import com.sunny.univstar.model.service.IHomeWork;
import com.sunny.univstar.model.service.LiveCourseDetailedService;
import com.sunny.univstar.model.service.LiveCourseService;
import com.sunny.univstar.model.service.MasterDetailService;
import com.sunny.univstar.model.service.MyFollowPraiseService;
import com.sunny.univstar.model.service.MyOrderService;
import com.sunny.univstar.model.service.NewPswService;
import com.sunny.univstar.model.service.NoticeDetailedService;
import com.sunny.univstar.model.service.OrderDetailService;
import com.sunny.univstar.model.service.PersonalLoginService;
import com.sunny.univstar.model.service.PersonalRegisterService;
import com.sunny.univstar.model.service.PreferenceService;
import com.sunny.univstar.model.service.RechargeService;
import com.sunny.univstar.model.service.UpdateUserMessService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sunny.univstar.app.App.context;

/**
 * Created by 张玗 on 2018/5/3.
 */

public class RetrofitUtils {
    public static RetrofitUtils retrofitUtils;
    public Retrofit retrofit;
    //    设置网络数据缓存
    //指定缓存目录为SD卡下的cache目录
    File cacheFile = new File(Environment.getExternalStorageDirectory().getPath() + "/cache");
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            return proceed.newBuilder()
                    .header("Cache-Control", "max-age=" + 60 * 60 * 24)
                    .removeHeader("Pragma")
                    .build();
        }
    };
    public static void deleteFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {

                        deleteFile(files[i].getAbsolutePath());
                    }
                }

                if (!file.isDirectory()) {// 如果是文件，删除
                    file.delete();
                } else {// 目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public RetrofitUtils() {

        if (TextUtils.isEmpty(getAppToken(context))){
            loadApptoken(context);
        }

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addNetworkInterceptor(interceptor)//添加网络缓存
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .cache(cache)  //添加缓存
                .connectTimeout(60l, TimeUnit.SECONDS)
                .readTimeout(60l, TimeUnit.SECONDS)
                .writeTimeout(60l, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.univstar.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//        手机权限问题
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(App.context, mPermissionList, 123);
        }
    }

    public static String getAppToken(Context context) {

        if (context == null) {
            return "";
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);
        if (sharedPreferences == null){
            return "";
        }
        String apptoken = sharedPreferences.getString("xyxy_apptoken", "");
        if (TextUtils.isEmpty(apptoken)) {
            return "";
        }

        //TODU测试id
        return apptoken;

    }

    private void loadApptoken(final Context context) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.univstar.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(IAppToken.class)
                .getAppToken("/v1/m/security/apptoken")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppTokenModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppTokenModel value) {

                        if (value == null || value.getData() == null) {
                            return;
                        }

                        String apptoken = value.getData().getApptoken();

                        long time = System.currentTimeMillis();

                        try {
                            String desApptoken = EncryptUtil.decrypt(apptoken);//解码后的数据
                            //解码后的数据拼接上当前系统时间 再编码 去掉换行 把所有字母转成大写
                            String headerApptoken = EncryptUtil.encrypt(time + desApptoken).replaceAll("\\n", "").toUpperCase();

                            saveAppToken(context, headerApptoken, time);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void saveAppToken(Context context, String token, long time) {
        if (context == null) {
            return;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("77777", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //存储的时候 转成大写后的token + "."  + 加上当前系统时间 组成最中的token
        editor.putString("xyxy_apptoken", token + "." + time);
        editor.commit();

    }

    //    单例模式创建
    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }

    //    设置偏好
    public PreferenceService getPreferenceService() {
        return retrofit.create(PreferenceService.class);
    }

    //    作业
    public IHomeWork gethomewokData() {
        return retrofit.create(IHomeWork.class);
    }

    //    名师
    public HomeMasterService getHomeMasterService() {
        return retrofit.create(HomeMasterService.class);
    }

    //    宝典
    public HomeValuableService getHomeValuableService() {
        return retrofit.create(HomeValuableService.class);
    }

    public HomeNoticeService getHomeNoticeService() {
        return retrofit.create(HomeNoticeService.class);
    }

    public PersonalRegisterService getPersonalRegisterService() {
        return retrofit.create(PersonalRegisterService.class);
    }

    public FindTeacherService getFindTeacherService() {
        return retrofit.create(FindTeacherService.class);
    }

    public PersonalLoginService getPersonalLoginService() {
        return retrofit.create(PersonalLoginService.class);
    }

    public LiveCourseService getLiveCourseService() {
        return retrofit.create(LiveCourseService.class);
    }

    public MasterDetailService getMasterDatailService() {
        return retrofit.create(MasterDetailService.class);
    }

    public LiveCourseDetailedService getLiveCourseDetailedService() {
        return retrofit.create(LiveCourseDetailedService.class);
    }
//    修改密码
    public AlertPasswordService getAlertPasswordService(){
        return retrofit.create(AlertPasswordService.class);
    }
//    设置新的密码
    public NewPswService getNewPswService(){
        return retrofit.create(NewPswService.class);
    }

    // 获取文件
    // Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/
    // 目录，一般放一些长时间保存的数据
    // Context.getExternalCacheDir() -->
    // SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            int size2 = 0;
            if (fileList != null) {
                size2 = fileList.length;
                for (int i = 0; i < size2; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * 计算缓存的大小
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            // return size + "Byte";
            return size+"k";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
    public NoticeDetailedService getNoticeDetailedService(){
        return retrofit.create(NoticeDetailedService.class);
    }
    public MyFollowPraiseService getMyFollowPraiseService(){
        return retrofit.create(MyFollowPraiseService.class);
    }
//   金豆充值价格
    public RechargeService getRechargeService(){

        return retrofit.create(RechargeService.class);
    }
//    更改用户信息
    public UpdateUserMessService getUpdateUserMessService(){
        return retrofit.create(UpdateUserMessService.class);
    }
//    我的订单
    public MyOrderService getMyOrderService(){
        return retrofit.create(MyOrderService.class);
    }
//    订单详情
    public OrderDetailService getOrderDetailService(){
        return retrofit.create(OrderDetailService.class);
    }
}
