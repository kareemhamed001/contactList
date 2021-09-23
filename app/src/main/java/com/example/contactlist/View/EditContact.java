package com.example.contactlist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactlist.Model.ApplicationClass;
import com.example.contactlist.Model.Person;
import com.example.contactlist.R;

public class EditContact extends AppCompatActivity  {
    EditText name,tel;
    Button button;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        name=findViewById(R.id.etNameEditAc);
        tel=findViewById(R.id.etTelEditAc);
        button=findViewById(R.id.btnSaveEditAc);
        position=getIntent().getIntExtra("position",0);
        String name1=getIntent().getStringExtra("name");
        String tel1=getIntent().getStringExtra("tel");
        name.setText(name1);
        tel.setText(tel1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (name.getText().toString().isEmpty()||tel.getText().toString().isEmpty())
                {
                    Toast.makeText(EditContact.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                Person person=new Person(name.getText().toString(),tel.getText().toString().trim());
                ApplicationClass.people.set(position,person);
                finish();
                }
            }
        });

    }
}