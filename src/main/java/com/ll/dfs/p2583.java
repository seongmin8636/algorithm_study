package com.ll.dfs;

import java.util.*;
//백준 2583번 영역 구하기
//https://www.acmicpc.net/problem/2583
public class p2583 {
    static int m, n, k; // 직사각형의 세로, 가로 크기, 그리고 좌표 개수
    static int[][] map; // 직사각형 내부를 나타내는 배열
    static boolean[][] visited; // 해당 지점을 방문했는지 여부를 나타내는 배열
    static int[] dx = {0, 1, 0, -1}; // 상하좌우로 이동하는 데 사용되는 배열
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Integer> list = new ArrayList<>(); // 영역의 크기를 저장할 리스트

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();
        map = new int[m][n]; // 초기화
        visited = new boolean[m][n];

        // 좌표를 입력받아 map 배열에 표시
        for (int i = 0; i < k; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            for (int j = x1; j < x2; j++) {
                for (int l = y1; l < y2; l++) {
                    map[l][j] = 1; // 해당 좌표를 표시
                }
            }
        }

        int count = 0; // 영역의 개수를 저장할 변수
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 0) { // 방문하지 않은 0인 지점이라면
                    count++; // 새로운 영역을 찾은 것이므로 count를 증가시킴
                    list.add(dfs(i, j)); // dfs 함수를 호출하여 영역의 크기를 구하고, list에 추가
                }
            }
        }

        System.out.println(count); // 영역의 개수 출력
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    // dfs 함수
    static int dfs(int x, int y) {
        visited[x][y] = true; // 해당 지점을 방문했음을 표시
        int area = 1; // 영역의 크기를 저장할 변수를 1로 초기화

        for (int i = 0; i < 4; i++) { // 상하좌우로 이동하며 영역의 크기를 계산
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) { // 직사각형 범위 내에 있으면서 방문하지 않은 0인 지점이라면
                if (!visited[nx][ny] && map[nx][ny] == 0) { //재귀적으로 dfs 함수 호출
                    area += dfs(nx, ny);
                }
            }
        }

        return area;
    }
}