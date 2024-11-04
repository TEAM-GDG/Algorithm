package DFS_BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class fs_2667_단지번호붙이기 {

    static int[] nx = {-1,0,1,0};    // 상, 우, 하, 좌
    static int[] ny = {0,1,0,-1};
    static int[][] map;
    static int count, N;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 입력으로부터 지도를 설정
        for (int i = 0; i < N; i++) {
            char[] chs = br.readLine().toCharArray();
            for (int j = 0; j < chs.length; j++) {
                map[i][j] = chs[j] - '0';
            }
        }

        // 지도를 순회하며 단지를 찾아서 크기를 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) { // 아직 방문하지 않은 집이 있는 경우
                    count = 0;
                    DFS(i, j);
                    result.add(count); // 각 단지의 집 수를 결과 리스트에 추가
                }
            }
        }

        // 결과 리스트를 오름차순 정렬 후 출력
        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n'); // 총 단지 수
        for (int n : result) {
            sb.append(n).append('\n'); // 각 단지의 집 수
        }
        System.out.println(sb);
    }

    // DFS를 이용하여 단지 내의 집들을 방문하고 집의 수를 세는 메소드
    private static void DFS(int i, int j) {
        map[i][j] = 0; // 방문한 집을 0으로 표시
        count++; // 집의 수 증가
        for (int direction = 0; direction < 4; direction++) {
            int x = i + nx[direction];
            int y = j + ny[direction];
            // 유효한 좌표이고, 아직 방문하지 않은 집인 경우 DFS 재귀 호출
            if (0 <= x && x < N && 0 <= y && y < N && map[x][y] == 1) {
                DFS(x, y);
            }
        }
    }
}