package DFS_BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class fs_15270_친구_팰린드롬2 {
    static ArrayList<Friend> A; // 친구 관계를 저장하는 리스트
    static boolean[] V; // 친구가 선택되었는지 확인하는 배열
    static int max = 0; // 최대 친구 쌍 수를 저장하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()) + 1; // 친구 수 (1부터 시작하므로 +1)
        int M = Integer.parseInt(st.nextToken()); // 친구 관계 수

        A = new ArrayList<>(); // 친구 관계 리스트 초기화
        V = new boolean[N]; // 선택 여부 배열 초기화

        // 친구 관계 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A.add(new Friend(a, b)); // 친구 관계 추가
        }

        // 모든 친구 관계에 대해 탐색 수행
        for (int i = 0; i < A.size(); i++) {
            search(i, 2); // 초기 쌍의 수는 2로 시작
        }

        // 결과 출력: 조건에 따라 쌍의 수를 조정
        System.out.println(max % 2 == 0 && max != N - 1 ? max + 1 : max);
    }

    // 재귀적으로 친구 쌍을 탐색하는 메서드
    public static void search(int i, int count) {
        Friend x = A.get(i); // 현재 탐색 중인 친구 쌍
        if (x.isChecked()) return; // 이미 선택된 쌍이면 리턴
        if (count > max) max = count; // 최대 쌍의 수 갱신

        // 모든 친구 관계에 대해 재귀적으로 탐색 수행
        for (int j = i; j < A.size(); j++) {
            x.check(); // 현재 쌍 선택 상태 변경
            search(j, count + 2); // 재귀 호출로 다음 쌍 탐색 (쌍이 추가되므로 +2)
            x.check(); // 탐색 후 다시 선택 상태 원복
        }
    }

    // 친구 쌍을 표현하는 클래스
    static class Friend {
        int x; // 첫 번째 친구
        int y; // 두 번째 친구

        public Friend(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 친구 쌍의 선택 상태를 반전시키는 메서드
        public void check() {
            V[this.x] = !V[this.x]; // 첫 번째 친구 상태 반전
            V[this.y] = !V[this.y]; // 두 번째 친구 상태 반전
        }

        // 친구 쌍이 선택되었는지 확인하는 메서드
        public boolean isChecked() {
            return V[this.x] || V[this.y]; // 둘 중 하나라도 선택되었으면 true 반환
        }
    }
}