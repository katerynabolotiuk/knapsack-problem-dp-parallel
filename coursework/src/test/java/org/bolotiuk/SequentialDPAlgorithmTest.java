package org.bolotiuk;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SequentialDPAlgorithmTest {
    @Test
    void testKnapsackFromFile100Items() throws IOException {
        String filename = "src/test/resources/knapsack_100_items_100_capacity_all_ones.txt";
        int expectedValue = 100;

        KnapsackProblem problem = Utils.readProblemFromFile(filename);
        int actualValue = SequentialDPAlgorithm.solve(problem);

        assertEquals(expectedValue, actualValue, "Results don't match");
    }

    @Test
    void testKnapsackFromFile1000Items() throws IOException {
        String filename = "src/test/resources/knapsack_1000_items_1000_capacity_all_ones.txt";
        int expectedValue = 1000;

        KnapsackProblem problem = Utils.readProblemFromFile(filename);
        int actualValue = SequentialDPAlgorithm.solve(problem);

        assertEquals(expectedValue, actualValue, "Results don't match");
    }

    @Test
    void testKnapsackFromFile10000Items() throws IOException {
        String filename = "src/test/resources/knapsack_10000_items_10000_capacity_all_ones.txt";
        int expectedValue = 10000;

        KnapsackProblem problem = Utils.readProblemFromFile(filename);
        int actualValue = SequentialDPAlgorithm.solve(problem);

        assertEquals(expectedValue, actualValue, "Results don't match");
    }

    @Test
    void testKnapsackFromFileNoItemsFit() throws IOException {
        String filename = "src/test/resources/knapsack_100_items_no_items_fit.txt";
        int expectedValue = 0;

        KnapsackProblem problem = Utils.readProblemFromFile(filename);
        int actualValue = SequentialDPAlgorithm.solve(problem);

        assertEquals(expectedValue, actualValue, "Results don't match");
    }

    @Test
    void testSmallHandmadeCase3Items() {
        List<Item> items = List.of(
                new Item(1, 2, 3),
                new Item(2, 3, 4),
                new Item(3, 4, 5)
        );
        int capacity = 5;
        int expectedValue = 7;

        KnapsackProblem problem = new KnapsackProblem(items, capacity);
        int result = SequentialDPAlgorithm.solve(problem);
        assertEquals(expectedValue, result);
    }

    @Test
    void testSmallHandmadeCase5Items() {
        List<Item> items = List.of(
                new Item(1, 1, 1),
                new Item(2, 2, 6),
                new Item(3, 3, 10),
                new Item(4, 2, 7),
                new Item(5, 1, 3)
        );

        int capacity = 5;
        int expectedValue = 17;

        KnapsackProblem problem = new KnapsackProblem(items, capacity);
        int result = SequentialDPAlgorithm.solve(problem);
        assertEquals(expectedValue, result);
    }
}

