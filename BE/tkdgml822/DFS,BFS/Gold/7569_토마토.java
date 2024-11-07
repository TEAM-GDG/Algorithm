package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7569 {
    static Queue<Tomato> queue = new LinkedList<>();
    static int[][][] box;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int M;
    static int N;
    static int H;
    static int day = 0;


    static class Tomato {
        int x;
        int y;
        int z;
        int day;

        public Tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        // 깊이, 행, 열
        box = new int[H][N][M];

        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());

                for (int x = 0; x < M; x++) {
                    box[z][y][x] = Integer.parseInt(st.nextToken());

                    if (box[z][y][x] == 1) {
                        queue.add(new Tomato(x, y, z, 0));
                    }
                }
            }
        }

        bfs();
    }

    public static void bfs() {

        while (!queue.isEmpty()) {
            Tomato t = queue.poll(); // 꺼낸다
            day = t.day;

            for (int i = 0; i < 6; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                int nz = t.z + dz[i];

                if (0 <= nx && 0 <= ny && 0 <= nz && nx < M && ny < N && nz < H) {
                    if (box[nz][ny][nx] == 0) {
                        box[nz][ny][nx] = 1;
                        queue.add(new Tomato(nx, ny, nz, day + 1));
                    }
                }
            }
        }

        if (!tomatoCheck()) {
            System.out.println(-1);
        } else {
            System.out.println(day);
        }
    }

    public static boolean tomatoCheck() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


}
