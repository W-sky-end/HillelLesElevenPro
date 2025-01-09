import java.time.LocalDate;

public class Product {
    private String type;
    private String productName;
    private double productPrice;
    private final boolean discount;
    private LocalDate createDate;


    public Product(String type, String productName, double productPrice,
                   boolean discount, LocalDate createDate) {
        this.type = type;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discount = discount;
        this.createDate = createDate;
    }

    public double getProductPrice() {
        if (discount) {
            return productPrice * 0.9;
        }
        return productPrice;
    }

    public boolean isDiscount() {
        return discount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }


    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product" +
                " Type= '" + type + '\'' +
                ", ProductName= '" + productName + '\'' +
                ", ProductPrice= " + getProductPrice() + "USD"
                + ", Discount ? " + discount + " Date : " + createDate;
    }
}