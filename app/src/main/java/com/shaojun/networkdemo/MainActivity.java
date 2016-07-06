package com.shaojun.networkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shaojun.networkdemo.network.ApiWrapper;
import com.shaojun.networkdemo.network.http.subscriber.NewSubscriber;
import com.shaojun.networkdemo.network.service.models.MeiZhi;
import com.shaojun.networkdemo.network.service.models.User;

import java.util.List;

import rx.Subscription;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        result = (TextView) findViewById(R.id.result);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
//                do2(2);
                doLogin();
                break;
            case R.id.btn2:
                do2(1);
                break;
        }
    }

    private void doLogin() {
        String accountName = "";
        String password = "";
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.login(accountName, password).subscribe(new NewSubscriber<User>(this, true) {
            @Override
            public void onNext(User user) {
                result.clearComposingText();
                result.setText(new Gson().toJson(user));
            }
        });
        addSubscrebe(subscription);
    }

    private void do2(int page) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getMeiZhiList(page).subscribe(new NewSubscriber<List<MeiZhi>>(this, true) {
            @Override
            public void onNext(List<MeiZhi> meiZhis) {
                result.clearComposingText();
                result.setText(new Gson().toJson(meiZhis));
            }
        });
        addSubscrebe(subscription);

    }
}
