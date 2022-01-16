package com.example.kobinath.pos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class posview extends AppCompatActivity {
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posview);

        SQLiteDatabase db = openOrCreateDatabase("pos", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from posss",null);
        int proid = c.getColumnIndex("proid");
        int proname = c.getColumnIndex("proname");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");
        int total = c.getColumnIndex("total");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<productview1> product1 = new ArrayList<productview1>();


        if(c.moveToFirst())
        {
            do{
                productview1 stu = new productview1();
                stu.id = c.getString(proid);
                stu.product = c.getString(proname);
                stu.qty= c.getString(qty);
                stu.price = c.getString(price);
                stu.total = c.getString(total);
                product1.add(stu);
                titles.add(c.getString(proid) + " \t " + c.getString(proname) + " \t "  + c.getString(qty)   + " \t " + c.getString(price) + " \t " + c.getString(total));

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }













    }
}
