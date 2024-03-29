package com.akshayram.contests;

import java.util.*;

import static java.util.stream.Collectors.joining;


public class Result {

    /*
     * Complete the 'minimizeBias' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY ratings as parameter.
     */

    public static int minimizeBias(List<Integer> ratings) {
    // Write your code here
        int totalBias=0;
        ratings.sort(Comparator.naturalOrder());
        
        //ratings.forEach(System.out::println);
        int evenSum =0, oddSum=0;
        //int evenSum =ratings.stream().filter(n -> n%2 ==0).reduce(0, Integer::sum);
       
        for(int i=0;i<ratings.size();i++){
            if(i % 2 ==0){
                evenSum += ratings.get(i);
            } else {
                oddSum += ratings.get(i);
            }
        }
        totalBias = Math.abs(evenSum - oddSum);
        
        return totalBias;
    }

}