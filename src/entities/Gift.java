package entities;

import fileio.GiftInput;

public final class Gift {

    private String productName;
    private Double price;
    private String category;
    private Integer quantity;

    public Gift(final GiftInput giftData) {
        this.productName = giftData.getProductName();
        this.price = giftData.getPrice();
        this.category = giftData.getCategory();
        this.quantity = giftData.getQuantity();
    }

    public Gift(final Gift gift) {
        this.productName = gift.productName;
        this.price = gift.price;
        this.category = gift.category;
        this.quantity = gift.quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
