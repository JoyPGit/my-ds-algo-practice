import java.util.*;

// https://leetcode.com/problems/distribute-candies-to-people
/** candies>=count helps to stop 
 * assign remaining candies to the next person
 * as we are traversing the array multiple times, 
 * use mod circular array concept
 */
public class Mathprob {
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int count = 1; int i =0;
        while(candies>=count){
            res[i%num_people] += count;       
            i++;
            candies-=count++;
        }
        if(candies!=0) res[i%num_people] += candies;
        
        return res;
    }

      // https://leetcode.com/problems/water-bottles/
      public int numWaterBottles(int numBottles, int numExchange) {
        int i = 0; 
        while(i <= numBottles){
            if(i%numExchange==0) {
                numBottles++;
            }
            i++;
        }

        // for(i =1; i<=numBottles; i++){
        //     if(i%numExchange==0) numBottles++;
        // }
        // System.out.println(numBottles);
        return numBottles;
    }
    
    // https://leetcode.com/problems/lemonade-change/
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        
        int c5 = 0; int c10 =0; 
        for(int i :bills){
            if(i==5) c5++;
            else if(i == 10) {
                c10++;
                if(c5>=1) c5--; 
                else return false;
            }
            else {
                if(c5>=1 && c10>=1){
                    c5--; c10--;
                } else if(c5>=3) c5-=3;
                else return false;
            }
            // System.out.println("c5 "+c5 + " c10 "+c10);
        }
        return true;
    }

    // https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        float a = 0; float b = 0; 
        float c= tomatoSlices, d = cheeseSlices;
        // 4*a+2*(cheeseSlices - a) = tomatoSlices;
        a = (c/2 - d); b = d - a;
        
        ArrayList<Integer> res= new ArrayList<>();
        if(a!=(int)a || b!=(int)b || (a*b)<0) return res;
        res.add((int)a); res.add((int)b);
        return (List<Integer>) res;
    }

    // https://leetcode.com/problems/water-and-jug-problem/

    // Find power(a, n) iteratively without extra space 
    // https://practice.geeksforgeeks.org/problems/abset-25327/1
    long power(int a, long b){
        //complete the function here
        if(a==0 || b==0) return a;
        if(b==1) return a;
        int base = a; int mul = a;
        int k =1;
        while(k<b){
            base*=mul;
            k++;
        }
        System.out.println("base "+base);
        return Long.valueOf((base)%(10^9+7));
    }

    // For a >= b >= c, a,b,c can form a triangle if a < b + c.
    // https://leetcode.com/problems/largest-perimeter-triangle/
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        if(n<3) return 0;
        
        for(int i=n-1; i>1; i--){
            if (A[i] < A[i - 1] + A[i - 2]) return (A[i] + A[i - 1] + A[i - 2]);
        }
        return 0;
    }

    // https://leetcode.com/problems/path-crossing
    public boolean isPathCrossing(String path) {
        HashSet<String> set = new HashSet<>();
        set.add("00");
        int n = path.length(); 
        int x =0; int y =0; 
        
        for(int i =0; i<n; i++){
            String point = "";
            if(path.charAt(i) == 'E') x = x+1;
            if(path.charAt(i) == 'W') x = x-1;
            if(path.charAt(i) == 'N') y = y+1;
            if(path.charAt(i) == 'S') y = y-1;
            point = x+""+y;
            // System.out.println("point " + point);
            // System.out.println("x " + x +" y "+y);
            if(set.contains(point)) return true;
            set.add(point);
        }
        return false;
    }

    /**
     * [1,7,9,9,8,3] 
     * index = 1
     * sort from index 3 till end
     * swap 7 with just larger to right
     * 
     * Why reverse sort? 
     * Because we are searching for the first smaller el
     * that means all els till now are in descending order.
     * 
     * find first smaller, sort, swap
    */
    // https://leetcode.com/problems/next-permutation
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n<2) return;
        
        int index = -1; int i = n-1;
        while(i>0){
            if(nums[i]>nums[i-1]) {
                index = i-1;
                break;
            }
            i--;
        }
        
        if(index == -1) reverseSort(nums, index+1, n-1);
        else{
            reverseSort (nums, index+1, n-1);
            for(i = index+1; i<n; i++){
                if(nums[i]>nums[index]) {
                    swap(nums, index, i);
                    break;
                }   
            }
        }
    }
    
    void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    void reverseSort(int[] arr, int start, int end){
        while(start <= end){
            swap(arr, start++, end--);
        }
    }


    // https://leetcode.com/problems/maximum-swap/
    // https://leetcode.com/problems/next-permutation/
    // https://leetcode.com/problems/h-index/discuss/70810/A-Clean-O(N)-Solution-in-Java
    // https://leetcode.com/problems/power-of-four
    
    public static void main(String[] args) {
        Mathprob math = new Mathprob();
        math.power(99, 9);
    }

}