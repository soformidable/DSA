
Problem : https://leetcode.com/problems/add-two-numbers/description/

Code: https://github.com/soformidable/DSA/blob/main/LinkedList/Medium/AddTwoNumbersLL.java


Approach 1:

1. Create 2 current nodes
2. Create two StringBuilder str1 and str2
3. Run 2 (Separate) while loops for current1 and curren2
	1. str1.append(current1.val)
	2. same for str2
4. Reverse both str1 and str2
5. Create two variables i & j (int) and carry = 0 (int)
	1. i = str1.length()-1
	2. i = str2.length()-1
	3. carry = 0
6. Run a While Loop where (i>=0 || j>=0 || carry >0)
	1. ==|| is used to ensure the loop doesn't exit when end of the string is reached for str1 OR str2==
	2. digit1 = str1.charAt(i)
	3. Same for digit 2
	4. str3.append(sum%10) 
	5. carry = sum / 10 
7. The str3 is already reversed at this point as we added from the last digit of str1 and str2
8. Initialize dummy and current
9. Run a FOR loop k<str3.length()
	1. add new nodes with str3.charAt(k)
10. return dummy.next


Approach 2 (OPTIMAL):

1. Initialize dummy and current
2. carry = 0;
3. Run a while loop (l1!null || l2 != null || carry!=0 (same as >0))
4. sum = carry
5. ==TWO SEPARATE IF to add the digits and make sure we don't seek nodes beyond the length of l1 and l1==
	1. if(l1!=null)
		1. sum+=l1.value
		2. l1=l1.next;
	2. Same for l2
6. create new node from current.next as sum%10
7. carry = sum/10
8. current = current.next