package com.example.fbagheri.fulfill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.fbagheri.fulfill.Helper.APP_PASSWORD;
import static com.example.fbagheri.fulfill.Helper.APP_USSERNAME;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    private G myApp;
    SharedPreferences shared ;
    SharedPreferences.Editor editor ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myApp = (G) getApplication();
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_enter);
        btnLogin.setOnClickListener(this);

        shared = getSharedPreferences("Pref", MODE_PRIVATE);
        editor = shared.edit();


        editor.putInt("id",0);;
        editor.putInt("happiness",10);

        editor.putInt("satisfaction",30);
        editor.putInt("health",30);
        editor.putInt("experience" ,20) ;
        editor.putInt("heart" ,0);
        editor.putInt("coin",0);
        editor.putInt("status" ,0);
        editor.putInt("age" ,0);
        editor.putString("name","star");
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_enter:
                if(edtUsername.getText().toString().equals(APP_USSERNAME)
                   && edtPassword.getText().toString().equals(APP_PASSWORD)){
                    Intent intent = new Intent(this,TaskActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                         Toast.makeText(this ,"Incorrect username or password",Toast.LENGTH_LONG).show();
                }

                break;





        }


    }
}
