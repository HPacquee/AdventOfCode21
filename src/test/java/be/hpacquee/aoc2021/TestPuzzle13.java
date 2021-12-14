package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle13 extends BasePuzzleTest {

    private final String puzzleInput = """
6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle13(puzzleInput);
        assertEquals(puzzle.solvePart1(), "17");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle13(puzzleInput);
        assertEquals(puzzle.solvePart2(), "16");
    }

}