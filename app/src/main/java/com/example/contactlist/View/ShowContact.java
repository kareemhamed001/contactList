package com.example.contactlist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.contactlist.Model.ApplicationClass;
import com.example.contactlist.R;

public class ShowContact extends AppCompatActivity {
    TextView mname,mtel;
    int position;
    String tel,name;
    ImageButton call,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_contact);

        mname=findViewById(R.id.tvName);
        mtel=findViewById(R.id.tvTel);
        call=findViewById(R.id.call);
        edit=findViewById(R.id.editContact);

        String name1 = getIntent().getStringExtra("Name");
        String tel1 = getIntent().getStringExtra("Tel");
        position = getIntent().getIntExtra("Position", 0);

        mname.setText(ApplicationClass.people.get(position).getName());
        mtel.setText(ApplicationClass.people.get(position).getTelNumber());
        tel=ApplicationClass.people.get(position).getTelNumber();
        name=ApplicationClass.people.get(position).getName();

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowContact.this,EditContact.class);
                intent.putExtra("position",position);
                intent.putExtra("name",name);
                intent.putExtra("tel",tel);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        mname.setText(ApplicationClass.people.get(position).getName());
        mtel.setText(ApplicationClass.people.get(position).getTelNumber());
        tel=ApplicationClass.people.get(position).getTelNumber();

    }
}