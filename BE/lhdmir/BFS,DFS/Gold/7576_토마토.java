import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    // 토마토 정보를 저장할 2차원 배열
    static int[][] box;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        box = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(n, m));
    }

    static int bfs(int n, int m) {
        Queue<Pair> q = new LinkedList<>();

        // 처음에 익은 토마토의 위치를 큐에 삽입
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) {
                    q.add(new Pair(i, j));
                }
            }
        }


        int maxDay = 0;
        // 각 레벨별로 토마토가 익는 날짜를 기록할 변수
        int currentDay;
        int nowX, nowY;
        int nextX, nextY;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            nowX = p.x;
            nowY = p.y;
            // 박스에서 현재 토마토가 며칠째에 익었는지 기록
            currentDay = box[nowX][nowY];

            // 4방향을 검사
            for (int i = 0; i < 4; i++) {
                nextX = nowX + dx[i];
                nextY = nowY + dy[i];

                // 다음 검사할 위치가 box 범위 밖이라면 continue
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
                    continue;
                }

                // 다음 검사할 위치가 익지 않은 토마토라면
                if (box[nextX][nextY] == 0) {
                    // 큐에 위치를 삽입
                    q.add(new Pair(nextX, nextY));
                    // 다음 토마토의 위치에 현재일자 + 1을 저장
                    box[nextX][nextY] = currentDay + 1;
                    // 현재 일자와 최대 일자를 구해서 maxDay에 저장
                    maxDay = Math.max(maxDay, currentDay);
                }
            }
        }

        // 만약 박스에 익지 않은 토마토가 있다면 -1 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    return -1;
                }
            }
        }

        return maxDay;
    }
}