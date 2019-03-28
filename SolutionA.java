import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.*;
import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Road {
    public String city1;
    public String city2;

    public Road(String city1, String city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

}

class RoadMap {
    Map<String, Set<Road>> roadMap = new HashMap<String, Set<Road>>();

    //This function helps to get all the cities in the graph
    public Set<String> getAllCities() {
        return this.roadMap.keySet();
    }

    //This function will read the input
    public void readLine(String line) {
        String[] csv = line.split(",");
        String city1 = csv[0];
        String city2 = csv[1];
        addRoad(city1, city2);
    }

    private void addCity(String city) {
        this.roadMap.put(city, new HashSet<Road>());
    }

    //This function will add both outgoing and incoming roads between two cities
    private void addRoad(String city1, String city2) {
        Road road1 = new Road(city1, city2);
        Road road2 = new Road(city2, city1);
        if (!this.roadMap.containsKey(city1)) {
            addCity(city1);
        }
        if (!this.roadMap.containsKey(city2)) {
            addCity(city2);
        }
        this.roadMap.get(city1).add(road1);
        this.roadMap.get(city2).add(road2);
    }

    //This function will return all the outgoing roads from a city
    public Set<Road> getAllOutgoingRoads(String node) {
        return this.roadMap.get(node);
    }

}

public class GraphAssignment {
    static RoadMap roadMap = new RoadMap();

    public static void readMap(Scanner scanner) {
        while (true) {
            String mapLine = scanner.nextLine();
            if (mapLine.equals("")) {
                break;
            }
            roadMap.readLine(mapLine);
        }
        System.out.println("Read map");

    }


    public static void findAnyRouteToCity(String source, String destination) {
        // PART-A: Write the required java code to find the possible routes from source to destination
        
        Queue<String> toProcess = new LinkedList<>();
        Set<String> visitedNodes = new HashSet<>();
        Map<String, Road> nodeToParentPointer = new HashMap<>();

        // Initial nodes
        toProcess.add(source);
        nodeToParentPointer.put(source, null);

        // Traverse the queue
        while (!toProcess.isEmpty()) {
            String current = toProcess.remove();
            visitedNodes.add(current);
            if (current.equals(destination)) {
                break;
            }

            // Relax node
            for (Road road : roadMap.getAllOutgoingRoads(current)) {
                if (!visitedNodes.contains(road.city2)){
                    toProcess.add(road.city2);
                    nodeToParentPointer.put(road.city2, road);
                }
            }
        }

        // Backtrack final path
        List<String> path = new LinkedList<>();
        String current = destination;
        while (true) {
            path.add(current);
            Road parentPointer = nodeToParentPointer.get(current);
            if (parentPointer == null) {
                break;
            }
            current = parentPointer.city1;

        }

        Collections.reverse(path);
        StringJoiner sj = new StringJoiner("->");
        for (String city : path) {
            sj.add(city);
        }

            System.out.println(sj);
        }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Map");
        readMap(scanner);

        System.out.println("Enter the source ");
        String source = scanner.nextLine();
        System.out.println("Enter the destination ");
        String destination = scanner.nextLine();
        System.out.println("The route from "+source+" to "+destination+" is");

        findAnyRouteToCity(source, destination);
    }

}