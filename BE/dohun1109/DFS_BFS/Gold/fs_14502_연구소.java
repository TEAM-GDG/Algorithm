package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class fs_14502_연구소 {

    static int N,M,MAX;
    static int zero = -3;
    static int[][] lab;
    static int[][] virusMap;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Virus> viruses;
    static Queue<Virus> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken())+1;
        lab = new int[N][M];
        queue = new LinkedList<>();
        viruses = new ArrayList<>();
        virusMap = new int[N][M];
        MAX = 0;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                lab[i][j] = num;
                if (num == 2){
                    viruses.add(new Virus(i, j));
                } else if (num == 0) {
                    zero++;
                }
            }
        }

        zeroPosition(0);
        System.out.println(MAX);

    }

    static void zeroPosition(int depth) {

        if (depth == 3) {
            for (int i = 1; i < N; i++) {
                virusMap[i] = lab[i].clone();
            }
            for (Virus v : viruses) {
                queue.offer(v);
            }
            int count = diffusion();
            MAX = Math.max(MAX, zero - count);
            return;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    zeroPosition(depth + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }
    
    static int diffusion() {
        int count = 0;
        while(!queue.isEmpty()){
            Virus v = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];
                if (v.isChecked(nx, ny)) {
                    virusMap[nx][ny] = 2;
                    count++;
                    queue.offer(new Virus(nx, ny));
                }
            }
        }
        return count;
    }

    static class Virus {
        int x;
        int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean isChecked(int nx, int ny) {
            if (0<nx&& nx<N && 0<ny&&ny<M){
                return virusMap[nx][ny] == 0;
            }
            return false;
        }

    }

    

    
}
