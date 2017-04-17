package com.utoo.test.mybmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private EditText user_name, m_password;
    private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name= (EditText) findViewById(R.id.user_name);
        m_password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.bt_login);
        //第一：默认初始化
        Bmob.initialize(this,"1b17f39b56759ab0caec5d4e915099ca");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=user_name.getText().toString();
                String password= m_password.getText().toString();
                if(name.equals("")||password.equals("")){
                    return;
                }
                Person personObject=new Person();
                personObject.setUser_name(name);
                personObject.setPassword(password);
                personObject.save( new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_LONG);
                        }else{
                            Toast.makeText(LoginActivity.this,"Failure",Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });


    }

}
