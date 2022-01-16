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

public class productview extends AppCompatActivity {
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);


        SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from product",null);
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("proname");
        int category = c.getColumnIndex("category");
        int brand1 = c.getColumnIndex("brand");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<productview1> product1 = new ArrayList<productview1>();


        if(c.moveToFirst())
        {
            do{

                productview1 stu = new productview1();
                stu.id = c.getString(id);
                stu.product = c.getString(product);
                stu.category = c.getString(category);
                stu.brand = c.getString(brand1);
                stu.qty= c.getString(qty);
                stu.price = c.getString(price);
                product1.add(stu);
                titles.add(c.getString(id) + " \t " + c.getString(product) + " \t "  + c.getString(category)   + " \t " + c.getString(brand1) + " \t " + c.getString(qty) + " \t " + c.getString(price));

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa = titles.get(position).toString();productview1 stu = product1.get(position);
                Intent i = new Intent(getApplicationContext(), productedit.class);
               i.putExtra("id", stu.id);
                i.putExtra("product", stu.product);
                i.putExtra("category", stu.category);
               i.putExtra("brand", stu.brand);
              i.putExtra("qty", stu.qty);
                i.putExtra("price", stu.price);






                startActivity(i);
            }
        });









    }
}
