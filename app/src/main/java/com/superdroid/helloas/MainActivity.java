package com.superdroid.helloas;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.notification.PushNotificationMessage;

public class MainActivity extends BaseActivity implements View.OnClickListener, RongIMClient.OnReceivePushMessageListener, RongIMClient.OnReceiveMessageListener, RongIM.UserInfoProvider {
    private Button btn_connect;
    private Button btn_conversation;
    private Button btn_conversation_list;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //小花
        token = "ZDyGbtJ7Y1+ZlyrMSTzW/hqckun6xAmkSDRs2b3DbpcDPyslscLJ5NL0TMtXMDCghrbRPKZisyI=";
//        小红
        token = "mUuDr1CTjCFWj7MXMy+92tP0HeoYS6yo9AaqwZMA06KCkGIeIdcXNoxejF/LfajIBI5VLcmSGfdCjVOTuwsNnA==";
        initView();
        registerListener();
    }

    private void registerListener() {
        btn_connect.setOnClickListener(this);
        btn_conversation.setOnClickListener(this);
        btn_conversation_list.setOnClickListener(this);
        RongIM.setUserInfoProvider(this, true);
        RongIM.setOnReceiveMessageListener(this);
        RongIM.setOnReceivePushMessageListener(this);
    }

    private void initView() {
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_conversation = (Button) findViewById(R.id.btn_open_conversation);
        btn_conversation_list = (Button) findViewById(R.id.btn_open_conversation_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect:
                Log.e("tag", "onClick");
                Toast.makeText(this, "onclicked", Toast.LENGTH_LONG).show();
                connectRong();
                break;
            case R.id.btn_open_conversation:
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(this, "001", "hello");
                }
                break;
            case R.id.btn_open_conversation_list:
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startConversationList(this);
                }
                break;
        }
    }

    private void connectRong() {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            //
            public void onTokenIncorrect() {
                Log.e("tag", "onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.e("tag", "onSuccess");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("tag", "onError " + errorCode.getValue());
            }
        });
    }

    @Override
    public UserInfo getUserInfo(String s) {
        UserInfo userInfo = null;
        Uri uri = null;
        if (s.equals("001")) {
            uri = Uri.parse("http://xyapp.ikinvin.net/upload/gallery/default/886217BF-B328-9249-FC49BA5CD560.jpg");
            userInfo = new UserInfo("001", "小花", uri);
        } else {
            uri = Uri.parse("http://xyapp.ikinvin.net/upload/gallery/default/4688EEEC-206D-6941-549DB0B60867.jpg");
            userInfo = new UserInfo("002", "小红", uri);
        }
        //
        return userInfo;
    }
//    https://github.com/superdroidgithup/helloas.git
    @Override
    public boolean onReceived(Message message, int i) {
        Log.i("tag", "onReceived");
        return false;
    }

    @Override
    public boolean onReceivePushMessage(PushNotificationMessage pushNotificationMessage) {
        Log.i("tag", "onReceivePushMessage");
        return false;
    }
}
