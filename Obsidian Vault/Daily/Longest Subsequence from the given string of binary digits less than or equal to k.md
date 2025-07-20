

Given a string s = "10010110" and k = 5

Find the longest subsequence such that when converted to base10 then value <= k

**Subsequence means selecting characters from a string or an array by deleting the characters in between. The characters cannot be moved. Just selected or unselected**



Optimal approach:

1. Select all the 0s because they do not have any effect on the value
2. Starting from right -> left (LSB -> HSB) if you encounter 1 multiply and add the value (v = 0) by 2^power where power start with 0. 
3. Each time you encounter 1 check if value converted to decimal by adding that 1 <=k
	1. If true then power++ and count++ (currently count = number of zeroes)
	2. If not then continue to count all zeroes till the end.

**Note: The 2^power can be imitated by using 1L << power where 1L represents 1 long to prevent overflow.**


