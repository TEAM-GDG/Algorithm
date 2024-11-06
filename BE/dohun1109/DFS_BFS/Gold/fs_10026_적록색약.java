package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class fs_10026_적록색약 {

    static int[] nx = {-1,0,1,0};    // 상, 우, 하, 좌 방향
    static int[] ny = {0,1,0,-1};
    static int[] color = new int[4];
    static int[][] board;
    static boolean[][] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        // 입력으로부터 지도를 설정
        for (int i = 0; i < N; i++) {
            char[] chs = br.readLine().toCharArray();
            for (int j = 0; j < chs.length; j++) {
                board[i][j] = chs[j] == 'R' ? 1 : chs[j] == 'G' ? 2 : 3; // R은 1, G는 2, B는 3으로 설정
            }
        }

        StringBuilder sb = new StringBuilder();
        visited = new boolean[N][N];
        // 일반적인 시력으로 구역 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 아직 방문하지 않은 곳이 있는 경우
                    DFS(i, j, board[i][j], board[i][j]);
                    color[board[i][j]]++;
                }
            }
        }
        sb.append(color[1] + color[2] + color[3]).append(' ');
        Arrays.fill(color, 0);

        visited = new boolean[N][N];
        // 적록색약 시각으로 구역 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 아직 방문하지 않은 곳이 있는 경우
                    if (board[i][j] == 1 || board[i][j] == 2) {
                        DFS(i, j, 1, 2); // 적록색약인 경우 R과 G는 같은 색으로 취급
                        color[1]++;
                    } else {
                        DFS(i, j, 3, 3);
                        color[board[i][j]]++;
                    }
                }
            }
        }

        sb.append(color[1] + color[3]); // 적록색약일 때 구역 수 추가
        System.out.println(sb);
    }

    // DFS 메서드
    private static void DFS(int i, int j, int color1, int color2) {
        visited[i][j] = true;
        for (int direction = 0; direction < 4; direction++) {
            int x = i + nx[direction];
            int y = j + ny[direction];
            // 유효한 좌표이고, 아직 방문하지 않은 곳인 경우 DFS 재귀 호출
            if (0 <= x && x < N && 0 <= y && y < N && !visited[x][y]) {
                int currentColor = board[x][y];
                if (currentColor == color1 || currentColor == color2) { // 같은 색이거나 적록색약 시각에서 동일시되는 색일 경우
                    DFS(x, y, color1, color2);
                }
            }
        }
    }
}