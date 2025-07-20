

1. Divide the number repeatedly by k
2. add the remainder to a string (Use StringBuilder ideally)
3. divide the n by k till n>0
4. finally reverse the string because the digits are placed in a reverse order



```
    public static String convertToBaseK(long num, int k) {

        StringBuilder sb = new StringBuilder();

        while (num > 0) {

            sb.append(num % k);

            num /= k;

        }

        return sb.reverse().toString();

    }
```