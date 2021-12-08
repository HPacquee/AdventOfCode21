package be.hpacquee.aoc2021;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle08 extends AbstractPuzzle {
    public Puzzle08(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        List<String> lines = getPuzzleInput().lines().toList();
        List<String> splitLines = lines.stream().flatMap(s -> Arrays.stream(s.split("\\|"))).toList();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < splitLines.size(); i++) {
            if (i % 2 == 1) {
                output.addAll(Arrays.stream(splitLines.get(i).split(" ")).toList());
            }
        }
        long result = output.stream().filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7).count();
        return String.valueOf(result);
    }

    @Override
    public String solvePart2() {

        List<String> lines = getPuzzleInput().trim().lines().toList();
        List<String> splitLines = lines.stream().flatMap(s -> Arrays.stream(s.split(" \\| "))).toList();
        long total = 0L;
        for (int i = 0; i < splitLines.size(); i += 2) {
            String input = splitLines.get(i);
            String output = splitLines.get(i + 1);
            Map<String, Integer> numberMapping = buildNumberMapping(input);
            String[] outputs = output.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String number : outputs) {
                String numberLetters = String.join("", toStringList(number));
                sb.append(numberMapping.get(numberLetters));
            }
            total += Integer.parseInt(sb.toString());
        }
        return String.valueOf(total);
    }

    public static Map<String, Integer> buildNumberMapping(String input) {
        Map<String, Integer> finalNumberMap = new HashMap<>();
        Map<Integer, List<String>> numberMapping = new HashMap<>();
        List<List<String>> inputList = Arrays.stream(input.split(" ")).map(Puzzle08::toStringList).sorted(Comparator.comparingInt(List::size)).toList();
        // 1
        numberMapping.put(1, inputList.get(0));
        // 7
        numberMapping.put(7, inputList.get(1));
        // 4
        numberMapping.put(4, inputList.get(2));
        // 2, 3, 5
        List<List<String>> twoThreeOrFive = new ArrayList<>(List.of(inputList.get(3), inputList.get(4), inputList.get(5)));
        // 6,9
        List<List<String>> sixOrNineOrZero = new ArrayList<>(List.of(inputList.get(6), inputList.get(7), inputList.get(8)));
        // 8
        numberMapping.put(8, inputList.get(9));

        // 9 is the one that contains all lights to make 4
        List<String> nine = sixOrNineOrZero.stream().filter(s -> s.containsAll(numberMapping.get(4))).findFirst().orElseThrow();
        numberMapping.put(9, nine);

        sixOrNineOrZero.remove(nine);

        // 0 is the one that contains all lights to make 1
        List<String> zero = sixOrNineOrZero.stream().filter(s -> s.containsAll(numberMapping.get(1))).findFirst().orElseThrow();
        numberMapping.put(0, zero);

        sixOrNineOrZero.remove(zero);

        // 6 is the last one left
        numberMapping.put(6, sixOrNineOrZero.get(0));

        // with 6 and 9 known we can determine 5 due to their missing pieces from 8
        List<String> mapToFive = new ArrayList<>(numberMapping.get(8));
        mapToFive.removeAll(numberMapping.get(9));

        List<String> fiveDifferciators = new ArrayList<>(mapToFive);
        mapToFive = new ArrayList<>(numberMapping.get(8));
        mapToFive.removeAll(numberMapping.get(6));

        fiveDifferciators.addAll(mapToFive);
        mapToFive = new ArrayList<>(numberMapping.get(8));
        mapToFive.removeAll(fiveDifferciators);

        numberMapping.put(5, mapToFive);

        twoThreeOrFive.removeIf(s -> s.containsAll(numberMapping.get(5)));

        // 2 is the one that has neither of the things that make 5 unique
        List<String> two = twoThreeOrFive.stream().filter(s -> s.contains(fiveDifferciators.get(0)) && s.contains(fiveDifferciators.get(1))).findFirst().orElseThrow();
        numberMapping.put(2, two);
        twoThreeOrFive.remove(two);

        // 3 is last one standing
        numberMapping.put(3, twoThreeOrFive.get(0));

        numberMapping.forEach((key, value) -> finalNumberMap.put(String.join("", value), key));

        return finalNumberMap;
    }

    private static List<String> toStringList(String input) {
        return input.chars().mapToObj(Character::toString).sorted().collect(Collectors.toList());
    }
}