import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < numCourses ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] courses:prerequisites){
            int course = courses[0];
            int prerequesite = courses[1];

            adj.get(prerequesite).add(course);
        }

        int state[]  = new int[numCourses];

        for(int i = 0 ; i < numCourses ; i++){
            if(hasCycle(adj, state, i))
                return false;
        }

        return true;
    }

    //  1 == GRAY -- > Cycle ; 2 == BLACK --> SAFE
    private static boolean hasCycle(List<List<Integer>> adj, int[] state, int node){

        if(state[node] == 2) return false;
        if(state[node] == 1) return true;

        state[node] = 1;

        for(int neighbour : adj.get(node)){
            if(hasCycle(adj, state, neighbour)){
                return true;
            }
        }
        state[node] = 2;
        return false;
    }

    public static boolean canFinishMap(int numCourses, int[][]prerequisites){

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] courses : prerequisites){
            adj.computeIfAbsent(courses[1], k -> new ArrayList<>()).add(courses[0]);
        }

        Map<Integer,Integer> state = new HashMap<>();

        for(int i = 0 ; i < numCourses ; i++){
            if(hasCycleHM(adj, state, i))
                return false;
        }
        return true;
    }

    private static boolean hasCycleHM(Map<Integer, List<Integer>> adj, Map<Integer,Integer> state, int node){

        if(state.getOrDefault(node, 0) == 2) return false;
        if(state.getOrDefault(node, 0) == 1) return true;

        state.put(node , 1);

        for(int neighbour : adj.getOrDefault(node, Collections.emptyList())){
            if(hasCycleHM(adj, state, neighbour))
                return true;
        }
        state.put(node , 2);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canFinishMap(2, new int[][]{{1,0}}));
    }
}
