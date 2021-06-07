package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static char[][] field;
    static int rows;
    static int seats;
    static int totalSeats;
    static int tickets = 0;
    static double percentage;
    static int income = 0;
    static int totalIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        field = arrInit(rows, seats);
        setTotalIncome(rows, seats);

        while (true) {
            System.out.println(
                    "1. Show the seats\n" +
                            "2. Buy a ticket\n" +
                            "3. Statistics\n" +
                            "0. Exit");

            int request = scanner.nextInt();
            if (request == 1) printRoom();
            if (request == 2) buyTicket();
            if (request == 3) showStatistics();
            if (request == 0) break;
        }
    }


    private static void setTotalIncome(int rows, int seats) {
        totalSeats = rows * seats;
        if (totalSeats <= 60) totalIncome = totalSeats * 10;
        if (totalSeats > 60) {
            int front = rows / 2 * seats;
            totalIncome = (front * 10) + ((totalSeats - front) * 8);
        }
    }

    private static void showStatistics() {
        percentage = ((tickets * 1.0) / totalSeats) * 100;

        System.out.printf("" +
                "Number of purchased tickets: %d%n" +
                "Percentage: %.2f%%%n" +
                "Current income: $%d%n" +
                "Total income: $%d%n", tickets, percentage, income, totalIncome);
    }

    private static void buyTicket() {
        while (true) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (seat > seats) {
                System.out.println("Wrong input!");
            } else if (row > rows) {
                System.out.println("Wrong input!");
            } else if (field[row - 1][seat - 1] != 'B') {
                getTicket(row, seat);
                printTicketPrice(rows, seats, row);
                break;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        }
    }

    private static void getTicket(int row, int seat) {
        field[row - 1][seat - 1] = 'B';
        tickets++;

    }

    private static void printTicketPrice(int rows, int seats, int row) {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            System.out.printf("Ticket price: $%d%n", 10);
            income += 10;
        }

        if (totalSeats > 60) {
            int front = (rows / 2);
            int val = row <= front ? 10 : 8;
            System.out.printf("Ticket price: $%d%n", val);
            income += val;
        }

    }

    private static char[][] arrInit(int rows, int seats) {
        char[][] tempArr = new char[rows][seats];
        for (int i = 0; i < tempArr.length; i++) {
            for (int j = 0; j < tempArr[i].length; j++) {
                tempArr[i][j] = 'S';
            }
        }
        return tempArr;
    }

    private static void printRoom() {
        System.out.print("Cinema:\n ");
        for (int i = 0; i < field[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.print("\n");
        for (int i = 0; i < field.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.print("\n");
        }
    }
}