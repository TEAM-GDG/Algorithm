package 투포인터_슬라이딩윈도우.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_슬라이딩윈도우_15961_회전초밥 {
    static int N, D, K, C; // N: 벨트에 놓인 접시 수, D: 초밥 가짓수, K: 연속해서 먹는 접시 수, C: 쿠폰 번호
    static int[] sushiTable; // 벨트 위에 놓인 초밥의 배열 (각 초밥의 종류가 기록됨)
    static int[] sushi;      // 현재 선택된 초밥 종류에 대한 카운트 배열 (1이면 선택됨, 0이면 선택 안됨)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄 입력 처리: N, D, K, C 값 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시의 수
        D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수 D
        K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수 K
        C = Integer.parseInt(st.nextToken()); // 쿠폰 번호 C (특정 초밥을 먹을 수 있는 쿠폰)

        // 두 번째 줄 이후로 각 접시의 초밥 종류를 입력 받아서 sushiTable 배열에 저장
        sushiTable = new int[N];
        for (int i = 0; i < N; i++) {
            sushiTable[i] = Integer.parseInt(br.readLine());
        }

        // sushi 배열은 초밥의 종류별로 몇 개가 선택되었는지를 저장
        sushi = new int[D + 1]; // 초밥 종류가 1부터 D까지 있으므로 D+1 크기로 설정
        System.out.println(check()); // 최대 초밥 종류 수 계산 후 출력
    }

    // 초밥을 슬라이딩 윈도우로 선택하고, 가장 많은 종류의 초밥을 먹을 수 있는 경우를 찾는 함수
    private static int check() {
        int count = 0; // 현재 먹고 있는 초밥 종류의 개수를 추적하는 변수
        int max = 0;   // 가장 많이 먹을 수 있는 초밥 종류 수를 저장하는 변수

        // 첫 번째 윈도우 (처음 K개의 접시)에서 먹은 초밥을 처리
        for (int i = 0; i < K; i++) {
            int a = sushiTable[i]; // 현재 접시의 초밥 종류 가져오기

            // 초밥 종류가 처음 선택된 경우 count 증가
            if (sushi[a] == 0) {
                count++;
            }
            // 해당 종류의 초밥을 먹은 횟수 증가
            sushi[a]++;
        }

        // 처음 선택한 K개의 초밥 종류 수로 max 초기화
        max = count;

        // 슬라이딩 윈도우 방식으로 초밥 벨트를 한 바퀴 회전하면서 확인
        for (int i = 0; i < N - 1; i++) {
            // 최댓값 판별: 현재 count가 max보다 크거나 같으면 업데이트
            if (count >= max) {
                // 추가로 쿠폰 초밥을 먹었을 때, 해당 초밥이 현재까지 먹은 초밥에 포함되어 있지 않으면 +1
                if (sushi[C] == 0) {
                    max = count + 1;
                } else {
                    max = count; // 이미 먹은 초밥이면 그대로
                }
            }

            // 윈도우에서 첫 번째 초밥 제거 (슬라이딩 윈도우의 시작 부분 변경)
            sushi[sushiTable[i]]--;
            // 첫 번째 초밥을 제거했을 때 해당 종류가 더 이상 포함되지 않으면 count 감소
            if (sushi[sushiTable[i]] == 0) {
                count--;
            }

            // 다음 윈도우의 마지막 초밥 추가 (윈도우 끝 부분에 새로운 초밥 추가)
            int next = sushiTable[(i + K) % N]; // 회전 초밥이므로 벨트를 넘어서 다시 처음으로 돌아감

            // 새로 추가된 초밥 종류가 처음 선택된 경우 count 증가
            if (sushi[next] == 0) {
                count++;
            }
            // 해당 종류의 초밥을 먹은 횟수 증가
            sushi[next]++;
        }

        return max; // 최대 초밥 종류 반환
    }
}