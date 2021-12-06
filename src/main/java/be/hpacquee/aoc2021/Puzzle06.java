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
        List<Integer> lanternfish = Arrays.stream(getPuzzleInput().lines().toList().get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return reproduceFish(lanternfish, 80);
    }

    @Override
    public String solvePart2() {
        List<Integer> lanternfish = Arrays.stream(getPuzzleInput().lines().toList().get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return reproduceFish(lanternfish, 256);
    }

    private String reproduceFish(List<Integer> lanternfish, int days) {
        return String.valueOf(reproduce(lanternfish.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())), 1, days));
    }

    private Long reproduce(Map<Integer, Long> fishCount, int day, int days) {
        if(day <= days) {
            Map<Integer, Long> newMap = new HashMap<>();
            for (int i = 1; i <= 8; i++) {
                newMap.put(i - 1, fishCount.getOrDefault(i, 0L));
            }
            newMap.put(8, fishCount.getOrDefault(0, 0L));
            newMap.put(6, newMap.getOrDefault(6, 0L) + fishCount.getOrDefault(0, 0L));
            return reproduce(newMap, day + 1, days);
        } else {
            return fishCount.values().stream().reduce(Long::sum).get();
        }
    }
}