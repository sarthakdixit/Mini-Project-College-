package com.example.doner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HBSearchActivity extends AppCompatActivity {

    private Spinner spinnerOrg, spinnerBloodType ;
    private Button search ;
    private ListView HBsearchList ;
    private String s, s1 ;
    private DatabaseReference data ;
    private ArrayAdapter<String> arrayAdapter ;
    private ArrayList<String> arrayList ;
    private LinearLayout list_asd, details ;
    private TextView details_name, details_email, details_contact, details_address, back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbsearch);

        spinnerOrg = (Spinner)findViewById(R.id.spinnerOrg);
        spinnerBloodType = (Spinner)findViewById(R.id.spinnerBloodType);
        search = (Button)findViewById(R.id.search);
        HBsearchList = (ListView)findViewById(R.id.HBsearchList);
        list_asd = (LinearLayout)findViewById(R.id.list_asd);
        details = (LinearLayout)findViewById(R.id.details);
        details_name = (TextView)findViewById(R.id.details_name);
        details_email = (TextView)findViewById(R.id.details_email);
        details_contact = (TextView)findViewById(R.id.details_contact);
        details_address = (TextView)findViewById(R.id.details_address);
        back = (TextView)findViewById(R.id.back1);

        final List<String> list = new ArrayList<String>();
        list.add("Hospital");
        list.add("Blood Bank");
        list.add("Donner");
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrg.setAdapter(dataAdapter);

        List<String> list1 = new ArrayList<String>();
        list1.add("A-");
        list1.add("A+");
        list1.add("B-");
        list1.add("B+");
        list1.add("AB-");
        list1.add("AB+");
        list1.add("O-");
        list1.add("O+");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodType.setAdapter(dataAdapter1);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        HBsearchList.setAdapter(arrayAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = String.valueOf(spinnerOrg.getSelectedItem());
                s1 = String.valueOf(spinnerBloodType.getSelectedItem());
                if(s1.contains("-")){
                    s1 = s1.replace("-","negative");
                }else{
                    s1 = s1.replace("+","positive");
                }
                data = FirebaseDatabase.getInstance().getReference(s1);
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(final DataSnapshot snap : dataSnapshot.getChildren()){
                            data = FirebaseDatabase.getInstance().getReference(s);
                            data.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(snap.getKey().toString()).exists()){
                                        data = FirebaseDatabase.getInstance().getReference(s+"/"+snap.getKey().toString()+"/UserName");
                                        data.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                arrayList.add(dataSnapshot.getValue().toString());
                                                arrayAdapter.notifyDataSetChanged();
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        HBsearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String s2 = arrayList.get(position);
                data = FirebaseDatabase.getInstance().getReference(s);
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snap : dataSnapshot.getChildren()){
                            if(snap.child("UserName").getValue().toString().equals(s2)){
                                details_name.setText(snap.child("UserName").getValue().toString());
                                details_email.setText(snap.child("UserEmail").getValue().toString());
                                details_contact.setText(snap.child("UserPhoneNumber").getValue().toString());
                                details_address.setText(snap.child("UserAddress").getValue().toString());
                                details.setVisibility(View.VISIBLE);
                                list_asd.setVisibility(View.GONE);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setVisibility(View.GONE);
                list_asd.setVisibility(View.VISIBLE);
            }
        });

    }
}
