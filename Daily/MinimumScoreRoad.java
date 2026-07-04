import java.util.*;
import java.util.LinkedList;

public class MinimumScoreRoad {
    public static int minScore(int n, int[][] roads){

        HashSet<Integer> reachable = new HashSet<Integer>();
        int min = Integer.MAX_VALUE;
        reachable.add(1);
        boolean changed = true;

        while(changed){
            changed = false;
            for(int[] road : roads){
                int u = road[0];
                int v = road[1];
                int dist = road[2];

                if(reachable.contains(u) || reachable.contains(v)){
                    if (!reachable.contains(u)){
                        reachable.add(u);
                        changed = true;
                    }
                    if(!reachable.contains(v)){
                        reachable.add(v);
                        changed = true;
                    }
                    min = Math.min(min,dist);
                }
            }
        }
        return min;
    }

    public static int minScore_BFS(int n, int[][] roads){

        int min = Integer.MAX_VALUE;

        Map<Integer,List<int[]>> graph = new HashMap<>();

        for(int[] road:roads){
            graph.computeIfAbsent(road[0], key -> new ArrayList<>()).add(road);
            graph.computeIfAbsent(road[1], key -> new ArrayList<>()).add(road);
        }

        //BFS First Search:
        Set<Integer> visited = new HashSet<>();

        //Queue is used for breadth first search
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited.add(1);

        while(!queue.isEmpty()){
            //poll pops the city from the queue's top
            int city = queue.poll();
            for(int road[]:graph.getOrDefault(city, new ArrayList<>())){
                min = Math.min(min,road[2]);

                int next = (road[0] == city) ? road[1] : road[0];
                
                if(!visited.contains(next)){
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return min;
    }

    public static void main(String args[]){

        int arr[][] = new int[][]{{1,3,1484},{3,2,3876},{2,4,6823},{6,7,579},{5,6,4436},{4,5,8830}};
        
        System.out.println(minScore_BFS(4, arr));
    }
}
