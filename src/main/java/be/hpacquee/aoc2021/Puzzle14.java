package be.hpacquee.aoc2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Puzzle14 extends AbstractPuzzle {

    private final String polymerTemplate;
    private final Map<String, String> pairInsertionRules;

    public Puzzle14(String puzzleInput) {
        super(puzzleInput);
        List<String> inputList = getPuzzleInput().lines().toList();
        polymerTemplate = inputList.get(0);
        pairInsertionRules = inputList.subList(2, inputList.size()).stream().collect(Collectors.toMap(s -> s.split(" -> ")[0], s -> s.split(" -> ")[1]));

    }

    @Override
    public String solvePart1() {
        String templateInProgress = polymerTemplate;
        for (int loopsize = 0; loopsize < 10; loopsize++) {
            StringBuilder nextTemplateBuilder = new StringBuilder();
            for (int i = 0; i < templateInProgress.length() - 1; i++) {
                String pair = templateInProgress.substring(i, i + 2);
                String toInsert = pairInsertionRules.get(pair);
                String[] charSplit = pair.split("");
                if (i == 0) {
                    nextTemplateBuilder.append(charSplit[0]);
                }
                nextTemplateBuilder.append(toInsert);
                nextTemplateBuilder.append(charSplit[1]);
            }
            templateInProgress = nextTemplateBuilder.toString();
        }
        Map<Character, Long> charCounts = templateInProgress.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Long min = charCounts.values().stream().min(Long::compareTo).orElseThrow();
        Long max = charCounts.values().stream().max(Long::compareTo).orElseThrow();

        return String.valueOf(max - min);
    }

    @Override
    public String solvePart2() {
        Map<String, Long> pairCounts = new HashMap<>();
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            String pair = polymerTemplate.substring(i, i + 2);
            pairCounts.merge(pair, 1L, Long::sum);
        }

        for (int i = 0; i < 40; i++) {
            Map<String, Long> newPairCounts = new HashMap<>();
            pairCounts.forEach((pair, value) -> {
                if (pairInsertionRules.containsKey(pair)) {
                    String toInsert = pairInsertionRules.get(pair);
                    newPairCounts.merge(pair.charAt(0) + toInsert, value, Long::sum);
                    newPairCounts.merge(toInsert + pair.charAt(1), value, Long::sum);
                }
            });
            pairCounts = newPairCounts;
        }

        Map<String, Long> characterCount = new HashMap<>();
        characterCount.put(polymerTemplate.charAt(polymerTemplate.length() - 1) + "", 1L);
        pairCounts.forEach((pair, value) -> {
            characterCount.put(pair.charAt(0) + "", characterCount.getOrDefault(pair.charAt(0) + "", 0L) + value);
        });
        Long max = characterCount.values().stream().max(Long::compareTo).orElseThrow();
        Long min = characterCount.values().stream().min(Long::compareTo).orElseThrow();

        return String.valueOf(max - min);
    }

}