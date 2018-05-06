package com.sunny.univstar.contract;

import com.sunny.univstar.model.entity.RegisterCodeEntity;

import java.util.Map;

/**
 * Created by DELL on 2018/5/5.
 */

public interface RegisterContract {
    interface RegisterView{
        void getIdentifyingCode(RegisterCodeEntity registerCodeEntity);
        void codePhoneAndIdentifying(RegisterCodeEntity registerCodeEntity);
    }
    interface RegisterPresenter{
        void sendRegister(String url , Map<String,String> map);
        void sendCode(String url , Map<String,String> map);
    }
}
