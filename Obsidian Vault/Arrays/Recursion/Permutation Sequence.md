
Problem: https://leetcode.com/problems/permutation-sequence/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Recursion/PermutationSequence.java


Approach1 :

1. Main function will have a boolean used\[1 to n+1] (1-indexed) --> initialised as false
2. Create a helper function that will return the list of the sequences
3. Base case is if the current str.length() == n
	1. add string to the List\<String>
	2. use a loop from i = 1 to i<=n
	3. check if used\[i] is false
		1. then set used\[i] = true (So that if the string starts with 2 then 223 or 232... should not be created)
		2. recurse by calling the function by incrementing i's value
		3. Backtrack --> reset used\[i] = true (for other branches)
4. Break out of the function if we reach lst.size() == k
5. Collection.sort and return lst.get(k-1) (0-indexed)


Code:
```
    public static String getPermutation(int n, int k) {

  

        boolean used[] = new boolean[n+1];

  

        List<String> lst = new ArrayList<String>();

        getPermutationList("",used,n,k,lst);

        Collections.sort(lst);

        return(lst.get(k-1));

    }

  

    public static void getPermutationList(String str, boolean used[], int n, int k, List<String> lst){

        if (str.length() == n){

            lst.add(str);

            return;

        }

  

        if(lst.size() > k)

            return;

  
  

        for(int i = 1 ; i <= n ; i++)

            if(!used[i]){

                used[i] = true;

                getPermutationList(str + i, used, n, k, lst);

                used[i] = false;

            }

  

    }
```
