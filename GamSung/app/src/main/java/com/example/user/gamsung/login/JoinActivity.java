package com.example.user.gamsung.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.gamsung.R;
import com.example.user.gamsung.network.ApiValue;
import com.example.user.gamsung.network.response.JoinResult;

public class JoinActivity extends AppCompatActivity {

    EditText n_userid,n_userpw,n_userage,n_gender;
    String new_userid,new_userpw,new_userage,new_gender;
    Button join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        n_userid = findViewById(R.id.nuser_id);
        n_userpw = findViewById(R.id.nuser_pw);
        n_userage = findViewById(R.id.nuser_age);
        n_gender = findViewById(R.id.nuser_gender);
        join = findViewById(R.id.join);


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_userid = n_userid.getText().toString();
                new_userpw = n_userpw.getText().toString();
                new_userage = n_userage.getText().toString();
                new_gender = n_gender.getText().toString();
                if(new_gender.equals("여자") || new_gender.equals("여")){ new_gender = "f";}
                else if(new_gender.equals(("남자")) || new_gender.equals("남")){new_gender = "m";}
                if(new_userid.isEmpty() || new_userpw.isEmpty() || new_userage.isEmpty()||new_gender.isEmpty()){
                    Toast.makeText(JoinActivity.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                join_in();
            }
        });

    }


    public void join_in(){
        JoinAsyncTask searchAsyncTask = new JoinAsyncTask(new JoinAsyncTask.JoinResultHandler() {

            @Override
            public void onSuccessAppAsyncTask(JoinResult result) {

                if(result != null){
                    if(result.success){
                        Toast.makeText(JoinActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(JoinActivity.this, "중복된 아이디가 있습니다.다른 아이디로 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    Toast.makeText(JoinActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailAppAsysncask() {
                Toast.makeText(JoinActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelAppAsyncTask() {
                Toast.makeText(JoinActivity.this, "서버 통신에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();

            }
        });


        searchAsyncTask.execute(ApiValue.API_JOIN,new_userid, new_userpw, new_userage, new_gender);
    }
}
