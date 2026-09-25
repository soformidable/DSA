public class MinimumInRotatedSortedArray{

    public static int findMin(int[] nums) {
        // Edge case: single element array
        if (nums.length == 1) {
            return nums[0];
        }

        int start = 0, end = nums.length - 1;

        // Binary search for the minimum element
        // The minimum is the element that has a greater element to its right
        // (i.e., the "pivot" point where rotation happened)
        while (start < end) {
            int mid = start + (end - start) / 2;

            // If mid element is greater than the rightmost element,
            // the minimum lies in the right half (excluding mid)
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            }
            // Otherwise, the minimum lies in the left half (including mid)
            else {
                end = mid;
            }
        }

        // start == end, pointing to the minimum element
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{0,1,2,4,5,6,})); // Output: 1
    }

}