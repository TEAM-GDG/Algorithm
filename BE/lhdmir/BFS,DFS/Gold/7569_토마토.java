import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토의 좌표를 저장할 클래스
class Coordinates {
    int z;
    int x;
    int y;

    public Coordinates(int z, int x, int y) {
        this.z = z;
        this.x = x;
        this.y = y;
    }
}

public class Main {
    // 토마토 정보를 저장할 3차원 배열
    static int[][][] box;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        box = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(bfs(n, m, h));
    }

    static int bfs(int n, int m, int h) {
        Queue<Coordinates> q = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    // 처음에 익은 토마토의 위치를 큐에 삽입
                    if (box[i][j][k] == 1) {
                        q.add(new Coordinates(i, j, k));
                    }
                }
            }

        }

        int maxDay = 0;
        // 각 레벨별로 토마토가 익는 날짜를 기록할 변수
        int currentDay;
        int nowX, nowY, nowZ;
        int nextX, nextY, nextZ;

        while (!q.isEmpty()) {
            Coordinates p = q.poll();
            nowX = p.x;
            nowY = p.y;
            nowZ = p.z;
            // 현재 토마토가 며칠째에 익었는지 기록
            currentDay = box[nowZ][nowX][nowY];

            // 앞, 뒤, 왼쪽, 오른쪽 검사
            for (int i = 0; i < 4; i++) {
                nextX = nowX + dx[i];
                nextY = nowY + dy[i];

                // 다음 검사할 위치가 box 범위 밖이라면 continue
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                    continue;
                }

                // 다음 검사할 위치가 익지 않은 토마토라면
                if (box[nowZ][nextX][nextY] == 0) {
                    // 큐에 다음 토마토의 위치를 삽입
                    q.add(new Coordinates(nowZ, nextX, nextY));
                    // 다음 토마토의 위치에 현재일자 +1 을 저장
                    box[nowZ][nextX][nextY] = currentDay + 1;
                    // 현재 일자와 최대 일자를 구해서 maxDay에 저장
                    maxDay = Math.max(maxDay, currentDay);
                }
            }

            // 위, 아래 검사
            for (int dz : new int[]{1, -1}) {
                nextZ = nowZ + dz;

                // 다음 검사할 위치가 box 범위 밖이라면 continue
                if (nextZ < 0 || nextZ >= h) {
                    continue;
                }

                // 다음 검사할 위치가 익지 않은 토마토라면
                if (box[nextZ][nowX][nowY] == 0) {
                    // 큐에 다음 토마토의 위치를 삽입
                    q.add(new Coordinates(nextZ, nowX, nowY));
                    // 다음 토마토의 위치에 현재일자 +1 을 저장
                    box[nextZ][nowX][nowY] = currentDay + 1;
                    // 현재 일자와 최대 일자를 구해서 maxDay에 저장
                    maxDay = Math.max(maxDay, currentDay);
                }
            }
        }

        // 만약 박스에 익지 않은 토마토가 있다면 -1 리턴
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k] == 0) {
                        return -1;
                    }
                }
            }
        }

        return maxDay;
    }
}