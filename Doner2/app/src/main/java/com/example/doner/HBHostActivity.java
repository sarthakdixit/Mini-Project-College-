package com.example.doner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HBHostActivity extends AppCompatActivity {

    private EditText campName, venue, contact, addEmail, rewardDonner, date_from, date_to, timing ;
    private TextView host_text, backing1, addDonner, myCamp1, donner, donner_name, donner_email, donner_contact, donner_address, donner_bloodType,
            otherCampName, otherVenue, otherContact, otherRewardDonner, otherDateFrom, otherDateTo, otherTiming, other;
    private Button host, backing, backing2, backing3, saving, otherBacking ;
    private ListView list1, list2, list3 ;
    private DatabaseReference data ;
    private String date ;
    private FirebaseAuth firebaseAuth ;
    private LinearLayout host1, host_page, myCamp, donnerDetails, add, otherPage ;
    private FirebaseDatabase firebaseDatabase ;
    private SharedPreferences sharedPreferences ;
    private ArrayAdapter<String> arrayAdapter ;
    private ArrayAdapter<String> myCampArrayAdapter ;
    private ArrayAdapter<String> other_arrayAdapter;
    private ArrayList<String> arrayList ;
    private ArrayList<String> myCampArrayList ;
    private ArrayList<String> other_arrayList ;
    private int da ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbhost);

        otherPage = (LinearLayout)findViewById(R.id.othersPage);
        other = (TextView)findViewById(R.id.other);
        otherCampName = (TextView)findViewById(R.id.othercampName);
        otherVenue = (TextView)findViewById(R.id.othervenue);
        otherContact = (TextView)findViewById(R.id.othercontact);
        otherRewardDonner = (TextView)findViewById(R.id.otherrewardDonner);
        otherDateFrom = (TextView)findViewById(R.id.otherdate_from);
        otherDateTo = (TextView)findViewById(R.id.otherdate_to);
        otherTiming = (TextView)findViewById(R.id.othertiming);
        otherBacking = (Button)findViewById(R.id.otherbacking);
        campName = (EditText)findViewById(R.id.campName);
        venue = (EditText)findViewById(R.id.venue);
        contact = (EditText)findViewById(R.id.contact);
        rewardDonner = (EditText)findViewById(R.id.rewardDonner);
        date_from = (EditText)findViewById(R.id.date_from);
        date_to = (EditText)findViewById(R.id.date_to);
        timing = (EditText)findViewById(R.id.timing);
        host = (Button)findViewById(R.id.hosting);
        backing = (Button)findViewById(R.id.backing);
        host1 = (LinearLayout)findViewById(R.id.host);
        host_page = (LinearLayout)findViewById(R.id.host_page);
        host_text = (TextView)findViewById(R.id.host_text);
        list1 = (ListView)findViewById(R.id.list1);
        list2 = (ListView)findViewById(R.id.list2);
        backing1 = (TextView)findViewById(R.id.backing1);
        list3 = (ListView)findViewById(R.id.list3);
        myCamp1 = (TextView)findViewById(R.id.myCamp1);
        myCamp = (LinearLayout)findViewById(R.id.my_camp);
        donnerDetails = (LinearLayout)findViewById(R.id.donnerDetails);
        donner_address = (TextView)findViewById(R.id.donner_address);
        donner_bloodType = (TextView)findViewById(R.id.donner_bloodType);
        donner_contact = (TextView)findViewById(R.id.donner_contact);
        donner_email = (TextView)findViewById(R.id.donner_email);
        donner_name = (TextView)findViewById(R.id.donner_name);
        donner = (TextView)findViewById(R.id.donner);
        backing2 = (Button)findViewById(R.id.backing2);
        addDonner = (TextView)findViewById(R.id.addDonner);
        add = (LinearLayout)findViewById(R.id.add);
        addEmail = (EditText)findViewById(R.id.addEmail);
        backing3 = (Button)findViewById(R.id.backing3);
        saving = (Button)findViewById(R.id.saving);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        data = firebaseDatabase.getReference();

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        da = Integer.parseInt(date.replaceAll("-",""));

        sharedPreferences = getSharedPreferences("hi",0);
        final String s = sharedPreferences.getString("hi","ABCD").replaceAll("[-+.^:,.@]","");

        other_arrayList = new ArrayList<>();
        other_arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,other_arrayList);
        list2.setAdapter(other_arrayAdapter);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        list1.setAdapter(arrayAdapter);

        myCampArrayList = new ArrayList<>();
        myCampArrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,myCampArrayList);
        list3.setAdapter(myCampArrayAdapter);

        data = FirebaseDatabase.getInstance().getReference("Hosting");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                arrayAdapter.notifyDataSetChanged();
                other_arrayList.clear();
                other_arrayAdapter.notifyDataSetChanged();
                for(DataSnapshot sn1:dataSnapshot.getChildren()){
                    String str = sn1.child("Date(to)").getValue().toString().replace("-","");
                    String org = sn1.child("Organised By").getValue().toString();
                    if(org.equals(s) && da < Integer.parseInt(str)){
                        arrayAdapter.add(sn1.getKey().toString());
                    }else if(da < Integer.parseInt(str)){
                        other_arrayAdapter.add(sn1.getKey().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        backing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donnerDetails.setVisibility(View.GONE);
                myCamp.setVisibility(View.VISIBLE);
            }
        });

        addDonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCamp.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
            }
        });

        backing3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmail.setText("");
                add.setVisibility(View.GONE);
                myCamp.setVisibility(View.VISIBLE);
            }
        });

        backing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCamp.setVisibility(View.GONE);
                host_page.setVisibility(View.VISIBLE);
            }
        });

        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!addEmail.getText().toString().isEmpty()){
                    final String sgh = addEmail.getText().toString().replaceAll("[-+.^:,.@]","") ;
                    data = FirebaseDatabase.getInstance().getReference("Hosting/"+myCamp1.getText().toString()+"/Donners");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue().toString().contains(sgh)){
                                Toast.makeText(getApplicationContext(), "Donated", Toast.LENGTH_SHORT).show();
                            }else{
                                if(dataSnapshot.getValue().toString().contains(",")){
                                    data = FirebaseDatabase.getInstance().getReference("Hosting/"+myCamp1.getText().toString());
                                    data.child("Donners").setValue(","+sgh);
                                }else{
                                    data = FirebaseDatabase.getInstance().getReference("Hosting/"+myCamp1.getText().toString());
                                    data.child("Donners").setValue(sgh);
                                }
                                Toast.makeText(getApplicationContext(), "Donor Added", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    addEmail.setText("");
                    add.setVisibility(View.GONE);
                    myCamp.setVisibility(View.VISIBLE);
                }
            }
        });

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myCampArrayList.clear();
                myCampArrayAdapter.notifyDataSetChanged();
                String s2 = arrayList.get(position);
                host_page.setVisibility(View.GONE);
                myCamp.setVisibility(View.VISIBLE);
                myCamp1.setText(s2);
                data = FirebaseDatabase.getInstance().getReference("Hosting/"+s2+"/Donners");
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String[] s = dataSnapshot.getValue().toString().split(",");
                        for(int i=0;i<s.length;i++){
                            myCampArrayAdapter.add(s[i]);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String s3 = other_arrayList.get(position);
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();
                data = firebaseDatabase.getReference();
                host_page.setVisibility(View.GONE);
                otherPage.setVisibility(View.VISIBLE);
                data = FirebaseDatabase.getInstance().getReference("Hosting/"+s3);
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        other.setText(s3);
                        otherCampName.setText(s3);
                        otherVenue.setText(dataSnapshot.child("Venue").getValue().toString());
                        otherContact.setText(dataSnapshot.child("Contact").getValue().toString());
                        otherRewardDonner.setText(dataSnapshot.child("Reward").getValue().toString());
                        otherDateFrom.setText(dataSnapshot.child("Date(from)").getValue().toString());
                        otherDateTo.setText(dataSnapshot.child("Date(to)").getValue().toString());
                        otherTiming.setText(dataSnapshot.child("Timing").getValue().toString());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        otherBacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherPage.setVisibility(View.GONE);
                host_page.setVisibility(View.VISIBLE);
            }
        });

        list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String s3 = myCampArrayList.get(position).replaceAll("[-+.^:,.@]","");
                myCamp.setVisibility(View.GONE);
                donnerDetails.setVisibility(View.VISIBLE);
                donner.setText(s3);
                data = FirebaseDatabase.getInstance().getReference("Donner");
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(s3).exists()){
                            donner_address.setText(dataSnapshot.child(s3+"/UserAddress").getValue().toString());
                            donner_bloodType.setText(dataSnapshot.child(s3+"/UserBloodType").getValue().toString());
                            donner_contact.setText(dataSnapshot.child(s3+"/UserPhoneNumber").getValue().toString());
                            donner_email.setText(dataSnapshot.child(s3+"/UserEmail").getValue().toString());
                            donner_name.setText(dataSnapshot.child(s3+"/UserName").getValue().toString());
                        }else{
                            Toast.makeText(getApplicationContext(), "Donner is not registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Empty()){
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    data = firebaseDatabase.getReference("Hosting");
                    String s1 = campName.getText().toString().replaceAll("[-+.^:,.@]","");
                    data = firebaseDatabase.getReference("Hosting/"+s1);
                    data.child("Venue").setValue(venue.getText().toString());
                    data.child("Contact").setValue(contact.getText().toString());
                    data.child("Reward").setValue(rewardDonner.getText().toString());
                    data.child("Date(from)").setValue(date_from.getText().toString());
                    data.child("Date(to)").setValue(date_to.getText().toString());
                    data.child("Timing").setValue(timing.getText().toString());
                    data.child("Organised By").setValue(s);
                    data.child("Donners").push();
                    data.child("Donners/asd").setValue("asd");
                    campName.setText("");
                    venue.setText("");
                    contact.setText("");
                    rewardDonner.setText("");
                    date_from.setText("");
                    date_to.setText("");
                    timing.setText("");
                    startActivity(new Intent(HBHostActivity.this, HospitalBloodBankSetting.class));
                }
            }
        });

        backing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campName.setText("");
                venue.setText("");
                contact.setText("");
                rewardDonner.setText("");
                date_from.setText("");
                date_to.setText("");
                timing.setText("");
                host1.setVisibility(View.GONE);
                host_page.setVisibility(View.VISIBLE);
            }
        });

        host_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campName.setText("");
                venue.setText("");
                contact.setText("");
                rewardDonner.setText("");
                date_from.setText("");
                date_to.setText("");
                timing.setText("");
                host1.setVisibility(View.VISIBLE);
                host_page.setVisibility(View.GONE);
            }
        });
    }

    private boolean Empty(){
        if(campName.getText().toString().isEmpty() || venue.getText().toString().isEmpty() || contact.getText().toString().isEmpty()
                || rewardDonner.getText().toString().isEmpty() || date_from.getText().toString().isEmpty() || date_to.getText().toString().isEmpty() || timing.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill Up the details", Toast.LENGTH_SHORT).show();
            return false ;
        }
        return true ;
    }
}
