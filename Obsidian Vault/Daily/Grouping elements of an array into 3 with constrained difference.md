


The problem is to group of 3 elements an array of length divisible by 3 and any two
elements have a difference <=k
Idea to solve is:
The grouping is done on the elements which are closer to each other so that 
they will have a difference <=k.

1. Sorting the array in a ascending order.
2. Jumping from i to i+2 because if max(group) - min(group) <=k
		then middle(group) will also satisfy the condition.

![[Pasted image 20250618104118.png]]

