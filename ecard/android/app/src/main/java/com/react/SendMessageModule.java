package com.react;

import android.widget.Toast;


import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by pc on 2018/4/12.
 */

public class SendMessageModule extends ReactContextBaseJavaModule{
    public SendMessageModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SendMessage";
    }
    @ReactMethod
    public void sendMsg(String phone,Callback callback){
        sendCode("86",phone,callback);
    }
    @ReactMethod
    public void submitMsg(String phone,String code,Callback callback){
        submitCode("86",phone,code,callback);
    }
    public void sendCode(String country, String phone, final Callback callback) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    callback.invoke(0);
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                } else{
                    // TODO 处理错误的结果
                    callback.invoke(data+"");
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }
    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code,final  Callback callback) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    callback.invoke(0);
                } else{
                    // TODO 处理错误的结果
                    callback.invoke(data+"");
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }
    @ReactMethod
    protected void onDestroy() {
//        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    };
}
