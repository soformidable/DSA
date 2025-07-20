
n => size of array
m => value ranges from (1,m)
k => total pairs of adjacent elements

arranging m elements in n array size =

total combinations = m ^ n


Total number of pairs of adjacent elements in array of size n = (n-1)

Total blocks = n - k (array is divided in to blocks of adjacent elements and non adjacent elements)

The number of ways to split the blocks of equal adjacent elements =  (n-1 C k) 

Placing elements that have different adjacent values = m . (m-1) ^ (n-k-1) 

Total arrays possible = (n-1 C k) . m . (m-1) ^ (n-k-1)


*Modulo* (used to reduce large m and n)

mod = 10^9 + 7

fact % mod
invFact % mod


