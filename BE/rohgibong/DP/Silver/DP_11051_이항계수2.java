import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11051_이항계수2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][K+1];

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= Math.min(i, K); j++){
                if(N==K || j==0){
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = (arr[i-1][j-1] + arr[i-1][j]) % 10007;
                }
            }
        }

        System.out.println(arr[N][K]);
    }

}
