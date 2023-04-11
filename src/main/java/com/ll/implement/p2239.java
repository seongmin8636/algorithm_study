package com.ll.implement;

import java.util.Scanner;
//백준 2239번 문제 스도쿠
//https://www.acmicpc.net/problem/2239
public class p2239 {
    static int[][] sudoku = new int[9][9]; // 스도쿠 판
    static boolean[][] rowUsed = new boolean[9][10]; // 행에 사용된 숫자 체크
    static boolean[][] colUsed = new boolean[9][10]; // 열에 사용된 숫자 체크
    static boolean[][][] squareUsed = new boolean[3][3][10]; // 3x3 박스에 사용된 숫자 체크

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 스도쿠 판 입력 받기
        for (int i = 0; i < 9; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = line.charAt(j) - '0';
                if (sudoku[i][j] != 0) {
                    rowUsed[i][sudoku[i][j]] = true;
                    colUsed[j][sudoku[i][j]] = true;
                    squareUsed[i / 3][j / 3][sudoku[i][j]] = true;
                }
            }
        }

        solve(0, 0); // 스도쿠 문제 풀기

        sc.close();
    }

    static void solve(int row, int col) {
        // 스도쿠 문제가 모두 채워졌으면 출력하고 종료
        if (row == 9) {
            printSudoku();
            System.exit(0);
        }

        // 현재 칸이 채워져 있으면 다음 칸으로 이동
        if (sudoku[row][col] != 0) {
            next(row, col);
            return;
        }

        // 현재 칸에 숫자를 채우기
        for (int i = 1; i <= 9; i++) {
            if (!rowUsed[row][i] && !colUsed[col][i] && !squareUsed[row / 3][col / 3][i]) {
                sudoku[row][col] = i;
                rowUsed[row][i] = true;
                colUsed[col][i] = true;
                squareUsed[row / 3][col / 3][i] = true;
                next(row, col);
                // 다음 칸에서 시도하기 위해 현재 칸에 채운 숫자 지우기
                sudoku[row][col] = 0;
                rowUsed[row][i] = false; // 재사용을 위해 값 되돌리기
                colUsed[col][i] = false;
                squareUsed[row / 3][col / 3][i] = false;
            }
        }
    }

    // 다음 칸으로 이동
    static void next(int row, int col) {
        if (col == 8)
            solve(row + 1, 0);
        else
            solve(row, col + 1);
    }

    static void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }
}