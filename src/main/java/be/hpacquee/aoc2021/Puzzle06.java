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
        for (int days = 0; days < 80; days++) {
            List<Integer> newLanternFish = new ArrayList<>();
            for (int i = 0; i < lanternfish.size(); i++) {
                Integer fish = lanternfish.get(i);
                if(fish == 0) {
                    newLanternFish.add(8);
                    fish=6;
                } else {
                    fish--;
                }
                lanternfish.set(i, fish);
            }
            lanternfish.addAll(newLanternFish);
        }
        return String.valueOf(lanternfish.size());
    }

    @Override
    public String solvePart2() {
        List<Integer> lanternfish = Arrays.stream(getPuzzleInput().lines().toList().get(0).split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return reproduceFish(lanternfish);
    }

    private String reproduceFish(List<Integer> lanternfish) {
        try {
            reproduce(lanternfish.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())), 1);
        } catch (DoneLooping dl) {
            return String.valueOf(dl.totalCount);
        }
        return "";
    }

    private void reproduce(Map<Integer, Long> fishCount, int day) throws DoneLooping{
        if(day > 256) {
            throw new DoneLooping(fishCount.values().stream().reduce(Long::sum).get());
        }
        Map<Integer, Long> newMap = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            newMap.put(i-1, fishCount.getOrDefault(i, 0L));
        }
        newMap.put(8, fishCount.getOrDefault(0, 0L));
        newMap.put(6, newMap.getOrDefault(6, 0L) + fishCount.getOrDefault(0, 0L));
        reproduce(newMap, day+1);
    }

    private static class DoneLooping extends Throwable{
        Long totalCount;

        public DoneLooping(Long totalCount) {
            this.totalCount = totalCount;
        }
    }
}