package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle01 extends BasePuzzleTest {

    private final String puzzleInput = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle01(puzzleInput);
        assertEquals(puzzle.solvePart1(), "7");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle01(puzzleInput);
        assertEquals(puzzle.solvePart2(), "5");
    }

}