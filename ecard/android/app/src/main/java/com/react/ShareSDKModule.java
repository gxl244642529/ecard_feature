package com.react;

import android.app.Activity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by pc on 2018/4/13.
 */

public class ShareSDKModule extends ReactContextBaseJavaModule{
    ReactApplicationContext reactContext ;
    public ShareSDKModule(ReactApplicationContext reactContext) {
        super( reactContext );
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ShareSDKModule";
    }
    @ReactMethod
    public void share( final  Callback callback){
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("测试分享");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(reactContext);

        oks.setCallback( new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                callback.invoke( hashMap );
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                callback.invoke( throwable );
            }

            @Override
            public void onCancel(Platform platform, int i) {
                callback.invoke("cancel");
            }
        } );
    }



}
