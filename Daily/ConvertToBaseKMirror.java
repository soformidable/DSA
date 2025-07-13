public class ConvertToBaseKMirror {

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
    //     String s = String.valueOf(n);
    //     return s.equals(new StringBuilder(s).reverse().toString());
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

    public static void main(String args[]){
        System.out.println(kMirror(2,5));
        //StringBuilder s = new StringBuilder(convertToBaseK(23, 2));

    }
}
