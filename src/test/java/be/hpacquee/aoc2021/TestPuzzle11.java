package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle11 extends BasePuzzleTest {

    private final String puzzleInput = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle11(puzzleInput);
        assertEquals(puzzle.solvePart1(), "1656");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle11(puzzleInput);
        assertEquals(puzzle.solvePart2(), "195");
    }

}