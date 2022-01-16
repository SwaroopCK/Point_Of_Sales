package com.example.kobinath.pos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class catview extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catview);

        SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from category", null);
        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int description = c.getColumnIndex("description");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
        lst1.setAdapter(arrayAdapter);
        final ArrayList<cate> cat = new ArrayList<cate>();
        if (c.moveToFirst()) {
            do {
                cate stu = new cate();
                stu.id = c.getString(id);
                stu.category = c.getString(category);
                stu.description = c.getString(description);
                cat.add(stu);
                titles.add(c.getString(id) + " \t " + c.getString(category) + " \t " + c.getString(description));

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String aa = titles.get(position).toString();
                    cate stu = cat.get(position);
                    Intent i = new Intent(getApplicationContext(), editcategory.class);
                    i.putExtra("id", stu.id);
                    i.putExtra("category", stu.category);
                    i.putExtra("description", stu.description);
                    startActivity(i);
            }
        });
    }


























}
