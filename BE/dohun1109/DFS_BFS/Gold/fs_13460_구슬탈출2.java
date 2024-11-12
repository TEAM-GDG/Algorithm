package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class fs_13460_구슬탈출2 {
    // 게임 보드
    static char[][] board;
    // 구슬 상태를 저장하는 큐
    static Queue<Beads> beadsQueue = new LinkedList<>();
    // 상, 우, 하, 좌 방향 이동을 위한 배열
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    // 구슬 객체
    static Beads beads;
    // 방문한 위치를 체크하기 위한 배열 (Red와 Blue 구슬의 위치를 모두 체크)
    static boolean[][][][] visited;
    // 보드의 행과 열 크기
    static int N, M;

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 보드의 행(N)과 열(M)을 입력받음
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        // 빨간 구슬과 파란 구슬의 초기 위치를 저장할 변수
        Red red = null;
        Blue blue = null;

        // 보드를 설정하면서 구슬 위치를 저장
        for (int i = 0; i < N; i++) {
            char[] width = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = width[j];
                if (width[j] == 'R') {  // 빨간 구슬
                    red = new Red(i, j, 0);
                } else if (width[j] == 'B') {  // 파란 구슬
                    blue = new Blue(i, j);
                }
            }
        }

        // 초기 구슬 상태를 큐에 추가
        beads = new Beads(red, blue);
        beadsQueue.offer(beads);

        // BFS 시작 및 결과 출력
        System.out.println(bfs());
    }

    // BFS 탐색 함수
    private static int bfs() {
        while (!beadsQueue.isEmpty()) {
            Beads beads = beadsQueue.poll();
            Red red = beads.red;
            Blue blue = beads.blue;

            // 이동 횟수가 10번을 초과하면 실패로 간주
            if (red.count > 10) return -1;
            // 파란 구슬이 구멍에 빠졌다면 이 경우는 무시
            if (board[blue.x][blue.y] == 'O') continue;
            // 빨간 구슬이 구멍에 빠졌다면 현재 이동 횟수를 반환
            if (board[red.x][red.y] == 'O') return red.count;

            // 네 방향으로 이동을 시도
            for (int i = 0; i < 4; i++) {
                int nxR = red.x, nyR = red.y, nxB = blue.x, nyB = blue.y;
                int rMove = 0, bMove = 0;

                // 빨간 구슬이 벽에 부딪히거나 구멍에 빠질 때까지 이동
                while (board[nxR + dx[i]][nyR + dy[i]] != '#' && board[nxR][nyR] != 'O') {
                    nxR += dx[i];
                    nyR += dy[i];
                    rMove++;
                }
                // 파란 구슬이 벽에 부딪히거나 구멍에 빠질 때까지 이동
                while (board[nxB + dx[i]][nyB + dy[i]] != '#' && board[nxB][nyB] != 'O') {
                    nxB += dx[i];
                    nyB += dy[i];
                    bMove++;
                }

                // 두 구슬이 같은 위치에 있다면 더 많이 이동한 구슬을 뒤로 한 칸 이동
                if (nxR == nxB && nyR == nyB && board[nxR][nyR] != 'O') {
                    if (rMove > bMove) {
                        nxR -= dx[i];
                        nyR -= dy[i];
                    } else {
                        nxB -= dx[i];
                        nyB -= dy[i];
                    }
                }

                // 새로운 위치가 방문한 적이 없다면 큐에 추가하고 방문 체크
                if (!visited[nxR][nyR][nxB][nyB]) {
                    visited[nxR][nyR][nxB][nyB] = true;
                    beadsQueue.offer(new Beads(new Red(nxR, nyR, red.count + 1), new Blue(nxB, nyB)));
                }
            }
        }
        // 실패한 경우 -1 반환
        return -1;
    }

    // 구슬 상태 클래스
    static class Beads {
        Red red;
        Blue blue;

        public Beads(Red red, Blue blue) {
            this.red = red;
            this.blue = blue;
        }
    }

    // 빨간 구슬 위치 및 이동 횟수 클래스
    static class Red {
        int x, y, count;

        public Red(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    // 파란 구슬 위치 클래스
    static class Blue {
        int x, y;

        public Blue(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}