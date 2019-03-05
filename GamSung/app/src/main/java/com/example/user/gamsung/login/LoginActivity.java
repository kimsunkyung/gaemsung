package com.example.user.gamsung.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.gamsung.MainActivity;
import com.example.user.gamsung.R;
import com.example.user.gamsung.network.ApiValue;
import com.example.user.gamsung.network.response.JoinResult;
import com.example.user.gamsung.network.response.LoginResult;

public class LoginActivity extends AppCompatActivity {
    public static String real_user_id;
    EditText user_id,user_pw;
    Button login,join;
    String u_id,u_pw;
    int user_int_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        login = findViewById(R.id.login);
        join = findViewById(R.id.join);

        u_id = user_id.getText().toString();
        u_pw = user_pw.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_id = user_id.getText().toString();
                u_pw = user_pw.getText().toString();
                if(u_id.isEmpty() || u_pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                login();

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void login(){
        UserLoginAsyncTask LoginAsyncTask = new UserLoginAsyncTask(new UserLoginAsyncTask.UserLoginResultHandler() {
            @Override
            public void onSuccessAppAsyncTask(LoginResult result) {
                if(result != null){
                    if(result.success){
                        user_int_id = result.getUserid();
                        real_user_id = String.valueOf(user_int_id);
                        Log.d("real_user_id", String.valueOf(real_user_id));
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{

                        Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(LoginActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(LoginActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        });


        LoginAsyncTask.execute(ApiValue.API_Login,u_id,u_pw);
    }



}
