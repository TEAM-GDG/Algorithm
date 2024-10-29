import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N+2];
        int[] P = new int[N+2];

        for(int i = 1; i <=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());        //T: 일하는 일 수
            P[i] = Integer.parseInt(st.nextToken());        //P: 금액
        }

        int[] DP = new int[N+2];        //DP[i]: i일까지 일했을 때의 최고 수익
        int max = Integer.MIN_VALUE;

        for(int i = 1; i < N+2; i++){
            if(max < DP[i]) max = DP[i];    //현재까지의 최대 수익

            int day = i + T[i];     //day: ~까지 일한 날
            if(day < N + 2){
                DP[day] = Math.max(DP[day], max + P[i]);    //이미 구해진 그 날의 수익과 최대값+P[i] 비교해서 더 큰값 넣기
            }
        }

        System.out.println(max);
    }
}
