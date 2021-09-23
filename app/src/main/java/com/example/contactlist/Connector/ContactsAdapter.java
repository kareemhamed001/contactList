package com.example.contactlist.Connector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactlist.Listeners.onItemClicked;
import com.example.contactlist.Listeners.onLongClick;
import com.example.contactlist.Model.ApplicationClass;
import com.example.contactlist.Model.Person;
import com.example.contactlist.R;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    ArrayList<Person> people;


    Context context;
    com.example.contactlist.Listeners.onItemClicked onItemClicked;
    com.example.contactlist.Listeners.onLongClick onLongClick;



    public ContactsAdapter(Context context, ArrayList<Person> people, onItemClicked onItemClicked, onLongClick onLongClick) {
        this.people = people;
        this.context = context;
        this.onItemClicked = onItemClicked;
        this.onLongClick = onLongClick;
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        int position;

        public ContactsViewHolder(@NonNull View itemView, onItemClicked onItemClicked, onLongClick onLongClick) {
            super(itemView);
            name = itemView.findViewById(R.id.peronName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClicked.OnItemClickedListener(position);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClick.onLongClickListener(position);
                    return true;
                }
            });

        }
    }

    @NonNull
    @Override
    public ContactsAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new ContactsViewHolder(view, onItemClicked, onLongClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ContactsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Person person = people.get(position);
        holder.itemView.setTag(person);
        holder.name.setText(person.getName());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
