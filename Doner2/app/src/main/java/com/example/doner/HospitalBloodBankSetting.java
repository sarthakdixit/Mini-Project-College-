package com.example.doner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HospitalBloodBankSetting extends AppCompatActivity {

    private ListView settingList ;
    private List<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_blood_bank_setting);

        settingList = (ListView)findViewById(R.id.settingList);

        list = new ArrayList<String>();
        list.add("Search");
        list.add("Host");
        list.add("About Me");
        list.add("Sign Out");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        settingList.setAdapter(dataAdapter);

        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = list.get(position);
                if(s.equals("Sign Out")){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(HospitalBloodBankSetting.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else if(s.equals("Search")){
                    startActivity(new Intent(HospitalBloodBankSetting.this, HBSearchActivity.class));
                }else if(s.equals("Host")){
                    startActivity(new Intent(HospitalBloodBankSetting.this, HBHostActivity.class));
                }else{
                    startActivity(new Intent(HospitalBloodBankSetting.this, HBAboutMe.class));
                }
            }
        });
    }
}
