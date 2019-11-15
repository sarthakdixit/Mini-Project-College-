package com.example.doner;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HBAboutMe extends AppCompatActivity {

    private SharedPreferences sharedPreferences ;
    private TextView ab_name, ab_email,  ab_contact, ab_address ;
    private DatabaseReference data ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbabout_me);

        ab_name = (TextView)findViewById(R.id.ab_name);
        ab_email = (TextView)findViewById(R.id.ab_email);
        ab_contact = (TextView)findViewById(R.id.ab_contact);
        ab_address = (TextView)findViewById(R.id.ab_address);

        sharedPreferences = getSharedPreferences("hi",0);
        final String s = sharedPreferences.getString("hi","ABCD").replaceAll("[-+.^:,.@]","");

        data = FirebaseDatabase.getInstance().getReference("Hospital");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(s).exists()){
                    ab_name.setText(dataSnapshot.child(s+"/UserName").getValue().toString());
                    ab_email.setText(dataSnapshot.child(s+"/UserEmail").getValue().toString());
                    ab_contact.setText(dataSnapshot.child(s+"/UserPhoneNumber").getValue().toString());
                    ab_address.setText(dataSnapshot.child(s+"/UserAddress").getValue().toString());
                }else{
                    data = FirebaseDatabase.getInstance().getReference("Blood Bank");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(s).exists()) {
                                ab_name.setText(dataSnapshot.child(s+"/UserName").getValue().toString());
                                ab_email.setText(dataSnapshot.child(s+"/UserEmail").getValue().toString());
                                ab_contact.setText(dataSnapshot.child(s+"/UserPhoneNumber").getValue().toString());
                                ab_address.setText(dataSnapshot.child(s+"/UserAddress").getValue().toString());
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
}
