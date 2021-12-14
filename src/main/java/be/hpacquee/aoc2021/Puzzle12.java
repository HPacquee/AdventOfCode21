package be.hpacquee.aoc2021;

import java.util.*;

import static be.hpacquee.aoc2021.Puzzle12.CaveType.*;

public class Puzzle12 extends AbstractPuzzle {

    Set<Cave> allCaves;
    Map<String, Cave> caveByName = new HashMap<>();

    public Puzzle12(String puzzleInput) {
        super(puzzleInput);
        getPuzzleInput().lines().forEach(s -> {
            String[] split = s.split("-");
            String name = split[0];
            String destinationName = split[1];
            Cave source;
            Cave destination;

            if(caveByName.containsKey(name)) {
                source = caveByName.get(name);
            } else {
                source = new Cave(name);
            }

            if(caveByName.containsKey(destinationName)) {
                destination = caveByName.get(destinationName);
            } else {
                destination = new Cave(destinationName);
            }


            source.getConnectedCaves().add(destination);
            caveByName.put(name, source);

            destination.getConnectedCaves().add(source);
            caveByName.put(destinationName, destination);
        });
        allCaves = new HashSet<>(caveByName.values());
    }

    @Override
    public String solvePart1() {
        Cave startingCave = allCaves.stream().filter(cave -> cave.caveType == CaveType.START).findFirst().orElseThrow();
        boolean thereIsNoScenicRoute = true;
        long totalPaths = calculatePaths(startingCave, new LinkedList<>(), thereIsNoScenicRoute);
        return String.valueOf(totalPaths);
    }

    @Override
    public String solvePart2() {
        Cave startingCave = allCaves.stream().filter(cave -> cave.caveType == CaveType.START).findFirst().orElseThrow();
        long totalPaths = calculatePaths(startingCave, new LinkedList<>(), false);
        return String.valueOf(totalPaths);
    }

    public long calculatePaths(Cave cave, LinkedList<Cave> visitedCaves, boolean doneTheScenicRoute) {
        if(cave.isStart() && visitedCaves.contains(cave)) {
            return 0;
        }
        if(cave.isEnd()) {
            return 1;
        }
        if(cave.isSmall() && visitedCaves.contains(cave)) {
            if(doneTheScenicRoute) {
               return 0;
            }
            doneTheScenicRoute = true;
        }
        visitedCaves.addLast(cave);
        long count = 0L;
        for (Cave nextCave: cave.getConnectedCaves()) {
            count += calculatePaths(nextCave, visitedCaves, doneTheScenicRoute);
        }
        visitedCaves.removeLast();
        return count;
    }

    public static class Cave {

        private final String name;
        private final CaveType caveType;
        private final List<Cave> connectedCaves = new ArrayList<>();

        public Cave(String name) {
            this(name, CaveType.fromName(name));
        }

        private Cave(String name, CaveType caveType) {
            this.name = name;
            this.caveType = caveType;
        }

        public String getName() {
            return name;
        }

        public CaveType getCaveType() {
            return caveType;
        }

        public List<Cave> getConnectedCaves() {
            return connectedCaves;
        }

        public boolean isStart() {
            return caveType == START;
        }

        public boolean isSmall() {
            return caveType == SMALL;
        }

        public boolean isEnd() {
            return caveType == END;
        }

        @Override
        public String toString() {
            return "Cave{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cave cave = (Cave) o;
            return Objects.equals(name, cave.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public enum CaveType {
        START,
        END,
        SMALL,
        BIG;

        static CaveType fromName(String name) {
            if (Objects.equals(name, "start")) {
                return START;
            }
            if (Objects.equals(name, "end")) {
                return END;
            }
            if (Objects.equals(name, name.toLowerCase())) {
                return SMALL;
            } else {
                return BIG;
            }
        }
    }
}