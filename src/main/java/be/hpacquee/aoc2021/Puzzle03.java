package be.hpacquee.aoc2021;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Puzzle03 extends AbstractPuzzle {
    public Puzzle03(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public String solvePart1() {
        List<String> strings = getPuzzleInput().lines().toList();
        long[] totalOnes = new long[strings.get(0).length()];
        long[] totalZeroes = new long[strings.get(0).length()];
        getTotalOnesAndZeroes(strings, totalOnes, totalZeroes);
        StringBuilder result = new StringBuilder();
        StringBuilder inverse = new StringBuilder();
        for (int i = 0; i < strings.get(0).length(); i++) {
            if(totalOnes[i] > totalZeroes[i]) {
                result.append("1");
                inverse.append("0");
            } else {
                result.append("0");
                inverse.append("1");
            }
        }
        Integer gamma = Integer.parseInt(result.toString(), 2);
        Integer epsilon = Integer.parseInt(inverse.toString(), 2);
        return String.valueOf(gamma * epsilon);
    }

    @Override
    public String solvePart2() {
        List<String> oxygenList = filterOut('1', '0');
        List<String> co2ScrubberList = filterOut( '0', '1');

        int oxygen = Integer.parseInt(oxygenList.get(0), 2);
        int co2 = Integer.parseInt(co2ScrubberList.get(0), 2);

        return String.valueOf(oxygen * co2);
    }

    @NotNull
    private List<String> filterOut(char whenBiggerOrEqualChar, char whenSmallerChar) {
        int i;
        List<String> itemList = getPuzzleInput().lines().toList();
        i = 0;
        while (itemList.size() != 1) {
            long[] totalOnes = new long[itemList.get(0).length()];
            long[] totalZeroes = new long[itemList.get(0).length()];
            getTotalOnesAndZeroes(itemList, totalOnes, totalZeroes);
            int finalI = i;
            if(totalOnes[i] >= totalZeroes[i]) {
                itemList = itemList.stream().filter(s -> s.charAt(finalI) == whenBiggerOrEqualChar).collect(Collectors.toList());
            } else {
                itemList = itemList.stream().filter(s -> s.charAt(finalI) == whenSmallerChar).collect(Collectors.toList());
            }
            i++;
        }
        return itemList;
    }

    private void getTotalOnesAndZeroes(List<String> strings, long[] totalOnes, long[] totalZeroes) {
        strings.forEach(s ->
        {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '1') {
                    totalOnes[i]++;
                }
                if(s.charAt(i) == '0') {
                    totalZeroes[i]++;
                }
            }
        });
    }
}