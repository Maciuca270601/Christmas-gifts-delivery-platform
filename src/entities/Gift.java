package entities;

import fileio.GiftInput;

public final class Gift {

    private String productName;
    private Double price;
    private String category;

    public Gift(final GiftInput giftData) {
        this.productName = giftData.getProductName();
        this.price = giftData.getPrice();
        this.category = giftData.getCategory();
    }

    public Gift(final Gift gift) {
        this.productName = gift.productName;
        this.price = gift.price;
        this.category = gift.category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }
}
