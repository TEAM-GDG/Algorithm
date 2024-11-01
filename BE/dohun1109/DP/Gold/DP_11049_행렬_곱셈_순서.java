package DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11049_행렬_곱셈_순서 {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] MATRIX;  //행렬
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        MATRIX = new int[N + 1][2];     //각각의 행렬 크기(1 ~ N)

        // 주어진 행렬 저장
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            MATRIX[i][0] = Integer.parseInt(st.nextToken());
            MATRIX[i][1] = Integer.parseInt(st.nextToken());
        }

        DP = new int[N + 1][N + 1];
        //(A * B), (B * C) 와 같은 두개를 구하는 경우는 항상 하나 이기 이때문에 미리 구함.
        for (int i = 1; i < N; i++) {
            DP[i][i] = 0;
            DP[i][i+1] = MATRIX[i][0] * MATRIX[i][1] * MATRIX[i+1][1];
        }

        BottomUp();
        sb.append(DP[1][N]);
        System.out.println(sb);
        
    }

    private static void BottomUp() {
        for (int len = 3; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j  = i + len -1;

                DP[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int value = DP[i][k] + DP[k + 1][j] + (MATRIX[i][0] * MATRIX[k][1] * MATRIX[j][1]);
                    DP[i][j] = Math.min(DP[i][j], value);
                }
            }
        }
    }
}
