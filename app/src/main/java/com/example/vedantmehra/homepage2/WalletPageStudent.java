package com.example.vedantmehra.homepage2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletPageStudent extends AppCompatActivity {

    private DatabaseReference databaseInvestor, databaseStudent, databaseReference;
    private TextView amount;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_page_student);
        amount = findViewById(R.id.balance);
        if(FirebaseAuth.getInstance() != null && FirebaseAuth.getInstance().getCurrentUser() != null)
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        else
            return;

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("money").exists()){
                    String currentBalance = dataSnapshot.child("money").getValue().toString();
                    amount.setText("Your current Balance is - " + currentBalance);
                }else{
                    amount.setText("Your current Balance is - " + "0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(WalletPageStudent.this, HomePage.class);
        startActivity(intent);
    }
}
