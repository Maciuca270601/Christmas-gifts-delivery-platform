package fileio;

public class GiftInput {

    private String productName;
    private Double price;
    private String category;

    public GiftInput(String productName, Double price, String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() { return productName; }

    public Double getPrice() { return price; }

    public String getCategory() { return category; }
}
