package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_1940_수들의합2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        int start = 0;
        int end = 0;
        int sum = A[start];

        while(end <N){
            if (sum < M) {
                end++;
                sum += A[end];
            } else if (sum > M) {
                sum -= A[start];
                start++;
            }else{
                count++;
                sum -= A[start];
                start++;
                end++;
                sum += A[end];
            }
        }

        System.out.println(count);


    }
}
