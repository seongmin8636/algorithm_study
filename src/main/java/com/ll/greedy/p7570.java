package com.ll.greedy;
import java.util.Scanner;

//백준 p7570 줄 세우기
//https://www.acmicpc.net/problem/7570
public class p7570 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] dp = new int[1000001];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int children = 0;

        for (int i = 0; i < n; i++) {
            if (dp[nums[i] - 1] != 0) {
                dp[nums[i]] = dp[nums[i] - 1] + 1;
            } else {
                dp[nums[i]] = 1;
            }

            children = Math.max(children, dp[nums[i]]);
        }

        System.out.println(n - children);
    }

}