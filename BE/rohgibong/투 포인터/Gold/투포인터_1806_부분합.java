import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //N: 수열의 길이
        int S = Integer.parseInt(st.nextToken());   //S: 연속된 수들의 부분합이 S 이상

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int total = 0;
        int minLength = 100000;

        while(left <= N && right <= N) {
            if(total < S){
                total += arr[right++];
            } else {
                minLength = Math.min(minLength, right - left);
                total -= arr[left++];
            }
        }
        System.out.println(minLength != 100000 ? minLength : 0);
    }
}
