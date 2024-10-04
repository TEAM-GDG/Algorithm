package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 슬라이딩윈도우_12891_DNA비밀번호 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());   //DNA 문자열 길이
        int P = Integer.parseInt(st.nextToken());   //부분문자열의 길이

        char[] DNA = br.readLine().toCharArray();   // DNA 문자열

        st = new StringTokenizer(br.readLine());
        int A_Min = Integer.parseInt(st.nextToken());   // 최소 A 개수
        int C_Min = Integer.parseInt(st.nextToken());   // 최소 C 개수
        int G_Min = Integer.parseInt(st.nextToken());   // 최소 G 개수
        int T_Min = Integer.parseInt(st.nextToken());   // 최소 T 개수

        // 슬라이딩 윈도우에서 각 문자의 등장 횟수를 카운팅할 배열
        int[] currentCount = new int[4];  // A, C, G, T 순서대로 카운팅

        // 첫 윈도우 설정
        for (int i = 0; i < P; i++) {
            addChar(DNA[i], currentCount);
        }

        int totalCount = 0;

        // 첫 윈도우가 조건을 만족하는지 확인
        if (check(currentCount, A_Min, C_Min, G_Min, T_Min)) {
            totalCount++;
        }

        // 슬라이딩 윈도우
        for (int i = P; i < S; i++) {
            // 앞의 문자를 빼고 새로운 문자를 더함
            addChar(DNA[i], currentCount);
            removeChar(DNA[i - P], currentCount);

            // 조건을 만족하는지 확인
            if (check(currentCount, A_Min, C_Min, G_Min, T_Min)) {
                totalCount++;
            }
        }

        System.out.println(totalCount);
    }

    // 현재 윈도우에 문자를 추가하는 함수
    private static void addChar(char c, int[] currentCount) {
        switch (c) {
            case 'A': currentCount[0]++; break;
            case 'C': currentCount[1]++; break;
            case 'G': currentCount[2]++; break;
            case 'T': currentCount[3]++; break;
        }
    }

    // 현재 윈도우에서 문자를 제거하는 함수
    private static void removeChar(char c, int[] currentCount) {
        switch (c) {
            case 'A': currentCount[0]--; break;
            case 'C': currentCount[1]--; break;
            case 'G': currentCount[2]--; break;
            case 'T': currentCount[3]--; break;
        }
    }

    // 현재 윈도우가 조건을 만족하는지 확인하는 함수
    private static boolean check(int[] currentCount, int A_Min, int C_Min, int G_Min, int T_Min) {
        return currentCount[0] >= A_Min &&
                currentCount[1] >= C_Min &&
                currentCount[2] >= G_Min &&
                currentCount[3] >= T_Min;
    }
}