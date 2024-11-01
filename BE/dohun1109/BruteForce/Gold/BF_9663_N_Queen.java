package BruteForce.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BF_9663_N_Queen {
    static int N;
    static int count = 0;
    static int[] queens; // 각 행의 열에 위치한 퀸을 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        queens = new int[N]; // 각 행의 열에 위치한 퀸을 저장할 배열
        Arrays.fill(queens, -1);

        solveNQueens(0); // 첫 번째 행부터 시작
        System.out.println(count);
    }

    static void solveNQueens(int row) {
        if (row == N) {
            count++; // 모든 퀸을 배치 완료한 경우, 해를 하나 찾음
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) { // 현재 위치에 퀸을 놓을 수 있는지 확인
                queens[row] = col; // 현재 행(row)의 퀸 위치를 col로 설정
                solveNQueens(row + 1); // 다음 행으로 이동
                queens[row] = -1; // 백트래킹: 이전 상태로 되돌림
            }
        }
    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 같은 열에 있는지 또는 대각선에 있는지 확인
            if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false; // 공격 범위 안에 있어 안전하지 않음
            }
        }
        return true; // 안전한 위치
    }
}
