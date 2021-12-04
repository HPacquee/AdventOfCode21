package be.hpacquee.aoc2021;

import org.reflections.Reflections;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {

    public static final boolean INIT_ALL_PUZZLES = false;

    public static void main(String[] args) {
        List<AbstractPuzzle> puzzles = INIT_ALL_PUZZLES ? initAllPuzzles() : List.of(initLastPuzzle());
        puzzles.forEach(puzzle -> {
            var day = String.format("%02d", puzzle.getDay());
            System.out.println("Day " + day + " Part 1: " + puzzle.solvePart1());
            System.out.println("Day " + day + " Part 2: " + puzzle.solvePart2());
        });
    }

    private static AbstractPuzzle initLastPuzzle() {
        return initPuzzles().max(Comparator.comparing(AbstractPuzzle::getDay)).orElseThrow();
    }

    private static List<AbstractPuzzle> initAllPuzzles() {
        return initPuzzles().sorted(Comparator.comparing(AbstractPuzzle::getDay)).toList();
    }

    private static Stream<AbstractPuzzle> initPuzzles() {
        return new Reflections("be.hpacquee.aoc2021").getSubTypesOf(AbstractPuzzle.class).stream().map(aClass ->
                        {
                            try {
                                return (AbstractPuzzle) aClass.getConstructors()[0].newInstance(new PuzzleInputFetcher().getPuzzleInput(aClass));
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                )
                .filter(Objects::nonNull);
    }
}