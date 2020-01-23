package com.bvcoe.demo;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    //cart item

    public CartItemModel(int type, int totalItems, String totalAmount, String deliveryPrice, String savedAmount) {
        this.type = type;
        this.totalItems = totalItems;
        TotalItemsPrice = totalAmount;
        DeliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
    }
    private int productImage;
    private String productTitle;
    private String productPrice;
    private String cuttedPrice;
    private int productquantity;
    private int offersApplied;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalItemsPrice(String totalAmount) {
        TotalItemsPrice = totalAmount;
    }

    public String getDeliveryPrice() {
        return DeliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        DeliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }

    public static int getCartItem() {
        return CART_ITEM;
    }

    public static int getTotalAmount() {
        return TOTAL_AMOUNT;
    }



    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    public int getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(int offersApplied) {
        this.offersApplied = offersApplied;
    }

    public CartItemModel( int type,int productImage, String productTitle, String productPrice, String cuttedPrice, int productquantity, int offersApplied) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.productquantity = productquantity;
        this.offersApplied = offersApplied;
    }

    //cart total
    private int totalItems;
    private String TotalItemsPrice;
    private String DeliveryPrice;
    private String savedAmount;

}
