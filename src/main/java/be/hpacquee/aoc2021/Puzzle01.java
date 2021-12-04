package be.hpacquee.aoc2021;

import java.util.concurrent.atomic.AtomicInteger;

public class Puzzle01 extends AbstractPuzzle {
    public Puzzle01(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        final AtomicInteger count = new AtomicInteger(0);
        final AtomicInteger prev = new AtomicInteger(Integer.MAX_VALUE);
        getPuzzleInput().lines()
                .mapToInt(Integer::parseInt)
                .forEach(value -> {
                    if(prev.get() < value) {
                        count.incrementAndGet();
                    }
                    prev.set(value);
                });
        return String.valueOf(count.get());
    }

    @Override
    public String solvePart2() {
        int[] inputs = getPuzzleInput().lines().mapToInt(Integer::parseInt).toArray();
        final AtomicInteger count = new AtomicInteger(0);
        final AtomicInteger prev = new AtomicInteger(Integer.MAX_VALUE);
        for (int i = 0; i < inputs.length - 2; i++) {
            int sum = inputs[i] + inputs[i + 1] + inputs[i + 2];
            if(prev.get() < sum) {
                count.incrementAndGet();
            }
            prev.set(sum);
        }
        return count + "";
    }
}