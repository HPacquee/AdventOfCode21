package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle06 extends BasePuzzleTest {

    private final String puzzleInput = """
            3,4,3,1,2
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle06(puzzleInput);
        assertEquals(puzzle.solvePart1(), "5");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle06(puzzleInput);
        assertEquals(puzzle.solvePart2(), "12");
    }

}