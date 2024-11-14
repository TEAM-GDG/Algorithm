import java.io.*;
import java.util.*;

class Position {
    int redX, redY, blueX, blueY, moves;

    Position(int redX, int redY, int blueX, int blueY, int moves) {
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
        this.moves = moves;
    }
}

public class Main {
    // 보드 상태 저장
    static char[][] board;
    // 각 위치에서 방문 여부 체크[redX][redY][blueX][blueY]
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visited = new boolean[n][m][n][m];

        Position start = new Position(0, 0, 0, 0, 0);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    start.redX = i;
                    start.redY = j;
                } else if (board[i][j] == 'B') {
                    start.blueX = i;
                    start.blueY = j;
                }
            }
        }

        System.out.println(bfs(start));
    }

    static int bfs(Position start) {
        Queue<Position> queue = new LinkedList<>();

        // 처음 위치 방문 처리
        queue.add(start);
        visited[start.redX][start.redY][start.blueX][start.blueY] = true;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            // 최대 10번 이하로 움직일 수 있으므로 10 이상인 경우 -1 반환
            if (pos.moves >= 10) return -1;

            // 네 가지 방향으로 기울이기
            for (int i = 0; i < 4; i++) {
                // 빨간 구슬의 다음 X,Y 좌표
                int nRedX = pos.redX;
                int nRedY = pos.redY;
                // 파란 구슬의 다음 X,Y 좌표
                int nBlueX = pos.blueX;
                int nBlueY = pos.blueY;

                boolean redHole = false, blueHole = false;

                // 빨간 구슬 이동
                // 다음 위치가 벽이거나 구멍일때까지 이동
                while (board[nRedX + dx[i]][nRedY + dy[i]] != '#' && board[nRedX][nRedY] != 'O') {
                    nRedX += dx[i];
                    nRedY += dy[i];
                    // 구멍에 도달했다면 반복문 종료
                    if (board[nRedX][nRedY] == 'O') {
                        redHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동
                // 다음 위치가 벽이거나 구멍일때까지 이동
                while (board[nBlueX + dx[i]][nBlueY + dy[i]] != '#' && board[nBlueX][nBlueY] != 'O') {
                    nBlueX += dx[i];
                    nBlueY += dy[i];
                    if (board[nBlueX][nBlueY] == 'O') {
                        blueHole = true;
                        break;
                    }
                }

                // 파란 구슬이 구멍에 빠지면 무시하고 다음 방향으로
                if (blueHole) continue;

                // 빨간 구슬이 구멍에 빠지면 성공, 움직임 횟수 반환
                if (redHole) return pos.moves + 1;

                // 빨간 구슬과 파란 구슬이 같은 위치에 있다면
                // 이동 거리가 더 큰 구슬을 한 칸 되돌림
                // 왜냐하면 이동 거리가 더 큰 구슬이 나중에 왔다는 의미.
                // 구슬은 똑같은 속도로 움직이기 때문에 이동거리가 다른 구슬보다 많다면
                // 해당 구슬이 나중에 도착한것.
                if (nRedX == nBlueX && nRedY == nBlueY) {

                    // 빨간 구슬이 더 많이 이동했다면
                    // 빨간 구슬을 이 전의 칸으로 이동
                    if (Math.abs(nRedX - pos.redX) + Math.abs(nRedY - pos.redY) > Math.abs(nBlueX - pos.blueX) + Math.abs(nBlueY - pos.blueY)) {
                        nRedX -= dx[i];
                        nRedY -= dy[i];
                    } else {
                        nBlueX -= dx[i];
                        nBlueY -= dy[i];
                    }
                }

                // 방문하지 않은 위치라면 방문 처리 후 큐에 추가
                if (!visited[nRedX][nRedY][nBlueX][nBlueY]) {
                    visited[nRedX][nRedY][nBlueX][nBlueY] = true;
                    queue.add(new Position(nRedX, nRedY, nBlueX, nBlueY, pos.moves + 1));
                }
            }
        }

        // 조건에 맞는 경우가 없으면 -1 반환
        return -1;
    }
}
