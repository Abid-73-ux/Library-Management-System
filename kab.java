import java.util.Scanner;

public class kab {

    static Scanner sc = new Scanner(System.in);

    static final int MAX_BOOKS = 100;
    static final int MAX_USERS = 50;
    static final int MAX_BORROWED = 5;

    static String[] bookIds = new String[MAX_BOOKS];
    static String[] bookTitles = new String[MAX_BOOKS];
    static String[] bookAuthors = new String[MAX_BOOKS];
    static boolean[] isBorrowed = new boolean[MAX_BOOKS];
    static int bookCount = 0;

    static String[] usernames = new String[MAX_USERS];
    static String[] passwords = new String[MAX_USERS];
    static int userCount = 0;

    static String[][] borrowedBooks = new String[MAX_USERS][MAX_BORROWED];

    public static void main(String[] args) {
        usernames[userCount] = "user1";
        passwords[userCount] = "1234";
        userCount++;

        boolean running = true;
        while (running) {
            System.out.println("\n=== Welcome to Library Management System ===");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                adminLogin();
            } else if (choice == 2) {
                userMenu();
            } else if (choice == 3) {
                running = false;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    static void adminLogin() {
        System.out.print("Enter admin username: ");
        String name = sc.nextLine();
        System.out.print("Enter admin password: ");
        String pass = sc.nextLine();

        if (name.equals("admin") && pass.equals("admin123")) {
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. View Users");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                viewBooks();
            } else if (choice == 3) {
                searchBook();
            } else if (choice == 4) {
                removeBook();
            } else if (choice == 5) {
                viewUsers();
            } else if (choice == 6) {
                back = true;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    static void userMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- User Section ---");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                loginUser();
            } else if (choice == 2) {
                signUpUser();
            } else if (choice == 3) {
                back = true;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    static void addBook() {
        if (bookCount < MAX_BOOKS) {
            System.out.print("Enter Book ID: ");
            bookIds[bookCount] = sc.nextLine();
            System.out.print("Enter Title: ");
            bookTitles[bookCount] = sc.nextLine();
            System.out.print("Enter Author: ");
            bookAuthors[bookCount] = sc.nextLine();
            isBorrowed[bookCount] = false;
            bookCount++;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library full. Cannot add more books.");
        }
    }

    static void viewBooks() {
        if (bookCount == 0) {
            System.out.println("No books in library.");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.println((i + 1) + ". ID: " + bookIds[i] + ", Title: " + bookTitles[i] + ", Author: " + bookAuthors[i] + ", Borrowed: " + isBorrowed[i]);
            }
        }
    }

    static void searchBook() {
        System.out.print("Enter title to search: ");
        String title = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (bookTitles[i].equalsIgnoreCase(title)) {
                System.out.println("Found -> ID: " + bookIds[i] + ", Title: " + bookTitles[i] + ", Author: " + bookAuthors[i] + ", Borrowed: " + isBorrowed[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        String id = sc.nextLine();
        int index = -1;
        for (int i = 0; i < bookCount; i++) {
            if (bookIds[i].equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1 && !isBorrowed[index]) {
            for (int i = index; i < bookCount - 1; i++) {
                bookIds[i] = bookIds[i + 1];
                bookTitles[i] = bookTitles[i + 1];
                bookAuthors[i] = bookAuthors[i + 1];
                isBorrowed[i] = isBorrowed[i + 1];
            }
            bookCount--;
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book cannot be removed (not found or currently borrowed).");
        }
    }

    static void viewUsers() {
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + usernames[i]);
        }
    }

    static void loginUser() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        int index = -1;
        for (int i = 0; i < userCount; i++) {
            if (usernames[i].equals(name) && passwords[i].equals(pass)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            userLoggedInMenu(index);
        } else {
            System.out.println("Invalid login.");
        }
    }

    static void signUpUser() {
        if (userCount < MAX_USERS) {
            System.out.print("Enter new username: ");
            String name = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();
            usernames[userCount] = name;
            passwords[userCount] = pass;
            userCount++;
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User limit reached.");
        }
    }

    static void userLoggedInMenu(int userIndex) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Available Books");
            System.out.println("4. View My Borrowed Books");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                borrowBook(userIndex);
            } else if (choice == 2) {
                returnBook(userIndex);
            } else if (choice == 3) {
                viewAvailableBooks();
            } else if (choice == 4) {
                viewMyBorrowed(userIndex);
            } else if (choice == 5) {
                changePassword(userIndex);
            } else if (choice == 6) {
                back = true;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    static void borrowBook(int userIndex) {
        System.out.print("Enter Book ID to borrow: ");
        String id = sc.nextLine();
        int index = -1;
        for (int i = 0; i < bookCount; i++) {
            if (bookIds[i].equals(id) && !isBorrowed[i]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int j = 0; j < MAX_BORROWED; j++) {
                if (borrowedBooks[userIndex][j] == null) {
                    borrowedBooks[userIndex][j] = id;
                    isBorrowed[index] = true;
                    System.out.println("Book borrowed successfully.");
                    return;
                }
            }
            System.out.println("Borrow limit reached.");
        } else {
            System.out.println("Book not available.");
        }
    }

    static void returnBook(int userIndex) {
        System.out.print("Enter Book ID to return: ");
        String id = sc.nextLine();
        for (int j = 0; j < MAX_BORROWED; j++) {
            if (borrowedBooks[userIndex][j] != null && borrowedBooks[userIndex][j].equals(id)) {
                borrowedBooks[userIndex][j] = null;
                for (int i = 0; i < bookCount; i++) {
                    if (bookIds[i].equals(id)) {
                        isBorrowed[i] = false;
                        break;
                    }
                }
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("You have not borrowed this book.");
    }

    static void viewAvailableBooks() {
        for (int i = 0; i < bookCount; i++) {
            if (!isBorrowed[i]) {
                System.out.println("ID: " + bookIds[i] + ", Title: " + bookTitles[i] + ", Author: " + bookAuthors[i]);
            }
        }
    }

    static void viewMyBorrowed(int userIndex) {
        boolean empty = true;
        for (int j = 0; j < MAX_BORROWED; j++) {
            if (borrowedBooks[userIndex][j] != null) {
                System.out.println("Borrowed Book ID: " + borrowedBooks[userIndex][j]);
                empty = false;
            }
        }
        if (empty) {
            System.out.println("No borrowed books.");
        }
    }

    static void changePassword(int userIndex) {
        System.out.print("Enter new password: ");
        String pass = sc.nextLine();
        passwords[userIndex] = pass;
        System.out.println("Password changed successfully.");
    }
}
