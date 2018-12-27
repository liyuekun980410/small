package com.bwei.day01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText login_edit;
    private EditText login_pwd;
    private Button btn_login;
    private Button btn_zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_edit = findViewById(R.id.login_edit);
        login_pwd = findViewById(R.id.login_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_zhuce = findViewById(R.id.btn_zhuce);
        //点击注册按钮，跳转到注册页面
        btn_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
                startActivity(intent);
            }
        });
    }
}
