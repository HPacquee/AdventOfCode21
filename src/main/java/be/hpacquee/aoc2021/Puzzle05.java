package be.hpacquee.aoc2021;

import java.util.ArrayList;
import java.util.List;
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
                .flatMap(pipe -> getPointsBetween(pipe).stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() >= 2)
                .count());
    }

    @Override
    public String solvePart2() {
        List<Pipe> pipes = getPuzzleInput().lines().map(Pipe::of).toList();
        return String.valueOf(pipes.stream()
                .flatMap(pipe -> getPointsBetween(pipe, false).stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() >= 2)
                .count());

    }

    record Coordinate(Integer x, Integer y) {}
    record Pipe(Integer x1, Integer y1, Integer x2, Integer y2) {
        static Pipe of(String input) {
            String[] twoParts = input.split(" -> ");
            String[] partOne = twoParts[0].split(",");
            String[] partTwo = twoParts[1].split(",");
            return new Pipe(Integer.parseInt(partOne[0]),
                    Integer.parseInt(partOne[1]),
                    Integer.parseInt(partTwo[0]),
                    Integer.parseInt(partTwo[1]));
        }
    }

    public static List<Coordinate> getPointsBetween(Pipe pipe) {
        return getPointsBetween(pipe, true);
    }

    public static List<Coordinate> getPointsBetween(Pipe pipe, boolean onlyVerticals) {
        int x1 = pipe.x1;
        int x2 = pipe.x2;
        int y1 = pipe.y1;
        int y2 = pipe.y2;
        List<Coordinate> pointsBetween = new ArrayList<>();
        if (x1 == x2 && y1 != y2) {
            int toY = Math.max(y1, y2);
            int fromY = Math.min(y1, y2);
            while (fromY <= toY) {
                pointsBetween.add(new Coordinate(x1, fromY));
                fromY++;
            }
        }

        if (x1 != x2 && y1 == y2) {
            int toX = Math.max(x1, x2);
            int fromX = Math.min(x1, x2);
            while (fromX <= toX) {
                pointsBetween.add(new Coordinate(fromX, y1));
                fromX++;
            }
        }

        if (!onlyVerticals) {

            if (x1 < x2 && y1 < y2) {
                int i = 0;
                while (x1 + i <= x2 && y1 + i <= y2) {
                    pointsBetween.add(new Coordinate(x1 + i, y1 + i));
                    i++;
                }
            }
            if (x1 < x2 && y1 > y2) {
                int i = 0;
                while (x1 + i <= x2 && i <= y1) {
                    pointsBetween.add(new Coordinate(x1 + i, y1 - i));
                    i++;
                }
            }

            if (x1 > x2 && y1 < y2) {
                int i = 0;
                while (i <= x1 && y1 + i <= y2) {
                    pointsBetween.add(new Coordinate(x1 - i, y1 + i));
                    i++;
                }
            }

            if (x1 > x2 && y1 > y2) {
                int i = 0;
                while (x2 + i <= x1 && y2 + i <= y1) {
                    pointsBetween.add(new Coordinate(x1 - i, y1 - i));
                    i++;
                }
            }
        }

        return pointsBetween;
    }
}