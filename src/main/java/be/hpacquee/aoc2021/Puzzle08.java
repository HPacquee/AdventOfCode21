package be.hpacquee.aoc2021;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle08 extends AbstractPuzzle {

    private final List<String> splitLines;
    private final Pattern findOneAndFour = Pattern.compile("(\\b\\w{2}\\b|\\b\\w{4}\\b)", Pattern.MULTILINE);

    public Puzzle08(String puzzleInput) {
        super(puzzleInput);
        List<String> lines = getPuzzleInput().lines().toList();
        splitLines = lines.stream().flatMap(s -> Arrays.stream(s.split(" \\| "))).toList();
    }

    @Override
    public String solvePart1() {
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
        long total = 0L;
        for (int i = 0; i < splitLines.size(); i += 2) {
            Map<Integer, Mapping> baseMapping = getBaseMapping(splitLines.get(i));
            String[] outputs = splitLines.get(i + 1).split(" ");
            for (int j = 0; j < outputs.length; j++) {
                total += getNumericalValue(outputs[j], baseMapping) * (Math.pow(10, 3-j));
            }
        }
        return String.valueOf(total);
    }

    private Map<Integer, Mapping> getBaseMapping(String input) {
        Map<Integer, Mapping> mappingMap = new HashMap<>();
        Matcher matcher = findOneAndFour.matcher(input);
        while (matcher.find()) {
            String group = matcher.group(1);
            mappingMap.put(group.length() == 2 ? 1: 4, Mapping.of(group));
        }
        mappingMap.put(8, Mapping.of("abcdefg"));
        return mappingMap;
    }

    private int getNumericalValue(String input, Map<Integer, Mapping> baseMapping) {
        switch (input.length()) {
            case 2:
                return 1;
            case 3:
                return 7;
            case 4:
                return 4;
            case 5: {
                if (baseMapping.get(1).hasAllSegmentsFor(input)) {
                    return 3;
                } else if (baseMapping.get(8).minus(baseMapping.get(4)).hasAllSegmentsFor(input)) {
                    return 2;
                } else {
                    return 5;
                }
            }
            case 6: {
                if (baseMapping.get(4).hasAllSegmentsFor(input)) {
                    return 9;
                } else if (baseMapping.get(1).hasAllSegmentsFor(input)) {
                    return 0;
                } else {
                    return 6;
                }
            }
            case 7:
                return 8;
        }
        throw new IllegalArgumentException("Should map to at least one value");
    }

    public record Mapping(Set<String> letters) {
        public static Mapping of(String input) {
            return new Mapping(input.chars().mapToObj(Character::toString).collect(Collectors.toSet()));
        }

        public Mapping minus(Mapping other) {
            Set<String> result;
            if(other.letters.size() > this.letters.size()) {
                result = new HashSet<>(other.letters);
                result.removeAll(this.letters);
            } else {
                result = new HashSet<>(this.letters);
                result.removeAll(other.letters);
            }
            return new Mapping(result);
        }

        public boolean hasAllSegmentsFor(String input) {
            if(input.length() > letters.size()) {
                return input.chars().mapToObj(Character::toString).collect(Collectors.toSet()).containsAll(this.letters);
            } else {
                return this.letters.containsAll(input.chars().mapToObj(Character::toString).collect(Collectors.toSet()));
            }
        }
    }
}