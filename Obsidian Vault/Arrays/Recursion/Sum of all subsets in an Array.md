
problem : Given an Array arr and the size N. return a list containing all the sums of the subsets (including 0)

Code: https://github.com/soformidable/DSA/blob/main/Arrays/Recursion/SumOfSubsets.java

**Recursive Approach:**

1. Create a helper function that takes index, sum, N, arr, subset List
	1. This will run recursively and get called from the primary function
	2. Signature is void
2. Base case for recursion is index == N
	1. subset.add(sum)
	2. return
3. Primary function (driver) will call the helper
	1. (0,0,N,arr,subset)
	2. sort it using Collections.sort
	3. return subset
4. NOTE : If duplicates are to be avoided, use Set instead of List/ArrayList
5. **NOTE: ONLY LEAF NODES are added into the subset because of the base condition if (index == N)**


![[Pasted image 20251115120710.png]]


Code:

```
   public static List<Integer> getSubsetSum(int N,int arr[]){

        ArrayList<Integer> lst = new ArrayList<Integer>();

        getSubsetHelper(0, 0, N, arr, lst);

        Collections.sort(lst);

        return lst;

    }

    // Use Set<Integer> to avoid duplicates in the final subset for example {1,-1,2}

    public static void getSubsetHelper(int index, int sum, int N, int arr[], List<Integer> subset){

  

        if(N == index){

            subset.add(sum);

            return;

        }

  

        // Creating "choose the element" tree

        getSubsetHelper(index + 1, sum + arr[index], N, arr, subset);

  

        // Creating "not to choose the element" tree

        getSubsetHelper(index + 1, sum, N, arr, subset);

    }
```