import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2491_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());      //수열의 수를 배열에 담음
        }

        int result = 1;     //최종 결과를 담을 변수
        int increaseResult = 1;     //수가 연속해서 커지는 횟수
        int decreaseResult = 1;     //수가 연속해서 작아지는 횟수
        for(int i = 0; i < N-1; i++) {
            if(arr[i] <= arr[i + 1]){   //다음수가 더 크거나 같은 수이면
                increaseResult++;   //increaseResult 1 증가
            } else {    //다음수가 더 작은 수이면
                result = Math.max(result, increaseResult);  //result에 담긴 수와 increaseResult에 담긴 수 중 더 큰 값을 result에 담음
                increaseResult = 1;     //increaseResult 초기화
            }
            if(arr[i] >= arr[i + 1]){   //다음수가 더 작거나 같은 수이면
                decreaseResult++;   //decreaseResult 1 증가
            } else {
                result = Math.max(result, decreaseResult);  //result에 담긴 수와 decreaseResult에 담긴 수 중 더 큰 값을 result에 담음
                decreaseResult = 1;     //decreaseResult 초기화
            }
        }
        System.out.println(Math.max(Math.max(result, increaseResult), decreaseResult));
        //위에서 else에 한번도 안 걸리는 경우가 있을수있으니 마지막으로 increaseResult, decreaseResult와 result의 값을 비교해서 더 큰 수를 출력
    }
}
