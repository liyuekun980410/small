package com.bwei.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.day01.bean.Login;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhuceActivity extends AppCompatActivity {

    private EditText zhuce_edit;
    private EditText zhuce_pwd;
    private Button btn_ljzc;
    private String edit;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        zhuce_edit = findViewById(R.id.zhuce_edit);
        zhuce_pwd = findViewById(R.id.zhuce_pwd);
        btn_ljzc = findViewById(R.id.btn_ljzc);
        //点击立即注册按钮，进行注册
        btn_ljzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = zhuce_edit.getText().toString().trim();
                pwd = zhuce_pwd.getText().toString().trim();
                if (checkDate(edit,pwd)){
                    RequestParams params = new RequestParams("http://172.17.8.100/small/user/v1/register");
                    params.addBodyParameter("edit",edit);
                    params.addBodyParameter("pwd",pwd);
                    /*params.addQueryStringParameter("edit",edit);
                    params.addQueryStringParameter("pwd",pwd);*/
                    x.http().get(params, new Callback.CacheCallback<String>() {

                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
                            Login login = gson.fromJson(result, Login.class);
                            if (login.getStatus().equals("0000")){
                                Toast.makeText(ZhuceActivity.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ZhuceActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else if (login.getStatus().equals("1001")){
                                Toast.makeText(ZhuceActivity.this,login.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }

                        @Override
                        public boolean onCache(String result) {
                            return false;
                        }
                    });
                }
            }
        });
    }

    private boolean checkDate(String edit,String pwd){
        if (TextUtils.isEmpty(edit) || TextUtils.isEmpty(pwd)){
            Toast.makeText(ZhuceActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isphone(edit)){
            Toast.makeText(ZhuceActivity.this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (pwd.length()<6){
            Toast.makeText(ZhuceActivity.this,"密码不能小于6位数",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isphone(String edit) {
        if (edit.length()==11){
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(edit);
            return matcher.matches();
        }
        return false;
    }

}
