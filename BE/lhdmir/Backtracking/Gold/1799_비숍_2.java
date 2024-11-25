import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    // 우하향 대각선 상태 표시
    static boolean[] diag1;
    // 좌하향 대각선 상태 표시
    static boolean[] diag2;
    static int maxBishops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 대각선 배열 초기화
        // n = 5일때 우하향, 좌하향 대각선의 갯수는 각각 9개
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];

        // 색상별 최대 비숍 수 계산
        int maxBlack = getMaxBishops(0);
        int maxWhite = getMaxBishops(1);

        System.out.println(maxBlack + maxWhite);
    }

    static int getMaxBishops(int color) {
        maxBishops = 0;
        dfs(color, 0, 0);
        return maxBishops;
    }

    static void dfs(int color, int idx, int count) {
        maxBishops = Math.max(maxBishops, count);

        // 현재칸(idx)부터 n*n 칸까지 반복
        // 2차원 배열을 1차원 배열로 변경해서 순회
        // n = 5일때 좌상단 칸이 0, 우하단 칸이 24
        for (int i = idx; i < n * n; i++) {
            // i = 2, n = 5 일때
            // row = 0, col = 2 (0,2)
            // i = 8, n = 5 일때
            // row = 1, col = 3 (1,3)
            int row = i / n;
            int col = i % n;

            // 색상 조건 및 비숍 놓을 수 있는지 확인
            // (row + col) % 2 != color(0 = black, 1 = white)
            // 현재 탐색 중인 칸이 지정된 색상(color)과 맞는지 확인
            // board[row][col] == 0 인경우 비숍을 놓을 수 없다
            if ((row + col) % 2 != color || board[row][col] == 0) continue;

            // 각 대각선 배열의 고유값을 식별하기 위한 값
            // row = 0, col = 2 (0,2) 라면 d1 = 2 diag1(우하향 대각선)의
            // 3번째 대각선을 의미.
            // 0  1  2  3  4
            // 1  2  3  4  5
            // 2  3  4  5  6
            // 3  4  5  6  7
            // 4  5  6  7  8
            int d1 = row + col;
            // row = 0, col = 2 (0,2) 라면 d2는 = 2 diag2(좌하향 대각선)의
            // 3번째 대각선을 의미
            // 4  3  2  1  0
            // 5  4  3  2  1
            // 6  5  4  3  2
            // 7  6  5  4  3
            // 8  7  6  5  4
            int d2 = row - col + (n - 1);

            // 우하향,좌하향 대각선에 다른 비숍이 있는지 확인
            // 두 대각선에 비숍이 없어야 현재 칸에 비숍을 놓을 수 있다.
            if (!diag1[d1] && !diag2[d2]) {
                // 대각선 사용 표시
                diag1[d1] = true;
                diag2[d2] = true;

                // 비숍을 배치했으므로 다음 칸(i + 1)부터 탐색
                // count + 1로 현재 배치된 비숍의 갯수를 하나 증가시킴
                dfs(color, i + 1, count + 1);

                // 대각선 사용 해제
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }
}
