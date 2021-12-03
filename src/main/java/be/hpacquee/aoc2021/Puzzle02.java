package be.hpacquee.aoc2021;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle02 extends AbstractPuzzle {
    public Puzzle02(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String solvePart1() {
        List<String> collect = getPuzzleInput().lines().collect(Collectors.toList());
        int horizontal = 0;
        int depth = 0;
        for (String input :
                collect) {
            if (input.contains("forward"))
                horizontal += Integer.parseInt(input.split(" ")[1]);
            if (input.contains("down"))
                depth += Integer.parseInt(input.split(" ")[1]);
            if (input.contains("up"))
                depth -= Integer.parseInt(input.split(" ")[1]);
        }
        return horizontal * depth + "";
    }

    @Override
    public String solvePart2() {
        List<String> collect = getPuzzleInput().lines().collect(Collectors.toList());
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String input :
                collect) {
            if (input.contains("forward")) {
                horizontal += Integer.parseInt(input.split(" ")[1]);
                depth += aim * Integer.parseInt(input.split(" ")[1]);
            }
            if (input.contains("down"))
                aim += Integer.parseInt(input.split(" ")[1]);
            if (input.contains("up"))
                aim -= Integer.parseInt(input.split(" ")[1]);
        }
        return horizontal * depth + "";

    }
}