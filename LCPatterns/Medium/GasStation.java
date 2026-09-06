public class GasStation {
public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currentTank = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            
            totalTank += netGas;
            currentTank += netGas;

            // If we run out of gas, station 'i' cannot be reached from 'startStation'.
            // Reset our start station to the next station 'i + 1'.
            if (currentTank < 0) {
                startStation = i + 1;
                currentTank = 0; // Reset current trip fuel
            }
        }

        // If total gas >= total cost, startStation is guaranteed to be valid
        return totalTank >= 0 ? startStation : -1;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }
}
