package com.ll.dp;

import java.util.Scanner;

public class p1562 {
    static final int MOD = 1000000000;
    static int N, dp[][][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N+1][10][1<<10];
        // 길이가 1인 수에 대한 초기화
        for(int i=1;i<=9;i++) {
            dp[1][i][1<<i] = 1;
        }
        // 모든 수에서 시작하는 계단 수를 더해줌
        int ans = 0;
        for(int i=1;i<=9;i++) {
            ans += solve(N, i, 1<<i);
            ans %= MOD;
        }
        System.out.println(ans);
        sc.close();
    }
    public static int solve(int n, int k, int visit) {
        if(n == 1) {
            if(visit == (1<<10)-1) return 1;
            else return 0;
        }
        // 메모이제이션
        if(dp[n][k][visit] > 0) return dp[n][k][visit];
        int res = 0;
        // 현재 수에서 선택 가능한 다음 수를 찾아 계단 수의 개수를 더해줌
        if(k-1 >= 0) res += solve(n-1, k-1, visit | (1<<(k-1)));
        if(k+1 <= 9) res += solve(n-1, k+1, visit | (1<<(k+1)));
        res %= MOD;
        dp[n][k][visit] = res;
        return res;
    }
}