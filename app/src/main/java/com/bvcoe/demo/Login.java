package com.bvcoe.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bvcoe.demo.R;
import com.bvcoe.demo.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Login extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);
        }

        setTitle(Html.fromHtml("<font color='#000000'>Sign In</font>"));

        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
//        Button register = findViewById(R.id.button2);
        TextView register = findViewById(R.id.textView);
        final Intent i = new Intent(this, SignUp.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void LogIn(View view)
    {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (Exception e){

        }
        final TextInputLayout email = findViewById(R.id.editText);
        TextInputLayout password = findViewById(R.id.editText2);
        final String Stremail = email.getEditText().getText().toString();
        final String Strpass= password.getEditText().getText().toString();

        if (Stremail.isEmpty() && Strpass.isEmpty()) {
            email.setError("Enter your Email address");
            password.setError("Enter a Valid password");
        }
        else if (Stremail.isEmpty())
            email.setError("Enter your Email address");
        else if(Strpass.isEmpty())
            password.setError("Enter a valid password");
        else {
            firebaseAuth.signInWithEmailAndPassword(Stremail, Strpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {

                        firebaseAuth.fetchSignInMethodsForEmail(Stremail).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean b = !task.getResult().getSignInMethods().isEmpty();

                                if (b) {
                                    Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "User not Registered!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                    else {
                        if(firebaseAuth.getCurrentUser().isEmailVerified()){
                            SaveSharedPreference.setUserName(Login.this,Stremail);
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        }
                        else
                            Toast.makeText(Login.this,"Email Not Verified! Please verify before continuing",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (SaveSharedPreference.getUserName(Login.this).length() != 0) {
//            startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//        }
////        firebaseAuth.addAuthStateListener(authStateListener );
//    }

}
