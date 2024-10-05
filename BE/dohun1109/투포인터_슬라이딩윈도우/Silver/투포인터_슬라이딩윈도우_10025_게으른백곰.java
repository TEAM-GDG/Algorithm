package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_슬라이딩윈도우_10025_게으른백곰 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //양동이 개수
        int K = Integer.parseInt(st.nextToken())*2 +1; //닿을 수 있는 거리

        int[] iceBucket = new int[1_000_001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gi = Integer.parseInt(st.nextToken());
            int xi = Integer.parseInt(st.nextToken());
            iceBucket[xi] = gi;
        }


        int e = 0,sum =0, max =0;
        while(e<1_000_001){
            if (e - K >=0) sum -= iceBucket[e - K];
            sum += iceBucket[e];
            max = Math.max(max, sum);
            e++;
        }

        System.out.println(max);
    }
}
