
https://leetcode.com/problems/pascals-triangle/description/

![[Pasted image 20250616180233.png]]


rth row and cth column  = rCc

r! / c! (r-c)!


Multidimensional ArrayList:

```
List<List<Integer> lst = new ArrayList<List<Integer>>();
lst.add(new ArrayList<Integer>());
lst.get(0).add(0,1);
lst.get(0).add(1,2);
lst.add(new ArrayList<Integer>());
lst.get(1).add(0,3);
lst.get(1).add(1,4);
```

Final List -> [[1,2],[3,4]]



*Type 1:* 
find nth row and cth column ->
any particular (n,r) is given by nCr
= n!/r!(n-r!) 

Optimal way ->

5C2 = 5 x 4 x 3! / 2 x 1 x 3! = 5 x 4 / 1 x 2
thus nCr ~= (n-r)!/r!

res = res * (i)
res = res / (n-i)

*Type 2:*
For printing the entire row of the row num n:

```
for(int i = 1; i < n ; i++){
	res = res * (n-i)	 
		res = res / i
}
```

O(n) -> Time complexity
O(1) -> Space complexity



**Type 3: return the entire Pascal triangle through List\<List>**

```
List<Integer> generateRow(int row){
		List<Integer> temp = new ArrayList<Integer>();
			int ans =1;
			lst.add(1); ----> 1st element is always 1 and to avoid dividing by zero when considered zero indexed columns;
	for(int col=1;col<row;col++){
		ans = ans * (row - col);
			ans = ans / col; 
			temp.add(ans);
	}
	return temp;
}
```

// Create a another loop that runs from 1 to row and then call the generate row function.
return the result into another List\<List\<Integer>>.






