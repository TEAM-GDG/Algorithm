import java.io.*;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static char[][] board = new char[5][5];
    static boolean[][] selected = new boolean[5][5];
    static int result = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        combine(0, 0, 0, 0);
        System.out.println(result);
    }

    // 학생자리별 조합(25C7) = 48,620
    // sCount: 이다솜파 학생 수
    static void combine(int x, int y, int depth, int sCount) {
        // 7명이 모였다면
        if (depth == 7) {
            // 이다솜파 학생이 4명 이상인지 검사
            // isConnected(): 선택된 학생들이 연결되어있는지 검사
            if (sCount >= 4 && isConnected()) {
                result++;
            }
            return;
        }

        for (int i = x; i < 5; i++) {
            // i == x(같은 행이라면) 이전에 탐색된 열(y) 부터 시작
            // 아니라면 해당 행의 첫 번째 열(j = 0)부터 시작
            // 예를 들어, (0,0)부터 시작됬다면 다음에 함수가 실행될때 j가 0부터 시작하지 않고
            // y부터 시작할 수 있다. 즉, 이전 탐색된 열부터 탐색을 시작할 수 있으므로 연산의 시도자체를
            // 없애버릴수있다.
            for (int j = (i == x ? y : 0); j < 5; j++) {
                // 현재 좌표의 학생이 선택되지 않았다면
                if (!selected[i][j]) {
                    selected[i][j] = true;
                    combine(i, j + 1, depth + 1, sCount + (board[i][j] == 'S' ? 1 : 0));
                    selected[i][j] = false;
                }
            }
        }
    }

    static boolean isConnected() {
        boolean[][] visited = new boolean[5][5];
        Queue<Pair> q = new LinkedList<>();
        int count = 0;

        // 밖의 for문의 레이블명을 outer 로 지정
        outer:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // 현재위치가 조합에 선택된 학생이라면
                if (selected[i][j]) {
                    // 큐에 추가
                    q.add(new Pair(i, j));
                    // 방문 표시
                    visited[i][j] = true;
                    // 안쪽 반복문 종료
                    // outer 반복문도 종료
                    break outer;
                    // 조합에 처음으로 선택된 학생의 좌표를 찾고나면 더이상 반복문을
                    // 실행할 필요가 없으므로 모든 반복문을 종료
                }
            }
        }

        while (!q.isEmpty()) {
            Pair current = q.poll();
            // 카운트 증가
            count++;

            // 4방향 검사
            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                // 다음 좌표가 범위안에 있고
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                    // 조합에 선택된 학생이며 방문하지 않은 좌표일때
                    if (selected[nx][ny] && !visited[nx][ny]) {
                        // 방문 표시
                        visited[nx][ny] = true;
                        // 다음에 탐색할 좌표에 추가
                        q.add(new Pair(nx, ny));
                    }
                }
            }
        }

        // 카운트가 7이라면 모든 학생이 연결되어있으므로 true를 반환
        return count == 7;
    }
}
