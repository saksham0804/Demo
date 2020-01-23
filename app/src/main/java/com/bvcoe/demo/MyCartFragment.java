package com.bvcoe.demo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }
    private RecyclerView cartItemsrecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_cart, container, false);
        cartItemsrecyclerview = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsrecyclerview.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList= new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.download,"MacBook Air(128GB)","₹99,100/-","1,10,100",1,0));
        cartItemModelList.add(new CartItemModel(1,"Price (1 item)","₹99,100/-","Free","₹20,000/-","₹99,100/-"));

        cartAdapter cartAdapter = new cartAdapter(cartItemModelList);
        cartItemsrecyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        return view;
    }

}
