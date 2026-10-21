import java.util.Scanner;

public class VirtualLibrarySystem {
    String bid;
    static String[] sections = { "fiction", "technology", "history", "philosophy" };
    String section;
    String book;
    String author;
    int copies = 5;
    static int count = -1;

    static void viewBookSection(VirtualLibrarySystem book[]) {
        if (count == -1) {
            System.out.println(" Sorry No book available ");
            return;
        }
        for (int i = 0; i <= count; i++) {
            if (book[i] != null) {
                System.out.println(" Book name : " + book[i].book + " | section : " + book[i].section + " | copies: "
                        + book[i].copies);
            }
        }
    }

    static void popularBook(VirtualLibrarySystem book[]) {
        if (count == -1) {
            System.out.println(" No books available ");
            return;
        }

        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i <= count; i++) {
            if (book[i] != null && book[i].copies < min) {
                min = book[i].copies;
                index = i;
            }
        }

        if (index != -1) {
            System.out.println(" Most popular book : ");
            System.out.println(book[index].book + " ( Copies left: " + book[index].copies + " )");
        }
    }

    static void issueBook(VirtualLibrarySystem book[], String sec) {

        Scanner sc = new Scanner(System.in);

        int index[] = new int[100];
        int j = 0;

        System.out.println(" Books in " + sec + " : ");

        for (int i = 0; i <= count; i++) {
            if (book[i] != null && book[i].section.equals(sec)) {
                System.out.println((j + 1) + " -> " + book[i].book + " ( Copies: " + book[i].copies + ")");
                index[j] = i;
                j++;
            }
        }

        if (j == 0) {
            System.out.println("No books available in this section");
            return;
        }

        System.out.println("Choose book number : ");
        int choice;

        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input");
            sc.next();
            return;
        }

        if (choice < 1 || choice > j) {
            System.out.println("Invalid choice");
            return;
        }

        int realIndex = index[choice - 1];

        if (book[realIndex].copies > 0) {
            book[realIndex].copies--;
            System.out.println("Book issued");
        } else {
            System.out.println("No copies left");
        }

    }

    static void chooseSec(VirtualLibrarySystem s, int sec) {
        if (sec >= 1 && sec <= sections.length) {
            s.section = sections[sec - 1];
        }
    }

    public static void main(String[] args) {
        System.out.println("----- WELCOME TO VIRTUAL LIBRARY -----");
        Scanner sc = new Scanner(System.in);
        int i = 0, sec;
        VirtualLibrarySystem[] book = new VirtualLibrarySystem[100];
        while (i == 0) {
            System.out.println(
                    "Enter : \n 1 for viewing all books \n 2 for adding books \n 3 for issuing books \n 4 for recommend popular book \n 5 for exit the library");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        viewBookSection(book);
                        break;
                    case 2:
                        System.out.println(
                                "choose section : \n 1 for Fiction \n 2 for Technology \n 3 for history \n 4 for philosophy ");
                        try {
                            sec = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("only enter from given valid option");
                            sc.next();
                            continue;
                        }
                        if (sec < 1 || sec > 4) {
                            System.out.println("Invalid section");
                            continue;
                        }
                        book[++count] = new VirtualLibrarySystem();
                        sc.nextLine();
                        System.out.println("Enter book id : ");
                        book[count].bid = sc.nextLine();
                        System.out.println("Enter book name : ");
                        book[count].book = sc.nextLine();
                        System.out.println("Enter author name : ");
                        book[count].author = sc.nextLine();
                        chooseSec(book[count], sec);
                        System.out.println("NEW BOOK ADDED");
                        break;
                    case 3:
                        System.out.println(
                                "choose section : \n 1 for Fiction \n 2 for Technology \n 3 for history \n 4 for philosophy ");
                        sec = sc.nextInt();
                        if (sec < 1 || sec > 4) {
                            System.out.println("Invalid section");
                            break;
                        }
                        String selectedSection = sections[sec - 1];
                        issueBook(book, selectedSection);

                        break;
                    case 4:
                        popularBook(book);
                        break;
                    case 5:
                        System.out.println("  ....... THANKYOU VISIT AGAIN ....... ");
                        i = 1;
                        break;
                    default:
                        System.out.println(" invalid input enter only from given options ");
                        break;
                }
            } catch (Exception e) {
                System.out.println("enter only numeric value");
                sc.next();
            }
        }
    }
}
