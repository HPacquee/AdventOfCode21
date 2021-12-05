package be.hpacquee.aoc2021;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Puzzle05 extends AbstractPuzzle {
    public Puzzle05(String puzzleInput) {
        super(puzzleInput);
    }

    public static List<Coordinate> getPointsBetween(int x1, int y1, int x2, int y2) {
        List<Coordinate> pointsBetween = new ArrayList<>();
        if(x1 < x2 && y1 < y2) {
            int i = 0;
            while (x1 + i <= x2 && y1+ i <= y2) {
                pointsBetween.add(new Coordinate(x1+i,y1+i));
                i++;
            }
        }
        if(x1 < x2 && y1 > y2) {
            int i = 0;
            while (x1 + i <= x2 && i <= y1) {
                pointsBetween.add(new Coordinate(x1+i,y1-i));
                i++;
            }
        }

        if(x1 > x2 && y1 < y2) {
            int i = 0;
            while (i <= x1 && y1 + i <= y2) {
                pointsBetween.add(new Coordinate(x1-i,y1+i));
                i++;
            }
        }

        if(x1 > x2 && y1 > y2) {
            int i = 0;
            while (x2 + i <= x1 && y2 + i <= y1) {
                pointsBetween.add(new Coordinate(x1-i,y1-i));
                i++;
            }
        }

        return pointsBetween;
    }

    @Override
    public String solvePart1() {
        List<Pipe> pipes = getPuzzleInput().lines().map(Pipe::new).toList();
        Integer maxX1 = pipes.stream().max(Comparator.comparingInt(Pipe::x1)).map(Pipe::x1).get();
        Integer maxX2 = pipes.stream().max(Comparator.comparingInt(Pipe::x2)).map(Pipe::x2).get();
        Integer maxY1 = pipes.stream().max(Comparator.comparingInt(Pipe::y1)).map(Pipe::y1).get();
        Integer maxY2 = pipes.stream().max(Comparator.comparingInt(Pipe::y2)).map(Pipe::y2).get();

        int maxX = Math.max(maxX1, maxX2);
        int maxY = Math.max(maxY1, maxY2);

        int[][] groundMap = new int[maxX + 1][maxY + 1];
        AtomicInteger count = new AtomicInteger(0);

        pipes.forEach(pipe -> {
            if (Objects.equals(pipe.x1, pipe.x2)) {
                int from = Math.min(pipe.y1, pipe.y2);
                int to = Math.max(pipe.y1, pipe.y2);
                for (int i = from; i <= to; i++) {
                    groundMap[i][pipe.x1] += 1;
                    if (groundMap[i][pipe.x1] == 2) {
                        count.getAndIncrement();
                    }
                }
            }
            if (Objects.equals(pipe.y1, pipe.y2)) {
                int from = Math.min(pipe.x1, pipe.x2);
                int to = Math.max(pipe.x1, pipe.x2);

                for (int i = from; i <= to; i++) {
                    groundMap[pipe.y1][i] += 1;
                    if (groundMap[pipe.y1][i] == 2) {
                        count.getAndIncrement();
                    }
                }
            }
        });
        return String.valueOf(count);
    }

    record Coordinate(Integer x, Integer y) {}
    record Pipe(Integer x1, Integer y1, Integer x2, Integer y2) {
        Pipe(String input) {
            this(Integer.parseInt(input.split(" -> ")[0].split(",")[0]),
                    Integer.parseInt(input.split(" -> ")[0].split(",")[1]),
                    Integer.parseInt(input.split(" -> ")[1].split(",")[0]),
                    Integer.parseInt(input.split(" -> ")[1].split(",")[1]));
        }
    }

    @Override
    public String solvePart2() {
        List<Pipe> pipes = getPuzzleInput().lines().map(Pipe::new).toList();
        Integer maxX1 = pipes.stream().max(Comparator.comparingInt(Pipe::x1)).map(Pipe::x1).get();
        Integer maxX2 = pipes.stream().max(Comparator.comparingInt(Pipe::x2)).map(Pipe::x2).get();
        Integer maxY1 = pipes.stream().max(Comparator.comparingInt(Pipe::y1)).map(Pipe::y1).get();
        Integer maxY2 = pipes.stream().max(Comparator.comparingInt(Pipe::y2)).map(Pipe::y2).get();

        int maxX = Math.max(maxX1, maxX2);
        int maxY = Math.max(maxY1, maxY2);

        int[][] groundMap = new int[maxX + 1][maxY + 1];
        AtomicInteger count = new AtomicInteger(0);
        pipes.forEach(pipe -> {
            if (Objects.equals(pipe.x1, pipe.x2)) {
                int from = Math.min(pipe.y1, pipe.y2);
                int to = Math.max(pipe.y1, pipe.y2);
                for (int i = from; i <= to; i++) {
                    groundMap[i][pipe.x1] += 1;
                    if (groundMap[i][pipe.x1] == 2) {
                        count.getAndIncrement();
                    }
                }
            }
            if (Objects.equals(pipe.y1, pipe.y2)) {
                int from = Math.min(pipe.x1, pipe.x2);
                int to = Math.max(pipe.x1, pipe.x2);

                for (int i = from; i <= to; i++) {
                    groundMap[pipe.y1][i] += 1;
                    if (groundMap[pipe.y1][i] == 2) {
                        count.getAndIncrement();
                    }
                }
            }

            if (!Objects.equals(pipe.y1, pipe.y2) && !Objects.equals(pipe.x1, pipe.x2)) {
                List<Coordinate> pointsBetween = getPointsBetween(pipe.x1, pipe.y1, pipe.x2, pipe.y2);
                pointsBetween.forEach(coordinate -> {
                    groundMap[coordinate.y][coordinate.x] += 1;
                    if (groundMap[coordinate.y][coordinate.x] == 2) {
                        count.getAndIncrement();
                    }
                });

            }
        });
        return String.valueOf(count);

    }
}