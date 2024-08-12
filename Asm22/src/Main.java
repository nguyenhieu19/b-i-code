import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class product {
    private String code;
    private String name;
    private float price;

    public product(String code, String name, float price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class Main {

    static List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add new product");
            System.out.println("2. Print product list");
            System.out.println("3. Remove product by code");
            System.out.println("4. Sort products by descending price");
            System.out.println("5. Search product by code or name");
            System.out.println("6. Search products with price >= x");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    input();
                    break;
                case 2:
                    output();
                    break;
                case 3:
                    System.out.println("Enter the code of the product to remove:");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 4:
                    sortByPriceDesc();
                    break;
                case 5:
                    System.out.println("Enter the product code or name to search:");
                    String keyword = scanner.nextLine();
                    Product product = findByCodeOrName(keyword);
                    if (product != null) {
                        System.out.println(product.toString());
                    } else {
                        System.out.println("Product not found");
                    }
                    break;
                case 6:
                    System.out.println("Enter price x:");
                    float x = scanner.nextFloat();
                    List<Product> filteredProducts = filterByPrice(x);
                    if (!filteredProducts.isEmpty()) {
                        for (Product p : filteredProducts) {
                            System.out.println(p.toString());
                        }
                    } else {
                        System.out.println("No products found with price >= " + x);
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to input new products
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product code:");
        String code = scanner.nextLine();
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price:");
        float price = scanner.nextFloat();
        productList.add(new Product(code, name, price));
        System.out.println("Product added successfully!");
    }

    // Method to output the product list
    public static void output() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product p : productList) {
                System.out.println(p.toString());
            }
        }
    }

    // Method to remove a product by code
    public static void removeByCode(String code) {
        Product productToRemove = null;
        for (Product p : productList) {
            if (p.getCode().equals(code)) {
                productToRemove = p;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product with code " + code + " not found.");
        }
    }

    // Method to sort products by descending price
    public static void sortByPriceDesc() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Float.compare(p2.getPrice(), p1.getPrice());
            }
        });
        System.out.println("Products sorted by descending price.");
    }

    // Method to find a product by code or name
    public static Product findByCodeOrName(String keyword) {
        for (Product p : productList) {
            if (p.getCode().equalsIgnoreCase(keyword) || p.getName().equalsIgnoreCase(keyword)) {
                return p;
            }
        }
        return null;
    }

    // Method to filter products by price
    public static List<Product> filterByPrice(float x) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product p : productList) {
            if (p.getPrice() >= x) {
                filteredProducts.add(p);
            }
        }
        return filteredProducts;
    }
}
