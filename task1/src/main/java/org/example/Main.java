package org.example;

public class Main {
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("Enter 2 numbers");
            return;
        }
     int n = Integer.parseInt(args[0]);
     int m = Integer.parseInt(args[1]);

        if (n <= 0 || m <= 0) {
            System.out.println("Numbers have to be positive integers.");
            return;
        }

     StringBuilder path = new StringBuilder();
     int current = 1;
        for (int i = 0; i < n; i++) {
            path.append(current);
            current = (current+m-1)%n+1;
        }
        System.out.println(path.toString());
    }
}