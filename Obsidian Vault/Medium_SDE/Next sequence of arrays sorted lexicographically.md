
https://leetcode.com/problems/next-permutation/description/

Brute force approach:
Find all the possible combinations using recursion and then a linear search to find the i+1th array in the List<List<Integer\>> 
when i = given combination.
complexity = n! x n


*Observation*:
The permutations when sorted lexicographically are always in a increasing order from left to right
left ------> right  (increasing)
right ------> left (decreasing)

(1,2,3),(1,3,2),(2,1,3),(2,3,1),(3,1,2),(3,2,1)

*Note: If the given input is the last array, return the first because there is no increasing permutation left*

***************************************************************************

**Break Point**

It is the index i where arr\[i]<arr\[i+1]
This is where the left part is fixed and right part is already in the increasing order from left to right.

Steps:

1. Find the breakpoint:
		arr(i) < arr(i+1)
2. Find the arr(i) such that 
		arr(i) > break_point but smallest in the right subarray.
		 But we don't actually find the global smallest in the right subarray because the right is already in a non increasing order. (MAXED OUT). Thus only checking for the first occurrence of the slightly larger than breakpoint from right to left is enough
3.  Swap the arr(i) with break_point
4. Place the remaining elements in a sorted order(ASC). This is done by reversing the right subarray as they are already in a decreasing order (left to right).

functions:
1. next_permutation(int arr[])
2. swap(int arr[], index ,i)
3. reverse(int arr[],index)




