package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle02 extends BasePuzzleTest {

    private final String puzzleInput = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle02(puzzleInput);
        assertEquals(puzzle.solvePart1(), "150");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle02(puzzleInput);
        assertEquals(puzzle.solvePart2(), "900");
    }

}