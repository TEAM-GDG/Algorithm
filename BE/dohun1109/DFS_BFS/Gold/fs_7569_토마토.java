package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class fs_7569_토마토 {
    static int N, M, H;
    static int boxes[][][];
    static int dh[] = {-1, 1};
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Tomato> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()) + 1;
        N = Integer.parseInt(st.nextToken()) + 1;
        H = Integer.parseInt(st.nextToken()) + 1;

        boxes = new int[H][N][M];
        queue = new LinkedList<>();
        for (int h = 1; h < H; h++) {
            for (int n = 1; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 1; m < M; m++) {
                    int num = Integer.parseInt(st.nextToken());
                    boxes[h][n][m] = num;
                    if (num == 1) {
                        queue.offer(new Tomato(h, n, m));
                    }
                }
            }
        }

        int minDay = diffusion();
        StringBuilder sb = new StringBuilder();
        if (allRipeCheck()) {
            sb.append(minDay>1?(minDay - 1):minDay);
        } else {
            sb.append(-1);
        }

        System.out.println(sb);


    }

    static int diffusion() {
        int max = 0;
        while (!queue.isEmpty()) {
            Tomato currentTomato = queue.poll();

            int currentDepth = boxes[currentTomato.floor][currentTomato.x][currentTomato.y] + 1;
            for (int i = 0; i < 4; i++) {
                int nx = currentTomato.x + dx[i];
                int ny = currentTomato.y + dy[i];

                if (currentTomato.isChecked(currentTomato.floor, nx, ny)) {
                    boxes[currentTomato.floor][nx][ny] = currentDepth;
                    queue.offer(new Tomato(currentTomato.floor, nx, ny));
                    max = Math.max(max, currentDepth);
                }
            }
            for (int i = 0; i < 2; i++) {
                int nh = currentTomato.floor + dh[i];
                if (currentTomato.isChecked(nh, currentTomato.x, currentTomato.y)) {
                    boxes[nh][currentTomato.x][currentTomato.y] = currentDepth;
                    queue.offer(new Tomato(nh, currentTomato.x, currentTomato.y));
                    max = Math.max(max, currentDepth);
                }
            }
        }
        return max;
    }

    static boolean allRipeCheck() {
        for (int h = 1; h < H; h++) {
            for (int n = 1; n < N; n++) {
                for (int m = 1; m < M; m++) {
                    if (boxes[h][n][m] == 0) return false;
                }
            }
        }
        return true;
    }

    static class Tomato {
        int floor;
        int x;
        int y;

        public Tomato(int floor, int x, int y) {
            this.floor = floor;
            this.x = x;
            this.y = y;
        }


        public boolean isChecked(int nh, int nx, int ny) {
            if (0 < nh && nh < H && 0 < nx && nx < N && 0 < ny && ny < M) {
                return boxes[nh][nx][ny] == 0;
            }
            return false;
        }
    }
}
