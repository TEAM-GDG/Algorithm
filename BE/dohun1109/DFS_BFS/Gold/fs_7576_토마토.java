package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class fs_7576_토마토 {
    static int columns, rows, totalRipeTomatoes;
    static int[][] farm;
    // 방향 배열: 상, 우, 하, 좌 (0=북, 1=동, 2=남, 3=서)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Tomato> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로 columns, 세로 rows 크기 +1 (경계값을 피하기 위함)
        columns = Integer.parseInt(st.nextToken()) + 1;
        rows = Integer.parseInt(st.nextToken()) + 1;

        farm = new int[rows][columns];
        visited = new boolean[rows][columns];
        queue = new LinkedList<>();
        totalRipeTomatoes = 0;

        // 농장 내 토마토 상태 입력
        for (int i = 1; i < rows; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < columns; j++) {
                int tomatoState = Integer.parseInt(st.nextToken());
                farm[i][j] = tomatoState;

                // 익은 토마토는 큐에 추가
                if (tomatoState == 1) {
                    queue.offer(new Tomato(i, j));
                    totalRipeTomatoes++;
                    visited[i][j] = true;
                }
                // 빈 공간은 방문 처리
                else if (tomatoState == -1) {
                    visited[i][j] = true;
                }
            }
        }

        int daysToRipenAll = calculateDaysToRipenAll();
        StringBuilder result = new StringBuilder();

        // 모든 토마토가 익었는지 확인
        if (isAllRipened()) {
            result.append(daysToRipenAll);
        } else {
            result.append(-1);
        }

        System.out.println(result);
    }

    // 모든 토마토가 익었는지 확인하는 함수
    static boolean isAllRipened() {
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (!visited[i][j]) return false;
            }
        }
        return true;
    }

    // 최소 날짜 계산 함수
    static int calculateDaysToRipenAll() {
        int currentRipeCount = 0;
        int nextRipeCount = 0;
        int daysPassed = 0;

        // BFS 탐색
        while (!queue.isEmpty()) {
            Tomato currentTomato = queue.poll();
            int newRipenedTomatoes = currentTomato.spreadRipeness();

            // 현재 익은 토마토 수가 기존 익은 토마토 수보다 작으면 다음 익은 토마토 수 증가
            if (totalRipeTomatoes > currentRipeCount) {
                nextRipeCount += newRipenedTomatoes;
            } else {
                // 모든 현재 토마토를 익힌 후 다음 단계로 넘어감
                totalRipeTomatoes = nextRipeCount;
                currentRipeCount = 0;
                nextRipeCount = newRipenedTomatoes;
                daysPassed++;
            }
            currentRipeCount++;
        }
        return daysPassed;
    }

    // 토마토 클래스 (위치 정보 포함)
    static class Tomato {
        int x;
        int y;

        public Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 토마토 확산 메서드
        public int spreadRipeness() {
            int newRipenedCount = 0;
            for (int i = 0; i < 4; i++) {
                int nx = this.x + dx[i];
                int ny = this.y + dy[i];
                // 확산 가능 여부 확인
                if (canRipen(nx, ny)) {
                    visited[nx][ny] = true;
                    newRipenedCount++;
                    queue.offer(new Tomato(nx, ny));
                }
            }
            return newRipenedCount;
        }

        // 위치가 박스 안에 있고, 방문하지 않았으며, 익지 않은 토마토인 경우 확인
        public boolean canRipen(int nx, int ny) {
            if (0 < nx && nx < rows && 0 < ny && ny < columns) {
                return !visited[nx][ny] && farm[nx][ny] == 0;
            }
            return false;
        }
    }
}