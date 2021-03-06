package com.example.doner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DonnerPage extends AppCompatActivity {

    private TextView name, email, campname, venue, contact, reward, dateFrom, dateTo, timing, d, dd, bcc, sc ;
    private ImageView ic, icc;
    private DatabaseReference data ;
    private ListView list4 ;
    private ArrayAdapter<String> arrayAdapter ;
    private ArrayList<String> arrayList ;
    private String date ;
    private int da ;
    private LinearLayout dPage, donn, bloodtt ;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor ;
    private EditText ed ;
    private Button save ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_page);

        name = (TextView)findViewById(R.id.d_vis_name);
        email = (TextView)findViewById(R.id.d_vis_email);
        ic = (ImageView)findViewById(R.id.d_vis_icon);
        list4 = (ListView)findViewById(R.id.list4);
        dPage = (LinearLayout)findViewById(R.id.d_Page);
        donn = (LinearLayout)findViewById(R.id.donn);
        campname = (TextView)findViewById(R.id.d_othercampName);
        venue = (TextView)findViewById(R.id.d_othervenue);
        contact = (TextView)findViewById(R.id.d_othercontact);
        reward = (TextView)findViewById(R.id.d_otherrewardDonner);
        dateFrom = (TextView)findViewById(R.id.d_otherdate_from);
        dateTo = (TextView)findViewById(R.id.d_otherdate_to);
        timing = (TextView)findViewById(R.id.d_othertiming);
        d = (TextView)findViewById(R.id.d);
        icc = (ImageView)findViewById(R.id.d_image);
        dd = (TextView)findViewById(R.id.d_result);
        bcc = (TextView)findViewById(R.id.bcc);
        bloodtt = (LinearLayout)findViewById(R.id.bloodtt);
        ed = (EditText)findViewById(R.id.bloodttedit);
        save =(Button)findViewById(R.id.bloodregis);
        sc = (TextView)findViewById(R.id.sc);

        dPage.setVisibility(View.GONE);
        donn.setVisibility(View.GONE);

        final String s = getIntent().getStringExtra("email");

        data = FirebaseDatabase.getInstance().getReference("Donner/"+s);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Blood Type").exists()){
                    bloodtt.setVisibility(View.GONE);
                    donn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        da = Integer.parseInt(date.replaceAll("-",""));

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        list4.setAdapter(arrayAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed.getText().toString().isEmpty()){
                    String str = null ;
                    if(ed.getText().toString().contains("-")){
                        str = ed.getText().toString().replace("-","negative");
                    }else{
                        str = ed.getText().toString().replace("+","positive");
                    }
                    data = FirebaseDatabase.getInstance().getReference(str);
                    data.child(s).setValue("asd");

                }
            }
        });

        sharedPreferences = getSharedPreferences("hii",0);
        editor = sharedPreferences.edit();
        editor.putString("hii",s);
        editor.commit();

        ic.setImageResource(R.drawable.patient);

        data = FirebaseDatabase.getInstance().getReference("Donner");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(s).exists()){
                    name.setText(dataSnapshot.child(s+"/UserName").getValue().toString());
                    email.setText(dataSnapshot.child(s+"/UserEmail").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dPage.setVisibility(View.GONE);
                donn.setVisibility(View.VISIBLE);
            }
        });

        ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonnerPage.this, DoonerPageSetting.class));
            }
        });

        data = FirebaseDatabase.getInstance().getReference("Hosting");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn1: dataSnapshot.getChildren()){
                    if(da < Integer.parseInt(sn1.child("Date(to)").getValue().toString().replaceAll("-",""))){
                        arrayAdapter.add(sn1.getKey().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                donn.setVisibility(View.GONE);
                dPage.setVisibility(View.VISIBLE);
                final String s2 = arrayList.get(position);
                data = FirebaseDatabase.getInstance().getReference("Hosting/"+s2);
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        d.setText(s2);
                        campname.setText(s2);
                        venue.setText(dataSnapshot.child("Venue").getValue().toString());
                        contact.setText(dataSnapshot.child("Contact").getValue().toString());
                        reward.setText(dataSnapshot.child("Reward").getValue().toString());
                        dateFrom.setText(dataSnapshot.child("Date(from)").getValue().toString());
                        dateTo.setText(dataSnapshot.child("Date(to)").getValue().toString());
                        timing.setText(dataSnapshot.child("Timing").getValue().toString());
                        data = FirebaseDatabase.getInstance().getReference("Hosting/"+s2+"/Donners");
                        data.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue().toString().contains(s)){
                                    icc.setImageResource(R.drawable.uj);
                                    dd.setText("Donated");
                                }else{
                                    icc.setImageResource(R.drawable.ui);
                                    dd.setText("Not Donated");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
