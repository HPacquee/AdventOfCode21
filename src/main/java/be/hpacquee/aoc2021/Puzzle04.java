package be.hpacquee.aoc2021;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Puzzle04 extends AbstractPuzzle {
    public Puzzle04(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        List<String> strings = getPuzzleInput().lines().collect(Collectors.toList());
        List<Board> boards = getBoards(strings);
        AtomicInteger score = new AtomicInteger(-1);
        for (Integer number  : extractDrawnNumber(strings.get(0))) {
            if (score.get() != -1) {
                return String.valueOf(score.get());
            }
            boards.stream()
                    .filter(board -> board.allNumbers.contains(number))
                    .forEach(board -> score.set(board.crossNumber(number)));
        }
        return "No winners today";
    }

    @Override
    public String solvePart2() {
        List<String> strings = getPuzzleInput().lines().collect(Collectors.toList());
        List<Board> boards = getBoards(strings);
        AtomicInteger winner = new AtomicInteger(-1);
        for (Integer number : extractDrawnNumber(strings.get(0))) {
            boards.stream().filter(board -> !board.hasWon && board.allNumbers.contains(number))
                    .forEach(board -> winner.set(board.crossNumber(number)));
        }
        return String.valueOf(winner.get());
    }

    static class Board {
        int[][] bingoCard = new int[5][5];
        int[][] bingoCardCrosses = new int[5][5];
        int totalCrosses = 0;
        boolean hasWon = false;
        Set<Integer> allNumbers = new HashSet<>();

        public Board(List<String> inputs) {
            for (int i = 0; i < inputs.size(); i++) {
                int j = 0;
                for (String value : inputs.get(i).split(" ")) {
                    if(!value.isBlank()) {
                        int inputNumber = Integer.parseInt(value);
                        bingoCard[i][j++] = inputNumber;
                        allNumbers.add(inputNumber);
                    }
                }
            }
        }

        public Integer crossNumber(int number) {
            boolean crossedOne = false;
            outside:
            for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (bingoCard[i][j] == number) {
                            allNumbers.remove(number);
                            totalCrosses++;
                            bingoCardCrosses[i][j] = 1;
                            crossedOne = true;
                            break outside;
                        }
                    }
                }
            if (crossedOne && cardHasBingo()) {
                return getSumOfUnmarked() * number;
            } else {
                return -1;
            }
        }

        private Integer getSumOfUnmarked() {
            return allNumbers.stream().reduce(0, Integer::sum);
        }

        private boolean cardHasBingo() {
            if (totalCrosses > 5) {
                for (int i = 0; i < 5; i++) {
                    int horizontalSum = 0;
                    int verticalSum = 0;
                    for (int j = 0; j < 5; j++) {
                        verticalSum += bingoCardCrosses[j][i];
                        horizontalSum += bingoCardCrosses[i][j];
                        if (verticalSum == 5 || horizontalSum == 5) {
                            this.hasWon = true;
                            return true;
                        }
                    }
                }

            }
            return false;
        }
    }

    private int[] extractDrawnNumber(String numbersDrawn) {
        return Arrays.stream(numbersDrawn.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    @NotNull
    private List<Board> getBoards(List<String> strings) {
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < strings.size(); i += 6) {
            boards.add(new Board(strings.subList(i, i + 5)));
        }
        return boards;
    }

}