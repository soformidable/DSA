import java.util.*;
import java.util.LinkedList;

public class CourseScheduleII{
    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjacency = new ArrayList<>();

        // Use deque instead of stack (more optimized)
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0 ; i < numCourses ; i++){
            adjacency.add(new ArrayList<>());
        }

        for(int courses[]: prerequisites){
            int pre = courses[1];
            int course = courses[0];
            adjacency.get(pre).add(course);
        }

        int state[] = new int[numCourses];

        for(int i = 0 ; i < numCourses; i++){
            if(state[i] == 0){
                if(hasCycle(state, adjacency, stack, i))
                    return new int[]{};
            }
        }
            
        int result[] = new int[numCourses];

        for(int i = 0 ; i<numCourses; i++)
            result[i] = stack.pop();


        return result;
    }

    private static boolean hasCycle(int[] state, List<List<Integer>> adjacency, Deque<Integer> stack, int node){
        if(state[node] == 2)
            return false;
        if(state[node] == 1)
            return true;

        state[node] = 1;

        for(int neighbour: adjacency.get(node)){
            if(hasCycle(state, adjacency, stack, neighbour))
                return true;
        }   
        state[node] = 2;
        stack.push(node);
        return false;
    }

    public static int[] findOrderKahn(int numCourses, int[][]prerequisites){

        List<List<Integer>> adjacency = new ArrayList<>();
        int inDegree[] = new int[numCourses];

        for(int i = 0 ; i < numCourses ; i++)
            adjacency.add(new ArrayList<>());

        for(int courses[]:prerequisites){
            adjacency.get(courses[1]).add(courses[0]);
            inDegree[courses[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Add all the nodes with indegree == 0 (remove non dependent nodes first)
        for(int i = 0 ; i < numCourses; i++){
            if(inDegree[i] == 0)
                queue.offer(i);
        }

        int order[] = new int[numCourses];
        int index = 0;

        while(!queue.isEmpty()){
            int course = queue.poll();

            order[index++] = course;

            for(int next : adjacency.get(course)){
                // One degree reduced for the next
                if(--inDegree[next] == 0)
                    queue.offer(next);
            }
           
        }
        return index == numCourses ? order : new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrderKahn(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
    }
}