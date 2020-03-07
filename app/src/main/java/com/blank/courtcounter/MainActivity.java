package com.blank.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (App.getNewSession()) {    //第一次访问时弹出
            alert("声明", "此应用为本人学习安卓基础时第一个微型应用，欢迎访问者批评指正！\n\n\n" +
                    "联系邮箱:liufei_blank@qq.com");
            App.setNewSession();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void jumpBasketballCourtCounter(View view) {   //跳转篮球计分器页面
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, BasketballCourtCounterActivity.class);
        startActivity(intent);
    }

    public void jumpPingpongCourtCounter(View view) { //跳转乒乓球计分器页面
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, PingpongCourtCounterActivity.class);
        startActivity(intent);
    }

    private void alert(String title, String message) {
        /*
         * 提示信息
         * */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
