package Java_DS_Algo;
import java.util.*;


public class Partition{
    
    /** 
     * QUES :
     * 1 CAPACITY TO SHIP
     * 2 SPLIT ARRAY LARGEST SUM
     * 3 MAX SUM AFTER PARTITIONING
     * 4 
     * 
     * if no of partitions is <=k, we are getting bigger chunks.
     * then we need to decrease the capacity
    */

    // https://www.geeksforgeeks.org/split-the-given-array-into-k-sub-arrays-
    // such-that-maximum-sum-of-all-sub-arrays-is-minimum/
    int minSplit(int[] arr, int k){
        int max = 0, sum = 0;
        int res= 0;

        for(int i : arr){
            max = Math.max(max, i);
            sum+=i;
        }

        int lo = max, hi = sum;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            int x = maxPartition(arr, mid);
            if(x<=k){
                res = mid; hi = mid-1;
            }
            else lo = mid+1;
        }
        System.out.println("minimized max sum subarray is "+res);
        return res;
    }

    int maxPartition(int[] arr, int capacity){
        int sum = 0; int partition = 0;
        for(int i : arr){
            sum+=i;
            if(sum>capacity) {
                sum = i;
                partition++;
            }
        }
        return partition;
    }

    /** 
     * POINTS :
     * 1 IF CAPACITY = SUM OF ALL WTS, IT CAN BE TRANSFERRED IN A DAY
     * 2 CAPACITY CAN'T BE LESSER THAN MAX WT
     * 3 START FROM MIN AND GO TILL MAX
     * 4 CHECK IF ANY WT MATCHES THE ISSAFE CONDN
     * 
     * ISSAFE CONDN : 
     * ADD UP THE WEIGHTS TILL IT IS LESSER THAN CURRENT CAPACITY
     * ELSE WE WILL LOAD THIS WT FROM THE NEXT DAY. 
     * ALSO WE WILL TRY TO ADD AS MUCH WT AS POSSIBLE
     * (LESS THAN CURRENT CAPACITY, OF COURSE) THAT'S WHY SUM+=WEIGHT
     * 
     * normal version w/o binary search
     * for(int i = minW, i<=maxW; i++){
     *    if(isSafe(i, weights, D)) return i;
     * }
     * 
     * BINARY SEARCH:
     * START FROM MID, IF MID IS VALID, WE LOOK FOR A LESSER CAPACITY
     * ELSE WE MOVE TOWARDS HIGHER CAPACITY
     * 
    */
    // https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0; int max = 0;
        for(int i : weights){
            sum+=i;
            max = Math.max(max, i);
        }
        
        int minW = max; int maxW = sum;
        
        while(minW<maxW){
            int mid = minW + (maxW - minW)/2;
            if(isSafe(mid, weights, D)){
                maxW = mid;
            } else minW = mid+1;
        }
        return minW;
    }
    
    boolean isSafe(int capacity, int[] weights, int D){
        int sum = 0; int days = 1;
        
        for(int w : weights){
            if(sum+w<=capacity) sum+=w;
            else{
                days++;
                sum = w;
            }
        }
        return days<=D;
    }

    /** 
     * SIMILAR TO ABOVE : WHENEVER QUESTION ASKS FOR CONTIGUOUS PARTITION 
     * THINK OF BINARY SEARCH
     * 
     * idea is if we can find max of all partitions less than mid and 
     * no of partitions <= m, then we check if an even lesser max is possible.
     * so hi = mid-1;
     * 
     * 1 MIN VALUE = MAX AND MAX VALUE = SUM OF ALL ELS
     * 2 BIN SEARCH FROM MIN TILL MAX
     * 3 IF SUM EXCEEDS MID, TAKE A NEW PARTITION
     * 4 RETURN TRUE ONLY IF NO OF PARTITIONS IS <= m
     * 
     * imp : partitions = 1
    */
    // https://leetcode.com/problems/split-array-largest-sum/
    public int splitArray(int[] nums, int m) {
        int sum =0, max = 0;
        for(int i : nums){
            max = Math.max(max, i);
            sum+= i;
        }
        
        int lo = max; int hi = sum;
        
        while(lo<=hi){ //
            int mid= lo +(hi-lo)/2;
            
            boolean x = checkMax(nums, mid, m);
            if(x) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo;
    }
    
    boolean checkMax(int[] nums, int limit, int maxPartitions){
        int sum = 0; int partitions = 1; // 
        for(int i : nums){
            sum+=i;
            if(sum>limit){
                partitions++;
                sum = i;
            }
        }
        
        return partitions<=maxPartitions;
    }

    /** 
     * POINTS :
     * 1 FILL DP ARRAY FOR FIRST K ELS, THEN FOR OTHER ELS
     * 2 FOR FIRST K, WE FIND MAX AND STORE FOR ALL
     * i+1 as we start from i=0, so i+1 is the size
     * 
     * 3 FOR NEXT ELS
     * 
     * max at index i =
     * 
     * dp[i-1] + arr[i]*1 or
     * dp[i-2] + arr[i]*2 or
     * dp[i-3] + arr[i]*3 ...
     * 
     * arr[i-size+1] -> max 
     * dp[i-size] + max*size
     * 
     * imp : whichever is max of the last k els is selected as max
    */
    // https://www.youtube.com/watch?v=RNGA4gHvC7E
    // https://leetcode.com/problems/partition-array-for-maximum-sum/
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length; int[] dp = new int[n];
        int max = 0;
        
        for(int i =0; i<k; i++){
            max = Math.max(max, arr[i]);
            dp[i] = max*(i+1);
        }
        
        for(int i =k; i<n; i++){
            int subMax = arr[i];
            for(int size = 1; size<=k; size++){
                // whichever is max of the last k els is selected as max
                // arr
                subMax = Math.max(subMax, arr[i-size+1]);
                // dp, dp[i] = dp[i-2]+max*2
                dp[i] = Math.max(dp[i], dp[i-size]+ subMax*size);
            }
        }
        for(int i : dp) System.out.print(i+", ");
        return dp[n-1];
    }

    // https://leetcode.com/problems/partition-equal-subset-sum/
    // size, first row
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        for(int i : nums) sum+=i;
        if(sum%2!=0) return false;
        int m = sum/2;
        boolean[][] dp = new boolean[n+1][m+1];
        
        for(int i =0; i<=n; i++) dp[i][0] = true;
        
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                if(j<nums[i-1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
            }
        }
        return dp[n][m];
    }


    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length; int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if (sum%k != 0) return false;
        
        int individual = sum/k;
        boolean[] visited = new boolean[n];

        return partition(0, visited, nums, k, individual, 0);
    }
    
    boolean partition(int index, boolean[] visited, int[] nums, int k, int individual, int currSum){
        if (k == 0) return true;
        // if one partition reaches target sum, recur for other k-1 partitions
        if (currSum == individual) return partition(0, visited, nums, k - 1, individual, 0);

        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && currSum + nums[i] <= individual) {
                visited[i] = true;
                // start from next index
                if (partition(i + 1, visited, nums, k, individual, currSum + nums[i])) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }


    // https://stackoverflow.com/questions/63329608/
    // how-can-i-divide-an-array-into-k-sub-arrays-such-that-the-sum-of-the-number-of-d#new-answer

    // https://stackoverflow.com/questions/41302115/
    // optimize-divide-an-array-into-continuous-subsequences-of-length-no-greater-than


    public static void main(String[] args) {
        Partition part = new Partition();

        // Scanner, parse, close 
		Scanner input = new Scanner(System.in);
        System.out.println("enter array size");
        int n  = Integer.parseInt(input.nextLine());
        int[] arr = new int[n]; 
        int index =0;
        
        for(int i = 0; i<n; i++){
            System.out.println("enter next entry");
            arr[index++] = Integer.parseInt(input.nextLine());
        }
        
        System.out.println("enter k limit");
        int k = Integer.parseInt(input.nextLine());
        input.close();

        part.minSplit(arr, k);
        
        // int[] arr = new int[]{1,2,3,4};
        // part.minSplit(arr, 3);
    }
}