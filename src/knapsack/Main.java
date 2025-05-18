package knapsack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Give number of items: ");
        int n = sc.nextInt();

        System.out.print("Give seed: ");
        long seed = sc.nextLong();

        System.out.print("Give knapsack capacity: ");
        int C = sc.nextInt();

        Problem p = new Problem(n, seed, 1, 10);
        for (int i = 0; i < n; i++) {
            Item it = p.getItems().get(i);
            System.out.printf("No: %d v: %2d w: %2d%n", i, it.value(), it.weight());
        }
        System.out.println("---------");

        Result r = p.solve(C);
        System.out.println(r);
    }
}
