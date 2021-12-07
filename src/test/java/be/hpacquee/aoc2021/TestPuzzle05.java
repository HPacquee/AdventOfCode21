package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle05 extends BasePuzzleTest {

    private final String puzzleInput = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle05(puzzleInput);
        assertEquals(puzzle.solvePart1(), "5");
    }

    @Test
    public void testPart2Example1() {
        var puzzle = new Puzzle05(puzzleInput);
        assertEquals(puzzle.solvePart2(), "12");
    }
}