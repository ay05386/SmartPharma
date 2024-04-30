package com.example.pharmacyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   public TextView register;
   public Button signin;
   public EditText useremail;
  private EditText password;
  FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register =(TextView) findViewById(R.id.textView3);
        signin = findViewById(R.id.button);
        useremail = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        mAuth=FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usersignin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActive();
            }
        });
    }

public void openActive(){
    startActivity(new Intent(this,registerActivity.class));
}

    public void Usersignin(){
String Email = useremail.getText().toString();
String thePassword = password.getText().toString();
if(Email.isEmpty()){
    useremail.setError("Email is Required");
    useremail.requestFocus();
    return;
}
if (thePassword.isEmpty()){
    password.setError("Password is Required");
    password.requestFocus();
    return;
}
mAuth.signInWithEmailAndPassword(Email,thePassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){
    startActivity(new Intent(MainActivity.this,navigation.class));
}else{
    Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
}
    }
});

            }
    }
