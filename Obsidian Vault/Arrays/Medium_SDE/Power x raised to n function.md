
Link: https://leetcode.com/problems/powx-n/description/


To create a function double myPow(double x , int n) ---> x^n

n can be negative or positive

Brute Force:

1. Loop from 1 to n 
2. res = res * x
3. If n is negative then x = (1/x)


Binomial Exponentiation

Idea: 
1. Create another variable long nn to take the value from n.  (Flip the sign) 
2. loop while(nn>0)
3. If nn is odd then
	1. res = res * x  
	2. Then nn--;
4. Else (nn is even) then
	1. x = x * x;  (Binomial exponentiation)
	2. n = n/2;
5. Finally check if n > 0 
	1. Return res if true
	2. Else return (long)(1.0)/(long)(res) ----? 1/res


long nn = (n > 0) ? (long)n : (long)(n * (long)-1);
**Flipping signs of an integer to a long value** :
[[Integer Overflow]]


Code:

```
        long nn = (n > 0) ? (long)n : (long)(n * (long)-1);

        double res = 1.0;

        while(nn > 0){

            if(nn % 2 == 1){

                res = res * x;

                nn--;

            }

            else{

                x = x * x;

                nn/=2;

            }

        }

        return((n > 0 ) ? res : (double)(1.0)/(double)(res));
```










