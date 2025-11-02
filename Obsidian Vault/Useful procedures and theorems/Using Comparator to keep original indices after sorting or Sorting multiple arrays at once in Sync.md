


##### NOTE

#### Arrays.sort(indices, Comparator.comparingInt(a -> nums\[a]));

This handles large numbers and effective avoids Integer Overflow



Integer\[] indices = new Integer\[nums.length]; 
for (int i = 0; i < indices. Length; i++) {
    indices\[i] = i;
}

Arrays.sort(indices, (a, b) -> nums\[a] - nums\[b]);
Step 1: Creating an indices array
You first create an array of indices (not actual numbers from nums):

nums:    \[ 50, 10, 30 ]
indices: \[ 0,  1,  2  ]
So indices\[i] = position of each element in nums.

Step 2: Sorting the indices using a Comparator
Now you call:

java
Copy code
Arrays.sort(indices, (a, b) -> nums\[a] - nums\[b]);
Arrays.sort() will try to sort the indices array.

The comparator (a, b) -> nums\[a] - nums\[b] tells it how to compare two indices a and b.

Step 3: What the comparator does
Let’s say the sorting algorithm compares a = 0 and b = 1.
The comparator returns:

java
Copy code
nums\[0] - nums\[1] = 50 - 10 = 40
If result > 0 → means nums\[a] > nums\[b], so a should come after b.

If result < 0 → means nums\[a] < nums\[b], so a should come before b.

If result == 0 → they’re considered equal in ordering.

So effectively, you’re sorting indices by comparing their corresponding nums values.

Step 4: Final result
After sorting:

nums:    \[ 50, 10, 30 ]
indices: \[ 1,  2,  0  ]
Now, if you iterate over indices:

java
Copy code
for (int i : indices) {
    System.out.print(nums\[i] + " ");
}
You’ll get:


10 30 50
— i.e., the nums array values in ascending order, but without actually modifying the original nums.

Step 5: Why use this approach?
✅ You keep nums intact (unmodified).
✅ You get a sorted ordering of indices that you can use for further processing — like ranking, reordering, or stable mapping between original and sorted positions.

This is common in problems like:

Sorting an array but needing to remember original positions (e.g., “return indices of sorted elements”).

Performing custom sorts on multiple arrays in sync.


