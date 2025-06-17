package org.bolotiuk;

import java.util.List;

public class SequentialDPAlgorithm {

    public static int solve(KnapsackProblem problem) {
        List<Item> items = problem.getItems();
        int w = problem.getCapacity();
        int n = items.size();

        int[] prev = new int[w + 1];
        int[] curr = new int[w + 1];

        for (int i = 1; i <= n; i++) {
            Item currentItem = items.get(i - 1);
            int wi = currentItem.getWeight();
            int pi = currentItem.getValue();

            for (int j = 0; j <= w; j++) {
                if (wi > j) {
                    curr[j] = prev[j];
                } else {
                    curr[j] = Math.max(
                            prev[j],
                            prev[j - wi] + pi
                    );
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[w];
    }
}