import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N+1];
        int[] dp = new int[N+1];        //가장 긴 증가하는 부분 수 저장할 배열
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());      //배열에 수 저장
            dp[i] = 1;      //dp 배열의 수를 다 1로 초기화
        }

        int result = 1;     //결과를 출력할 변수

        for(int i = 1; i <= N; i++) {       //1부터 N까지 반복
            for(int j = 1; j < i; j++) {    //1부터 i-1까지 반복해서
                if(arr[i] > arr[j]) {       //i번째 수가 이전의 수보다 크면
                    dp[i] = Math.max(dp[i], dp[j] + 1); //dp[i]의 값과, dp[j](이전 값)에 새로운 값(i번째 수)를 더했을 때, 더 긴값을 dp[i]에 저장
                }
            }
            result = Math.max(result, dp[i]);   //전체에서 가장 긴 값을 저장
        }
        System.out.println(result);
    }
}
