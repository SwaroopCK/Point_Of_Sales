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

public class editcategory extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcategory);

        ed1 = findViewById(R.id.catid);
        ed2 = findViewById(R.id.catname);
        ed3  = findViewById(R.id.catdes);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("category").toString();
        String t3 = i.getStringExtra("description").toString();

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });
    }
    public void Delete()
    {
        try
        {
            String id = ed1.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("pos",Context.MODE_PRIVATE,null);
            String sql = "delete from category where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit() {
        try {
            String id = ed1.getText().toString();
            String category = ed2.getText().toString();
            String catdes = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);
            String sql = "update category set category = ?,description=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, category);
            statement.bindString(2, catdes);
            statement.bindString(3,id);
            statement.execute();
            Toast.makeText(this, "Record Updateddd", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

}
