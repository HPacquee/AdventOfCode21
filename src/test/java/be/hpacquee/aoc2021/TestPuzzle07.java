package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle07 extends BasePuzzleTest {

    private final String puzzleInput = "16,1,2,0,4,2,7,1,2,14";

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle07(puzzleInput);
        assertEquals(puzzle.solvePart1(), "37");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle07(puzzleInput);
        assertEquals(puzzle.solvePart2(), "168");
    }

}