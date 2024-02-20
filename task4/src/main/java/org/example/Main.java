package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static int minMovesToEqualize(ArrayList<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return 0;
        }

        Collections.sort(nums);
        int medianIndex = nums.size() / 2;
        int median = nums.get(medianIndex);

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }

        String inputFile = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            ArrayList<Integer> nums = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                nums.add(Integer.parseInt(line.trim()));
            }

            int minMoves = minMovesToEqualize(nums);
            System.out.println(minMoves);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input format: " + e.getMessage());
        }
    }
}
