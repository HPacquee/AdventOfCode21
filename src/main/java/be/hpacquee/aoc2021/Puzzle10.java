package be.hpacquee.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Puzzle10 extends AbstractPuzzle {

    private final List<String> lines;
    private final Map<Character, Character> characterMapping = Map.of('(', ')', '[', ']', '{', '}', '<', '>');
    private final Map<Character, Integer> corruptionScores = Map.of(')', 3 , ']', 57 , '}', 1197 , '>', 25137);
    private final Map<Character, Integer> incompleteScores = Map.of(')', 1 , ']', 2 , '}', 3 , '>', 4);

    public Puzzle10(String puzzleInput) {
        super(puzzleInput);
        lines = getPuzzleInput().lines().toList();
    }

    @Override
    public String solvePart1() {
        long score = 0L;
        for (String line : lines) {
            score += getScoreForCorruptLine(line);
        }
        return String.valueOf(score);
    }

    private long getScoreForCorruptLine(String line) {
        Stack<Character> currentOpeningCharacters = new Stack<>();
        for (Character character : line.toCharArray()) {
            if(characterMapping.containsKey(character)) {
                currentOpeningCharacters.push(character);
            }
            if(characterMapping.containsValue(character)) {
                if(characterMapping.get(currentOpeningCharacters.peek()) != character) {
                    System.out.println("Expecting " + characterMapping.get(currentOpeningCharacters.peek()) + " but got "+ character);
                    return corruptionScores.get(character);
                } else {
                    currentOpeningCharacters.pop();
                }
            }
        }
        return 0;
    }

    @Override
    public String solvePart2() {
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            long scoreForIncompleteLine = getScoreForIncompleteLine(line);
            if(scoreForIncompleteLine != 0) {
                scores.add(scoreForIncompleteLine);
            }
        }
        scores.sort(Long::compare);
        return String.valueOf(scores.get(scores.size()/2));
    }

    private long getScoreForIncompleteLine(String line) {
        Stack<Character> currentOpeningCharacters = new Stack<>();
        for (Character character : line.toCharArray()) {
            if(characterMapping.containsKey(character)) {
                currentOpeningCharacters.push(character);
            }
            if(characterMapping.containsValue(character)) {
                if(characterMapping.get(currentOpeningCharacters.peek()) != character) {
                    return 0;
                } else {
                    currentOpeningCharacters.pop();
                }
            }
        }
        long score = 0L;
        while(!currentOpeningCharacters.empty()) {
            score *= 5;
            score += incompleteScores.get(characterMapping.get(currentOpeningCharacters.pop()));
        }
        System.out.println("Score was " +score);
        return score;
    }
}