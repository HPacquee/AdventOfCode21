package be.hpacquee.aoc2021;

import java.util.stream.Collectors;

public class Puzzle01 extends AbstractPuzzle {
    public Puzzle01(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public String solvePart1() {
        final int[] count = {0};
        final int[] prev = {-1};
        getPuzzleInput().lines()
                .mapToInt(Integer::parseInt)
                .forEach(value -> {
                    if(prev[0] == -1) {
                        prev[0] = value;
                    }
                    if(prev[0] < value) {
                        count[0]++;
                    }
                    prev[0] = value;
                });
        return count[0] + "";
    }

    @Override
    public String solvePart2() {
        int[] inputs = getPuzzleInput().lines().mapToInt(Integer::parseInt).toArray();
        int count = 0;
        int prev = -1;
        for (int i = 0; i < inputs.length - 2; i++) {
            int sum = inputs[i] + inputs[i + 1] + inputs[i + 2];
            if(prev == -1) {
                prev = sum;
            }
            if(prev < sum) {
                count ++;
            }
            prev = sum;
        }
        return count + "";
    }
}