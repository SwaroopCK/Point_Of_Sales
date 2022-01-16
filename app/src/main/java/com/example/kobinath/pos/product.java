package com.example.kobinath.pos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class product extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> titles1 = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    EditText ed1,ed2,ed3,ed4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ed1 = findViewById(R.id.pname);
        spinner = findViewById(R.id.catid);
        spinner1 = findViewById(R.id.brandid);
        ed2 = findViewById(R.id.proqty);
        ed3 = findViewById(R.id.proprice);
        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        //category
        SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select category from category",null);
        int category = c.getColumnIndex("category");
        titles.clear();
        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        spinner.setAdapter(arrayAdapter);

        final  ArrayList<cate> stud = new ArrayList<cate>();
        if(c.moveToFirst()) {
            do {
                cate stu = new cate();
                stu.category = c.getString(category);
                stud.add(stu);
                titles.add(c.getString(category) );

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();

        }
        //Brand
        final Cursor b = db.rawQuery("select brand from brand",null);
        int brand = b.getColumnIndex("brand");
        titles1.clear();
        arrayAdapter1= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles1);
        spinner1.setAdapter(arrayAdapter1);
        final  ArrayList<bran> brann = new ArrayList<bran>();

        if(b.moveToFirst()) {
            do {
                bran aa = new bran();
                aa.brand = b.getString(brand);
                brann.add(aa);
                titles1.add(b.getString(brand) );
            } while (b.moveToNext());
            arrayAdapter1.notifyDataSetChanged();

        }

    }

    public void insert() {
        try {
            String productname = ed1.getText().toString();
            String categor = spinner.getSelectedItem().toString();
            String brand = spinner1.getSelectedItem().toString();
            String qty = ed2.getText().toString();
            String price = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,proname VARCHAR,category VARCHAR,brand VARCHAR,qty VARCHAR,price VARCHAR)");

            String sql = "insert into product(proname,category,brand,qty,price)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, productname);
            statement.bindString(2, categor);
            statement.bindString(3, brand);
            statement.bindString(4, qty);
            statement.bindString(5, price);
            statement.execute();
            Toast.makeText(this, "Product addded", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

}
