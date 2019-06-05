package com.example.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText itemET;
    private Button btn;



    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemET=findViewById(R.id.item_edit_text);
        btn=findViewById(R.id.add_btn);
        items= FileAdapter.readData(this);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        ListView itemsList=findViewById(R.id.items_list);
        itemsList.setAdapter(adapter);
        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn: String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                                 Date currentTime = Calendar.getInstance().getTime();
                               String itemEntered = itemET.getText().toString()+"       "+currentDateTimeString+"    ";
                               adapter.add(itemEntered);
                               itemET.setText("");
                               FileAdapter.writeData(items,this);
                Log.v("Debug", String.valueOf(items));


                                 Toast.makeText(this,"Item Added",Toast.LENGTH_SHORT).show();
                                 break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
    }
}
