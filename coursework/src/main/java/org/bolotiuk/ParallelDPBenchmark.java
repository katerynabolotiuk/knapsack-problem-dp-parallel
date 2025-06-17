package org.bolotiuk;

public class ParallelDPBenchmark {
    public static void main(String[] args) {
        int[] itemCounts = {1000, 5000, 10_000, 50_000};
        int[] capacities = {10_000, 50_000, 100_000, 200_000, 500_000, 1_000_000};
        int[] threadCounts = {4, 9, 12, 16};

        int warmupRuns = 10;
        int measurementRuns = 20;

        System.out.printf("=========================== Number of runs: %d ============================%n", measurementRuns);
        System.out.printf("%-6s | %-8s | %-8s | %-9s | %-9s | %-7s | %-10s%n",
                "N", "W", "Threads", "T_seq, ms", "T_par, ms", "Speedup", "Efficiency");

        for (int n : itemCounts) {
            for (int w : capacities) {
                KnapsackProblem problem = Utils.generateRandomProblem(n, 100, 100, w);

                long seqTimeMs = benchmark(() -> SequentialDPAlgorithm.solve(problem), warmupRuns, measurementRuns);

                for (int threads : threadCounts) {
                    long parTimeMs = benchmark(() -> ParallelDPAlgorithm.solve(problem, threads), warmupRuns, measurementRuns);

                    double speedup = (double) seqTimeMs / parTimeMs;
                    double efficiency = speedup / threads;

                    System.out.printf("%-6d | %-8d | %-8d | %-9d | %-9d | %-7.2f | %-10.2f%n",
                            n, w, threads, seqTimeMs, parTimeMs, speedup, efficiency);
                }
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

