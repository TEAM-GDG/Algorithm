import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 그래프를 표현할 2차원 ArrayList
    static ArrayList<ArrayList<Integer>> graph;
    // 방문한 노드를 기록할 배열
    static boolean[] visited;
//    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        // 그래프 생성
        graph = new ArrayList<>();

        // 노드의 갯수만큼 graph의 각 칸에 ArrayList 추가
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 접근이 가능하기때문에 각 노드별로 접근할 수 있는 노드 추가
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 방문할 수 있는 노드가 여러개인 경우 노드 번호가 작은 순으로 방문해야하기때문에
        // 각 노드별로 접근할 수 있는 노드의 목록을 오름차순으로 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }

//        for (int i = 1; i <= n; i++) {
//            System.out.println(graph.get(i).toString());
//        }

        // 방문한 노드 초기화
        visited = new boolean[n + 1];
        dfs(v);
        System.out.println();

        // 방문한 노드 초기화
        visited = new boolean[n + 1];
        bfs(v);
//        System.out.println();
    }

    public static void dfs(int v) {
        // 현재 방문한 노드를 true로 설정
        visited[v] = true;
        System.out.print(v + " ");

        // 현재 노드에서 접근 가능한 노드의 목록들이 graph.get(v)에 오름차순으로 저장되어있다.
        for (int nextV : graph.get(v)) {
            // 만약 nextV 노드에 방문하지 않았다면 nextV를 탐색
            if (!visited[nextV]) dfs(nextV);
        }
    }

    public static void bfs(int v) {
        // 탐색할 노드들을 추가할 큐 생성
        Queue<Integer> q = new LinkedList<>();
        // 현재 방문한 노드로 v추가
        q.add(v);
        // 현재 방문한 노드를 true로 설정
        visited[v] = true;

        // 큐가 비어있지 않다면(아직 탐색할 곳이 남았다면)
        while (!q.isEmpty()) {
            // 현재 탐색중인 노드 추출
            int currentV = q.poll();
            System.out.print(currentV + " ");

            // 현재 노드에서 접근 가능한 노드의 목록들이 graph.get(currentV)에 오름차순으로 저장되어있다.
            for (int nextV : graph.get(currentV)) {
                // 만약 nextV 노드에 방문하지 않았다면
                if (!visited[nextV]) {
                    // 다음에 탐색할 노드로 nextV를 추가
                    q.add(nextV);
                    // nextV를 방문한 노드로 설정
                    visited[nextV] = true;
                }
            }
        }
    }
}
