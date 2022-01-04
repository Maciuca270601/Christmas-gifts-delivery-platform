package fileio;

public final class GiftInput {

    private final String productName;
    private final Double price;
    private final String category;
    private final Integer quantity;

    public GiftInput(final String productName, final Double price, final String category,
                     final Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
