package com.example.kobinath.pos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class productedit extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner1;

    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    EditText ed1, ed2, ed3, ed4;
    Button b1, b2;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productedit);


        ed1 = findViewById(R.id.pid);
        ed2 = findViewById(R.id.pname);
       spinner = findViewById(R.id.catid);
       spinner1 = findViewById(R.id.brandid);
       ed3 = findViewById(R.id.proqty);
       ed4 = findViewById(R.id.proprice);
       b1 = findViewById(R.id.btn1);
       b2 = findViewById(R.id.btn2);

    Intent i = getIntent();
    String t1 = i.getStringExtra("id").toString();
    String t2 = i.getStringExtra("product").toString();
    String t3 = i.getStringExtra("category").toString();

    //   String t4 = i.getStringExtra("brand").toString();
//      String t5 = i.getStringExtra("qty").toString();
      String t6 = i.getStringExtra("price").toString();

       ;




       ed1.setText(t1);
        ed2.setText(t2);
     // setSpinText(spinner,t3);
    //   setSpinText(spinner,t4);
   //    ed3.setText(t5);
       ed4.setText(t6);



    }


    public void setSpinText(String text){
        for(int i= 0; i < spinner.getAdapter().getCount(); i++) {
            if(spinner.getAdapter().getItem(i).toString().contains(text)) {
                spinner.setSelection(i);
            }
        }
    }



}



