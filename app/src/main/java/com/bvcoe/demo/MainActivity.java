package com.bvcoe.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    List<recycleview> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList= new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList.add(
                new recycleview(
                    R.drawable.download,
                        "Macbook Air",
                        "₹99,000"
                )
        );
        productList.add(
                new recycleview(
                        R.drawable.download,
                        "Macbook Pro",
                        "₹1,19,000"
                )
        );
         adapter=new Adapter(this,productList);
         recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
            // Setting Dialog Title
            alertDialog2.setTitle("Confirm SignOut");


            // Setting Dialog Message
            alertDialog2.setMessage("Are you sure you want to Signout?");

            // Setting Positive "Yes" Btn
            alertDialog2.setPositiveButton("Logout",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog

                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            SaveSharedPreference.clearUserName(getApplicationContext());
                            startActivity(i);
                            finish();
                        }
                    });

            // Setting Negative "NO" Btn
            alertDialog2.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            // Showing Alert Dialog
            alertDialog2.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
