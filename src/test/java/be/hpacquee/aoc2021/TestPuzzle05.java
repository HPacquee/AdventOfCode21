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

    @Test
    void testGetPointsBetween() {
        List<Puzzle05.Coordinate> pointsBetween = Puzzle05.getPointsBetween(new Puzzle05.Pipe(0, 0, 2, 2), false);
        assertEquals(0, pointsBetween.get(0).x());
        assertEquals(0, pointsBetween.get(0).y());
        assertEquals(1, pointsBetween.get(1).x());
        assertEquals(1, pointsBetween.get(1).y());
        assertEquals(2, pointsBetween.get(2).x());
        assertEquals(2, pointsBetween.get(2).y());

        pointsBetween = Puzzle05.getPointsBetween(new Puzzle05.Pipe(0, 9, 0, 5));
        assertEquals(0, pointsBetween.get(0).x());
        assertEquals(5, pointsBetween.get(0).y());
        assertEquals(0, pointsBetween.get(1).x());
        assertEquals(6, pointsBetween.get(1).y());
        assertEquals(0, pointsBetween.get(2).x());
        assertEquals(7, pointsBetween.get(2).y());
        assertEquals(0, pointsBetween.get(3).x());
        assertEquals(8, pointsBetween.get(3).y());
        assertEquals(0, pointsBetween.get(4).x());
        assertEquals(9, pointsBetween.get(4).y());

        pointsBetween = Puzzle05.getPointsBetween(new Puzzle05.Pipe(0, 2, 2, 0), false);
        assertEquals(0, pointsBetween.get(0).x());
        assertEquals(2, pointsBetween.get(0).y());
        assertEquals(1, pointsBetween.get(1).x());
        assertEquals(1, pointsBetween.get(1).y());
        assertEquals(2, pointsBetween.get(2).x());
        assertEquals(0, pointsBetween.get(2).y());

        pointsBetween = Puzzle05.getPointsBetween(new Puzzle05.Pipe(2, 0, 0, 2), false);
        assertEquals(2, pointsBetween.get(0).x());
        assertEquals(0, pointsBetween.get(0).y());
        assertEquals(1, pointsBetween.get(1).x());
        assertEquals(1, pointsBetween.get(1).y());
        assertEquals(0, pointsBetween.get(2).x());
        assertEquals(2, pointsBetween.get(2).y());

        pointsBetween = Puzzle05.getPointsBetween(new Puzzle05.Pipe(2, 2, 0, 0), false);
        assertEquals(2, pointsBetween.get(0).x());
        assertEquals(2, pointsBetween.get(0).y());
        assertEquals(1, pointsBetween.get(1).x());
        assertEquals(1, pointsBetween.get(1).y());
        assertEquals(0, pointsBetween.get(2).x());
        assertEquals(0, pointsBetween.get(2).y());

        pointsBetween = Puzzle05.getPointsBetween(new Puzzle05.Pipe(5, 5, 8, 2), false);
        assertEquals(5, pointsBetween.get(0).x());
        assertEquals(5, pointsBetween.get(0).y());
        assertEquals(6, pointsBetween.get(1).x());
        assertEquals(4, pointsBetween.get(1).y());
        assertEquals(7, pointsBetween.get(2).x());
        assertEquals(3, pointsBetween.get(2).y());
        assertEquals(8, pointsBetween.get(3).x());
        assertEquals(2, pointsBetween.get(3).y());
    }
}