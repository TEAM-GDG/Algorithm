package DFS_BFS;

import java.util.*;

public class DFS와BFS_1260 {
    static boolean[] visited;
    static ArrayList<Integer>[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int start = sc.nextInt();
        /**
         * 4 5 1
         * 1 2
         * 1 3
         * 1 4
         * 2 4
         * 3 4
         */
        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        /**
         * 인접리스트 생성
         * 1 - 2, 3, 4
         * 2 - 4, 1
         * 3 - 4, 1
         * 4 - 1, 2, 3
         */
        for (int i = 0; i < M; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
            A[E].add(S);
        }

        // 오름 차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(A[i]);
        }

        visited = new boolean[N + 1]; // default 값 - false
        DFS(start);
        System.out.println();
        Arrays.fill(visited, false);
        // visited = new boolean[N + 1];
        BFS(start);
    }

    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(Node); // 처음 반복한곳은 바로 Node 추가
        visited[Node] = true; // 방문한곳 true

        while (!queue.isEmpty()) { // 큐가 비어질때까지 반복
            int now_Node = queue.poll(); // node를 꺼낸다.
            System.out.print(now_Node + " ");
            for (int i : A[now_Node]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    private static void DFS(int Node) {
        System.out.print(Node + " "); // 시작 부분은 바로 출력
        visited[Node] = true; // 방문만 곳 true
        for (int i : A[Node]) { // 인접 리스트를 계속 반복
            if (!visited[i]) { // 방문 하지 않은 곳이라면
                DFS(i); // 반복
            }
        }
    }

}
