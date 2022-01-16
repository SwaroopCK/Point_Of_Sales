package com.example.kobinath.pos;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.pass);
        ed3 = findViewById(R.id.conpass);
        b1 = findViewById(R.id.btn1);
        login = findViewById(R.id.login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(user.this,login.class);
            startActivity(i);
        }
    });
}

    public void insert() {
        try {
            String user = ed1.getText().toString();
            String pass = ed2.getText().toString();
            String conpass = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY ,user VARCHAR,pass VARCHAR,conpass VARCHAR )");


            if(!pass.equals(conpass)) {
                Toast.makeText(user.this, "Password Not matching", Toast.LENGTH_SHORT).show();
            }
            else {
                String sql = "insert into user(user,pass,conpass)values(?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, user);
                statement.bindString(2, pass);
                statement.bindString(3, conpass);
                statement.execute();
                Toast.makeText(this, "Record addded", Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed1.requestFocus();
            }

        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }


    }


}


















