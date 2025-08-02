
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/


Approach 1:
O(n^2)

1. Create string str = ""
2. For loop from - to s.length() - 1
3. index = i + 1 and less than s.length()
4. start while loop
	1. Check if charAt(index) already exists in str.
	2. no -> str+= ..
	3. yes -> break while
5. check maxLen with str.length()
6. return maxLen
Code:
```
    public static int lengthOfLongestSubstring(String s) {

        if(s.length() == 0 || s == null)

            return 0;

        String str = "";

        int maxLen = 0, index = 0;

  

        for(int i = 0 ; i < s.length() ; i ++){

            str = String.valueOf(s.charAt(i));

            index = i+1;

            while(index < s.length()){          

            if(!str.contains(String.valueOf(s.charAt(index)))){

                str+=String.valueOf(s.charAt(index));

                index++;

            }

            else

                break;          

            }

            maxLen = Math.max(str.length() , maxLen);

        }

        return maxLen;

    }
```


**Approach 2 (Optimal):**

1. **Sliding Window Technique**:
    
    - Uses two pointers (`left` and `right`) to represent the current window
        
    - Expands the window by moving `right` forward
        
    - Shrinks the window from the left when a duplicate is found
        
2. **Efficient Lookups**:
    
    - Utilizes a `HashSet` for O(1) character existence checks
        
    - Avoids expensive string operations like `str.contains()`
        
3. **Optimal Time Complexity**:
    
    - **O(n)**: Each character is processed exactly twice (added once and removed once)
        
    - **O(min(n, m)) space**: Where m is the character set size (max 26 for lowercase)
        

### Explanation:

1. **Initialization**: Create an empty set to track characters in the current window
    
2. **Expand Window**:
    
    - Move `right` pointer to include new characters
        
    - For each new character:
        
        - If it's a duplicate, shrink window from left until duplicate is removed
            
        - Add new character to set
            
        - Update maximum window length
            
3. **Return Result**: The largest window size encountered during iteration


Code:

```
    public static int lengthOfLongestSubstring(String s) {

        if(s.length() == 0 || s == null)

            return 0;

        HashSet<Character> hs = new HashSet<Character>();

        long maxLen = 0L;

        int left = 0;

        for(int right = 0 ; right < s.length() ; right ++){

            char current = s.charAt(right);

  

            while(hs.contains(current)){

                hs.remove(s.charAt(left));

                left++;

            }

            hs.add(current);

            maxLen = Math.max(maxLen , ((long)right - (long)left) + (long)1 );

        }

        return (int)maxLen;

    }
```
