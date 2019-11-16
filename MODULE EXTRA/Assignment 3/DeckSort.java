import java.util.Scanner;

public class DeckSort {
    public int check(String str) {
        int sum = 1;

        if (str.charAt(0) == 'A') {
            sum = sum * 1;
        } else if (str.charAt(0) == 'T') {
            sum = sum * 10;
        } else if (str.charAt(0) == 'J') {
            sum = sum * 11;
        } else if (str.charAt(0) == 'Q') {
            sum = sum * 12;
        } else if (str.charAt(0) == 'K') {
            sum = sum * 13;
        } else {
            sum = (sum * ((int) str.charAt(0))) - 48;
        }

        if (str.charAt(1) == 'D') {
            sum = sum * 90000;
        } else if (str.charAt(1) == 'C') {
            sum = sum * 6900;
        } else if (str.charAt(1) == 'H') {
            sum = sum * 500;
        } else if (str.charAt(1) == 'S') {
            sum = sum * 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DeckSort ds = new DeckSort();

        try {
            System.out.println("How many cards you want to check : ");
            int n = sc.nextInt();

            String[] strArr = new String[n];

            System.out.println("---------------------------------------------");
            System.out.println("Conditions for card value :");
            System.out.println("for 1st char only 1-9 and T(10) J Q K allowed");
            System.out.println("for 2nd char only S H C D allowed");
            System.out.println("---------------------------------------------");

            System.out.println("Enter the card values : ");
            for (int i = 0; i < n; i++) {
                strArr[i] = sc.next();
            }

            String First = "A23456789TJQK";
            String Second = "SHCD";

            int flag = 0;
            for (int i = 0; i < n; i++) {
                if ((First.indexOf(strArr[i].charAt(0)) == -1) || (Second.indexOf(strArr[i].charAt(1)) == -1)) {
                    flag = 1;
                }
            }

            if (flag == 0) {
                for (int i = 1; i < n; i++) {
                    for (int j = i; j > 0; j--) {
                        if (ds.check(strArr[j]) < ds.check(strArr[j - 1])) {
                            break;
                        } else {
                            String temp = strArr[j];
                            strArr[j] = strArr[j - 1];
                            strArr[j - 1] = temp;
                        }
                    }
                }
                System.out.println("Values after sorted :");
                for (int i = 0; i < n; i++) {
                    System.out.println(strArr[i]);
                }

            } else {
                System.out.println();
                System.out.println("Card value is not according to rules");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Only integer value is allowed");
        }
    }
}