package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 토마토_7576 {
    static String[][] box;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int M;
    static int N;
    static Queue<Tomato> queue = new LinkedList<>();

    static class Tomato {
        int x;
        int y;
        int day;

        public Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        // 토마토를 받는 상자
        box = new String[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                String tomato = st.nextToken();
                box[i][j] = tomato;

                // 익은 토마토의 좌표를 저정한다.
                if (tomato.equals("1")) {
                    queue.add(new Tomato(i, j, 0));
                }
            }
        }

        bfs();
    }

    public static void bfs() {
        int day = 0; // 처음 토마토는 day 0

        while(!queue.isEmpty()) {
            Tomato t = queue.poll(); // 토마토를 꺼낸다.
            day = t.day; // 토마토의 날짜를 확인한다.

            // 4방향을 확인한다.
            for(int i=0; i<4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];

                // 범위 검사
                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    // 덜익은 토마토를 발견 하면 익은 토마토로 변경
                    if(box[nx][ny].equals("0")) {
                        box[nx][ny] = "1";
                        // 그리고 day + 1을 한다.
                        queue.add(new Tomato(nx, ny, day+1));
                    }
                }
            }
        }

        // 전부 돌고 나서 덜익은 토마토가 있는 검사한다.
        if(checkTomato()){
            // 없으면
            System.out.println(day);
        } else{
            // 있으면 --1
            System.out.println(-1);
        }

    }

    // 덜 익은 토마토를 검사하는 메서드
    static boolean checkTomato() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 덜 익은 토마토가 있다면
                if(box[i][j].equals("0")) {
                    return false;
                }
            }
        }
        return true;
    }

}
