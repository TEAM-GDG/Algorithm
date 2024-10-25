package BruteForce.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BF_1018_체스판_다시_칠하기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());    //세로 크기 (배열 크기)
        int M = Integer.parseInt(st.nextToken());    //가로 크기 (각 N줄)
        String[] A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }

        int MIN = Integer.MAX_VALUE;
        for (int i = 0; i < N-7; i++) {
            for (int j = 0; j < M-7; j++) {
                int count1 = 0;
                int count2 = 0;
                char now1 = 'W'; //B로 시작
                char now2 = 'B'; //W로 시작
                for (int k = 0; k < 8; k++) {
                    String S = A[i+k].substring(j,j+8);
                    for (int z = 0; z < S.length(); z++) {
                        char ch = S.charAt(z);
                        now1 = now1 == 'W'? 'B':'W';
                        now2 = now2 == 'B'? 'W':'B';
                        if (now1 != ch) count1++;
                        if (now2 != ch) count2++;
                    }
                    char temp = now1;
                    now1 = now2;
                    now2 = temp;
                }
                MIN = Math.min(MIN, Math.min(count1,count2));
            }
        }

        System.out.println(MIN);

    }

}
