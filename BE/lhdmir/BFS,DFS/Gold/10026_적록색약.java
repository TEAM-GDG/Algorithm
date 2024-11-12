import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static char[][] board;
    static boolean[][] visited;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        // 적록색약이 아닌 경우 구역의 수 계산
        visited = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 현재 위치에서 bfs실행
                if (bfs(i, j)) {
                    count++;
                }
            }
        }

        System.out.print(count + " ");

        // G인 구역을 R로 전부 변경
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'G') {
                    board[i][j] = 'R';
                }
            }
        }

        // 적록색약인 경우 구역의 수 계산
        visited = new boolean[n][n];
        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 현재 위치에서 bfs실행
                if (bfs(i, j)) {
                    count++;
                }
            }
        }

        System.out.print(count);

    }

    static boolean bfs(int x, int y) {
        // 방문한곳이면 false return
        if (visited[x][y]) return false;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;

        int nowX, nowY;
        int nextX, nextY;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            nowX = p.x;
            nowY = p.y;

            for (int i = 0; i < 4; i++) {
                nextX = nowX + dx[i];
                nextY = nowY + dy[i];

                // 다음 탐색할 위치가 그림판 범위 밖이라면
                if (nextX < 0 || nextY < 0 || nextX >= board.length || nextY >= board[0].length) {
                    continue;
                }

                // 다음에 탐색할 위치가 처음에 탐색을 시작했던 위치와 색이 같고 방문하지 않은 곳이라면
                if (!visited[nextX][nextY] && board[x][y] == board[nextX][nextY]) {
                    // 다음에 탐색할 위치로 추가
                    q.add(new Pair(nextX, nextY));
                    // 다음에 탐색할 위치를 방문 처리
                    visited[nextX][nextY] = true;
                }
            }
        }
        return true;
    }
}
