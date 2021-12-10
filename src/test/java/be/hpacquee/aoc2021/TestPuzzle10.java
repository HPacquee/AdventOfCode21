package be.hpacquee.aoc2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPuzzle10 extends BasePuzzleTest {

    private final String puzzleInput = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
            """;

    @Test
    public void testPart1Example1() {
        var puzzle = new Puzzle10(puzzleInput);
        assertEquals(puzzle.solvePart1(), "26397");
    }

    @Test
    public void testPart1Example2() {
        var puzzle = new Puzzle10(puzzleInput);
        assertEquals(puzzle.solvePart2(), "288957");
    }

}