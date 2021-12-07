package be.hpacquee.aoc2021;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Puzzle06 extends AbstractPuzzle {
    public Puzzle06(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        Map<Integer, Long> initialFishCount = Arrays.stream(getPuzzleInput().split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return reproduce(initialFishCount, 1, 80);
    }

    @Override
    public String solvePart2() {
        Map<Integer, Long> initialFishCount = Arrays.stream(getPuzzleInput().split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return reproduce(initialFishCount, 1, 256);
    }

    private String reproduce(Map<Integer, Long> fishCount, int day, int days) {
        if (day <= days) {
            Map<Integer, Long> newFishCount = new HashMap<>();
            for (int i = 1; i <= 8; i++) {
                newFishCount.put(i - 1, fishCount.getOrDefault(i, 0L));
            }
            newFishCount.put(8, fishCount.getOrDefault(0, 0L));
            newFishCount.put(6, newFishCount.getOrDefault(6, 0L) + fishCount.getOrDefault(0, 0L));
            return reproduce(newFishCount, day + 1, days);
        } else {
            return String.valueOf(fishCount.values().stream().reduce(Long::sum).get());
        }
    }
}