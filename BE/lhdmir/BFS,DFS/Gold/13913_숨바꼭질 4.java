import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 이동한 경로를 저장할 배열
    static int[] log;
    // 방문했는지 체크할 배열
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 최대 배열 사이즈 계산
        int maxPosition = Math.max(n, k) * 2;

        log = new int[maxPosition + 1];
        visited = new boolean[maxPosition + 1];
        // log의 값들을 -1로 채움
        Arrays.fill(log, -1);

        bfs(n, k, maxPosition);

        // 경로를 역순으로 꺼내기 위해서 스택 생성
        Stack<Integer> path = new Stack<>();

        // 현재위치 k로 설정
        int current = k;
        // 첫번째값으로 k push
        path.push(current);
        // current 가 n이 될때까지 반복
        while (current != n) {
            // log[current]를 경로에 삽입
            path.push(log[current]);
            // 현재 위치를 log[current]로 설정
            current = log[current];
        }

        System.out.println(path.size() - 1);

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }


    static void bfs(int n, int k, int maxPosition) {
        // 다음에 방문할 위치를 저장할 큐 생성
        Queue<Integer> q = new LinkedList<>();
        // 큐에 현재 위치 삽입
        q.add(n);
        // 현재 위치 방문 표시
        visited[n] = true;

        while (!q.isEmpty()) {
            // 현재 위치
            int current = q.poll();

            // 현재 위치가 목적지랑 같으면 종료
            if (current == k) {
                return;
            }

            // 수빈이가 이동할수있는 방법은 현재위치에서 +1, -1, *2
            int[] nextPositions = {current - 1, current + 1, current * 2};
            for (int next : nextPositions) {
                // 다음 위치가 범위 내에 있고 방문하지 않았다면
                if (next >= 0 && next <= maxPosition && !visited[next]) {
                    // 다음 위치를 큐에 추가
                    q.add(next);
                    // 다음 위치를 방문 표시
                    visited[next] = true;
                    // 이동한 기록을 저장할 배열의 next 칸에 현재 위치를 저장함으로써
                    // k부터 역순으로 추적하면 최단 경로가 나온다.
                    // ex) 5 4 8 16 17 이 최단 경로라면
                    // 4번째 칸에 5를 저장, 8번째 칸에 4를 저장 함
                    // 이동 경로를 추적할때는 log[k=17] 부터 시작해 안에 있는 값을 따라가다보면
                    // 최단 경로가 나온다.
                    log[next] = current;
                }
            }
        }
    }
}
