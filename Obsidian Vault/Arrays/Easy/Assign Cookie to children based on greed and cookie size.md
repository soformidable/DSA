
Problem: https://leetcode.com/problems/assign-cookies/description/
Code: https://github.com/soformidable/DSA/blob/main/Arrays/Easy/AssignCookies.java


Approach:

1. Sort the cookie and greed arr 
2. use pointer for greed starting from length - 1
3. run for in reverse
4. match the greed with cookie size
	1. if cookie\[j] >= greed\[i]
		1. happy++;
		2. j--;
5. return happy


Code:
```
    public static int assign(int cookie[], int greed[]){

        if(greed.length == 0 || cookie.length == 0 || greed == null || cookie == null)

            return 0;

        int happy = 0;

        int j = cookie.length-1;

  

        Arrays.sort(cookie);

        Arrays.sort(greed);

  

        for(int i = greed.length-1 ; i>=0 ; i--){

            if(j >= 0 && cookie[j]>=greed[i]){

                happy++;

                j--;

            }

        }

        return happy;

    }
```