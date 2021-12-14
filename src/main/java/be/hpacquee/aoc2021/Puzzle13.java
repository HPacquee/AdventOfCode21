package be.hpacquee.aoc2021;

import java.util.*;

public class Puzzle13 extends AbstractPuzzle {

    private final List<Coordinate> input = new ArrayList<>();
    private final List<Instruction> instructions = new ArrayList<>();

    public Puzzle13(String puzzleInput) {
        super(puzzleInput);
        List<String> lines = getPuzzleInput().lines().toList();
        lines.forEach(s -> {
                    if (!s.isBlank()) {
                        if (!s.startsWith("fold")) {
                            String[] split = s.trim().split(",");
                            input.add(new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                        } else {
                            String replace = s.replace("fold along ", "");
                            String[] split = replace.split("=");
                            instructions.add(new Instruction(split[0], Integer.parseInt(split[1])));
                        }
                    }
                }
            );
    }

    @Override
    public String solvePart1() {
        Set<Coordinate> coordinateSet = new HashSet<>();
        for (Coordinate coordinate : input) {
            Instruction instruction = instructions.get(0);
            if(instruction.direction == "y") {
                Integer firstFold = instruction.value;
                if (coordinate.y > firstFold) {
                    coordinateSet.add(new Coordinate(coordinate.x, (firstFold * 2) - coordinate.y));
                } else {
                    coordinateSet.add(coordinate);
                }
            } else {
                Integer firstFold = instruction.value;
                if (coordinate.x > firstFold) {
                    coordinateSet.add(new Coordinate((firstFold * 2) - coordinate.x, coordinate.y));
                } else {
                    coordinateSet.add(coordinate);
                }
            }
        }

        return String.valueOf(coordinateSet.size());
    }

    private void printField(Collection<Coordinate> input) {
        Coordinate maxX = input.stream().max(Comparator.comparingInt(Coordinate::x)).orElseThrow();
        Coordinate maxY = input.stream().max(Comparator.comparingInt(Coordinate::y)).orElseThrow();
        String [][] field = new String [maxY.y+1][maxX.x+1];
        for (int i = 0; i <= maxY.y; i++) {
            for (int j = 0; j <= maxX.x; j++) {
                field[i][j] = ".";
            }
        }
        input.forEach(coordinate -> field[coordinate.y][coordinate.x] = "#");
        String toString = Arrays.deepToString(field);
        String replace = toString.replace("],", "\n")
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(".", " ");
        System.out.println(replace);
    }

    @Override
    public String solvePart2() {
        Set<Coordinate> coordinateSet = new HashSet<>();
        Set<Coordinate> runningInput = new HashSet<>(input);
        for (Instruction instruction : instructions) {
            for (Coordinate coordinate : runningInput) {
                Integer foldOver = instruction.value;
                if (Objects.equals(instruction.direction, "y")) {
                    if (coordinate.y > foldOver) {
                        coordinateSet.add(new Coordinate(coordinate.x, (foldOver * 2) - coordinate.y));
                    } else {
                        coordinateSet.add(coordinate);
                    }
                } else {
                    if (coordinate.x > foldOver) {
                        coordinateSet.add(new Coordinate((foldOver * 2) - coordinate.x, coordinate.y));
                    } else {
                        coordinateSet.add(coordinate);
                    }
                }
            }
            runningInput = coordinateSet;
            coordinateSet = new HashSet<>();
        }

        printField(runningInput);
        return String.valueOf(runningInput.size());
    }

    public record Coordinate(Integer x, Integer y) { }
    public record Instruction(String direction, Integer value) { }
}