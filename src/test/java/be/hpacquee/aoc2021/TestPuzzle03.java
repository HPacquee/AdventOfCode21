package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle03 extends BasePuzzleTest {

    private final String puzzleInput = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle03(puzzleInput);
        assertEquals(puzzle.solvePart1(), "198");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle03(puzzleInput);
        assertEquals(puzzle.solvePart2(), "230");
    }

}