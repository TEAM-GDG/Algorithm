package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class fs_14503_로봇청소기 {
    // 방향 배열: 상, 우, 하, 좌 (0=북, 1=동, 2=남, 3=서)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] room; // 방의 상태를 저장하는 배열
    static boolean[][] visited; // 방문 여부를 저장하는 배열
    static int N, M, count; // 방의 크기(N x M)와 청소한 칸의 수

    public static void main(String[] args) throws IOException {
        /** 청소기 방향(d) : 0 = 북, 1 = 동, 2 = 남, 3 = 서 */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방의 크기 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        room = new int[N][M]; // 방의 상태 초기화
        visited = new boolean[N][M]; // 방문 배열 초기화

        // 로봇 청소기의 초기 위치와 방향 설정
        Robot robot = new Robot(
                Integer.parseInt(st.nextToken()), // x 좌표
                Integer.parseInt(st.nextToken()), // y 좌표
                Integer.parseInt(st.nextToken())  // 방향
        );

        // 방의 상태 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0; // 청소한 칸 수 초기화
        robot.cleaning(robot.x, robot.y, robot.d); // 청소 시작
        System.out.println(count); // 청소한 칸의 총 수 출력
    }

    static class Robot {
        int x; // 로봇의 x 좌표
        int y; // 로봇의 y 좌표
        int d; // 로봇이 바라보는 방향

        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        // 청소를 수행하는 메서드
        public void cleaning(int x, int y, int direction) {
            visited[x][y] = true; // 현재 위치를 청소 완료로 표시
            count++; // 청소한 칸 수 증가

            if (isChecked(x, y)) { // 주변에 청소하지 않은 칸이 있는지 확인
                for (int i = direction + 3; i >= direction; i--) { // 현재 방향에서 반시계 회전하며 확인
                    int ni = i % 4; // 회전 후의 방향
                    int nx = x + dx[ni];
                    int ny = y + dy[ni];
                    if (check(nx, ny)) { // 청소 가능한지 확인
                        cleaning(nx, ny, ni); // 청소 가능한 칸으로 이동하여 청소
                        return;
                    }
                }
            } else { // 청소할 칸이 없는 경우
                int ndir = (direction + 2) % 4; // 후진 방향
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];

                // 후진 가능한지 확인 후 후진하면서 청소 유지
                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (room[nx][ny] == 0) { // 후진이 가능하면
                        count--; // 이미 청소한 칸이므로 count 감소
                        cleaning(nx, ny, direction); // 후진한 칸에서 다시 청소 시작
                        return;
                    }
                }
            }
        }

        // 주어진 위치가 청소 가능하면 true 반환
        public boolean check(int nx, int ny) {
            return 0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && room[nx][ny] == 0;
        }

        // 주어진 위치 주변에 청소하지 않은 칸이 있는지 확인
        public boolean isChecked(int x, int y) {
            for (int i = 0; i < 4; i++) { // 상, 우, 하, 좌 네 방향 검사
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (check(nx, ny)) { // 청소할 수 있는 칸이 있으면 true 반환
                    return true;
                }
            }
            return false; // 청소할 칸이 없으면 false 반환
        }
    }
}