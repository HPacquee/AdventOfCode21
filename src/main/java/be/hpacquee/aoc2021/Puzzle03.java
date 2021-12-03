package be.hpacquee.aoc2021;

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
        List<String> strings = getPuzzleInput().lines().toList();
        List<String> oxygenList = strings;
        int i = 0;
        while (oxygenList.size() != 1) {
            long[] totalOnes = new long[strings.get(0).length()];
            long[] totalZeroes = new long[strings.get(0).length()];
            getTotalOnesAndZeroes(oxygenList, totalOnes, totalZeroes);
            int finalI = i;
            if(totalOnes[i] >= totalZeroes[i]) {
                oxygenList = oxygenList.stream().filter(s -> s.charAt(finalI) == '1').collect(Collectors.toList());
            } else {
                oxygenList = oxygenList.stream().filter(s -> s.charAt(finalI) == '0').collect(Collectors.toList());
            }
            i++;
        }

        List<String> co2ScrubberList = strings;
        i = 0;
        while (co2ScrubberList.size() != 1) {
            long[] totalOnes = new long[strings.get(0).length()];
            long[] totalZeroes = new long[strings.get(0).length()];
            getTotalOnesAndZeroes(co2ScrubberList, totalOnes, totalZeroes);
            int finalI = i;
            if(totalOnes[i] >= totalZeroes[i]) {
                co2ScrubberList = co2ScrubberList.stream().filter(s -> s.charAt(finalI) == '0').collect(Collectors.toList());
            } else {
                co2ScrubberList = co2ScrubberList.stream().filter(s -> s.charAt(finalI) == '1').collect(Collectors.toList());
            }
            i++;
        }


        int oxygen = Integer.parseInt(oxygenList.get(0), 2);
        int co2 = Integer.parseInt(co2ScrubberList.get(0), 2);

        return String.valueOf(oxygen * co2);
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