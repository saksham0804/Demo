package com.bvcoe.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context mCtx;
    private List<recycleview> productList;

     Adapter(Context mCtx, List<recycleview> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card1,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        recycleview product = productList.get(position);

        holder.textView.setText(product.getText1());
        holder.textView2.setText(product.getText2());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImageSource(),null));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
//    Intent i = new Intent(Adapter.this,dump.class);
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView, textView2;
//        CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.text);
            textView2=itemView.findViewById(R.id.text2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(mCtx,ProductDetails.class));
                }
            });
        }

//    @Override
//    public void onClick(View view) {
//
//    }
}
}
