package be.hpacquee.aoc2021;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle09 extends AbstractPuzzle {
    private final int[][] ground;
    private final int height;
    private final int width;

    public Puzzle09(String puzzleInput) {
        super(puzzleInput);
        List<String> lines = getPuzzleInput().lines().toList();
        height = lines.size();
        width = lines.get(0).length();
        ground = new int[height][width];
        for (int y = 0; y < height; y++) {
            ground[y] = Arrays.stream(lines.get(y).split("")).mapToInt(Integer::parseInt).toArray();
        }
    }

    @Override
    public String solvePart1() {
        return String.valueOf(getLowestCoordinates()
                .stream()
                .mapToInt(coordinate -> ground[coordinate.y][coordinate.x] + 1)
                .sum());
    }

    @Override
    public String solvePart2() {
        List<Integer> largestBasins = new ArrayList<>(List.of(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE));
        Set<Coordinate> seen = new HashSet<>();
        for (Coordinate coordinate : getLowestCoordinates()) {
            int size = 0;
            LinkedList<Coordinate> queue = new LinkedList<>();
            queue.add(coordinate);
            while (!queue.isEmpty()) {
                Coordinate current = queue.remove();
                if (!seen.add(current)) {
                    continue;
                }
                size++;
                queue.addAll(getHigherNeighbours(current));
            }
            Integer smallestBasin = largestBasins.stream().min(Integer::compareTo).orElse(0);
            if(size >= smallestBasin) {
                largestBasins.remove(smallestBasin);
                largestBasins.add(size);
            }
        }
        return String.valueOf(largestBasins.stream().reduce(1, (a, b) -> a * b));
    }

    private List<Coordinate> getLowestCoordinates() {
        List<Coordinate> coords = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                if (getNeighbours(coordinate).stream().allMatch(neighbour -> neighbour.higherThan(ground, coordinate))) {
                    coords.add(coordinate);
                }
            }
        }
        return coords;
    }

    private Collection<Coordinate> getHigherNeighbours(Coordinate coordinate) {
        return getNeighbours(coordinate).stream()
                .filter(p -> p.higherThan(ground, coordinate))
                .filter(p -> ground[p.y][p.x] != 9)
                .collect(Collectors.toList());
    }

    private List<Coordinate> getNeighbours(Coordinate coordinate) {
        List<Coordinate> coords = new ArrayList<>();
        coords.add(new Coordinate(coordinate.x, coordinate.y - 1));
        coords.add(new Coordinate(coordinate.x, coordinate.y + 1));
        coords.add(new Coordinate(coordinate.x - 1, coordinate.y));
        coords.add(new Coordinate(coordinate.x + 1, coordinate.y));
        coords.removeIf(coord -> coord.x < 0 || coord.x >= width || coord.y < 0 || coord.y >= height);
        return coords;
    }

    private record Coordinate(int x, int y) {
        boolean higherThan(int[][] ground, Coordinate other) {
            return ground[y][x] > ground[other.y][other.x];
        }
    }
}