import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그리디_11047_동전0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       //N: 동전의 갯수
        int K = Integer.parseInt(st.nextToken());       //K: K원을 만들어야함
        int result = 0;     //출력할 동전 갯수

        int[] arr = new int[N];     //동전들을 받을 배열
        for(int i = 0; i < N; i++) {        //N반큼 반복돌면서
            arr[i] = Integer.parseInt(br.readLine());       //동전들을 입력받음
        }

        for(int i = N-1; i >= 0; i--) {     //N-1번째부터 0번째까지 반복돌기
            if(K == 0) break;       //K가 0이되면 반복종료
            result += (K / arr[i]);     //K를 i번째값으로 나눈 값을 result에 더함
            K %= arr[i];        //K를 i번째값으로 나눈 나머지를 K값으로 넣음
        }

        System.out.println(result);     //동전 갯수 출력
    }
}
