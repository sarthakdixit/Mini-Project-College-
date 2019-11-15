package com.example.doner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HospitalBloodBankPage extends AppCompatActivity {

    private TextView name, email, blood, reload ;
    private EditText ammount_edit, quantity_edit ;
    private ImageView icon ;
    private LinearLayout bloodType, selectedType, loading ;
    private String choice, userEmail, asd, key ;
    private DatabaseReference data ;
    private FirebaseAuth firebaseAuth ;
    private FirebaseDatabase firebaseDatabase ;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    private Button Anegative, Apositive, Bnegative, Bpositive, ABnegative, ABpositive, Onegative, Opositive, b_ck, save ;
    private TextView Anegative_ammount, Anegative_quantity, Bnegative_ammount, Bnegative_quantity, ABnegative_ammount, ABnegative_quantity, Onegative_ammount, Onegative_quantity ;
    private TextView Apositive_ammount, Apositive_quantity, Bpositive_ammount, Bpositive_quantity, ABpositive_ammount, ABpositive_quantity, Opositive_ammount, Opositive_quantity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_blood_bank_page);

        name = (TextView)findViewById(R.id.vis_name);
        email = (TextView)findViewById(R.id.vis_email);
        icon = (ImageView) findViewById(R.id.vis_icon);
        Anegative = (Button)findViewById(R.id.Anegative);
        Apositive = (Button)findViewById(R.id.Apositive);
        Bnegative = (Button)findViewById(R.id.Bnegative);
        Bpositive = (Button)findViewById(R.id.Bpositive);
        ABnegative = (Button)findViewById(R.id.ABnegative);
        ABpositive = (Button)findViewById(R.id.ABpositive);
        Onegative = (Button)findViewById(R.id.Onegative);
        Opositive = (Button)findViewById(R.id.Opositive);
        Anegative_ammount = (TextView)findViewById(R.id.Anegative_ammount);
        Anegative_quantity = (TextView)findViewById(R.id.Anegative_quantity);
        Bnegative_ammount = (TextView)findViewById(R.id.Bnegative_ammount);
        Bnegative_quantity = (TextView)findViewById(R.id.Bnegative_quantity);
        ABnegative_ammount = (TextView)findViewById(R.id.ABnegative_ammount);
        ABnegative_quantity = (TextView)findViewById(R.id.ABnegative_quantity);
        Onegative_ammount = (TextView)findViewById(R.id.Onegative_ammount);
        Onegative_quantity = (TextView)findViewById(R.id.Onegative_quantity);
        Apositive_ammount = (TextView)findViewById(R.id.Apositive_ammount);
        Apositive_quantity = (TextView)findViewById(R.id.Apositive_quantity);
        Bpositive_ammount = (TextView)findViewById(R.id.Bpositive_ammount);
        Bpositive_quantity = (TextView)findViewById(R.id.Bpositive_quantity);
        ABpositive_ammount = (TextView)findViewById(R.id.ABpositive_ammount);
        ABpositive_quantity = (TextView)findViewById(R.id.ABpositive_quantity);
        Opositive_ammount = (TextView)findViewById(R.id.Opositive_ammount);
        Opositive_quantity = (TextView)findViewById(R.id.Opositive_quantity);
        ammount_edit = (EditText)findViewById(R.id.ammount_edit);
        quantity_edit = (EditText)findViewById(R.id.quantity_edit);
        b_ck = (Button)findViewById(R.id.b_ck);
        save = (Button)findViewById(R.id.save);
        bloodType = (LinearLayout)findViewById(R.id.bloodType);
        selectedType = (LinearLayout)findViewById(R.id.selectedType);
        blood = (TextView)findViewById(R.id.blood);
        reload = (TextView)findViewById(R.id.reload);
        loading = (LinearLayout)findViewById(R.id.loading);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        data = firebaseDatabase.getReference();

        bloodType.setVisibility(View.GONE);
        selectedType.setVisibility(View.GONE);

        userEmail = getIntent().getStringExtra("email");
        choice = getIntent().getStringExtra("choice");

        sharedPreferences = getSharedPreferences("hi",0);
        editor = sharedPreferences.edit();
        editor.putString("hi",userEmail);
        editor.commit();

        email.setText(userEmail);
        if(choice.equals("Hospital")){
            icon.setImageResource(R.drawable.hospital);
        }else{
            icon.setImageResource(R.drawable.bb);
        }

        data = FirebaseDatabase.getInstance().getReference(choice+"/"+userEmail.replaceAll("[-+.^:,.@]","")+"/UserName");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Register();

        Anegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(Anegative.getText().toString(),Anegative_ammount.getText().toString(),Anegative_quantity.getText().toString());
            }
        });

        Apositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(Apositive.getText().toString(), Apositive_ammount.getText().toString(), Apositive_quantity.getText().toString());
            }
        });

        Bnegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(Bnegative.getText().toString(),Bnegative_ammount.getText().toString(),Bnegative_quantity.getText().toString());
            }
        });

        Bpositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(Bpositive.getText().toString(), Bpositive_ammount.getText().toString(), Bpositive_quantity.getText().toString());
            }
        });

        ABnegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(ABnegative.getText().toString(),ABnegative_ammount.getText().toString(),ABnegative_quantity.getText().toString());
            }
        });

        ABpositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(ABpositive.getText().toString(), ABpositive_ammount.getText().toString(), ABpositive_quantity.getText().toString());
            }
        });

        Onegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(Onegative.getText().toString(),Onegative_ammount.getText().toString(),Onegative_quantity.getText().toString());
            }
        });

        Opositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeClicked(Opositive.getText().toString(), Opositive_ammount.getText().toString(), Opositive_quantity.getText().toString());
            }
        });

        b_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType.setVisibility(View.GONE);
                bloodType.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = firebaseDatabase.getReference();
                data.child(asd+"/"+key+"/Value").setValue(ammount_edit.getText().toString()+","+quantity_edit.getText().toString());
                selectedType.setVisibility(View.GONE);
                bloodType.setVisibility(View.VISIBLE);
            }
        });

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalBloodBankPage.this, HospitalBloodBankSetting.class));
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                bloodType.setVisibility(View.GONE);
                Register();
            }
        });
    }

    private void Register(){
        key = userEmail.replaceAll("[-+.^:,.@]","");
        Anegative();
        loading.setVisibility(View.GONE);
        bloodType.setVisibility(View.VISIBLE);
    }

    private void typeClicked(String type, String ammount, String quantity){
        if (type.contains("-")){
            asd = type.replace("-","negative");
        }else{
            asd = type.replace("+","positive");
        }
        blood.setText(type);
        ammount = ammount.replace("Ammount: ","");
        ammount = ammount.replace("ml", "");
        quantity = quantity.replace("Quantity: ","");
        ammount_edit.setText(ammount);
        quantity_edit.setText(quantity);
        bloodType.setVisibility(View.GONE);
        selectedType.setVisibility(View.VISIBLE);
    }

    private void Anegative(){
        data = FirebaseDatabase.getInstance().getReference("Anegative");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("Anegative/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            Anegative_ammount.setText("Ammount: "+s[0]+"ml");
                            Anegative_quantity.setText("Quantity: "+s[1]);
                            Apositive();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Anegative_ammount.setText("Ammount: "+0+"ml");
                    Anegative_quantity.setText("Quantity: "+0);
                    Apositive();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Apositive(){
        data = FirebaseDatabase.getInstance().getReference("Apositive");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("Apositive/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            Apositive_ammount.setText("Ammount: "+s[0]+"ml");
                            Apositive_quantity.setText("Quantity: "+s[1]);
                            Bnegative();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Apositive_ammount.setText("Ammount: "+0+"ml");
                    Apositive_quantity.setText("Quantity: "+0);
                    Bnegative();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Bnegative(){
        data = FirebaseDatabase.getInstance().getReference("Bnegative");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("Bnegative/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            Bnegative_ammount.setText("Ammount: "+s[0]+"ml");
                            Bnegative_quantity.setText("Quantity: "+s[1]);
                            Bpositive();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else {
                    Bnegative_ammount.setText("Ammount: "+0+"ml");
                    Bnegative_quantity.setText("Quantity: "+0);
                    Bpositive();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Bpositive(){
        data = FirebaseDatabase.getInstance().getReference("Bpositive");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("Bpositive/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            Bpositive_ammount.setText("Ammount: "+s[0]+"ml");
                            Bpositive_quantity.setText("Quantity: "+s[1]);
                            ABnegative();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Bpositive_ammount.setText("Ammount: "+0+"ml");
                    Bpositive_quantity.setText("Quantity: "+0);
                    ABnegative();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ABnegative(){
        data = FirebaseDatabase.getInstance().getReference("ABnegative");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("ABnegative/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            ABnegative_ammount.setText("Ammount: "+s[0]+"ml");
                            ABnegative_quantity.setText("Quantity: "+s[1]);
                            ABpositive();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    ABnegative_ammount.setText("Ammount: "+0+"ml");
                    ABnegative_quantity.setText("Quantity: "+0);
                    ABpositive();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ABpositive(){
        data = FirebaseDatabase.getInstance().getReference("ABpositive");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("ABpositive/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            ABpositive_ammount.setText("Ammount: "+s[0]+"ml");
                            ABpositive_quantity.setText("Quantity: "+s[1]);
                            Onegative();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    ABpositive_ammount.setText("Ammount: "+0+"ml");
                    ABpositive_quantity.setText("Quantity: "+0);
                    Onegative();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Onegative(){
        data = FirebaseDatabase.getInstance().getReference("Onegative");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("Onegative/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            Onegative_ammount.setText("Ammount: "+s[0]+"ml");
                            Onegative_quantity.setText("Quantity: "+s[1]);
                            Opositive();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Onegative_ammount.setText("Ammount: "+0+"ml");
                    Onegative_quantity.setText("Quantity: "+0);
                    Opositive();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Opositive(){
        data = FirebaseDatabase.getInstance().getReference("Opositive");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(key).exists()){
                    data = FirebaseDatabase.getInstance().getReference("Opositive/"+key+"/Value");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String[] s = dataSnapshot.getValue().toString().split(",");
                            Opositive_ammount.setText("Ammount: "+s[0]+"ml");
                            Opositive_quantity.setText("Quantity: "+s[1]);
                            data = null ;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Opositive_ammount.setText("Ammount: "+0+"ml");
                    Opositive_quantity.setText("Quantity: "+0);
                    data = null ;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
