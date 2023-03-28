package com.ll.dfs;

import java.util.*;
//백준 14503번 문제 로봇 청소기
//https://www.acmicpc.net/problem/14503
public class p14503 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0; // 청소한 칸의 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt(); // 로봇 청소기의 위치와 방향 정보
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dfs(r, c, d); // DFS로 청소 시작
        System.out.println(ans); // 결과 출력
        sc.close();
    }

    public static void dfs(int x, int y, int d) {
        // 청소하지 않은 칸을 방문하면 청소한다.
        if (!visited[x][y]) {
            visited[x][y] = true;
            ans++; //청소한 칸의 개수 저장
        }
        int nd = d; // 왼쪽으로 회전한 후의 방향
        for (int i = 0; i < 4; i++) { // 현재 위치에서 네 방향을 탐색
            nd = (nd + 3) % 4; // 왼쪽 방향으로 회전
            int nx = x + dx[nd];
            int ny = y + dy[nd];
            // 청소하지 않은 칸이 있으면 그 방향으로 이동
            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0 && !visited[nx][ny]) {
                dfs(nx, ny, nd);
                return; // DFS를 호출한 위치로 되돌아감
            }
        }
        // 네 방향 모두 청소가 이미 되어있거나 벽인 경우
        int bx = x - dx[d]; // 후진할 위치
        int by = y - dy[d];
        if (bx >= 0 && by >= 0 && bx < N && by < M && map[bx][by] == 0) {
            dfs(bx, by, d); // 후진 가능한 경우 후진
        }
    }
}