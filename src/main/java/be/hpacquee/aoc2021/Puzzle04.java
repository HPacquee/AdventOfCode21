package be.hpacquee.aoc2021;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Puzzle04 extends AbstractPuzzle {
    public Puzzle04(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        List<String> strings = getPuzzleInput().lines().collect(Collectors.toList());
        String numbersDrawn = strings.get(0);
        strings.remove(0);
        strings.remove(0);
        List<Board> boards = new ArrayList<>();
        while (strings.size() != 0) {
            boards.add(new Board(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4)));
            try {
                for (int i = 0; i < 6; i++) {
                    strings.remove(0);
                }
            } catch (Exception e) {
            }
        }
        AtomicInteger winner = new AtomicInteger(-1);
        for (String number : Arrays.stream(numbersDrawn.split(",")).toList()) {
            if ( winner.get() != -1)
                break;
        boards.forEach(board -> {
                Integer win = board.crossNumber(Integer.parseInt(number));
                if(win != -1)
                    winner.set(win);
            });
        }
        return String.valueOf(winner.get());
    }

    static class Board {
        int[][] bingoCard = new int[5][5];
        int[][] bingoCardCrosses = new int[5][5];
        int totalCrosses = 0;
        boolean hasWon = false;

        Board(String row1, String row2, String row3, String row4, String row5) {
            bingoCard[0] = Arrays.stream(row1.split(" ")).filter(s -> !s.isBlank()).mapToInt(Integer::parseInt).toArray();
            bingoCard[1] = Arrays.stream(row2.split(" ")).filter(s -> !s.isBlank()).mapToInt(Integer::parseInt).toArray();
            bingoCard[2] = Arrays.stream(row3.split(" ")).filter(s -> !s.isBlank()).mapToInt(Integer::parseInt).toArray();
            bingoCard[3] = Arrays.stream(row4.split(" ")).filter(s -> !s.isBlank()).mapToInt(Integer::parseInt).toArray();
            bingoCard[4] = Arrays.stream(row5.split(" ")).filter(s -> !s.isBlank()).mapToInt(Integer::parseInt).toArray();
        }

        public Integer crossNumber(int number) {
            boolean gotOne = false;
            for (int i = 0; i < 5; i++) {
                if (!gotOne) {
                    for (int j = 0; j < 5; j++) {
                        if (!gotOne && bingoCard[i][j] == number) {
                            gotOne = true;
                            totalCrosses++;
                            bingoCardCrosses[i][j] = 1;
                        }
                    }
                }
            }
            if (gotOne) {
                if (didIWin()) {
                    return getSumOfUnmarked() * number;
                }
            }

            return -1;
        }

        private Integer getSumOfUnmarked() {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (bingoCardCrosses[i][j] == 0) {
                        sum += bingoCard[i][j];
                    }
                }
            }
            return sum;
        }

        private boolean didIWin() {
            if (totalCrosses > 5) {
                for (int i = 0; i < 5; i++) {
                    if (Arrays.stream(bingoCardCrosses[i]).sum() == 5) {
                        this.hasWon = true;
                        return true;
                    }
                    int verticalSum = 0;
                    for (int j = 0; j < 5; j++) {
                        verticalSum += bingoCardCrosses[j][i];
                        if (verticalSum == 5) {
                            this.hasWon = true;
                            return true;
                        }
                    }
                }

            }
            return false;
        }
    }

    @Override
    public String solvePart2() {
        List<String> strings = getPuzzleInput().lines().collect(Collectors.toList());
        String numbersDrawn = strings.get(0);
        strings.remove(0);
        strings.remove(0);
        List<Board> boards = new ArrayList<>();
        while (strings.size() != 0) {
            boards.add(new Board(strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4)));
            try {
                for (int i = 0; i < 6; i++) {
                    strings.remove(0);
                }
            } catch (Exception e) {
            }
        }
        AtomicInteger winner = new AtomicInteger(-1);
        for (String number : Arrays.stream(numbersDrawn.split(",")).toList()) {
            boards.stream().filter(board -> !board.hasWon).forEach(board -> {
                Integer win = board.crossNumber(Integer.parseInt(number));
                if(win != -1)
                    winner.set(win);
            });
        }
        return String.valueOf(winner.get());

    }

}