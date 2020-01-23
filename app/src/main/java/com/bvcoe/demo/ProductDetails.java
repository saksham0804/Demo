package com.bvcoe.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.nio.file.FileVisitOption;
import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    private ViewPager productimagesViewpager;
    private TabLayout viewpagerIndicator;

    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar=findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);

        }
        viewpagerIndicator=findViewById(R.id.viewpager_indicattor);
        productimagesViewpager=findViewById(R.id.product_images_viewpager);
        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.download);
        productImages.add(R.drawable.download1);
        productImages.add(R.drawable.download2);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productimagesViewpager.setAdapter(productImagesAdapter);

        viewpagerIndicator.setupWithViewPager(productimagesViewpager,true  );

        viewpagerIndicator.setupWithViewPager(productimagesViewpager, true);
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams)viewpagerIndicator.getLayoutParams();//ConstraintLayout is my layout manager
        params.rightMargin = 3;
        params.leftMargin=3;
        viewpagerIndicator.setLayoutParams(params);

    productDetailsViewpager.setAdapter(new productDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));

    productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

    productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            productDetailsViewpager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_and_cart_icon,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.search_icon:
                return true;
//                break;

            case R.id.cart:
                return true;
//                break;

            case  android.R.id.home:
                finish();
                break;


        }
        return true;
    }
}
