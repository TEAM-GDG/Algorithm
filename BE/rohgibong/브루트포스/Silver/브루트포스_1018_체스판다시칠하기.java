import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 브루트포스_1018_체스판다시칠하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] arr = new char[N][M];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            arr[i] = line.toCharArray();
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i <= N - 8; i++) {
            for(int j = 0; j <= M - 8; j++) {
                result = Math.min(result, calculateRecolorCount(arr, i, j));
            }
        }

        System.out.println(result);
    }

    public static int calculateRecolorCount(char[][] arr, int startRow, int startCol) {
        int recolorCountB = 0; // 'B'로 시작하는 패턴
        int recolorCountW = 0; // 'W'로 시작하는 패턴

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    // 짝수 위치에서 'B'가 있어야 하는 경우
                    if (arr[startRow + i][startCol + j] != 'B') recolorCountB++;
                    if (arr[startRow + i][startCol + j] != 'W') recolorCountW++;
                } else {
                    // 홀수 위치에서 'W'가 있어야 하는 경우
                    if (arr[startRow + i][startCol + j] != 'W') recolorCountB++;
                    if (arr[startRow + i][startCol + j] != 'B') recolorCountW++;
                }
            }
        }

        return Math.min(recolorCountB, recolorCountW);
    }
}