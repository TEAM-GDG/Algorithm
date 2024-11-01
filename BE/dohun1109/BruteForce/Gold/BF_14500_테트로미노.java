package BruteForce.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BF_14500_테트로미노 {
    static int[][] Tetromino;
    static int MAX = Integer.MIN_VALUE;
    static int[][] board;
    static int N,M;
    static int[] dx = new int[]{1, 0, -1, 0};  //하 : 0 , 좌 : 1 , 상 : 2 , 우 : 3
    static int[] dy = new int[]{0, -1, 0, 1};

    static {
        Tetromino = new int[][]{
                {3,3,3},{2,2,2},    //작대기
                {3,0,1},            //정사각형
                {1,2,2},{2,3,3},{3,0,0},{0,1,1},    //31 형태 1유형
                {1,0,0},{3,2,2},{0,3,3},{2,1,1},    //31 형태 2유형
                {0,3,0},{3,2,3},{0,1,0},{3,0,3},    //지렁이
                {1,0,3},{2,1,0},{3,2,1},{0,3,2}
        };  //15 + 4 = 19  [18]

        

    }

    static void quest1 (){
        for (int k = 0;k<15; k++){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int sum = board[i][j];
                    int mov_x = i;
                    int mov_y = j ;
                    boolean visited = true;
                    for (int g = 0; g < 3; g++) {
                        int direction = Tetromino[k][g];
                        mov_x+= dx[direction];
                        mov_y+= dy[direction];
                        if (0<=mov_x && mov_x<N && 0<=mov_y && mov_y<M){
                            sum+=board[mov_x][mov_y];
                        }else {
                            visited = false;
                            break;
                        }
                    }
                    if (visited)MAX = Math.max(MAX,sum);
                }
            }
        }

    }
    static void quest2 (){
        for (int k = 15; k < 19; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int sum = board[i][j];
                    boolean visited = true;
                    for (int g = 0; g < 3; g++) {
                        int direction = Tetromino[k][g];
                        int mov_x = i + dx[direction];
                        int mov_y = j + dy[direction];
                        if (0<=mov_x && mov_x<N && 0<=mov_y && mov_y<M){
                            sum+=board[mov_x][mov_y];
                        }else {
                            visited = false;
                            break;
                        }
                    }
                    if (visited)MAX = Math.max(MAX,sum);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        /** `폴리노미노`
         *  1. 정사각형은 서로 곂치면 안된다.
         *  2. 도형은 모두 연결되어 있어야 한다.
         *  3. 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안된다.
         *
         *  정사각형 4개를 이어 붙인 `폴리오미노`는 `테트로미노` 라고 한다 (총 5개 존재)
         *
         *
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

         board = new int[N][M];  //x : N , y : M
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        quest1();
        quest2();

        System.out.println(MAX);

    }

}
