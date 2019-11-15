package com.example.doner;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText email, paswwrd, new_email, new_passwrd, new_name, new_phNumber, new_address, forgetEmail ;
    private Button login, register, find ;
    private TextView forgetPaswwrd, clickHere, backToLogin, backLoginPage ;
    private FirebaseAuth firebaseAuth ;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference ;
    private LinearLayout loginPage, registerUser, forgetPage ;
    private Spinner spinner1,spinner2;
    private VideoView video ;
    private MediaPlayer mediaPlayer ;
    private int mCurrentVideoPosition ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.email);
        paswwrd = (EditText)findViewById(R.id.passwrd);
        login = (Button)findViewById(R.id.loginbtn);
        forgetPaswwrd = (TextView)findViewById(R.id.forgetPasswrd);
        clickHere = (TextView)findViewById(R.id.clickHere);
        new_email = (EditText)findViewById(R.id.new_email);
        new_passwrd = (EditText)findViewById(R.id.new_password);
        new_name = (EditText)findViewById(R.id.new_name);
        new_phNumber = (EditText)findViewById(R.id.new_phoneNumber);
        new_address = (EditText)findViewById(R.id.new_address);
        register = (Button)findViewById(R.id.register);
        backToLogin = (TextView)findViewById(R.id.backToLogin);
        loginPage = (LinearLayout)findViewById(R.id.loginPage);
        registerUser = (LinearLayout)findViewById(R.id.registerUser);
        forgetPage = (LinearLayout)findViewById(R.id.forgetPage);
        forgetEmail = (EditText)findViewById(R.id.forgetEmail);
        find = (Button)findViewById(R.id.find);
        forgetPage = (LinearLayout)findViewById(R.id.forgetPage);
        backLoginPage = (TextView)findViewById(R.id.backLoginPage);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        video = findViewById(R.id.videoview);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video1);
        video.setVideoURI(uri);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp ;
                mediaPlayer.setLooping(true);
                if(mCurrentVideoPosition != 0){
                    mediaPlayer.seekTo(mCurrentVideoPosition);
                    mediaPlayer.start();
                }
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        List<String> list = new ArrayList<String>();
        list.add("Hospital");
        list.add("Blood Bank");
        list.add("Donner");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkForEmpty()){
                    final String s = String.valueOf(spinner1.getSelectedItem());
                    databaseReference = FirebaseDatabase.getInstance().getReference(s);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(email.getText().toString().replaceAll("[-+.^:,.@]","")).exists()){
                                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),paswwrd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                                Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_SHORT).show();
                                                if(s.equals("Donner")){
                                                    Intent intent = new Intent(LoginActivity.this, DonnerPage.class);
                                                    intent.putExtra("email",email.getText().toString().replaceAll("[-+.^:,.@]",""));
                                                    startActivity(intent);
                                                }else{
                                                    Intent intent = new Intent(LoginActivity.this, HospitalBloodBankPage.class);
                                                    intent.putExtra("email",email.getText().toString());
                                                    intent.putExtra("choice", s);
                                                    startActivity(intent);
                                                }
                                            }else{
                                                Toast.makeText(getApplicationContext(),"Please verify your email address",Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(), "Please Choose Correct Option", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        forgetPaswwrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage.setVisibility(View.GONE);
                forgetPage.setVisibility(View.VISIBLE);
            }
        });

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage.setVisibility(View.GONE);
                registerUser.setVisibility(View.VISIBLE);
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage.setVisibility(View.VISIBLE);
                registerUser.setVisibility(View.GONE);
            }
        });

        backLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPage.setVisibility(View.GONE);
                loginPage.setVisibility(View.VISIBLE);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!forgetEmail.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance().sendPasswordResetEmail(forgetEmail.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Check Email To Reset Password", Toast.LENGTH_SHORT).show();
                                        forgetEmail.setText("");
                                        forgetPage.setVisibility(View.GONE);
                                        loginPage.setVisibility(View.VISIBLE);
                                    }else{
                                        forgetEmail.setText("");
                                        Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekForEmptyRegister()){
                    final String s1 = String.valueOf(spinner2.getSelectedItem());
                    if(s1.equals("Hospital") || s1.equals("Blood Bank")){
                        databaseReference = FirebaseDatabase.getInstance().getReference("Certified "+s1);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.child(new_name.getText().toString().replaceAll("[-+.^:,.@]","")).exists()){
                                    firebaseAuth.createUserWithEmailAndPassword(new_email.getText().toString(), new_passwrd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                firebaseAuth.getCurrentUser().sendEmailVerification();
                                                Toast.makeText(getApplicationContext(),"Check email for verification",Toast.LENGTH_LONG).show();
                                                String s = new_email.getText().toString().replaceAll("[-+.^:,.@]","");
                                                databaseReference.child(s1+"/"+s).push() ;
                                                databaseReference.child(s1+"/"+s+"/UserEmail").setValue(new_email.getText().toString());
                                                databaseReference.child(s1+"/"+s+"/UserName").setValue(new_name.getText().toString());
                                                databaseReference.child(s1+"/"+s+"/UserPhoneNumber").setValue(new_phNumber.getText().toString());
                                                databaseReference.child(s1+"/"+s+"/UserAddress").setValue(new_address.getText().toString());
                                                new_email.setText("");
                                                new_phNumber.setText("");
                                                new_passwrd.setText("");
                                                new_name.setText("");
                                                new_address.setText("");
                                                registerUser.setVisibility(View.GONE);
                                                loginPage.setVisibility(View.VISIBLE);
                                            }else{
                                                new_email.setText("");
                                                new_phNumber.setText("");
                                                new_passwrd.setText("");
                                                new_name.setText("");
                                                new_address.setText("");
                                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else{
                        firebaseAuth.createUserWithEmailAndPassword(new_email.getText().toString(), new_passwrd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    firebaseAuth.getCurrentUser().sendEmailVerification();
                                    Toast.makeText(getApplicationContext(),"Check email for verification",Toast.LENGTH_LONG).show();
                                    String s = new_email.getText().toString().replaceAll("[-+.^:,.@]","");
                                    databaseReference.child(s1+"/"+s).push() ;
                                    databaseReference.child(s1+"/"+s+"/UserEmail").setValue(new_email.getText().toString());
                                    databaseReference.child(s1+"/"+s+"/UserName").setValue(new_name.getText().toString());
                                    databaseReference.child(s1+"/"+s+"/UserPhoneNumber").setValue(new_phNumber.getText().toString());
                                    databaseReference.child(s1+"/"+s+"/UserAddress").setValue(new_address.getText().toString());
                                    new_email.setText("");
                                    new_phNumber.setText("");
                                    new_passwrd.setText("");
                                    new_name.setText("");
                                    new_address.setText("");
                                    registerUser.setVisibility(View.GONE);
                                    loginPage.setVisibility(View.VISIBLE);
                                }else{
                                    new_email.setText("");
                                    new_phNumber.setText("");
                                    new_passwrd.setText("");
                                    new_name.setText("");
                                    new_address.setText("");
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private boolean checkForEmpty(){
        if(email.getText().toString().isEmpty() && paswwrd.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill up the details", Toast.LENGTH_SHORT).show();
            return false ;
        }
        if(email.getText().toString().isEmpty()){
            email.setError("Enter phone number");
        }
        if(paswwrd.getText().toString().isEmpty()){
            paswwrd.setError("Enter password");
            return false ;
        }
        return true ;
    }

    private boolean chekForEmptyRegister(){
        if(new_email.getText().toString().isEmpty() && new_passwrd.getText().toString().isEmpty() &&
                new_name.getText().toString().isEmpty() && new_phNumber.getText().toString().isEmpty() &&
                new_address.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill Up the details", Toast.LENGTH_SHORT).show();
            return false ;
        }
        if(new_email.getText().toString().isEmpty()){
            new_email.setError("Enter Email");
        }
        if(new_passwrd.getText().toString().isEmpty()){
            new_passwrd.setError("Enter Password");
        }
        if(new_name.getText().toString().isEmpty()){
            new_name.setError("Enter Name");
        }
        if(new_phNumber.getText().toString().isEmpty()){
            new_phNumber.setError("Enter Phone NUmber");
        }
        if(new_address.getText().toString().isEmpty()){
            new_address.setError("Enter Address");
            return false;
        }
        return true ;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentVideoPosition = mediaPlayer.getCurrentPosition();
        video.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        video.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
