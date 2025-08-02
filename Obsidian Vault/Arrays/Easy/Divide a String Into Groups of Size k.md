

To divide the characters of string into a length of k.
Fill the last group (if present -> s.length%k !=0) with the char fill.


**Use StringBuilder instead of String**

Calculate the number of groups ->
Len = (s.length%k - 1)/k

1. Use StringBuilder to create a string and create groups
2. loop from 0 to len (0-3, 0-4,etc)
3. start = i*k 
4. end = Math.min(s.length,end) -> This ensures we don't access values beyond the string length
5. StringBuilder sb = new StringBuilder(s.substring(start, end));
6. If there is an incomplete group use a while(sb.length<k) {sb.fill(char)}
7. Finally str\[i] = sb.toString() (str is the char array of length len)




```
    public static String[] divideString(String s, int k, char fill) {

  

    if(s == null || s.length() == 0 )    

    return new String\[0];

    int len = ((s.length()+k) - 1)/k;

    String str[] = new String\[len];

  
  

    for(int i = 0 ; i < len ; i++){

        int start = i * k;

        int end = Math.min(s.length(), start+k);

        StringBuilder sb = new StringBuilder(s.substring(start, end));

        while(sb.length()<k)

            sb.append(fill);

        str[i] = sb.toString();

    }

  

     return str;

    }
```


