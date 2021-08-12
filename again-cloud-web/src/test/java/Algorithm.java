import java.util.Arrays;

/**
 * @author create by 罗英杰 on 2021/8/12
 * @description:
 */
public class Algorithm {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums={3,6,8};
        System.out.println(solution.minMoves2(nums));
    }
}

/**
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
 *
 */
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0,j = nums.length - 1, res = 0;
        while (i < j) {
            res += nums[j--] - nums[i++];
        }
        return res;
    }
}
