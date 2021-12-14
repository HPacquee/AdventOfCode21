package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle14 extends BasePuzzleTest {

    private final String puzzleInput = """
            NNCB
                        
            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle14(puzzleInput);
        assertEquals(puzzle.solvePart1(), "1588");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle14(puzzleInput);
        assertEquals(puzzle.solvePart2(), "2188189693529");
    }

    @Test
    void name() {
        long sumH = 485345134L +
                485345134L +
                667587793L +
                823172187L +
                266514754L +
                886418173L +
                266514754L +
                604341807L;
        System.out.println(sumH);
    }
}