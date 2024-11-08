import java.util.*;
import java.io.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, m;
    // 연구소의 정보
    static int[][] lab;
    // 바이러스의 위치정보 저장
    static List<Pair> virusList = new ArrayList<>();
    // 빈 칸 위치 저장
    static List<Pair> emptySpaces = new ArrayList<>();
    // 최대 안전 영역
    static int maxSafeArea = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        lab = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                // 현재 위치가 바이러스라면 virusList에 삽입
                if (lab[i][j] == 2) virusList.add(new Pair(i, j));
                // 현재 위치가 빈칸이라면 빈칸이라면 emptySpaces에 삽입
                if (lab[i][j] == 0) emptySpaces.add(new Pair(i, j));
            }
        }

        buildWalls(0, 0);
        System.out.println(maxSafeArea);
    }

    // 빈 칸 위치 리스트를 이용하여 벽 세우기
    static void buildWalls(int count, int start) {
        // 벽이 3개가 됬을 경우
        if (count == 3) {
            // 현재 연구소의 상태를 복사
            int[][] labCopy = new int[n][m];
            for (int i = 0; i < n; i++) {
                labCopy[i] = lab[i].clone();
            }
            // 바이러스 확산 시뮬레이션 실행
            spreadVirus(labCopy);
            // 최대 안전 영역 계산
            maxSafeArea = Math.max(maxSafeArea, getSafeArea(labCopy));
            return;
        }

        // start 지점부터 빈공간을 저장한 리스트의 사이즈만큼 반복하면서 벽을 세움
        for (int i = start; i < emptySpaces.size(); i++) {
            // i번째의 빈공간에 벽을 세우고
            Pair p = emptySpaces.get(i);
            lab[p.x][p.y] = 1;
            // count(depth) 를 하나 증가시키고
            // i 번째의 다음 빈공간의 정보를 매개변수로 재귀함수 실행
            buildWalls(count + 1, i + 1);
            // 호출이 종료됬으면 세원던 벽을 다시 빈공간으로 설정
            lab[p.x][p.y] = 0;
        }
    }

    static void spreadVirus(int[][] labCopy) {
        Queue<Pair> q = new LinkedList<>(virusList);
        // 감염된 영역
        int infectedCount = 0;

        while (!q.isEmpty()) {
            Pair current = q.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 다음 바이러스의 확산 위치가 연구소 범위 안이고, 빈 공간이라면
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && labCopy[nx][ny] == 0) {
                    // 바이러스 확산
                    labCopy[nx][ny] = 2;
                    // 다음 바이러스가 확산된 위치를 큐에 저장
                    q.add(new Pair(nx, ny));
                    // 감염된 영역 증가
                    infectedCount++;

                    // 남은 안전 영역 이 현재 최대 안전 영역보다 작다면 탐색 종료

                    // 빈공간(emptySpaces) - 벽(3) - 최대 안전 영역(maxSafeArea)
                    // 최대 안전 영역보다 빈 칸이 남아있어야할 최소한의 숫자
                    // ex) 빈공간(40) - 벽(3) - 최대 안전 영역(30) = 7 즉, 최대 안전 영역보다 이번에는 7칸이나 더 확보할 수 있다.
                    // 하지만 감염됨 영역(infectedCount)이 위 수식보다 커지면 최대 안전 영역을 초과할 수 없는 상황
                    if (infectedCount > emptySpaces.size() - 3 - maxSafeArea) {
                        return;
                    }
                }
            }
        }
    }

    // 안전 영역 계산
    static int getSafeArea(int[][] labCopy) {
        int safeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (labCopy[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }
}