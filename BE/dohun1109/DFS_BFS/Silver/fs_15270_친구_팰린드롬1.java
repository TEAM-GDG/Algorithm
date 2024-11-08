package DFS_BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class fs_15270_친구_팰린드롬1 {
    static int n, m;
    static int[][] friend; // 친구 관계를 저장할 배열
    static boolean[] visited; // 친구 짝을 이미 이룬 사람을 표시할 배열
    static int answer = 0; // 최대 짝 수를 저장할 변수

    public static void main(String[] sdf) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 사람 수
        m = Integer.parseInt(st.nextToken()); // 친구 관계 수
        visited = new boolean[n + 1]; // 사람 수에 맞게 방문 여부 배열 생성

        friend = new int[m][2]; // 각 친구 관계를 저장할 배열 초기화

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friend[i][0] = a; // 친구 관계의 첫 번째 사람
            friend[i][1] = b; // 친구 관계의 두 번째 사람
        }

        dfs(0, 0); // 깊이 우선 탐색 시작

        answer *= 2; // 각 짝은 두 명이므로 최종 결과에 2를 곱해줌

        if (answer < n) { // 짝이 되지 못한 사람 한 명이 남을 수 있으므로 이를 고려
            answer++;
        }

        System.out.println(answer); // 결과 출력
    }

    private static void dfs(int depth, int count) {
        if (depth >= m) { // 모든 친구 관계를 확인한 경우
            answer = Math.max(answer, count); // 최대 짝 수 갱신
            return;
        }

        // 현재 친구 관계 중 한 명이라도 이미 다른 짝이 있는 경우
        if (visited[friend[depth][0]] || visited[friend[depth][1]]) {
            dfs(depth + 1, count); // 다음 관계로 이동
        } else {
            // 두 사람이 짝이 될 경우
            visited[friend[depth][0]] = true;
            visited[friend[depth][1]] = true;

            dfs(depth + 1, count + 1); // 짝을 이룬 경우의 수를 처리

            // 두 사람이 짝이 되지 않을 경우를 위해 방문 여부 초기화
            visited[friend[depth][0]] = false;
            visited[friend[depth][1]] = false;

            dfs(depth + 1, count); // 짝을 이루지 않은 경우의 수를 처리
        }
    }
}