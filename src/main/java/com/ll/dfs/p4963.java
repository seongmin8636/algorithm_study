package com.ll.dfs;

import java.util.*;
//백준 4963번 섬의 개수
public class p4963 {
    static int[][] map; // 입력받은 지도
    static boolean[][] visited; // 방문 여부를 저장할 배열
    static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1}; // 상하좌우 및 대각선 방향으로 이동하기 위한 배열
    static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1}; // 상하좌우 및 대각선 방향으로 이동하기 위한 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int w = sc.nextInt(); // 가로 길이
            int h = sc.nextInt(); // 세로 길이
            if (w == 0 && h == 0) break; // 입력 종료

            map = new int[h][w]; // 지도 배열 초기화
            visited = new boolean[h][w]; // 방문 여부 배열 초기화

            // 지도 입력
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int cnt = 0; // 섬의 개수
            // 모든 지점에 대해 탐색
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 땅인데 방문한 적 없으면 섬을 탐색 시작
                    if (map[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j, h, w); // DFS 실행
                        cnt++; // 섬의 개수 증가
                    }
                }
            }

            System.out.println(cnt); // 섬의 개수 출력
        }
        sc.close();
    }

    static void dfs(int x, int y, int h, int w) {
        visited[x][y] = true; // 현재 위치를 방문한 것으로 표시

        for (int i = 0; i < 8; i++) { // 상하좌우 및 대각선 방향으로 이동
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 지도 범위를 벗어나거나, 바다인 경우, 이미 방문한 경우는 스킵
            if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue; // 지도 범위를 벗어나면 스킵
            if (map[nx][ny] == 0 || visited[nx][ny]) continue; // 바다거나 이미 방문한 경우는 스킵
            dfs(nx, ny, h, w); // 새로운 위치에서 DFS 실행
        }
    }
}