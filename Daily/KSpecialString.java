import java.util.*;

class KSpecialString{
    public static int minimumDeletions(String word, int k) {
        HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
        char arr[] = word.toCharArray();
        for(char x : arr)
            hm.put(x,hm.getOrDefault(x,0)+1);
        System.out.println(hm);

        int freq[] = hm.values().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(freq);
        int min_changes = Integer.MAX_VALUE;
        int lowerbound = 0 , upperbound = 0;

        for(int i = 0 ; i < freq.length ; i ++){
            int delete = 0;
            lowerbound = freq[i];
            upperbound = freq[i] + k;
            for(int x : freq){
                if(x<lowerbound)
                    delete += x;
                else if(x > upperbound)
                    delete += x - upperbound;

                }
                min_changes = Math.min(min_changes , delete);
            }

        

        return min_changes;    
    }
    public static void main(String args[]){
        String str = "dabdcbdcdcd";
        int k = 2;
        System.out.println(minimumDeletions(str,k));
    }
}