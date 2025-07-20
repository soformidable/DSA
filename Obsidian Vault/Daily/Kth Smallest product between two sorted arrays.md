

Given two arrays:
nums1 and nums2 (both are zero indexed and already sorted)
i identifies nums1 index
j identifies nums2 index

product = nums1\[i] x nums2\[j]
Find the kth smallest product (1 indexed -> 1,2,3,4,.....)

Idea is to use Binary search.




```
     public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {

        long left = -10000000000L;

        long right = 10000000000L;

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (countProducts(nums1, nums2, mid) < k) {

                left = mid + 1;

            } else {

                right = mid;

            }

        }

        return left;

    }

    private static long countProducts(int[] nums1, int[] nums2, long target) {

        long count = 0;

        for (int num1 : nums1) {

            if (num1 == 0) {

                if (target >= 0) count += nums2.length;

                continue;

            }

            int low = 0, high = nums2.length;

            while (low < high) {

                int mid = low + (high - low) / 2;

                long product = (long) num1 * nums2[mid];

                if (product <= target) {

                    if (num1 > 0) low = mid + 1;

                    else high = mid;

                } else {

                    if (num1 > 0) high = mid;

                    else low = mid + 1;

                }

            }

            count += (num1 > 0) ? low : nums2.length - low;

        }

        return count;

    }
```

