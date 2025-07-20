
Integer.Max_Value = 2147483647
Integer.Min_Value = -2147483648

**NOTE**

Integer.Max_Value    ==**!=**==   -Integer.Min_Value

When casting from int to long, 
consider the overflow.

int n = Integer.Min_Value

long val = (n > 0) ? (long)n :  -(long)n

