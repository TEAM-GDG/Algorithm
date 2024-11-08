package DFS_BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class fs_15270_친구_팰린드롬3 {
    static ArrayList<Friend> A; // 친구 관계를 저장하는 리스트
    static boolean[] V; // 친구가 선택되었는지 확인하는 배열
    static int max = 0; // 최대 친구 쌍 수를 저장하는 변수
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()) + 1; // 친구 수 (1부터 시작하므로 +1)
        M = Integer.parseInt(st.nextToken()); // 친구 관계 수

        A = new ArrayList<>(); // 친구 관계 리스트 초기화
        V = new boolean[N]; // 선택 여부 배열 초기화

        // 친구 관계 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            A.add(new Friend(a, b)); // 친구 관계 추가
        }


        search(0, 0);
        max *= 2;   //각 짝은 두명임으로 최종 결과에 2를 곱해줌
        System.out.println(max<N-1?max+1:max);
    }

    // 재귀적으로 친구 쌍을 탐색하는 메서드
    public static void search(int depth, int count) {
        if (depth>=M){  //모든 친구 관계를 확인한 경우
            max = Math.max(max, count);
            return;
        }
        Friend now = A.get(depth); // 현재 탐색 중인 친구 쌍

        //현재 친구 관계 중 한명 이라도 다른 짝이 없는 경우
        if (now.isChecked()){
            search(depth + 1, count);
        }else{
            //두 사람이 짝이 될 경우
            now.check();
            search(depth + 1, count + 1);   //짝을 이룬 경우의 수 처리

            now.check();    //두 사람이 짝이 되지 않을 경우를 위해 방문 여부 초기화
            search(depth + 1, count);   // 짝을 이루지 않은 경우의 수 처리
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