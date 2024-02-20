package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }

        double[] circleData = readDataFromFile(args[0]);
        double centerX = circleData[0];
        double centerY = circleData[1];
        double radius = circleData[2];

        double[][] points = readPointsFromFile(args[1]);

        for (double[] point : points) {
            double distance = calculateDistance(centerX, centerY, point[0], point[1]);
            int result = getResult(distance, radius);
            System.out.println(result);
        }
    }

    private static double[] readDataFromFile(String fileName) {
        double[] data = new double[3];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            for (int i = 0; i < 3; i++) {
                data[i] = Double.parseDouble(parts[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static double[][] readPointsFromFile(String fileName) {
        double[][] points = new double[100][2];
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                for (int i = 0; i < 2; i++) {
                    points[index][i] = Double.parseDouble(parts[i]);
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static int getResult(double distance, double radius) {
        if (distance == radius) {
            return 0; // т. лежит на окружности
        } else if (distance < radius) {
            return 1; // т. внутри окружности
        } else {
            return 2; // т. снаружи окружности
        }
    }

}