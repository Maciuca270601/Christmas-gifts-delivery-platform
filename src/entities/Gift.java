package entities;

import fileio.GiftInput;

public class Gift {

    private String productName;
    private Double price;
    private String category;

    public Gift (GiftInput giftData) {
        this.productName = giftData.getProductName();
        this.price = giftData.getPrice();
        this.category = giftData.getCategory();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
