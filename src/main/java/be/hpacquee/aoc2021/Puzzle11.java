package be.hpacquee.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Puzzle11 extends AbstractPuzzle {

    int[][] field = new int[10][10];
    List<Coordinate> allCoordinates = new ArrayList<>();

    public Puzzle11(String puzzleInput) {
        super(puzzleInput);
        List<String> inputList = getPuzzleInput().lines().toList();
        for (int i = 0; i < 10; i++) {
            field[i] = Arrays.stream(inputList.get(i).split("")).mapToInt(Integer::parseInt).toArray();
        }
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }
    }

    @Override
    public String solvePart1() {
        long totalFlashes = 0L;
        for (int count = 0; count < 100; count++) {
            totalFlashes += cycle();
        }
        return String.valueOf(totalFlashes);
    }

    @Override
    public String solvePart2() {
        long cycles = 1L;
        long flashes = cycle();
        while (flashes != 100) {
            cycles++;
            flashes = cycle();
        }
        return String.valueOf(cycles);
    }

    public long cycle() {
        AtomicLong flashes = new AtomicLong(0L);
        allCoordinates.forEach(coordinate -> coordinate.increment(field));

        List<Coordinate> theyWhoHaveFlashed = new ArrayList<>();

        AtomicBoolean stillFlashing = new AtomicBoolean(false);
        do {
            stillFlashing.set(false);
            allCoordinates.stream()
                    .filter(coordinate -> coordinate.willFlash(field) && !theyWhoHaveFlashed.contains(coordinate))
                    .forEach(coordinate -> {
                        stillFlashing.set(true);
                        flashes.getAndIncrement();
                        theyWhoHaveFlashed.add(coordinate);
                        getNeighbours(coordinate)
                                .forEach(neighbour -> neighbour.increment(field));
                    });
        } while (stillFlashing.get());

        theyWhoHaveFlashed.forEach(coordinate -> coordinate.reset(field));

        return flashes.get();
    }

    private List<Coordinate> getNeighbours(Coordinate coordinate) {
        List<Coordinate> coords = new ArrayList<>();
        coords.add(new Coordinate(coordinate.x, coordinate.y - 1));
        coords.add(new Coordinate(coordinate.x, coordinate.y + 1));
        coords.add(new Coordinate(coordinate.x - 1, coordinate.y));
        coords.add(new Coordinate(coordinate.x + 1, coordinate.y));
        coords.add(new Coordinate(coordinate.x - 1, coordinate.y - 1));
        coords.add(new Coordinate(coordinate.x - 1, coordinate.y + 1));
        coords.add(new Coordinate(coordinate.x + 1, coordinate.y + 1));
        coords.add(new Coordinate(coordinate.x + 1, coordinate.y - 1));
        coords.removeIf(coord -> coord.x < 0 || coord.x >= 10 || coord.y < 0 || coord.y >= 10);
        return coords;
    }

    private record Coordinate(int x, int y) {
        void increment(int[][] field) {
            field[y][x]++;
        }
        void reset(int[][] field) {
            field[y][x] = 0;
        }

        public boolean willFlash(int[][] field) {
            return field[y][x] > 9;
        }
    }
}