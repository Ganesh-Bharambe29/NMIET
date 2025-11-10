import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of members
        System.out.print("Enter number of members: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] members = new String[n];
        String[][] borrowedBooks = new String[n][]; // 2D array to store books per member

        // Input member names and their borrowed books
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of member " + (i + 1) + ": ");
            members[i] = sc.nextLine();

            System.out.print("Enter number of books borrowed by " + members[i] + ": ");
            int m = sc.nextInt();
            sc.nextLine(); // consume newline

            borrowedBooks[i] = new String[m]; // initialize array for each member
            for (int j = 0; j < m; j++) {
                System.out.print("Enter book " + (j + 1) + ": ");
                borrowedBooks[i][j] = sc.nextLine();
            }
        }

        // 1. Compute average books borrowed
        int totalBooks = 0;
        for (int i = 0; i < borrowedBooks.length; i++) {
            totalBooks += borrowedBooks[i].length;
        }
        double average = (double) totalBooks / members.length;
        System.out.printf("\nAverage books borrowed per member: %.2f\n", average);

        // 2. Flatten all borrowed books into one array
        String[] allBooks = new String[totalBooks];
        int index = 0;
        for (int i = 0; i < borrowedBooks.length; i++) {
            for (int j = 0; j < borrowedBooks[i].length; j++) {
                allBooks[index++] = borrowedBooks[i][j];
            }
        }

        // 3. Count occurrences of each unique book
        String[] uniqueBooks = new String[allBooks.length];
        int[] counts = new int[allBooks.length];
        int uniqueCount = 0;

        for (int i = 0; i < allBooks.length; i++) {
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueBooks[j].equals(allBooks[i])) {
                    counts[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                uniqueBooks[uniqueCount] = allBooks[i];
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        // 4. Find most and least borrowed books
        if (uniqueCount > 0) {
            String mostBorrowed = uniqueBooks[0];
            String leastBorrowed = uniqueBooks[0];
            int maxCount = counts[0];
            int minCount = counts[0];

            for (int i = 1; i < uniqueCount; i++) {
                if (counts[i] > maxCount) {
                    maxCount = counts[i];
                    mostBorrowed = uniqueBooks[i];
                }
                if (counts[i] < minCount) {
                    minCount = counts[i];
                    leastBorrowed = uniqueBooks[i];
                }
            }

            System.out.println("Most borrowed book: " + mostBorrowed + " (" + maxCount + " times)");
            System.out.println("Least borrowed book: " + leastBorrowed + " (" + minCount + " times)");

            // Most frequently borrowed books (mode)
            System.out.print("Most frequently borrowed book(s): ");
            for (int i = 0; i < uniqueCount; i++) {
                if (counts[i] == maxCount) {
                    System.out.print(uniqueBooks[i] + " ");
                }
            }
            System.out.println();
        } else {
            System.out.println("No books borrowed.");
        }

        // 5. Count members with 0 borrowings
        int zeroBorrow = 0;
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i].length == 0) zeroBorrow++;
        }
        System.out.println("Number of members who borrowed 0 books: " + zeroBorrow);

        sc.close();
    }
}
