package com.ll.implement;

import java.util.Scanner;
//백준 14890번 문제 경사로
//https://www.acmicpc.net/problem/14890
public class p14890 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 지도의 크기
        int l = sc.nextInt(); // 경사로의 길이

        int[][] map = new int[n][n];

        //지도 정보 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int count = 0; //경사로 개수 카운트

        // 행 검사
        for (int i = 0; i < n; i++) {
            int[] height = new int[n]; // 행의 높이
            boolean[] isUsed = new boolean[n]; // 이미 경사로를 놓았는지 여부

            //해당 행의 높이
            for (int j = 0; j < n; j++) {
                height[j] = map[i][j];
            }

            boolean canBuild = true; // 경사로를 놓을 수 있는지

            // 경사로를 놓을 수 있는지 체크
            for (int j = 0; j < n - 1; j++) {
                if (height[j] != height[j + 1]) { // 높이의 차이가 있는 경우
                    int diff = Math.abs(height[j] - height[j + 1]);

                    if (diff != 1) {
                        canBuild = false;
                        break;
                    }

                    if (height[j] < height[j + 1]) { // 오르막 경사로인 경우
                        for (int k = j; k > j - l; k--) { // 경사로를 놓을 수 있는지 체크
                            if (k < 0 || height[k] != height[j] || isUsed[k]) { // 경사로를 놓을 수 없는 경우
                                canBuild = false;
                                break;
                            }
                            isUsed[k] = true;
                        }
                    } else { // 내리막 경사로인 경우
                        for (int k = j + 1; k <= j + l; k++) { // 경사로를 놓을 수 있는지 체크
                            if (k >= n || height[k] != height[j + 1] || isUsed[k]) { // 경사로를 놓을 수 없는 경우
                                canBuild = false;
                                break;
                            }
                            isUsed[k] = true;
                        }
                    }

                    if (!canBuild) {
                        break;
                    }
                }
            }

            if (canBuild) {
                count++;
            }
        }

        // 열 검사
        for (int j = 0; j < n; j++) {
            int[] height = new int[n];
            boolean[] isUsed = new boolean[n];

            for (int i = 0; i < n; i++) {
                height[i] = map[i][j];
            }

            boolean canBuild = true;

            for (int i = 0; i < n - 1; i++) {
                if (height[i] != height[i + 1]) {
                    int diff = Math.abs(height[i] - height[i + 1]);

                    if (diff != 1) {
                        canBuild = false;
                        break;
                    }

                    if (height[i] < height[i + 1]) {
                        // 오른쪽 경사로인 경우
                        for (int k = i; k > i - l; k--) { // 경사로를 놓을 수 있는지 체크
                            if (k < 0 || height[k] != height[i] || isUsed[k]) { // 경사로를 놓을 수 없는 경우
                                canBuild = false;
                                break;
                            }
                            isUsed[k] = true;
                        }
                    } else {
                        // 왼쪽 경사로인 경우
                        for (int k = i + 1; k <= i + l; k++) { // 경사로를 놓을 수 있는지 체크
                            if (k >= n || height[k] != height[i + 1] || isUsed[k]) { // 경사로를 놓을 수 없는 경우
                                canBuild = false;
                                break;
                            }
                            isUsed[k] = true;
                        }
                    }

                    if (!canBuild) {
                        break;
                    }
                }
            }

            if (canBuild) {
                count++;
            }
        }
        System.out.println(count);
    }
}
