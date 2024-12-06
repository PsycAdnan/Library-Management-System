import java.util.*;

abstract class LibraryItem {
    private String title;
    private String author;
    private String category;
    private String isAvailable; // Changed to "Yes" or "No"
    private int overdueDays; // To track overdue days for fines

    public LibraryItem(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = "Yes"; // Default to available
        this.overdueDays = 0; // No overdue days initially
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String isAvailable() {
        return isAvailable;
    }

    public void checkOut() {
        isAvailable = "No";
    }

    public void returnItem() {
        isAvailable = "Yes";
    }

    public void setOverdueDays(int days) {
        this.overdueDays = days;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public double calculateFine() {
        // Assuming a fine of 2 units per overdue day
        return overdueDays * 2.0;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Category: " + category + ", Available: " + isAvailable + 
               (overdueDays > 0 ? ", Overdue Days: " + overdueDays + ", Fine: " + calculateFine() : "");
    }
}

class Book extends LibraryItem {
    public Book(String title, String author, String category) {
        super(title, author, category);
    }
}

class Magazine extends LibraryItem {
    public Magazine(String title, String author, String category) {
        super(title, author, category);
    }
}

class DVD extends LibraryItem {
    public DVD(String title, String author, String category) {
        super(title, author, category);
    }
}

class Library {
    private List<LibraryItem> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Item added successfully.");
    }

    public void checkOutItem(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title) && item.isAvailable().equals("Yes")) {
                item.checkOut();
                System.out.println("Item checked out successfully.");
                return;
            }
        }
        System.out.println("Item not found or unavailable.");
    }

    public void returnItem(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title) && item.isAvailable().equals("No")) {
                item.returnItem();
                System.out.println("Item returned successfully.");
                return;
            }
        }
        System.out.println("Item not found or not checked out.");
    }

    public void searchByTitle(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                System.out.println(item);
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void searchByAuthor(String author) {
        for (LibraryItem item : items) {
            if (item.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(item);
            }
        }
    }

    public void searchByCategory(String category) {
        for (LibraryItem item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
            }
        }
    }

    public void showAvailableItems() {
        boolean found = false;
        for (LibraryItem item : items) {
            if (item.isAvailable().equals("Yes")) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available items.");
        }
    }

    public void setOverdueDays(String title, int days) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                item.setOverdueDays(days);
                System.out.println("Overdue days set for " + title);
                return;
            }
        }
        System.out.println("Item not found.");
    }
}

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Item");
            System.out.println("2. Check Out Item");
            System.out.println("3. Return Item");
            System.out.println("4. Search Item");
            System.out.println("5. Show Available Books");
            System.out.println("6. Set Overdue Days for Fine");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    checkOutItem();
                    break;
                case 3:
                    returnItem();
                    break;
                case 4:
                    searchItem();
                    break;
                case 5:
                    library.showAvailableItems();
                    break;
                case 6:
                    setOverdueDays();
                    break;
                case 7:
                    System.out.println("Thank you for using the Library Management System.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter type (Book/Magazine/DVD): ");
        String type = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        switch (type.toLowerCase()) {
            case "book":
                library.addItem(new Book(title, author, category));
                break;
            case "magazine":
                library.addItem(new Magazine(title, author, category));
                break;
            case "dvd":
                library.addItem(new DVD(title, author, category));
                break;
            default:
                System.out.println("Invalid type. Item not added.");
        }
    }

    private static void checkOutItem() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        library.checkOutItem(title);
    }

    private static void returnItem() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        library.returnItem(title);
    }

    private static void searchItem() {
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Category");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                library.searchByTitle(title);
                break;
            case 2:
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                library.searchByAuthor(author);
                break;
            case 3:
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                library.searchByCategory(category);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void setOverdueDays() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter overdue days: ");
        int days = scanner.nextInt();
        library.setOverdueDays(title, days);
    }
}
