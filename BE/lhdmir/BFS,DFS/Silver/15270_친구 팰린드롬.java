import java.io.*;
import java.util.*;

public class Main {
    static int n, m, max;
    // 친구 관계를 저장할 2차원 배열
    static int[][] graph;
    // 현재 설정에서 친구들이 선택되었는지(혹은 방문되었는지) 추적하는 배열
    static boolean[] matched;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 각 관계를 친구 쌍으로 저장할 배열을 초기화
        graph = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 관계의 첫 번째 친구
            graph[i][0] = Integer.parseInt(st.nextToken());
            // 관계의 두 번째 친구
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        // 무대에 세울 수 있는 최대 친구 수를 0으로 초기화
        max = 0;
        // 각 친구가 선택되었는지 여부를 추적하는 배열
        matched = new boolean[n + 1];

        // 무대에 세울 친구 수를 찾기 위해 탐색 시작. 첫 번째 인덱스와 카운트 0부터 시작
        dfs(0, 0);

        // 최종 카운트는 짝으로 친구들이 춤추므로 두 배를 곱함
        max *= 2;

        // 만약 홀수가 남아 있을 경우, 혼자 춤출 친구를 포함하여 하나를 더 추가
        if (max < n) {
            max++;
        }

        System.out.println(max);
    }

    static void dfs(int index, int count) {
        // 모든 관계를 다 확인한 경우
        if (index >= m) {
            max = Math.max(max, count);
            return;
        }

        // 현재 관계의 친구 둘 중 한 명이라도 이미 선택된 경우, 이 관계를 건너뜀
        if (matched[graph[index][0]] || matched[graph[index][1]]) {
            // 다음 관계로 넘어감
            dfs(index + 1, count);
        } else {
            // 두 친구 모두 아직 선택되지 않았으므로, 이 관계를 선택하고 탐색 진행
            matched[graph[index][0]] = true;
            matched[graph[index][1]] = true;

            // 친구 카운트를 1 증가시키고 다음 관계 탐색
            dfs(index + 1, count + 1);

            // 선택했던 친구들을 해제하고 다시 탐색
            matched[graph[index][0]] = false;
            matched[graph[index][1]] = false;

            // 이 관계를 선택하지 않고 다음 관계 탐색
            dfs(index + 1, count);
        }
    }
}
