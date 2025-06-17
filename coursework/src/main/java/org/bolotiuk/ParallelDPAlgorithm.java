package org.bolotiuk;
import java.util.concurrent.*;

import java.util.List;

public class ParallelDPAlgorithm {

    private static final int THRESHOLD = 1000;

    public static int solve(KnapsackProblem problem, int threads) {
        List<Item> items = problem.getItems();
        int w = problem.getCapacity();
        int n = items.size();

        int[] prev = new int[w + 1];
        int[] curr = new int[w + 1];

        ForkJoinPool pool = new ForkJoinPool(threads);

        for (int i = 1; i <= n; i++) {
            Item currentItem = items.get(i - 1);
            int wi = currentItem.getWeight();
            int pi = currentItem.getValue();

            pool.invoke(new ComputeTask(prev, curr, wi, pi, 0, w + 1));

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        pool.shutdown();

        return prev[w];
    }

    private static class ComputeTask extends RecursiveAction {
        private final int[] prev;
        private final int[] curr;
        private final int wi;
        private final int pi;
        private final int start;
        private final int end;

        public ComputeTask(int[] prev, int[] curr, int wi, int pi, int start, int end) {
            this.prev = prev;
            this.curr = curr;
            this.wi = wi;
            this.pi = pi;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                for (int j = start; j < end; j++) {
                    if (wi > j) {
                        curr[j] = prev[j];
                    } else {
                        curr[j] = Math.max(prev[j], prev[j - wi] + pi);
                    }
                }
            } else {
                int mid = start + length / 2;
                ComputeTask left = new ComputeTask(prev, curr, wi, pi, start, mid);
                ComputeTask right = new ComputeTask(prev, curr, wi, pi, mid, end);
                invokeAll(left, right);
            }
        }
    }
}



