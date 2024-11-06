import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 그래프를 표현할 2차원 ArrayList
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        // 해당 문제의 핵심은 양방향 무가중치 그래프에서 최단 경로를 찾는것이다.
        // 그러므로 그래프를 생성할때 양방향으로 생성하고, 무가중치 그래프에서 최단 경로를 찾을때는
        // 다른 알고리즘(다익스트라, 벨만-포드, A* 등)보다 BFS를 사용하는것이 더 효율적이다.
        // 다른 알고리즘은 가중치가 존재하는 전제하에 만들어진 알고리즘이고, BFS는 구조상 최단경로를 보장한다.

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 그래프 생성
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        System.out.println(bfs(v1, v2));
    }

    public static int bfs(int start, int target) {
        // 출발지와 목적지가 같으면 거리는 0
        if (start == target) return 0;

        // 방문했는지 확인할 배열
        boolean[] visited = new boolean[graph.size() + 1];
        // 각 노드까지의 거리
        int[] distance = new int[graph.size() + 1];

        // 탐색할 예정인 노드를 추가할 큐 생성
        Queue<Integer> q = new LinkedList<>();

        // 큐에 시작지점을 추가
        q.add(start);
        // 시작지점을 방문 기록
        visited[start] = true;
        // 시작시점부터 시작지점의 거리는 0
        distance[start] = 0;

        while (!q.isEmpty()) {
            // 탐색할 노드 추출
            int currentV = q.poll();

            // 현재 노드에서 접근 가능한 노드의 목록들이 graph.get(currentV)에 저장되어있다.
            for (int nextV : graph.get(currentV)) {
                // 만약 nextV 노드에 방문하지 않았다면
                if (!visited[nextV]) {
                    // 다음에 탐색할 노드로 nextV를 추가
                    q.add(nextV);
                    // nextV를 방문한 노드로 설정
                    visited[nextV] = true;
                    // 다음노드(nextV)까지의 거리를 현재노드(currentV) + 1 로 저장(각 노드사이의 거리는 1이기 때문)
                    distance[nextV] = distance[currentV] + 1;

                    // 다음노드가 목적지와 같다면 이전에 저장한 다음노드까지의 거리를 리턴
                    if (nextV == target) return distance[nextV];
                }
            }
        }

        // 목적지 노드를 찾지 못했다면 -1을 리턴
        return -1;
    }
}
