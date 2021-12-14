package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle12 extends BasePuzzleTest {

    private final String puzzleInput = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle12(puzzleInput);
        assertEquals(puzzle.solvePart1(), "10");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle12(puzzleInput);
        assertEquals(puzzle.solvePart2(), "36");
    }

}