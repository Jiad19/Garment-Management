package GarmentManagementSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Garment Class and Nested Classes
class Garment {

    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    void updateStock(int quantity) {
        this.stockQuantity = quantity;
    }

    double calculateDiscountPrice(double discountPercentage) {
        double discount = price * (discountPercentage / 100);
        return price - discount;
    }

    static class Fabric {

        public String id;
        public String type;
        public String color;
        public double pricePerMeter;

        public Fabric(String id, String type, String color, double pricePerMeter) {
            this.id = id;
            this.type = type;
            this.color = color;
            this.pricePerMeter = pricePerMeter;
        }

        double calculateCost(double meters) {
            return pricePerMeter * meters;
        }
    }

    static class Supplier {

        public String id;
        public String name;
        public String contactInfo;
        List<Fabric> suppliedFabric = new ArrayList<>();

        public Supplier(String id, String name, String contactInfo) {
            this.id = id;
            this.name = name;
            this.contactInfo = contactInfo;
        }

        void addFabric(Fabric fabric) {
            suppliedFabric.add(fabric);
        }

        List<Fabric> getSuppliedFabrics() {
            return suppliedFabric;
        }
    }

    static class Order {

        public String orderId;
        public Date orderDate;
        public List<Garment> garments = new ArrayList<>();
        private double totalAmount;

        public Order(String orderId, Date orderDate) {
            this.orderId = orderId;
            this.orderDate = orderDate;
        }

        void addGarment(Garment garment) {
            garments.add(garment);
        }

        double calculateTotalAmount() {
            for (Garment g : garments) {
                totalAmount += g.price;
            }
            return totalAmount;
        }

        void printOrderDetails() {
            System.out.println("------------------------------");
            System.out.println("Order Details");
            System.out.println("------------------------------");
            for (Garment g : garments) {
                System.out.println("Name: " + g.name);
                System.out.println("Price: " + g.price);
                System.out.println("Description: " + g.description);
                System.out.println("------------------------------");
            }
        }
    }

    static class Customer {

        public String customerId;
        public String name;
        public String email;
        public String phone;
        List<Order> orders = new ArrayList<>();

        public Customer(String customerId, String name, String email, String phone) {
            this.customerId = customerId;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        void placeOrder(Order order) {
            orders.add(order);
            order.printOrderDetails();
            System.out.println("Order Placed for Customer: " + name);
        }

        List<Order> viewOrders() {
            return orders;
        }
    }

    static class Inventory {

        List<Garment> garments = new ArrayList<>();

        void addGarment(Garment garment) {
            garments.add(garment);
        }

        void removeGarment(String id) {
            garments.removeIf(g -> g.id.equals(id));
        }

        Garment findGarment(String id) {
            for (Garment g : garments) {
                if (g.id.equals(id)) {
                    return g;
                }
            }
            return null;
        }

        void displayAllGarments() {
            for (Garment g : garments) {
                System.out.println("ID: " + g.id + ", Name: " + g.name + ", Price: " + g.price);
            }
        }
    }
}

// Main Class
public class OopLabTask3 {

    public static void main(String[] args) {
        Garment.Inventory inventory = new Garment.Inventory();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Garment Management System ---");
            System.out.println("1. Add Garment");
            System.out.println("2. Remove Garment");
            System.out.println("3. View Garment by ID");
            System.out.println("4. View All Garments");
            System.out.println("5. Apply Discount on Garment");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Garment ID: ");
                    String id = scanner.next();
                    System.out.print("Enter Garment Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Description: ");
                    String description = scanner.next();
                    System.out.print("Enter Size: ");
                    String size = scanner.next();
                    System.out.print("Enter Color: ");
                    String color = scanner.next();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int stockQuantity = scanner.nextInt();

                    Garment newGarment = new Garment(id, name, description, size, color, price, stockQuantity);
                    inventory.addGarment(newGarment);
                    System.out.println("Garment added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Garment ID to Remove: ");
                    String removeId = scanner.next();
                    inventory.removeGarment(removeId);
                    System.out.println("Garment removed successfully (if existed).");
                    break;

                case 3:
                    System.out.print("Enter Garment ID to View: ");
                    String viewId = scanner.next();
                    Garment garment = inventory.findGarment(viewId);

                    if (garment != null) {
                        System.out.println("ID: " + garment.id);
                        System.out.println("Name: " + garment.name);
                        System.out.println("Description: " + garment.description);
                        System.out.println("Size: " + garment.size);
                        System.out.println("Color: " + garment.color);
                        System.out.println("Price: " + garment.price);
                        System.out.println("Stock Quantity: " + garment.stockQuantity);
                    } else {
                        System.out.println("Garment not found.");
                    }
                    break;

                case 4:
                    inventory.displayAllGarments();
                    break;

                case 5:
                    System.out.print("Enter Garment ID to Apply Discount: ");
                    String discountId = scanner.next();
                    Garment discountGarment = inventory.findGarment(discountId);

                    if (discountGarment != null) {
                        System.out.print("Enter discount percentage: ");
                        double discount = scanner.nextDouble();
                        double discountedPrice = discountGarment.calculateDiscountPrice(discount);
                        System.out.println("Discounted Price: " + discountedPrice);
                    } else {
                        System.out.println("Garment not found.");
                    }
                    break;

                case 6:
                    exit = true;
                    System.out.println("Exiting Garment Management System.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
