package org.bolotiuk;

import java.io.*;
import java.util.*;

public class Utils {

    public static KnapsackProblem readProblemFromFile(String filename) throws IOException {
        List<Item> items = new ArrayList<>();
        int capacity = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            if ((line = br.readLine()) != null) {
                capacity = Integer.parseInt(line.trim());
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0]);
                    int weight = Integer.parseInt(parts[1]);
                    int value = Integer.parseInt(parts[2]);
                    items.add(new Item(id, weight, value));
                }
            }
        }

        return new KnapsackProblem(items, capacity);
    }


    public static KnapsackProblem generateRandomProblem(int itemCount, int maxItemWeight, int maxItemValue, int capacity) {
        Random rand = new Random();
        List<Item> items = new ArrayList<>();

        for (int i = 1; i <= itemCount; i++) {
            int weight = 1 + rand.nextInt(maxItemWeight);
            int value = 1 + rand.nextInt(maxItemValue);
            items.add(new Item(i, weight, value));
        }
        return new KnapsackProblem(items, capacity);
    }

}

