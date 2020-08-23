import java.util.*;

public class SortingTest {

    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
//            different version of quick sort
            int mid = partition(nums, l, r);
//            int mid = partition_3Select(nums, l, r);
//            int mid = partition_random(nums, l, r);
            quickSort(nums, l, mid - 1);
            quickSort(nums, mid + 1, r);
        }
    }

    public static int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }

    public static int partition_3Select(int[] nums, int l, int r) {
        int mid = l + (r - l)/2, pos1 = l, pos2 = r, pos = 0;
        if (nums[pos1] > nums[mid]) {
            pos1 = mid;
            mid = l;
        }
        if (nums[pos2] > nums[mid]) {
            pos = mid;
        } else if (nums[pos2] < nums[pos1]) {
            pos = pos1;
        } else {
            pos = pos2;
        }

        if (pos != l) {
            swap(nums, pos, l);
        }
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }

    public static int partition_random(int[] nums, int l, int r) {
        int pos = new Random().nextInt(r - l + 1) + l;
        int pivot = nums[pos];
        swap(nums, pos, l);

        while (l < r) {
            while (l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }

    public static boolean checkIfSorted(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int len = 1000000;          //the length of array
        int[] arr = new int[len];   //the array to be sorted

        int count = 10;             //Sorting algorithm repeated time
        long time = 0;              //runtime

        for (int testCnt = 0; testCnt < count; testCnt++) {
            // randomly initialize the arry
            for (int i = 0; i < len; i++) {
                arr[i] = new Random().nextInt(10000);
            }

            long startTime = System.currentTimeMillis();
            quickSort(arr, 0, arr.length - 1);
            //Arrays.sort(arr);
            long endTime = System.currentTimeMillis();

            if (checkIfSorted(arr) == false) {
                System.out.println("ERROR!!!");
                break;
            }

            time += (endTime - startTime);
        }

        System.out.println("The average runtime is : " + time/(double)count);

    }

}
