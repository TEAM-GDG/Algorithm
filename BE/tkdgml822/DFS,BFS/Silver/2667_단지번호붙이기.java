package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 단지번호붙이기_2667 {
    static int N;
    static int[][] myHome;
    static boolean[][] visited;
    static int cnt;
    static List<Integer> result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        result = new ArrayList<>();
        myHome = new int[N][N];
        visited = new boolean[N][N]; // 방문
        cnt = 1;

        // 값 2차원 배열에 넣기
        for (int i = 0; i < N; i++) {
            String number = br.readLine();
            for (int j = 0; j < N; j++) {
                int A = Integer.parseInt(String.valueOf(number.charAt(j)));
                myHome[i][j] = A;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (myHome[i][j] == 1) {
                        dfs(i, j);
                        result.add(cnt);
                    }
                }
                cnt = 1;
            }
        }

        System.out.println(result.size());
        Collections.sort(result); // 오름차순
        result.forEach(System.out::println);
    }

    static void dfs(int column, int row) {

        // 만약 현재 위치가 1이라면
        if (myHome[column][row] == 1) {
            /**
             * i = 0 : 위
             * i = 1 : 아래
             * i = 2 : 왼쪽
             * i = 3 : 오른쪽
             * 상, 하, 좌, 우 탐색을 한다.
             */
            for (int i = 0; i < 4; i++) {
                int curRow = row + dx[i];
                int curCol = column + dy[i];

                // 범위를 넘어가면 continue을 한다.
                if (curRow < 0 || curCol < 0 || curRow >= N || curCol >= N) continue;

                // 상하좌우 중에 1이 있으면 같은 단지 이기 떄문에 cnt 증가 그리고 방문을 하지않은곳
                if (!visited[curCol][curRow] && myHome[curCol][curRow] == 1) cnt++;

                // 아직 방문하지 않은 곳이라면
                if (!visited[curCol][curRow]) {
                    visited[curCol][curRow] = true; // 방문처리를 해준다.
                    dfs(curCol, curRow);
                }

            }
        }

    }
}
