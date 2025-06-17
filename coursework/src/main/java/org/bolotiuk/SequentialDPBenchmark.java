package org.bolotiuk;

public class SequentialDPBenchmark {
    public static void main(String[] args) {
        int[] itemCounts = {100, 500, 1000, 2000, 5000, 10000, 15000, 20000, 50000, 100000};
        int[] capacities = {100, 500, 1000, 2000, 5000, 10000, 15000, 20000, 50000, 100000};

        int warmupRuns = 10;
        int measurementRuns = 20;

        System.out.printf("============= Number of runs: %d =============\n", measurementRuns);
        for (int n : itemCounts) {
            for (int w : capacities) {
                KnapsackProblem problem = Utils.generateRandomProblem(n, 100, 100, w);

                long avgTimeMs = benchmark(() -> SequentialDPAlgorithm.solve(problem), warmupRuns, measurementRuns);

                System.out.printf("N = %-6d | W = %-6d | avg. time = %5d ms%n", n, w, avgTimeMs);

            }
        }
    }

    private static long benchmark(Runnable task, int warmupRuns, int measurementRuns) {
        for (int i = 0; i < warmupRuns; i++) {
            task.run();
        }

        long total = 0;
        for (int i = 0; i < measurementRuns; i++) {
            long start = System.nanoTime();
            task.run();
            long end = System.nanoTime();
            total += (end - start);
        }

        return total / measurementRuns / 1_000_000;
    }
}
