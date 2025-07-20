


***Greedy + Sliding Window***

Pre Requirement: Sorting the array of frequencies.

Required freq\[i] - freq\[j] <=k ; i,j from sorted frequencies arr.

“sliding” a window of allowed frequency range `[from, from + k]` over the sorted list, we ensure **all** frequencies inside the window automatically satisfy `|x - y| ≤ k`. Those outside either to the left must be deleted entirely, and those to the right must be shrunk down to `from + k`.

For `freq = [1,2,4]` and `k = 2`, if we stand at index 0 (`from = 1`), the valid window is `[1, 3]`. Only frequencies `1` and `2` lie inside; `4` is outside and would require deletions to bring it down to `3` (i.e., delete `4 - 3 = 1` occurrence).


Let `freq = [1, 2, 3, 4]` and `k = 2`.


Sorted freq = \[1, 2, 3, 4]  
We can try a sliding window of length k + 1 = 3.

- Starting at index 0 (from = 1), window = \[1, 2, 3] → Valid
    
    - Right side has freq\[3] = 4 → 4 > 3 → delete 1 to make it 3. 
    - Total deletions = 1. ✅
- Starting at index 1 (from = 2), window = \[2, 3, 4] → Valid
    
    - Left side has freq\[0] = 1 → completely delete 1 occurrence.
    - Total deletions = 1. ✅
- Starting at index 2 (from = 3), window = \[3, 4] → length < 3 → not enough
    
    - Add freq\[0] and freq\[1] (1 + 2 = 3 deletions needed)
    - Total deletions = 3 ❌

So the best is 1 deletion. 


```
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
		// Creating a window of allowed frequencies from freq to freq + k
            upperbound = freq[i] + k;

            for(int x : freq){

                if(x<lowerbound)

                    delete += x;

                else if(x > upperbound)
			// same as (freq[j] - freq[start] + k) where start is the fixed character index
                    delete += x - upperbound;


                }

                min_changes = Math.min(min_changes , delete);

            }

        return min_changes;    

    }
```



Optimization idea:

Instead of looping over all the frequencies, we can create a map<integer,integer>(frequence, number of characters that have the frequency)

As multiple characters can have the same frequencies, we only keep the unique frequency values.