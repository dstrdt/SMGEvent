package com.rika.kalenderevent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rika.kalenderevent.Model.User;

public class MainActivity extends AppCompatActivity {

    Button btnsignin, btnregister;
    RelativeLayout rootLayout;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///init Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        ///init Vie
        btnregister = (Button) findViewById(R.id.register);
        btnsignin = (Button) findViewById(R.id.signin);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        ///event
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterDialog();
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });


    }
    private void showLoginDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("SIGN IN "); //tulisan di dialog interface signin
        dialog.setMessage("Please Use Email to sign in");

        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout = inflater.inflate(R.layout.btn_login,null);

        final MaterialEditText eEmail = login_layout.findViewById(R.id.email);
        final MaterialEditText ePass  = login_layout.findViewById(R.id.password);

        dialog.setView(login_layout);

        //SetButton
        dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

                //cekvalidation
                if (TextUtils.isEmpty(eEmail.getText().toString())) { //kalo emailnya kosong
                    Snackbar.make(rootLayout, "Please enter email address", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(ePass.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter email Password", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (ePass.getText().toString().length() < 6) {
                    Snackbar.make(rootLayout, "Password too short", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                //Login
                auth.signInWithEmailAndPassword(eEmail.getText().toString(),ePass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this,MenuUtama.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout,"Failed "+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }
    private void showRegisterDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER ");
        dialog.setMessage("Please Use Email to Register");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.btn_register,null);

        final MaterialEditText eEmail = register_layout.findViewById(R.id.email);
        final MaterialEditText ePass = register_layout.findViewById(R.id.password);
        final MaterialEditText eName = register_layout.findViewById(R.id.nama);
        final MaterialEditText ePhone = register_layout.findViewById(R.id.phone);

        dialog.setView(register_layout);

        //Set button
        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //cekvalidation
                if (TextUtils.isEmpty(eEmail.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter email address", Snackbar.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(ePhone.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter phone number", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(ePass.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter email Password", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                if (ePass.getText().toString().length() < 6) {
                    Snackbar.make(rootLayout, "Password too short", Snackbar.LENGTH_SHORT).show();

                    return;
                }
                //Register User Baru
                auth.createUserWithEmailAndPassword(eEmail.getText().toString(),ePass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //Simpan User di DB
                                User user = new User();
                                user.setEmail(eEmail.getText().toString());
                                user.setName(eName.getText().toString());
                                user.setPhone(ePhone.getText().toString());
                                user.setPassword(ePass.getText().toString());

                                //use email to key
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(rootLayout, "Register Success", Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout, "Register Failed" + e.getMessage(), Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });
                            }

                        });
        }

        });


    dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();

        }
        });

        dialog.show();
    }
}