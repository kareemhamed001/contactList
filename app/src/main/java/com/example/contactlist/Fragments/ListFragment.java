package com.example.contactlist.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.contactlist.Model.ApplicationClass;
import com.example.contactlist.Connector.ContactsAdapter;
import com.example.contactlist.View.ShowContact;
import com.example.contactlist.Listeners.onItemClicked;
import com.example.contactlist.Listeners.onLongClick;
import com.example.contactlist.R;


public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager lManager;
    ContactsAdapter mAdapter;
    EditText mName, mTel;
    View view;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        lManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(lManager);

        Context context = this.getActivity();

        mAdapter =
                new ContactsAdapter(this.getActivity(), ApplicationClass.people, new onItemClicked() {
                    @Override
                    public void OnItemClickedListener(int position) {
                        Intent intent = new Intent(context, ShowContact.class);
                        intent.putExtra("Name", ApplicationClass.people.get(position).getName());
                        intent.putExtra("Tel", ApplicationClass.people.get(position).getTelNumber());
                        intent.putExtra("Position", position);
                        startActivity(intent);
                    }
                }, new onLongClick() {
                    @Override
                    public void onLongClickListener(int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Delete This Contact ?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApplicationClass.people.remove(position);
                                notifyDataChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
                    }
                });
        recyclerView.setAdapter(mAdapter);
    }

    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        notifyDataChanged();
    }
}
