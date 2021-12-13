package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle09 extends BasePuzzleTest {

    private final String puzzleInput = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle09(puzzleInput);
        assertEquals(puzzle.solvePart1(), "15");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle09(puzzleInput);
        assertEquals(puzzle.solvePart2(), "1134");
    }

}