package DFS_BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class fs_2644_촌수계산 {
    static ArrayList<Integer>[] A;  // 인접 리스트를 나타내는 배열
    static boolean[] visited;       // 방문 여부를 확인하는 배열
    static int E, result;           // 도착 노드와 결과 값을 저장할 변수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 노드(사람)의 수
        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()); // 시작점 (촌수를 계산할 시작 사람)
        E = Integer.parseInt(st.nextToken());     // 끝점 (촌수를 계산할 도착 사람)

        int M = Integer.parseInt(br.readLine()); // 간선(관계)의 수

        A = new ArrayList[N + 1]; // 각 노드의 인접 노드를 저장할 리스트 배열
        visited = new boolean[N + 1]; // 방문 여부 확인 배열 초기화
        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>(); // 각 노드에 대한 리스트 초기화
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 관계의 시작 노드
            int e = Integer.parseInt(st.nextToken()); // 관계의 끝 노드
            A[s].add(e); // 양방향 그래프이므로 두 노드를 서로 연결
            A[e].add(s);
        }

        result = -1; // 결과 값을 초기화 (-1은 촌수가 연결되지 않았음을 의미)
        DFS(S, 0); // DFS 호출하여 시작 노드부터 촌수 계산
        System.out.println(result); // 결과 출력
    }

    private static void DFS(int v, int count) {
        if (visited[v]) return; // 이미 방문한 노드는 다시 방문하지 않음
        if (v == E) { // 도착 노드에 도달하면 촌수 설정
            result = count;
            return;
        }

        visited[v] = true; // 현재 노드를 방문 처리
        count++; // 촌수 증가
        for (int i : A[v]) { // 인접 노드 순회
            if (!visited[i]) DFS(i, count); // 방문하지 않은 노드에 대해 DFS 재귀 호출
        }
    }
}