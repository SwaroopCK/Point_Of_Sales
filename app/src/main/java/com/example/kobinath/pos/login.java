package com.example.kobinath.pos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class login extends AppCompatActivity {

    EditText ed1, ed2;
    Button btn1, btn2,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.pass);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        register =findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,user.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public  void  login()
    {
        String user = ed1.getText().toString();
        String pass = ed2.getText().toString();
        if (user.equals("") || pass.equals("")) {
            Toast.makeText(this, "Username or Password blank", Toast.LENGTH_LONG).show();
        }
        else if (null!=checkUser(user,pass))
        {
            String userFromDb=checkUser(user,pass);

            Intent i = new Intent(login.this, MainActivity.class);
            i.putExtra("uname", userFromDb);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Username or Password not match", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }
    }
    public String checkUser(String name, String pass)
    {
        SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);
        Cursor cursor=db.rawQuery("SELECT id,user,pass FROM user WHERE user=? AND pass=?",new String[]{name,pass});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("username", MODE_PRIVATE).edit();
            sp.putString("uname", username);
            sp.apply();
            cursor.close();
            return username;
        }
        return null;
    }}



