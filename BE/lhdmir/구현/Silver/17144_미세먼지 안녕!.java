import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] home;
    static int[][] updateHome; // 미세먼지 확산후의 상태를 기록
    static int[] cleaner = new int[2]; // 공기청정기
    static int[] dx = {0, 0, -1, 1}; // 상하(0), 좌(-1), 우(1)
    static int[] dy = {1, -1, 0, 0}; // 상(1), 하(-1), 좌우(0)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();
        home = new int[R][C];
        updateHome = new int[R][C];

        int cleanerIdx = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                home[i][j] = sc.nextInt();
                if (home[i][j] == -1) {
                    cleaner[cleanerIdx++] = i; // 공기청정기의 행 위치 저장
                }
            }
        }

        while (T-- > 0) {
            spreadDust();  // 1. 미세먼지 확산
            operateCleaner();  // 2. 공기청정기 작동
        }

        int result = calculateDust();
        System.out.println(result);
    }

    // 미세먼지 확산
    static void spreadDust() {
        updateHome = new int[R][C]; // 임시 맵 초기화
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (home[i][j] > 0) { // 미세먼지가 있는 경우
                    int dust = home[i][j]; // 현재 미세먼지
                    int spreadAmount = dust / 5; // 확산되는 양
                    int spreadCount = 0; // 확산된 방향 개수

                    // 4방향으로 확산
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];

                        if (nx >= 0 && ny >= 0 && nx < R && ny < C && home[nx][ny] != -1) {
                            // 미세먼지 확산량을 확산후 미세먼지 상태를 저장하는 updateHome에 저장
                            updateHome[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }

                    // 남은 미세먼지 양 계산(미세먼지 잔존량)
                    updateHome[i][j] += dust - (spreadAmount * spreadCount);
                }
            }
        }

        // 확산이 완료된 상태로 맵 갱신
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (home[i][j] != -1) {
                    home[i][j] = updateHome[i][j];
                }
            }
        }
    }

    static void operateCleaner() {
        // 공기청정기의 위쪽 부분을 지정
        // 위쪽 공기청정기는 반시계방향으로 미세먼지를 이동.
        int top = cleaner[0];
        // 1열에서 미세먼지를 위쪽에서 아래로 이동
        for (int i = top - 1; i > 0; i--) home[i][0] = home[i - 1][0];
        // 1행에서 미세먼지를 오른쪽에서 왼쪽으로 이동
        for (int i = 0; i < C - 1; i++) home[0][i] = home[0][i + 1];
        // 마지막열에서 미세먼지를 아래쪽에서 위쪽으로 이동
        for (int i = 0; i < top; i++) home[i][C - 1] = home[i + 1][C - 1];
        // 공기청정기 위쪽부분이 위치한 행에서 미세먼지를 왼쪽에서 오른쪽으로 이동
        for (int i = C - 1; i > 1; i--) home[top][i] = home[top][i - 1];
        // 공기청정기의 바로 오른쪽을 0으로 설정.(미세먼지가 없는 공기)
        home[top][1] = 0;

        // 공기청정기의 아래쪽 부분을 지정
        // 아래쪽 공기청정기는 시계방향으로 미세먼지를 이동.
        int bottom = cleaner[1];
        // 1열에서 미세먼지를 아래쪽에서 위로 이동
        for (int i = bottom + 1; i < R - 1; i++) home[i][0] = home[i + 1][0];
        // 마지막행에서 미세먼지를 오른쪽에서 왼쪽으로 이동
        for (int i = 0; i < C - 1; i++) home[R - 1][i] = home[R - 1][i + 1];
        // 마지막열에서 미세먼지를 위쪽에서 아래쪽으로 이동
        for (int i = R - 1; i > bottom; i--) home[i][C - 1] = home[i - 1][C - 1];
        // 공기청정기 아래쪽부분이 위치한 행에서 미세먼지를 왼쪽에서 오른쪽으로 이동
        for (int i = C - 1; i > 1; i--) home[bottom][i] = home[bottom][i - 1];
        // 공기청정기의 바로 오른쪽을 0으로 설정.(미세먼지가 없는 공기)
        home[bottom][1] = 0;

        // 공기청정기로 들어오는 미세먼지를 삭제하는 코드가 없는 이유.
        // 80번째 줄에서 top - 1 위치까지만 미세먼지가 이동해서 애초에 미세먼지가 공기청정기로 들어오지 않는다.
        // 즉, 공기청정기가 1초뒤에 또 미세먼지를 이동시켜도 결국 공기청정기위의 칸까지만 이동하고 기존에 있던 미세먼지는
        // 그 다음 미세먼지가 해당칸으로 이동하면서 사라짐.
    }

    // 미세먼지 총합 계산
    static int calculateDust() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (home[i][j] > 0) {
                    sum += home[i][j];
                }
            }
        }
        return sum;
    }
}

