


Theorem states that, upto a certain point in the array-- all the frequencies of 
the elements (majority and non majority will cancel themselves).

For example: 1,2,1,1,3,1,1 --> (len = 8 // len/2 = 4)
Element is 1 ; Count is 1
Now, Element is 2 ; Count is 0 (1 != 2  --> count--)
Count is 0 so change the element value (Element -> 1 ; nums\[3]) and again count is 1)
Element is again 1, count++ --> count = 2
Element is 3, (1 != 3) --> count -- --> count = 1
Element is 1 --> count++ --> count = 2
Element is 1 --> count++ --> count = 3

1. The count is not important, The value in element will be the majority element **IF IT EXISTS**
2. To check if the element really is the majority element, then traverse the array again and keep it's frequency count.
3. If the frequency > nums.length/2 Then it is the majority element
4. Else There is no majority element in the array


