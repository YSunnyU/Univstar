package com.sunny.univstar.view.myself.jobs;

import com.sunny.univstar.model.service.FansService;
import com.sunny.univstar.model.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class FansPresenterImp implements IFansContract.IFansPresenter {

    private FansService fansService;
    private IFansContract.IFansView iFansView;

    public FansPresenterImp(IFansContract.IFansView iFansView) {
        fansService = RetrofitUtils.getInstance().getFansService();
        this.iFansView = iFansView;
    }

    @Override
    public void loadFansBean(int studentId) {
        Observable<FansBean> fansBean = fansService.getFansBean(studentId);
        fansBean.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FansBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FansBean fansBean) {
                        iFansView.showFansBean(fansBean.getData().getList());
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
