import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static boolean[][] visited;
    static int maxBishops;
    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxBishops = 0;
        deploymentBishop(0, 0, 0);
        int maxBlack = maxBishops;

        maxBishops = 0;
        deploymentBishop(1, 0, 0);
        int maxWhite = maxBishops;

        System.out.println(maxBlack + maxWhite);
    }

    static void deploymentBishop(int color, int row, int count) {
        // count와 maxBishops를 비교해 큰 값을 저장
        maxBishops = Math.max(maxBishops, count);

        for (int i = row; i < n; i++) {
            // i % 2 == color 가 짝수 즉 0이라면 검은색칸 계산
            // 홀수라면(1) 흰색칸 계산
            for (int j = (i % 2 == color ? 0 : 1); j < n; j += 2) {
                // 비숍을 놓을 수 있는 칸이고,
                // 방문하지 않은 칸이며
                // canPlace(i,j)를 통해서 비숍이 서로 공격할수없는지 확인 후
                if (board[i][j] == 1 && !visited[i][j] && canPlace(i, j)) {
                    // 비숍을 놓음
                    visited[i][j] = true;
                    // count + 1하고 다음 비숍을 놓을 수 있는 자리를 찾음
                    deploymentBishop(color, i, count + 1);
                    // 끝났으면 비숍을 삭제
                    visited[i][j] = false;
                }
            }
        }
    }

    static boolean canPlace(int x, int y) {
        // 현재위치 x,y에서 대각선 4방향으로 끝까지 이동
        for (int dir = 0; dir < 4; dir++) {
            int nx = x;
            int ny = y;

            while (true) {
                nx += dx[dir];
                ny += dy[dir];

                // 끝까지 왔다면 반복문 중단
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                // 해당 방향에 비숍이 있으면 false 리턴
                if (visited[nx][ny]) return false;
            }
        }
        // 아니라면 true리턴
        return true;
    }
}
