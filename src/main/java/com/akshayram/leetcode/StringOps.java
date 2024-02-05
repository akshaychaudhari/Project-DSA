package com.akshayram.leetcode;

import java.util.HashSet;

import javafx.util.Pair;

import java.util.*;

public class StringOps {

    // TC: O(n). SC: O(n)
    public static boolean isPathCrossing(String path) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        int x = 0;
        int y = 0;
        visited.add(new Pair<>(x, y));
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                y++;
            } else if (c == 'S') {
                y--;
            } else if (c == 'E') {
                x++;
            } else if (c == 'W') {
                x--;
            }
            Pair<Integer, Integer> coordinates = new Pair<>(x, y);
            if (visited.contains(coordinates)) {
                return true;
            } else {
                visited.add(new Pair<>(x, y));
            }
        }
        return false;
    }

    public static int minimumPushes(String word) {
        int n = word.length();
        System.out.println("n :" + n);
        if (n <= 8) return n;

        int[] counts = new int[26];  // Count occurrences of each letter
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }

        Arrays.sort(counts);  // Sort counts in ascending order

        int pushes = 8;
        int position = 2;

        //0 to 7 indexes are the highest freq chars to be mapped on first clicks
        //checking 8 onwards to be mapped to 2nd and 3rd positions
        for (int i = 0; i <= 17; i++) {
            while (counts[i] == 0) {
                i++;
            }
            if (i <= 1) {
                position = 4;
            } else if (i <= 9) {
                position = 3;
            } else {
                position = 2;
            }
            pushes += position * counts[i];  // Calculate pushes for this key
        }

        return pushes;
    }

    public static int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        // Early return for short words
        if (n <= k) return 1;
        Map<String, Integer> hmap = new HashMap<>();

        int subsPossible = n / k;
        System.out.println("sub" + subsPossible);
        int rounds = 0;
        int curr = 0;
        int tmpround = 0;
        for (int t = 0; t < subsPossible; t++) {
            tmpround++;
            String str = word.substring(curr, curr + k);
            hmap.put(str, tmpround);
        }
        String pc1 = word.substring(0, k);
        int val = 0;
        if (hmap.containsKey(pc1)) {
            //found first key, lets check from here
            val = hmap.get(pc1);
        }
        rounds = val;
        for (int t = 0; t < subsPossible; t++) {
            rounds++;
            System.out.println("rounds" + rounds);
            String pc = word.substring(0, k * rounds);
            String remain = word.substring(k * rounds);
            System.out.println("rem" + remain.length() + " " + pc.length());
            String original = word.substring(0, remain.length());
            if (original.equals(remain)) {
                //this needs to be satisfied
                break;
            }
            if (remain.length() < k) {
                rounds++;
                break;
            }
        }


        return rounds;
    }

    public static int minimumTimeToInitialState2(String inputWord, int removalCount) {
        int steps = 1;
        String remainingWord = inputWord;
        StringBuilder modifiedWord = new StringBuilder(inputWord.substring(0, inputWord.length() - removalCount));
        remainingWord = inputWord.substring(removalCount);

        while (remainingWord.length() > 0 && !remainingWord.equals(modifiedWord.toString())) {
            steps++;
            if (remainingWord.length() <= removalCount) {
                return steps;
            }
            modifiedWord.delete(modifiedWord.length() - removalCount, modifiedWord.length());
            remainingWord = remainingWord.substring(removalCount);
        }

        return steps;
    }

    //TC: O(n) SC: O(1)
    static int[] freqArray = new int[27];
    static int[] firstIndex = new int[27];
    static int[] firstView = new int[27];

    public static int firstUniqChar(String s) {
        Arrays.fill(firstView, -1);
        Arrays.fill(firstIndex, -1);
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            freqArray[val] += 1;
            if (firstIndex[val] < 0) {
                firstIndex[val] = i;
                firstView[j] = val;
                j++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (firstView[i] == -1) {
                break;
            }
            if (freqArray[firstView[i]] == 1) {
                return firstIndex[firstView[i]];
            }
        }
        return -1;
    }


    //TC: O(n) SC: O(1)
    public int firstUniqChar2(String s) {
        // Stores lowest index / first index
        int ans = Integer.MAX_VALUE;
        // Iterate from a to z which is 26 which makes it constant
        for (char c = 'a'; c <= 'z'; c++) {
            int index = s.indexOf(c);
            if (index != -1 && index == s.lastIndexOf(c)) {
                ans = Math.min(ans, index);
            }
        }

        // If ans remain's Integer.MAX_VALUE then their is no unique character
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println("MAIN EXEC...");
//        String input = "NESWW"; // "NES";
//        System.out.println("For input " + input + " " + isPathCrossing(input));

//        String input = "amrvxnhsewkoipjyuclgtdbfq";//"amrvxnhsewkoipjyuclgtdbfq"; // "NES";
//        System.out.println("For input " + input + " " + minimumPushes(input));

//        String input = "abcbabcd";
//        System.out.println("For input " + " output is " + minimumTimeToInitialState(input, 2));

        System.out.println("For input " + " output is " + firstUniqChar("yekbsxznylrwamcaugrqrurvpqybkpfzwbqiysrdnrsnbftvrnszfjbkbmrctjizkjqoxqzddyfnavnhqeblfmzqgsjflghaulbadwqsyuetdelujphmlgtmkoaoijypvcajctbaumeromgejtewbwqptotrorephegyobbstvywljboeihdliknluqdpgampjyjpinxhhqexoctysfdciqjbzilnodzoihihusxluqoayenluziobxiodrfdkinkzzozmxfezfvllpdvogqqtquwcsijwachefspywdgsohqtlquhnoecccgbkrzqcprzmwvygqwddnehhi"));
//        System.out.println("For input " + " output is " + firstUniqChar("leetcode"));


    }
}
