package org.bolotiuk;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParallelDPAlgorithmTest {

    private final int threads = Runtime.getRuntime().availableProcessors();

    @Test
    public void testGeneratedKnapsack1000Items() {
        KnapsackProblem problem = Utils.generateRandomProblem(1000, 100, 200, 10000);
        int resultSequential = SequentialDPAlgorithm.solve(problem);
        int resultParallel = ParallelDPAlgorithm.solve(problem, threads);
        assertEquals(resultSequential, resultParallel, "Results don't match");
    }

    @Test
    public void testGeneratedKnapsack5000Items() {
        KnapsackProblem problem = Utils.generateRandomProblem(5000, 100, 200, 10000);
        int expected = SequentialDPAlgorithm.solve(problem);
        int actual = ParallelDPAlgorithm.solve(problem, threads);
        assertEquals(expected, actual, "Results don't match");
    }

    @Test
    public void testGeneratedKnapsack10000Items() {
        KnapsackProblem problem = Utils.generateRandomProblem(10000, 30, 50, 10000);
        int expected = SequentialDPAlgorithm.solve(problem);
        int actual = ParallelDPAlgorithm.solve(problem, threads);
        assertEquals(expected, actual, "Results don't match");
    }

    @Test
    void testKnapsackNoItemsFit() throws IOException {
        String filename = "src/test/resources/knapsack_100_items_no_items_fit.txt";
        KnapsackProblem problem = Utils.readProblemFromFile(filename);

        int expected = SequentialDPAlgorithm.solve(problem);
        int actual = ParallelDPAlgorithm.solve(problem, threads);
        assertEquals(expected, actual, "Results don't match");
    }
}