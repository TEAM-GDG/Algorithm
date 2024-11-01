import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11049_행렬곱셈순서 {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        //N: 행렬의 개수
        arr = new int[N][2];        //행렬의 크기들을 담을 배열

        for(int i = 0; i < N; i++) {        //배열에 값 넣음
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];     //dp를 위한 이차원 배열 생성

        for(int d = 1; d < N; d++) {        //d: 연산하는 행렬의 갯수
            for(int i = 0; i+d < N; i++) {      //i: 대각선의 행
                int min = Integer.MAX_VALUE;
                for(int k = i; k < i+d; k++){       //k기준으로 연산을 나눔
                    int val = dp[i][k] + dp[k+1][i+d] + sum(i, k, i+d);
                    min = Math.min(min, val);
                }
                dp[i][i+d] = min;
            }
        }
        System.out.println(dp[0][N-1]);
    }

    public static int sum(int i, int k, int j){
        return arr[i][0] * arr[k][1] * arr[j][1];
    }
}
