package com.ann.greensolution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayActivity extends AppCompatActivity {

    @BindView(R.id.list_advisors)
    ListView listView;

    List<Advisors> data=new ArrayList<>();

    BaseAdapter adapter;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ButterKnife.bind(this);

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_advisors, parent, false);
                TextView tvName = v.findViewById(R.id.txtnames);
                TextView tvEmail = v.findViewById(R.id.txtemail);
                TextView tvNumber = v.findViewById(R.id.txtnumber);

                final Advisors x = data.get(position);
                tvName.setText(x.getName());
                tvEmail.setText(x.getEmail());
                tvNumber.setText("07"+x.getPhonenumber());


                return v;
            }

        };

        listView.setAdapter(adapter);

        db= FirebaseDatabase.getInstance().getReference("users");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren()){
                    Advisors advisors=d.getValue(Advisors.class);
                    advisors.setKey(d.getKey());
                    data.add(advisors);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
