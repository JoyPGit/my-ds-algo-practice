import java.util.*;

public class Recursion {
    Recursion() {
    }

    // https://www.techiedelight.com/recursion-practice-problems-with-solutions/
    void printListOflists(ArrayList<ArrayList<Integer>> list) {
        // System.out.println(list.size());//k
        for (int i = 0; i < list.size(); i++) {
            Object j;
            Iterator it = list.get(i).listIterator();
            while (it.hasNext()) {
                j = it.next();
                System.out.println(j);
            }
            System.out.println();
        }

    }

    void print1DMatrix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                System.out.print(arr[i]);
            else
                System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    boolean ans = false;

    public boolean canCross(int[] stones) {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();

        for (int i = 1; i < stones.length; i++) {
            h.put(stones[i], i);
        }

        System.out.println(h.entrySet());

        canCrossHelper(1, 1, h, stones);
        System.out.println("can the frog jump :" + this.ans);
        return this.ans;
    }

    void canCrossHelper(int k, int value, HashMap<Integer, Integer> h, int[] arr) {
        // if(index == 5) System.out.println("barrier");
        System.out.println("k " + k);
        System.out.println("value " + value);
        if (value == arr[arr.length - 1]) {
            this.ans = true;
            System.out.println("reached end");
            return;
        }

        if (k != 1) {
            if (h.containsKey(value + k - 1)) {
                System.out.println(k - 1 + " jumps " + (value + k - 1));
                canCrossHelper(k - 1, value + k - 1, h, arr);
            }

            if (h.containsKey(value + k)) {
                System.out.println(k + " jumps " + (value + k));
                canCrossHelper(k, value + k, h, arr);
            }

            if (h.containsKey(value + k + 1)) {
                System.out.println(k + 1 + " jumps " + (value + k + 1));
                canCrossHelper(k + 1, value + k + 1, h, arr);
            }

        } else {
            if (h.containsKey(value + k)) {
                System.out.println(k + " jumps " + (value + k));
                canCrossHelper(k, value + k, h, arr);
            }

            if (h.containsKey(value + k + 1)) {
                System.out.println(k + 1 + " jumps " + (value + k + 1));
                canCrossHelper(k + 1, value + k + 1, h, arr);
            }
        }

    }

    boolean subsetSum(int[] arr, int sum, int index) {
        if (index >= arr.length)
            return false;
        if (sum == 0)
            return true;
        return subsetSum(arr, sum - arr[index], index + 1) || subsetSum(arr, sum, index + 1);
    }

    // void divideInKSubsets(int[] arr , int k){
    // int sum =0;
    // for(int i =0; i<arr.length; i++){
    // sum+=arr[i];
    // }

    // if(sum%k!=0) return;
    // ArrayList<ArrayList<Integer>> listHolder = new
    // ArrayList<ArrayList<Integer>>();

    // for(int i=0; i<k; i++){
    // listHolder.add(new ArrayList<Integer>());
    // }

    // divideInKSubsetsHelper(arr, k, listHolder, sum/k, 0);
    // }

    // boolean proceed = true;
    // void divideInKSubsetsHelper(int[] arr, int k, ArrayList<ArrayList<Integer>>
    // holder,
    // int sum, int index){
    // if(sum == 0) {
    // System.out.println("found"); this.proceed = false;
    // printListOflists(holder);
    // return;
    // }

    // if(index==arr.length) return;
    // for(int i =0; i<k; i++){
    // if(this.proceed){
    // divideInKSubsetsHelper(arr, k, holder, sum, index+1);
    // holder.get(i).add(arr[index]);
    // divideInKSubsetsHelper(arr, k, holder, sum-arr[index], index+1);
    // }
    // }
    // }

    void divideInKSubsetsArray(int[] arr, int k) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % k != 0)
            return;
        int[] arr1 = new int[arr.length];
        int[] arr2 = new int[arr.length];
        int[] arr3 = new int[arr.length];

        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum / k, 0, 0, 0, 0);
    }

    boolean proceedArray = true;

    void divideInKSubsetsHelperArray(int[] arr, int k, int[] arr1, int[] arr2, int[] arr3, int sum, int index,
            int index1, int index2, int index3) {
        if (sum == 0) {
            System.out.println("found");
            this.proceedArray = false;
            print1DMatrix(arr1);
            print1DMatrix(arr2);
            print1DMatrix(arr3);
            return;
        }

        if (index == arr.length)
            return;
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum, index + 1, index1 + 1, index2 + 1, index3 + 1);
        arr1[index1] = arr[index];
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum - arr[index], index + 1, index1 + 1, index2, index3);
        arr2[index2] = arr[index];
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum - arr[index], index + 1, index1, index2 + 1, index3);
        arr3[index3] = arr[index];
        divideInKSubsetsHelperArray(arr, k, arr1, arr2, arr3, sum - arr[index], index + 1, index1, index2, index3 + 1);
    }

    /**
     * https://stackoverflow.com/questions/37424284/unreported-exception-
     * java-lang-exception-must-be-caught-or-declared-to-be-throw
     */
    boolean found = false;

    void startToDestination(int start, int end) throws Exception {
        try {
            if (start > end || start <= 0)
                return;
            if (start == end) {
                System.out.println("done");
                found = true;
                return;
            }
            if (!found) {
                System.out.println("current " + start);
                startToDestination(start * 2, end);
                startToDestination(start + 2, end);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            e.printStackTrace();
            throw new Exception(e);
        }

    }

    int maxDiv3 = 0;

    public int maxSumDivThree(int[] nums) {
        maxSumDivThreeHelper(nums, 1, nums[0]);
        maxSumDivThreeHelper(nums, 1, 0);

        return this.maxDiv3;

    }

    void maxSumDivThreeHelper(int[] nums, int index, int sum) {
        if (sum % 3 == 0) {
            System.out.println("in here , sum " + sum);
            this.maxDiv3 = Math.max(this.maxDiv3, sum);
            System.out.println("max " + this.maxDiv3);
        }
        if (index >= nums.length)
            return;

        maxSumDivThreeHelper(nums, index + 1, sum + nums[index]);
        maxSumDivThreeHelper(nums, index + 1, sum);
    }

    // https://www.geeksforgeeks.org/count-possible-groups-size-2-3-sum-multiple-3/
    // void max2or3Group(int[] arr){
    // max2or3GroupHelper(arr, 0, 0);
    // // max2or3GroupHelper(arr, 1);
    // }

    // void max2or3GroupHelper(int[] arr, int sum, int )

    void allSubsets(int[] arr) {
        int[] subset = new int[arr.length];
        allSubsetHelper(arr, subset, 0);
    }

    /**
     * always remember to print when index == arr.length & assign subset[index] = 0;
     */
    void allSubsetHelper(int[] arr, int[] subset, int index) {
        if (index == arr.length) {
            print1DMatrix(subset);
            return;
        }
        subset[index] = 0;
        allSubsetHelper(arr, subset, index + 1);
        subset[index] = arr[index];
        allSubsetHelper(arr, subset, index + 1);
    }

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs(char[] X, char[] Y, int m, int n) {
        System.out.println("m " + m + " n " + n);
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1]) {
            System.out.println("X " + X[m - 1] + " Y " + Y[n - 1]);
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            System.out.println("in else");
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }

    // https://leetcode.com/problems/different-ways-to-add-parentheses/
    // discuss/66328/A-recursive-Java-solution-(284-ms)

    // https://practice.geeksforgeeks.org/problems/reach-a-given-score/0
    // int reachScore(int score){

    // reachScoreHelper(score, )
    // }

    // https://www.geeksforgeeks.org/all-unique-combinations-whose-sum-equals-to-k/
    // int uniqueCombinations(int[] arr, int k){
    // ArrayList<Integer> list = new ArrayList<>();

    // for(int i =0; i<arr.length; i++){
    // list.add(arr[i]);
    // while(i+1<arr.length && arr[i] == arr[i+1]) i++;
    // }
    // System.out.println(list);
    // return 1;
    // }

    // https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
    // incomplete
    // void sortStack(int[] arr){
    // Deque<Integer> stack = new LinkedList<>();
    // for(int i =0; i<arr.length; i++){
    // stack.addLast(arr[i]);
    // }

    // sortStackUtil(stack);
    // }
    // //incomplete
    // void sortStackUtil(Deque<Integer> stack){
    // int top = stack.removeLast();
    // while(stack.getLast()>top){

    // }
    // }

    // https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
    // https://www.geeksforgeeks.org/recursively-break-number-3-parts-get-maximum-sum/
    public static void main(String[] args) throws Exception {
        Recursion recur = new Recursion();
        int[] stones =
                // {0,1,2,3};
                // {0,1,2,7};
                // {0,1,3,5,6,8,12,17};
                // {0,1,2,3,4,8,9,11};
                // {0,1,2,3,4,5,6,12};
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
                        28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
                        52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75,
                        76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99,
                        100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118,
                        119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137,
                        138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156,
                        157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175,
                        176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194,
                        195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213,
                        214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232,
                        233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251,
                        252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270,
                        271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289,
                        290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308,
                        309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327,
                        328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346,
                        347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365,
                        366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384,
                        385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403,
                        404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422,
                        423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441,
                        442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460,
                        461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479,
                        480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498,
                        499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517,
                        518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536,
                        537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555,
                        556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574,
                        575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593,
                        594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612,
                        613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631,
                        632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650,
                        651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669,
                        670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688,
                        689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707,
                        708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726,
                        727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745,
                        746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764,
                        765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783,
                        784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802,
                        803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821,
                        822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840,
                        841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859,
                        860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878,
                        879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897,
                        898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916,
                        917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935,
                        936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954,
                        955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973,
                        974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992,
                        993, 994, 995, 996, 997, 998, 999 };
        // recur.canCross(stones);

        int set[] = { 3, 34, 4, 12, 5, 2 }, sum = 30;
        // {3, 34, 4, 12, 5, 2}, sum = 9;
        // System.out.println(recur.subsetSum(set, sum, 0));

        int[] kSubsetArr = { 3, 3, 4, 1, 5, 2, 6 };
        int k = 3;
        // recur.divideInKSubsets(kSubsetArr, k);
        // recur.divideInKSubsetsArray(kSubsetArr, k);

        int start = 2;
        int end = 92;
        // recur.startToDestination(start, end);

        int[] nums = // {3,6,5,1,8};
                { 366, 809, 6, 792, 822, 181, 210, 588, 344, 618, 341, 410, 121, 864, 191, 749, 637, 169, 123, 472, 358,
                        908, 235, 914, 322, 946, 738, 754, 908, 272, 267, 326, 587, 267, 803, 281, 586, 707, 94, 627,
                        724, 469, 568, 57, 103, 984, 787, 552, 14, 545, 866, 494, 263, 157, 479, 823, 835, 100, 495,
                        773, 729, 921, 348, 871, 91, 386, 183, 979, 716, 806, 639, 290, 612, 322, 289, 910, 484, 300,
                        195, 546, 499, 213, 8, 623, 490, 473, 603, 721, 793, 418, 551, 331, 598, 670, 960, 483, 154,
                        317, 834, 352 };
        // System.out.println("max sum div by 3 "+recur.maxSumDivThree(nums));

        int[] subsets = { 1, 2 };
        // recur.allSubsets(subsets);

        int[] uniqueComb = { 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 5 };
        // recur.uniqueCombinations(uniqueComb, k);
    }
}