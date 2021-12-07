package be.hpacquee.aoc2021;

import java.util.Arrays;

public class Puzzle07 extends AbstractPuzzle {
    public Puzzle07(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        int[] ints = Arrays.stream(getPuzzleInput().lines().toList().get(0).split(","))
                .map(s -> s.replace(" ", ""))
                .mapToInt(Integer::parseInt).toArray();
        Arrays.sort(ints);
        double median;
        if (ints.length % 2 == 0)
            median = ((double)ints[ints.length/2] + (double)ints[ints.length/2 - 1])/2;
        else
            median = (double) ints[ints.length/2];
        long totalFuel = 0L;
        for (int anInt : ints) {
            totalFuel += Math.abs(anInt - median);
        }

        return String.valueOf(totalFuel);
    }

    @Override
    public String solvePart2() {
        int[] ints = Arrays.stream(getPuzzleInput().lines().toList().get(0).split(","))
                .map(s -> s.replace(" ", ""))
                .mapToInt(Integer::parseInt).toArray();
        int max = Arrays.stream(ints).max().getAsInt();
        int min = Arrays.stream(ints).min().getAsInt();
        int minimumFuel = Integer.MAX_VALUE;
        for (int i = min; i <= max ; i++) {
            int totalFuel = 0;
            for (int anInt : ints) {
                int diff = Math.abs(anInt - i);
                totalFuel += (diff * (diff + 1)) /2;
            }
            if(totalFuel<minimumFuel) {
                minimumFuel = totalFuel;
            }
        }

        return String.valueOf(minimumFuel);
    }
}