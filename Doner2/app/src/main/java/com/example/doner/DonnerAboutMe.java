package com.example.doner;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonnerAboutMe extends AppCompatActivity {

    private SharedPreferences sharedPreferences ;
    private TextView ab_name, ab_email,  ab_contact, ab_address ;
    private DatabaseReference data ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_about_me);

        ab_name = (TextView)findViewById(R.id.d_ab_name);
        ab_address = (TextView)findViewById(R.id.d_ab_address);
        ab_contact = (TextView)findViewById(R.id.d_ab_contact);
        ab_email = (TextView)findViewById(R.id.d_ab_email);

        sharedPreferences = getSharedPreferences("hii",0);
        final String s = sharedPreferences.getString("hii","ABCD").replaceAll("[-+.^:,.@]","");

        data = FirebaseDatabase.getInstance().getReference("Donner");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ab_name.setText(dataSnapshot.child(s+"/UserName").getValue().toString());
                ab_email.setText(dataSnapshot.child(s+"/UserEmail").getValue().toString());
                ab_contact.setText(dataSnapshot.child(s+"/UserPhoneNumber").getValue().toString());
                ab_address.setText(dataSnapshot.child(s+"/UserAddress").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
