package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditUserInfoActivity extends AppCompatActivity {

    EditText passwordEdt,repasswordEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);



        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        repasswordEdt = (EditText) findViewById(R.id.repasswordEdt);

    }
}