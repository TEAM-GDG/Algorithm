package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 적록색약_10026 {
    static boolean[][] visited;

    // 상하 좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;
    static List<Integer>[] result;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        char[][] colorBlindness = new char[N][N]; // 색맹
        char[][] person = new char[N][N]; // 일반인
        visited = new boolean[N][N]; // 방문확인
        result = new ArrayList[2]; // 두개의 값을 ArrayList로 받아준다.

        // 초기화
        result[0] = new ArrayList<>();
        result[1] = new ArrayList<>();

        // 값 입력
        for (int i = 0; i < N; i++) {
            String rgb = br.readLine();
            for (int j = 0; j < N; j++) {
                char word = rgb.charAt(j);
                if (word == 'G') { // 초록과 빨강을 똑같이 취급
                    colorBlindness[i][j] = 'R';
                } else {
                    colorBlindness[i][j] = word;
                }
                person[i][j] = word; // 일반 사람들은 그냥 값 넣어줌
            }
        }

        // dfs 탐색
        // 일반 사람들 먼저
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문한적이 있으면
                    visited[i][j] = true;
                    dfs(i, j, person);
                    result[0].add(cnt); // 결과 값 더해줌
                }
                cnt = 1;
                visited[i][j] = false; // 재활용해야되서 다시 초기화
            }
        }

        // 색맹인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, colorBlindness);
                    result[1].add(cnt);
                }
                cnt = 1;
            }
        }
        // 구역을 출력해야되서 사이즈 출력
        System.out.println(result[0].size() + " " + result[1].size());
        // System.out.println(Arrays.toString(result));
    }

    static void dfs(int column, int row, char[][] people) {

        for (int i = 0; i < 4; i++) {
            int newColumn = column - dy[i];
            int newRow = row - dx[i];

            // 범위 넘어 갔는지 확인
            if (newColumn < 0 || newRow < 0 || newColumn >= N || newRow >= N) {
                continue;
            }


            // 방문한적이 없으면
            if (!visited[newColumn][newRow]) {
                // 상하좌우에 같은 색이 있으면 카운터 증가
                if (people[newColumn][newRow] == people[column][row]) {
                    cnt++;
                    visited[newColumn][newRow] = true; // 방문 했기떄문에 true
                    dfs(newColumn, newRow, people);
                }
            }


        }
    }
}
