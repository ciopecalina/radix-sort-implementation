import java.util.*;

class RadixSortUtils {

    static int numLength(int num) {
        if (num == 0) {
            return 1;
        }

        int length = 0;
        while (num > 0) {
            length++;
            num /= 10;
        }

        return length;
    }

    static int getDigitAtIndexOfFromEnd(int num, int index) {
        num /= Math.pow(10, index);
        return num % 10;
    }

    static void reverseArray(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

public class Main {

    private static final int ASCENDING_ORDER = 1;

    private static void radixSort(int[] nums, boolean ascending) {
        int maxDigitsNr = 0;
        for (int number : nums) {
            int currentDigitsNr = RadixSortUtils.numLength(number);
            if (currentDigitsNr > maxDigitsNr) {
                maxDigitsNr = currentDigitsNr;
            }
        }

        for (int digitIndex = 0; digitIndex < maxDigitsNr; digitIndex++) {
            ArrayList<Deque<Integer>> queues = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                queues.add(new ArrayDeque<>());
            }

            for (int number : nums) {
                int digit = RadixSortUtils.getDigitAtIndexOfFromEnd(number, digitIndex);
                queues.get(digit).offer(number);
            }

            int index = 0;
            for (Deque<Integer> queue : queues) {
                while (!queue.isEmpty()) {
                    nums[index] = queue.pop();
                    index++;
                }
            }
        }

        if (ascending != true) {
            RadixSortUtils.reverseArray(nums);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int order = scanner.nextInt();
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = scanner.nextInt();
        }

        boolean ascending = (order == ASCENDING_ORDER ? true : false);

        radixSort(nums, ascending);

        System.out.println(Arrays.toString(nums));
    }
}
