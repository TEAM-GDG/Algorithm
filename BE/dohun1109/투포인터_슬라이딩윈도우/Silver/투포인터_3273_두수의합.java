package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 투포인터_3273_두수의합 {

    public static void main(String[] args) throws IOException {
        // 입력을 효율적으로 받기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 배열의 크기 입력 받음
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n]; // n개의 정수를 담을 배열 생성

        // 배열 요소 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken()); // 입력받은 값을 배열에 저장
        }

        // 목표 합 x 입력 받음
        int x = Integer.parseInt(br.readLine());

        // 투포인터 기법을 사용하기 위해 배열을 정렬
        Arrays.sort(A);

        // 두 포인터 초기화: 's'는 시작(0), 'e'는 끝(n-1)에서 시작
        int s = 0, e = n - 1, count = 0; // 'count'는 합이 x인 쌍의 개수를 카운트함

        // 두 포인터가 교차하기 전까지 반복
        while (s < e) {
            // 두 포인터의 합이 x보다 작으면, 시작 포인터를 오른쪽으로 이동
            if (A[s] + A[e] < x) {
                s++;
            }
            // 두 포인터의 합이 x보다 크면, 끝 포인터를 왼쪽으로 이동
            else if (A[s] + A[e] > x) {
                e--;
            }
            // 두 포인터의 합이 x와 같으면, count를 증가시키고 두 포인터를 각각 이동
            else {
                count++; // 합이 x인 쌍을 발견했으므로 count 증가
                s++;     // 시작 포인터를 오른쪽으로 이동
                e--;     // 끝 포인터를 왼쪽으로 이동
            }
        }

        // 합이 x인 쌍의 총 개수를 출력
        System.out.println(count);
    }
}