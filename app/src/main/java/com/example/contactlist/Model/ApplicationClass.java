package com.example.contactlist.Model;

import android.app.Application;

import com.example.contactlist.Model.Person;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person>people;
    @Override
    public void onCreate() {
        super.onCreate();
        people=new ArrayList<>();

    }
}
