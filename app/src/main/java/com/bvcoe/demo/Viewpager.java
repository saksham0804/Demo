package com.bvcoe.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Viewpager extends AppCompatActivity {

    ViewPager viewPager;
    private int[] layouts={R.layout.first_slide, R.layout.second_slide,R.layout.third_slide};
    private MpagerAdapter mpagerAdapter;
    private LinearLayout dots_layout;
    private ImageView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(new PrefernceManager(this).checkPrefernce()) {
            startActivity(new Intent(Viewpager.this, MainActivity.class));
            finish();
        }

//        if(Build.VERSION.SDK_INT>19)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        else
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_view_pager);

        viewPager=findViewById(R.id.viewpager);
        mpagerAdapter=new MpagerAdapter(layouts,this);
        viewPager.setAdapter(mpagerAdapter);
        final Button skip= findViewById(R.id.skip);
        final Button next=findViewById(R.id.next);
        next.setVisibility(View.GONE);

        dots_layout=findViewById(R.id.dots);
        dots(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots(position);
                if(position==layouts.length-1){
                    next.setVisibility(View.VISIBLE);
                    skip.setVisibility(View.GONE);
                }
                else
                {
                    next.setVisibility(View.GONE);
                    skip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        next.setVisibility(View.GONE);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new PrefernceManager(getApplicationContext()).writePrefernce();

                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();

            }
        };
        skip.setOnClickListener(listener);
        next.setOnClickListener(listener);


    }

    private void dots(int currpos){
        if(dots_layout!=null)
            dots_layout.removeAllViews();

        dots=new ImageView[layouts.length];

        for (int i=0;i<layouts.length;i++){
            dots[i]= new ImageView(this);
            if(i==currpos)
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.activedots));
            else
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactivedots));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            dots_layout.addView(dots[i],params);
        }
    }
}
