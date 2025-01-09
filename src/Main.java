import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    private static final String CASH = "USD";

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        products.add(new Product("fruit", "Apple",
                10, false, LocalDate.of(2022, 1, 1)));
        products.add(new Product("book", "Harry Poter"
                , 250, true, LocalDate.of(2023, 5, 15)));
        products.add(new Product("toy", "Car"
                , 30, false, LocalDate.of(2025, 1, 1)));
        products.add(new Product("car", "BMW"
                , 12400, true, LocalDate.of(2024, 11, 13)));
        products.add(new Product("fruit", "Banana"
                , 11, false, LocalDate.of(2024, 12, 5)));
        products.add(new Product("book", "Una"
                , 50.6, true, LocalDate.of(2023, 6, 25)));
        products.add(new Product("book", "Crazy Orange"
                , 300, false, LocalDate.of(2022, 8, 14)));
        products.add(new Product("fruit", "Watermelon"
                , 40, true, LocalDate.of(2024, 12, 30)));
        products.add(new Product("book", "War Hammer"
                , 550, false, LocalDate.of(2020, 1, 7)));
        products.add(new Product("book", "Holy Book"
                , 30, true, LocalDate.of(1, 7, 19)));
        products.add(new Product("fruit", "mango",
                13, false, LocalDate.of(2020, 1, 7)));
        products.add(new Product("book", "Torry"
                , 50.4, false, LocalDate.of(2025, 1, 7)));


        List<Product> books = products.stream().
                filter(b -> "book".equals(b.getType())).
                toList();
        System.out.println("All our books :");
        books.forEach(System.out::println);
        System.out.println("----------------- ");


        List<Product> highPriceBooks = products.stream().
                filter(b -> "book".equals(b.getType())).
                filter(b -> b.getProductPrice() > 250).
                toList();
        System.out.println("All our books more 250" + CASH + " :");
        highPriceBooks.forEach(System.out::println);
        System.out.println("----------------- ");


        List<Product> discountBooks = products.stream().
                filter(b -> "book".equals(b.getType())).
                filter(Product::isDiscount).
                toList();
        System.out.println("All our books with discount" + CASH + " :");
        discountBooks.forEach(System.out::println);
        System.out.println("----------------- ");

        List<Product> searchBook = products.stream()
                .filter(b -> "book".equals(b.getType()))
                .toList();

        if (searchBook.isEmpty()) {
            System.out.println("No books found");
        } else {
            Product cheap = searchBook.stream()
                    .min(Comparator.comparingDouble(Product::getProductPrice))
                    .get();
            System.out.println("Cheapest book: " + cheap);
            System.out.println("----------------");
        }
        List<Product> threeLastBooks = products.stream().
                sorted(Comparator.comparing(Product::getCreateDate).reversed()).
                limit(3)
                .toList();
        threeLastBooks.forEach(System.out::println);
        System.out.println("----------------");

        int currentDay = LocalDate.now().getDayOfYear();

        double totalPrice = products.stream()
                .filter(b -> b.getCreateDate().getYear() == LocalDate.now().getYear())
                .filter(b -> "book".equals(b.getType()))
                .filter(b->b.getProductPrice() <= 75)
                .mapToDouble(Product::getProductPrice)
                .sum();
        System.out.println("Total price books : " + totalPrice);

        Map<String, List<Product>> groupedByType = products.stream()
                .collect(Collectors.groupingBy(Product::getType));
        System.out.println("Products grouped by type:");
        groupedByType.forEach((type, productList) -> {
            System.out.println(type + ":");
            productList.forEach(System.out::println);
        });
    }
}