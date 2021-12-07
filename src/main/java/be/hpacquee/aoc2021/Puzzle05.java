package be.hpacquee.aoc2021;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Puzzle05 extends AbstractPuzzle {
    public Puzzle05(String puzzleInput) {
        super(puzzleInput);
    }


    @Override
    public String solvePart1() {
        List<Pipe> pipes = getPuzzleInput().lines().map(Pipe::of).toList();
        return String.valueOf(pipes.stream()
                .filter(Pipe::isVerticalOrHorizontal)
                .flatMap(pipe -> pipe.getCoordinates().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream().filter(value -> value >= 2)
                .count());
    }

    @Override
    public String solvePart2() {
        List<Pipe> pipes = getPuzzleInput().lines().map(Pipe::of).toList();
        return String.valueOf(pipes.stream()
                .flatMap(pipe -> pipe.getCoordinates().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream().filter(value -> value >= 2)
                .count());

    }

    record Coordinate(Integer x, Integer y) {
    }

    public record Pipe(Integer x1, Integer y1, Integer x2, Integer y2) {
        public static Pipe of(String input) {
            String[] twoParts = input.split(" -> ");
            String[] partOne = twoParts[0].split(",");
            String[] partTwo = twoParts[1].split(",");
            return new Pipe(Integer.parseInt(partOne[0]),
                    Integer.parseInt(partOne[1]),
                    Integer.parseInt(partTwo[0]),
                    Integer.parseInt(partTwo[1]));
        }

        public boolean isVerticalOrHorizontal() {
            return Objects.equals(x1, x2) || Objects.equals(y1, y2);
        }

        public Set<Coordinate> getCoordinates() {
            Set<Coordinate> coords = new HashSet<Coordinate>();
            int dx = Integer.signum(x2 - x1);
            int dy = Integer.signum(y2 - y1);
            int x = x1;
            int y = y1;
            while (x != x2 + dx || y != y2 + dy) {
                coords.add(new Coordinate(x, y));
                x += dx;
                y += dy;
            }
            return coords;
        }
    }
}