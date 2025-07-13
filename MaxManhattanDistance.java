import java.util.*;

public class MaxManhattanDistance {
        public static int maxDistance(String s, int k) {
    int x = 0, y = 0;
    
    for (char c : s.toCharArray()) {
        if (c == 'N') x++;
        if (c == 'S') x--;
        if (c == 'E') y++;
        if (c == 'W') y--;
    }
    
    int distance = Math.abs(x) + Math.abs(y) + k*2;
    return Math.min(distance, s.length());
        }

    public static void main(String args[]){
        
        System.out.println(maxDistance("NSWWEW",3));
    }
}
