package DFS_BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class fs_1260_DFS와BFS {

    static ArrayList<Integer>[] A;  // 인접 리스트를 나타내는 배열
    static Queue<Integer> bfs;      // BFS에서 사용할 큐
    static boolean[] visited;       // 노드 방문 여부를 저장하는 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int V = Integer.parseInt(st.nextToken()); // 탐색 시작 노드

        visited = new boolean[N + 1]; // 노드 방문 여부 배열 초기화
        A = new ArrayList[N + 1];     // 인접 리스트 초기화
        bfs = new LinkedList<>();     // BFS에서 사용할 큐 초기화

        for (int i = 1; i < N + 1; i++) {
            A[i] = new ArrayList<>(); // 각 노드에 대한 리스트 초기화
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 간선 시작 노드
            int e = Integer.parseInt(st.nextToken()); // 간선 끝 노드
            A[s].add(e); // 양방향 그래프이므로 두 노드 연결
            A[e].add(s);
        }

        // 각 노드의 인접 노드 리스트를 오름차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(A[i]);
        }

        StringBuilder result = new StringBuilder(); // 결과 출력을 위한 StringBuilder

        // DFS 탐색 시작
        DFS(V, result);

        // 방문 기록 초기화
        Arrays.fill(visited, false);

        result.append('\n'); // DFS 결과와 BFS 결과를 구분하기 위한 개행 문자

        // BFS 탐색 시작
        BFS(V, result);

        System.out.println(result); // 결과 출력
    }

    // BFS 탐색 메서드
    private static void BFS(int v, StringBuilder sb) {
        bfs.add(v);           // 시작 노드를 큐에 추가
        visited[v] = true;    // 시작 노드를 방문 처리

        while (!bfs.isEmpty()) {
            int next = bfs.poll();  // 큐에서 노드를 꺼냄
            sb.append(next).append(' '); // 노드를 결과에 추가

            for (int n : A[next]) { // 인접 노드를 순회
                if (!visited[n]) {  // 아직 방문하지 않은 노드라면
                    bfs.add(n);     // 큐에 추가하고
                    visited[n] = true; // 방문 처리
                }
            }
        }
    }

    // DFS 탐색 메서드 (재귀 방식)
    private static void DFS(int v, StringBuilder sb) {
        if (visited[v]) { // 이미 방문한 노드라면 종료
            return;
        }
        visited[v] = true;  // 현재 노드 방문 처리
        sb.append(v).append(' '); // 노드를 결과에 추가

        for (int i : A[v]) { // 인접 노드를 순회
            if (!visited[i]) { // 아직 방문하지 않은 노드라면
                DFS(i, sb);    // 재귀 호출로 DFS 수행
            }
        }
    }
}