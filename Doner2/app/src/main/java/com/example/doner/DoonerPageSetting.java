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

public class DoonerPageSetting extends AppCompatActivity {

    private ListView settingList ;
    private List<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dooner_page_setting);

        settingList = (ListView)findViewById(R.id.d_settingList);

        list = new ArrayList<String>();
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
                if(s.equals("About Me")){
                    startActivity(new Intent(DoonerPageSetting.this, DonnerAboutMe.class));
                }else if(s.equals("Sign Out")){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(DoonerPageSetting.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
