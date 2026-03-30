/**
 * Class Product: Represents an item in the store.
 */
class Product {
    private String productId;
    private String name;
    private double price;
    private int quantityInStock;

    // Constructor to initialize all attributes
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public int getQuantityInStock() { return quantityInStock; }

    public Product(String productId, String name, double price, int quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
        System.out.println("Price updated for " + name + ": $" + price);
    }

    /**
     * Reduces stock if enough quantity exists.
     */
    public void sell(int quantity) {
        if (quantity <= quantityInStock) {
            quantityInStock -= quantity;
            System.out.println("Sold " + quantity + " units of " + name);
        } else {
            System.out.println("Error: Insufficient stock for " + name);
        }
    }

    public void restock(int quantity) {
        quantityInStock += quantity;
        System.out.println("Restocked " + quantity + " units of " + name);
    }

    public void displayProduct() {
        System.out.printf("ID: %-6s | Name: %-12s | Price: $%-7.2f | Stock: %d%n", 
                          productId, name, price, quantityInStock);
    }
}

/**
 * Class Store: Manages the inventory array.
 */
class Store {
    private Product[] inventory;
    private int productCount;

    public Store(int capacity) {
        this.inventory = new Product[capacity];
        this.productCount = 0;
    }

    public void addProduct(Product p) {
        if (productCount < inventory.length) {
            inventory[productCount] = p;
            productCount++;
        } else {
            System.out.println("Inventory full!");
        }
    }

    /**
     * Locates a product by ID and performs a sale.
     */
    public void sellProduct(String productId, int quantity) {
        for (int i = 0; i < productCount; i++) {
            if (inventory[i].getProductId().equals(productId)) {
                inventory[i].sell(quantity);
                return;
            }
        }
        System.out.println("Product ID " + productId + " not found.");
    }

    /**
     * Finds a product by ID and adds to its stock.
     */
    public void restockProduct(String productId, int quantity) {
        for (int i = 0; i < productCount; i++) {
            if (inventory[i].getProductId().equals(productId)) {
                inventory[i].restock(quantity);
                return;
            }
        }
    }

    /**
     * Lists products where quantityInStock < 10.
     */
    public void listLowStock() {
        System.out.println("\n--- LOW STOCK ALERT (Quantity < 10) ---");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (inventory[i].getQuantityInStock() < 10) {
                inventory[i].displayProduct();
                found = true;
            }
        }
        if (!found) System.out.println("All products are well-stocked.");
        System.out.println("---------------------------------------\n");
    }
}

/**
 * Main Class: Executes the store operations.
 */
public class InventorySystem {
    public static void main(String[] args) {
        // 1. Create at least 4 Product objects
        Product p1 = new Product("P001", "Laptop", 1200.00, 15);
        Product p2 = new Product("P002", "Mouse", 25.00, 5);
        Product p3 = new Product("P003", "Keyboard", 45.00, 12);
        Product p4 = new Product("P004", "Monitor", 300.00, 8);

        // 2. Add them to a Store
        Store myStore = new Store(10);
        myStore.addProduct(p1);
        myStore.addProduct(p2);
        myStore.addProduct(p3);
        myStore.addProduct(p4);

        // 3. Sells some products and restocks others
        System.out.println("--- Store Transactions ---");
        myStore.sellProduct("P001", 10); // Laptop stock: 15 -> 5 (Low stock!)
        myStore.sellProduct("P003", 5);  // Keyboard stock: 12 -> 7 (Low stock!)
        myStore.restockProduct("P002", 20); // Mouse stock: 5 -> 25 (No longer low)

        // 4. List all low-stock products
        myStore.listLowStock();
    }
}