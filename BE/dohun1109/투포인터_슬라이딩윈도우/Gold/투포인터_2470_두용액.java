package 투포인터_슬라이딩윈도우.Gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 투포인터_2470_두용액 {

    public static void main(String[] args) throws IOException {

        // 콘솔로부터 입력을 받기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 개수를 입력받음 (2 <= N <= 100,000)
        int N = Integer.parseInt(br.readLine());

        // 용액 배열 선언
        int[] solutions = new int[N];

        // 용액 배열에 값을 입력받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터 알고리즘을 적용하기 위해 배열을 정렬
        Arrays.sort(solutions);

        // 두 포인터 변수 설정: 시작 포인터 s는 배열의 첫 번째, 끝 포인터 e는 마지막 원소를 가리킴
        int s = 0, e = N - 1;
        // 최소 절대값을 저장할 변수 min과 그 때의 답을 저장할 변수들 선언
        int min = Integer.MAX_VALUE, answer_s = 0, answer_e = 0;

        // 두 포인터가 교차할 때까지 반복
        while (s < e) {
            // 두 포인터가 가리키는 원소의 합 계산
            int sum = solutions[s] + solutions[e];

            // 현재 합의 절대값이 기존의 최소값보다 작으면 답을 갱신
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                answer_s = solutions[s];
                answer_e = solutions[e];
                // 합이 0이면 더 이상 최적의 해를 찾을 필요가 없으므로 반복 종료
                if (min == 0) break;
            }

            // 합이 0보다 작으면 왼쪽 포인터를 오른쪽으로 이동해서 합을 증가시킴
            if (sum < 0) {
                s++;
            }
            // 합이 0보다 크면 오른쪽 포인터를 왼쪽으로 이동해서 합을 감소시킴
            else if (sum > 0) {
                e--;
            }
            // 합이 0인 경우 (위의 min == 0에서 이미 break되므로 실질적으로 필요는 없음)
            else {
                s++;
                e--;
            }
        }

        // 결과를 출력하기 위해 BufferedWriter 사용
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(new StringBuilder().append(answer_s).append(' ').append(answer_e)));
        bw.close();
    }
}