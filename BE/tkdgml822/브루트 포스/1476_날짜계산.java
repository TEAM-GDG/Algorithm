package F_six_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 1476_날짜계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        int e = 1, s = 1 , m = 1;
        int year = 0;
        // 무한 루프
        while (true) {
            // (조건 종료) 입력이 년수가 맞을시 종료
            if (e - 1 == E && s - 1 == S && m - 1 == M) {
                break;
            }

            // 1 <= e <= 15  이 넘어갈시 1로 초기화
            if (15 < e) {
                e = 1;
            }

            // 1 <= s <= 15 이 넘어갈시 1로 초기화
            if (28 < s) {
                s = 1;
            }

            // 1 <= m <= 15 이 넘어갈시 1로 초기화
            if (19 < m) {
                m = 1;
            }

            e++; s++; m++;
            year++; // 1년 씩증가

        }

        // 출력
        System.out.println(year);
    }
}
