


Idea is to check if the number is a palindrome in base 10 (decimal) and base k

[[Converting a number (Integer) to base K]]


Instead of checking each and every integer starting from 1,
we create even and odd length palindromes

for example if i =12
evenLength = 1221
oddLength = 121

1. Start from len = 1 that runs to len x 10  (where n>0 for condition so that it keeps running)
2. another loop from  to i = len; n > 0 && i < len * 10
3. we generate even and odd length palindromes for each number using createPalindrome(long half , boolean isOdd)
4. The number passes through both function as 
	1. long p = createPalindrome(i, true);
	2. long p = createPalindrome(i, false);
5. Then we pass the p and base k into isBaseKPalindrome(Long n, int k)
	1. String baseKStr = convertToBaseK(n, k);
	2. return baseKStr.equals(new StringBuilder(baseKStr).reverse().toString());
6. If they are true then we add sum+= half and decrement the value n -> n--

Code:


```
   public static long kMirror(int k, int n) {

        long sum = 0;

        for (long len = 1; n > 0; len *= 10) {

            for (long i = len; n > 0 && i < len * 10; i++) {

                long p = createPalindrome(i, true);

                if (isBaseKPalindrome(p, k)) { sum += p; n--; }

            }

            for (long i = len; n > 0 && i < len * 10; i++) {

                long p = createPalindrome(i, false);

                if (isBaseKPalindrome(p, k)) { sum += p; n--; }

            }

        }

        return sum;

    }

  

    public static long createPalindrome(long half, boolean oddLength){

        String str = String.valueOf(half);

        StringBuilder sb = new StringBuilder(str);

        if (oddLength) sb.deleteCharAt(sb.length()-1);

        sb.reverse();

        return Long.parseLong(str + sb.toString());

    }

  

    // public static boolean isDecimalPalindrome(int n) {

    //     String s = String.valueOf(n);

    //     return s.equals(new StringBuilder(s).reverse().toString());

    // }

  

    public static boolean isBaseKPalindrome(Long n, int k) {

        String baseKStr = convertToBaseK(n, k);

        return baseKStr.equals(new StringBuilder(baseKStr).reverse().toString());

    }

  

    public static String convertToBaseK(long num, int k) {

        StringBuilder sb = new StringBuilder();

        while (num > 0) {

            sb.append(num % k);

            num /= k;

        }

        return sb.reverse().toString();

    }
	
```


