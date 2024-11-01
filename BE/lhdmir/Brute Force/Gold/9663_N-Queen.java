import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    // 현재 행(row), 열(col)에 퀸을 놓을 수 있는지 검사
    static boolean isSafe(int[] queens, int row, int col) {
        // 지금까지 놓인 모든 퀸의 위치를 확인하여 충돌 여부 검사
        // currentRow : 현재 검사 중인 행
        // queens[currentRow] : 현재 검사 중인 열
        for (int currentRow = 0; currentRow < row; currentRow++) {
            // queens[currentRow] : 퀸이 놓인 열 정보가 저장되어있음
            // queens[currentRow](이전 행의 퀸의 열) == col(현재 열) 이 같다면 퀸이 서로 공격가능
            // 대각선 검사는 퀸의 기울기를 통해서 확인
            // 두 좌표가 대각선에 위치한다는 것은 두 좌표간의 행의 차이와 열의 차이가 같다는것이다.
            // 퀸1(row1, col1), 퀸2(row2, col2) 에 있다면 |row1 - row2| == |col1 - col2| 이여야한다.
            if (queens[currentRow] == col || Math.abs(row - currentRow) == Math.abs(col - queens[currentRow])) {
                // 위 두 조건중 하나라도 만족한다면 공격이 가능한것이므로 false 리턴
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 0;

        // 퀸의 위치를 저장할 배열
        int[] queens = new int[n];

        int row = 0, col = 0;

        // 각 행에 퀸을 놓는 시도를 하는 반복문
        while (true) {
            // 현재 행에서 퀸을 놓을 수 있는 자리를 찾는 반복문
            while (col < n && !isSafe(queens, row, col)) {
                // 핸재 행, 열에서 퀸을 놓을 수 없었으므로 한칸 옆으로 이동
                col++;
            }

            // 퀸을 놓을 수 있는 경우
            if (col < n) {
                queens[row] = col; // 현재 열에 퀸을 놓고 배열에 저장
                row++;
                col = 0;

                // 모든 행에 퀸을 놓았다면 유효한 배치 완성
                if (row == n) {
                    count++;
                    // 이전 행으로 돌아가서
                    // 마지막으로 놓은 퀸의 다음 칸으로 이동해서 다시 퀸을 놓을 수 있는지 검사
                    row--;
                    col = queens[row] + 1;
                }
            } else { // 현재 행에 퀸을 놓을 수 없는 경우
                // 처음 행으로 돌아가 모든 가능한 배치를 확인했으므로 종료
                if (row == 0) {
                    break;
                }
                // 이전 행으로 돌아가서
                // 마지막으로 놓은 퀸의 다음 칸으로 이동해서 다시 퀸을 놓을 수 있는지 검사
                row--;
                col = queens[row] + 1;
            }
        }

        System.out.println(count);
    }
}