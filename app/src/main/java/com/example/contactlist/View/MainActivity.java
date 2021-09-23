package com.example.contactlist.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactlist.Connector.ContactsAdapter;
import com.example.contactlist.Fragments.ListFragment;
import com.example.contactlist.Model.ApplicationClass;
import com.example.contactlist.Model.Person;
import com.example.contactlist.R;

public class MainActivity extends AppCompatActivity  {

    EditText etName, etTel;
    TextView tvName, tvTel;
    Button addbtn;
    int position;
    ListFragment listFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etTel = findViewById(R.id.etTel);
        addbtn = findViewById(R.id.addbtn);
        tvName = findViewById(R.id.tvName);
        tvTel = findViewById(R.id.tvTel);
        fragmentManager = this.getSupportFragmentManager();

        if (findViewById(R.id.layout_portrait) != null) {

            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.listfrag))
                    .hide(fragmentManager.findFragmentById(R.id.addpersonfrag))
                    .commit();
            findViewById(R.id.addnewperson).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentManager.beginTransaction()
                            .hide(fragmentManager.findFragmentById(R.id.listfrag))
                            .show(fragmentManager.findFragmentById(R.id.addpersonfrag))
                            .commit();
                }
            });
        }
        if (findViewById(R.id.layout_land) != null) {

            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.listfrag))
                    .show(fragmentManager.findFragmentById(R.id.addpersonfrag))
                    .addToBackStack(null)
                    .commit();
            findViewById(R.id.addnewperson).setVisibility(View.INVISIBLE);
        }


        listFragment = (ListFragment) fragmentManager.findFragmentById(R.id.listfrag);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String tel = etTel.getText().toString().trim();
                if (name.isEmpty() || tel.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    ApplicationClass.people.add(new Person(name, tel));
                    Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    etName.setText(null);
                    etTel.setText(null);
                    listFragment.notifyDataChanged();
                    if (findViewById(R.id.layout_portrait) != null) {
                        fragmentManager.beginTransaction()
                                .show(fragmentManager.findFragmentById(R.id.listfrag))
                                .hide(fragmentManager.findFragmentById(R.id.addpersonfrag))
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });
    }



}