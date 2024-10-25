package F_six_week;

import java.io.*;
import java.util.StringTokenizer;

public class 1018_체스판다시칠하기 {
    public static int getMinCost(int startrow, int startcol, String[] chessboard) {
        String[] board = {"WBWBWBWB", "BWBWBWBW"}; // 화이트 버전, 블랙버전

        int whiteVerCount = 0; // 화이트를 기준으로 최소 비용을 고를 예정

        for (int i = 0; i < 8; i++) { // 세로 8
            int row = startrow + i; // 매개변수로 받은 chessboard의 값의 인덱스는 8X8이 아닌 전체 범위이기때문
            for (int j = 0; j < 8; j++) { // 가로 8
                int col = startcol + j;

                if (chessboard[row].charAt(col) != board[row % 2].charAt(j)) {
                    whiteVerCount++;
                }
            }
        }
        // whiteVerCount는 하얀버전으로 체스판을 자를때의 최소비용이고 64 - whiteVerCount하면 블랙의 최소비용이다.
        // 왜냐면, 체스판의 최대 크기가 8x8이기 때문
        return Math.min(whiteVerCount, 64 - whiteVerCount);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //세로줄 크기
        int M = Integer.parseInt(st.nextToken()); // 가로줄 크기

        String[] chessboard = new String[N];

        for (int i = 0; i < N; i++) {
            chessboard[i] = br.readLine(); //한줄씩 입력받는 String형 배열
        }

        br.close();

        int count = Integer.MAX_VALUE; //가장 큰값으로 지정해두고(초기값)
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                int resultCount = getMinCost(i, j, chessboard);

                if (count > resultCount) {
                    count = resultCount;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }
}