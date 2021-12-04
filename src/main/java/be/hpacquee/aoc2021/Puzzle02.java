package be.hpacquee.aoc2021;

import java.util.function.BiFunction;

public class Puzzle02 extends AbstractPuzzle {
    public Puzzle02(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public String solvePart1() {
        Submarine submarine = new Submarine();
        getPuzzleInput().lines().toList().forEach(submarine::move);
        return String.valueOf(submarine.getPosition());
    }

    @Override
    public String solvePart2() {
        AimingSubmarine submarine = new AimingSubmarine();
        getPuzzleInput().lines().toList().forEach(submarine::move);
        return String.valueOf(submarine.getPosition());

    }

    static class Submarine {

        Position position = new Position(0, 0, 0);

        void move(String input) {
            String[] split = input.split(" ");
            position = recalculatePosition(split[0], Integer.parseInt(split[1]));
        }

        Position recalculatePosition(String direction, int distance) {
            return Direction.valueOf(direction.toUpperCase()).function.apply(position, distance);
        }

        Integer getPosition() {
            return position.getPosition();
        }

    }

    static class AimingSubmarine extends Submarine {
        @Override
        Position recalculatePosition(String direction, int distance) {
            return AimingDirection.valueOf(direction.toUpperCase()).function.apply(position, distance);
        }
    }

    enum Direction {
        FORWARD((position, input) -> new Position(position.x() + input, position.y())),
        DOWN((position, input) -> new Position(position.x(), position.y() + input)),
        UP((position, input) -> new Position(position.x(), position.y() - input));

        final BiFunction<Position, Integer, Position> function;

        Direction(BiFunction<Position, Integer, Position> function) {
            this.function = function;
        }
    }

    enum AimingDirection {
        FORWARD((position, input) -> new Position(position.x() + input, position.y() + (position.aim() * input), position.aim())),
        DOWN((position, input) -> new Position(position.x(), position.y(), position.aim() + input)),
        UP((position, input) -> new Position(position.x(), position.y(), position.aim() - input));

        final BiFunction<Position, Integer, Position> function;

        AimingDirection(BiFunction<Position, Integer, Position> function) {
            this.function = function;
        }
    }

    record Position(Integer x, Integer y, Integer aim) {

        Position(Integer x, Integer y) {
            this(x, y, null);
        }

        Integer getPosition() {
            return x * y;
        }
    }
}