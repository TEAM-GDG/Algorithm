import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] room;
    static boolean[][] cleaned;

    // 북(-1, 0), 동(0, 1), 남(1, 0), 서(0, -1)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        room = new int[n][m];
        cleaned = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(cleanRoom(r, c, d));
    }

    static int cleanRoom(int x, int y, int dir) {
        int cleanCount = 0;

        while (true) {
            // 현재 위치가 청소되지 않았다면 
            // 현재위치를 청소하고 카운트 증가
            if (!cleaned[x][y]) {
                cleaned[x][y] = true;
                cleanCount++;
            }

            // 청소기가 이동했는지 기록할 플래그
            boolean moved = false;

            for (int i = 0; i < 4; i++) {
                // 현재 방향에서 반시계 방향으로 90도 회전하기때문에 +3 하고 나머지 연산으로 방향을 계산
                // ex) 북쪽(0)에서 반시계 방향으로 90도 회전 -> 서쪽(3)
                // (dir(0) + 3) % 4 = 3
                // ex) 남쪽(2)에서 반시계 방향으로 90도 회전 -> 동쪽(1)
                // (dir(2) + 3) % 4 = 1
                dir = (dir + 3) % 4;
                // 다음 이동 좌표 계산
                int nowX = x + dx[dir];
                int nowY = y + dy[dir];

                // 다음에 이동할 좌표가 room의 범위 안에 있고,
                // 다음에 이동할 좌표가 청소가 되지 않았고,
                // 다음에 이동할 좌표가 벽이 아니면
                // 다음에 이동할 좌표를 x, y에 저장하고 청소기가 이동했는지 기록 후 break
                if (nowX >= 0 && nowY >= 0 && nowX < n && nowY < m && !cleaned[nowX][nowY] && room[nowX][nowY] == 0) {
                    x = nowX;
                    y = nowY;
                    moved = true;
                    break;
                }
            }

            // 이동하지 못했다면 후진
            if (!moved) {
                // 현재 방향의 뒤로 이동해야 하기 때문에 방향에 +2 하고 나머지 연산으로 방향을 계산
                // ex) 북쪽(0)에서 180도 회전 -> 남쪽(2)
                // (dir(0) + 2) % 4 = 2
                // ex) 서쪽(3)에서 180도 회전 -> 동쪽(1)
                // (dir(3) + 2) % 4 = 1
                int backDir = (dir + 2) % 4;
                // 후진할 좌표를 계산
                int bx = x + dx[backDir];
                int by = y + dy[backDir];

                // 후진한 좌표가 room의 범위 밖이거나 벽이라면 while문 종료
                if (bx < 0 || bx >= n || by < 0 || by >= m || room[bx][by] == 1) {
                    break;
                }

                // 다음에 이동할 좌표를 후진할 좌표로 저장
                x = bx;
                y = by;
            }
        }

        return cleanCount;
    }
}
