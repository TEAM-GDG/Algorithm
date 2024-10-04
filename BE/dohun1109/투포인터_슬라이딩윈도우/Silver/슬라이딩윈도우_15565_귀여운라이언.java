package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 슬라이딩윈도우_15565_귀여운라이언 {
    public static void main(String[] args) throws IOException {

        // 입력을 효율적으로 받기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 입력 줄에서 N(배열 크기)과 K(필요한 라이언 인형 수)를 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 배열의 크기
        int K = Integer.parseInt(st.nextToken()); // 필요한 라이언 인형의 수
        int[] A = new int[N]; // N개의 정수를 저장할 배열 선언

        // 두 번째 입력 줄에서 배열 A의 요소들을 입력받음
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 1은 라이언 인형, 2는 어피치 인형을 나타냄
            // 어피치(2)는 0으로 처리하고, 라이언(1)은 그대로 저장
            if (num == 2) {
                A[i] = 0; // 어피치는 0으로 변환
            } else {
                A[i] = num; // 라이언은 1로 저장
            }
        }

        // 투포인터 및 변수 초기화
        int s = 0, e = 0, sum = 0, count = 0;
        ArrayList<Integer> subset = new ArrayList<>(); // 가능한 서브 배열의 길이를 저장할 리스트

        // 슬라이딩 윈도우를 이용한 투포인터 방식
        while (true) {
            // 만약 현재 선택된 라이언 인형의 수가 K 이상이면
            if (sum >= K) {
                sum -= A[s++]; // 시작 포인터를 오른쪽으로 이동하고 그 값을 빼줌
                count--; // 윈도우 크기를 감소
            }
            // 끝 포인터가 배열의 끝에 도달하면 반복 종료
            else if (e == N) break;
                // 현재 선택된 라이언 인형의 수가 K보다 작으면
            else {
                sum += A[e++]; // 끝 포인터를 오른쪽으로 이동하고 값을 더해줌
                count++; // 윈도우 크기를 증가
            }
            // 만약 정확히 K개의 라이언 인형을 포함한 경우
            if (sum == K) {
                subset.add(count); // 서브 배열의 길이를 리스트에 추가
            }
        }

        // 서브 배열의 길이를 오름차순으로 정렬
        Collections.sort(subset);

        // 라이언 인형을 K개 포함하는 배열이 없는 경우
        if (subset.isEmpty()) {
            System.out.println("-1"); // "-1" 출력
        }
        // 최소 길이의 서브 배열이 존재하는 경우
        else {
            System.out.println(subset.get(0)); // 가장 작은 길이 출력
        }
    }
}