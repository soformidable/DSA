
Problem: Given a string, find all the sub lists with the possible palindromes -->
s = "abba" ;; output = \[\[a, b, b, a], \[a, bb, a], \[abba]]

Code: https://github.com/soformidable/DSA/blob/main/Arrays/Recursion/PalindromeCombinations.java


Approach:

1. Create two helper functions 
	1. isPalindrome --> Boolean
	2. getCombination(original string, int start_index, List<\String>,List<List<\String>>)
2. In the helper function iterate through the string from left to right ---> with a for loop end = start + 1
	1. at every substring formed by start <--> end, check if it is a palindrome and make the cut
	2. At every 'end' index there are two options:
		1. Keep building the substring by adding characters and moving end forward
		2. Partition the string and add to current
3. backtracking the current List to explore other paths
4. Base case is if the start index == original_string.length
	1. Add the current list to result list
	2. return


Code:

```
        public static List<List<String>> partition(String s) {

            List<List<String>> result = new ArrayList<List<String>>();

            List<String> lst = new ArrayList<String>();

            String str = "";

            getCombinations(s, 0, lst, result);

  

            return result;

        }

  

        public static void getCombinations(String str, int start, List<String> current, List<List<String>> result){

            if(start == str.length()){

                result.add(new ArrayList<String>(current));

                return;

            }

  

            for(int end = start + 1 ; end <= str.length() ; end++) {

                String substring = str.substring(start, end);

  

                if(isPalindrome(substring)){

                    current.add(substring);

                    getCombinations(str, end, current, result);

                    current.remove(current.size() - 1);

                }

            }

  

        }

  

        public static boolean isPalindrome(String str){

            int left = 0 , right = str.length() - 1;

            while(left <= right){

                if(str.charAt(left) != str.charAt(right)){

                    return false;

                }

                left++;

                right--;

            }

  

            return true;

        }
```