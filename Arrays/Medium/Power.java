public class Power {
    public static double myPow(double x, int n) {
        long nn = (n > 0) ? (long)n : (long)(n * (long)-1);
        double res = 1.0;
        while(nn > 0){
            if(nn % 2 == 1){
                res = res * x;
                nn--;
            }
            else{
                x = x * x;
                nn/=2;
            }
        }
        return((n > 0 ) ? res : (double)(1.0)/(double)(res));
    }
    public static void main(String args[]) {
        System.out.println(myPow(2.0000  , -2147483648));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
