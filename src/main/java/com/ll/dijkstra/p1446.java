package com.ll.dijkstra;

import java.util.*;

// 지름길 정보를 담기 위한 클래스
class Shortcut {
    int start, end, length;
    Shortcut(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }
}

public class p1446 {
    static final int INF = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 지름길의 갯수
        int d = sc.nextInt(); // 고속도로의 길이

        List<Shortcut> shortcuts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt(); //시작점
            int end = sc.nextInt(); //끝점
            int length = sc.nextInt(); //지름길의 길이
            shortcuts.add(new Shortcut(start, end, length));
        }

        int[] dist = new int[d + 1]; //시작점에서부터 최단 거리 배열
        Arrays.fill(dist, INF);
        dist[0] = 0; // 시작점의 최단거리는 0

        //다익스트라 알고리즘 수행
        for (int i = 0; i < d; i++) { // 이전 위치에서 1만큼 이동하는 경우
            dist[i + 1] = Math.min(dist[i + 1], dist[i] + 1);
            for (Shortcut shortcut : shortcuts) {
                if (shortcut.start == i && shortcut.end <= d) { // 현재 위치에서 지름길을 이용할 수 있는 경우
                    dist[shortcut.end] = Math.min(dist[shortcut.end], dist[shortcut.start] + shortcut.length);
                }
            }
        }

        System.out.println(dist[d]);
    }
}