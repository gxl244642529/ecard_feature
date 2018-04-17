package com.react;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jzoom.alert.Alert;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by pc on 2018/3/7.
 */

public class AlertModule extends ReactContextBaseJavaModule{
    public AlertModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public String getName() {
        return "AlertModule";
    }
    @ReactMethod
    public void show(String message, int duration){
        Toast.makeText(getReactApplicationContext(),message,duration).show();
    }
    @ReactMethod
    public void alert(String message,final Callback callback){
        Alert.alert(getCurrentActivity(), "温馨提示", message, new Alert.AlertListener() {
            @Override
            public void onAlert(int buttonId) {
                if(buttonId== Alert.OK){
                    callback.invoke(0);
                }else{
                    callback.invoke(1);
                }
            }
        });
    }
    @ReactMethod
    public void confirm(String message,final Callback callback) {
        Alert.confirm(getCurrentActivity(), "温馨提示", message, new Alert.AlertListener() {
            @Override
            public void onAlert(int buttonId) {
                if(buttonId == Alert.OK){
                    callback.invoke(0);
                }else {
                    callback.invoke(1);
                }
            }
        });
    }
    @ReactMethod
    public void toast(String message){
        Alert.toast(getCurrentActivity(),message);
    }
    @ReactMethod
    public void wait(String message){
        Alert.showWait(getCurrentActivity(),message);
    }
    @ReactMethod
    public void cancelWait(){
        Alert.hidelWait();
    }
}

