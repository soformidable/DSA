https://leetcode.com/problems/majority-element-ii/description/

Similar to : [[Majority Element in an Array greater than half of nums.length]]

List\<Integers> lst :: where lst(i)   > nums.length/3

Using Hashmap  --> (NOT OPTIMAL  :: Still works)


## ***Observation***:
		The elements in an array where the frequency > nums.length/3 <= 2
		At most an array can have 2 elements where frequency is > len/3



**Using Boyer-Moore's majority voting theorem**

Theorem : [[Moore's majority voting theorem]]

1. Use two Variables e1 and e2 (candidates)
2. Use two frequencies for each variable c1 and c2
3. Create an if-else structure
4. Whenever a new element is identified --> REDUCE THE COUNT OF BOTH MAJORITY ELEMENTS  
5. Finally traverse the array and find out the true counts of e1 and e2
	1. If they are > nums.length/3 --> Add to lst


> [!NOTE]
> WHY REDUCE BOTH C1 AND C2 WHEN A NEW ELEMENT APPEARS:
> 
> In the `n/3` case:
> 
> - There can be **at most 2 elements** with frequency > n/3.
>     
> - When a **third unique element** appears, it is in "conflict" with both current candidates.
>     
> - To **maintain fairness**, you reduce **both counts**, as if the new element cancels one instance of each candidate.
> 
> ### ✅ Why not just reduce one of them?
> 
> Because you're not trying to compare the new element with one candidate — you’re saying:
> 
> - "This element is not **either** candidate"
>     
> - So it's valid to **cancel support for both existing candidates**


Code:

```
    public static List<Integer> majorityElementN3(int nums[]){

        int e1 = 0 , e2 = 0, c1 = 0 , c2 = 0;

        List<Integer> lst = new ArrayList<Integer>();

        for(int x : nums){

            if(x == e1)

                c1++;

            else if (x == e2)

                c2++;

            else if (c1 == 0){

                e1 = x;

                c1 = 1;

            }

            else if (c2 == 0){

                e2 = x;

                c2 = 1;

            }

            else{

                c1--;

                c2--;

            }

            }

        int cn1=0, cn2=0;

        for(int x : nums){

            if(x == e1) cn1++;

            if(x == e2) cn2++;

        }

        if(cn1 > nums.length/3) lst.add(e1);

        if(cn2 > nums.length/3  && !lst.contains(e2)) lst.add(e2);

        return lst;

    }

```



