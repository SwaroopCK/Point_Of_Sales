package com.example.kobinath.pos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class category extends AppCompatActivity {
    EditText ed1,ed2;
    Button b11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ed1 = findViewById(R.id.catname);
        ed2 = findViewById(R.id.catdes);
        b11 = findViewById(R.id.btn1);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }
    public void insert() {
        try {
            String category = ed1.getText().toString();
            String description = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR,description VARCHAR)");
            String sql = "insert into category(category,description)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, category);
            statement.bindString(2, description);
            statement.execute();
            Toast.makeText(this, "Record addded", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
    }







