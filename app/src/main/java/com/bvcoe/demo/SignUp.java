package com.bvcoe.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bvcoe.demo.MainActivity;
import com.bvcoe.demo.R;
import com.bvcoe.demo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignUp extends AppCompatActivity {

    private TextInputLayout username, password, name, email;
    private Button button;
    private FirebaseAuth mAuth;
    private static String TAG = "Sign Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(Html.fromHtml("<font color='#000000'>Sign Up</font>"));
        setContentView(R.layout.activity_sign_up);


        username = findViewById(R.id.editText6);
        password = findViewById(R.id.editText5);
        name = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);
        button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }catch (Exception e){

                }
                final String Strusername = username.getEditText().getText().toString();
                final String Strpassword = password.getEditText().getText().toString();
                final String Strname = name.getEditText().getText().toString();
                final String Stremail = email.getEditText().getText().toString();

                if (Stremail.isEmpty() && Strpassword.isEmpty() && Strusername.isEmpty() && Strname.isEmpty()) {
                    username.setError("Enter your username");
                    password.setError("Enter a valid password");
                    name.setError("Enter Username");
                    email.setError("Enter your email address");
                } else if (Stremail.isEmpty())
                    email.setError("Enter your Email address");
                else if (Strpassword.isEmpty())
                    password.setError("Enter a valid password");
                else if (Strname.isEmpty())
                    name.setError("Enter your name");
                else if (Strusername.isEmpty())
                    username.setError("Enter username ");
                else {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.fetchSignInMethodsForEmail(Stremail).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            boolean b = !task.getResult().getSignInMethods().isEmpty();
                            if (b) {
                                Toast.makeText(getApplicationContext(), "Email already Exist!", Toast.LENGTH_SHORT).show();
                            } else {
                                User.addUser(Strusername, Stremail, Strname, Strpassword);
                                mAuth.createUserWithEmailAndPassword(Stremail, Strpassword).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                        Toast.makeText(SignUp.this, "Registered! Email Verification sent", Toast.LENGTH_SHORT).show();
                                                    else
                                                        Toast.makeText(SignUp.this,task.getException().getMessage(),
                                                                Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            Log.d(TAG, "createUserWithEmail:success");
//                                            Toast.makeText(SignUp.this, "Registered!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignUp.this, Login.class);
                                            finish();
                                            startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        }
                                    }
                                });
                            }

                        }
                    });
                }
            }
        });
    }
}
