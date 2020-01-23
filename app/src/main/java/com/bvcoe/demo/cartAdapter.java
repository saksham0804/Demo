package com.bvcoe.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public cartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View CartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new CartItemViewholder(CartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new CartTotalAmountViewholder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch(cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                String productprice = cartItemModelList.get(position).getProductPrice();
                String cuttedprice  = cartItemModelList.get(position).getCuttedPrice();

                ((CartItemViewholder)holder).setItemDetails(resource,title,productprice,cuttedprice,);
                break;
            case CartItemModel.TOTAL_AMOUNT:

                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewholder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView cuttedprice;
        private TextView OffersApploed;
        private TextView productQuantity;



        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedprice=itemView.findViewById(R.id.cutted_price);
//            OffersApploed=itemView.findViewById(R.id.offers_applied);
            productQuantity=itemView.findViewById(R.id.product_quantity);
        }
        private void setItemDetails(int resource, String title , String productPriceText, String cuttedPriceText){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productPrice.setText(productPriceText);
            cuttedprice.setText(cuttedPriceText);

        }
    }
    class CartTotalAmountViewholder extends RecyclerView.ViewHolder{
        private TextView totalItems;
        private TextView totalitemprice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount ;
        public CartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id. total_items);
            totalitemprice = itemView.findViewById(R.id. total_items_price):
            deliveryPrice = itemView. findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id. total_price);
            savedAmount = itemView.findViewById(R.id. saved_amount);
        }
        private  void setTotalAmount(String totalItemText, String totalItemsPriceText, String deliveryPriceText,String totalAmountText, String savedAmountText){
            totalItems.setText(totalItemText):
            totalitemprice.setText(totalItemsPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
