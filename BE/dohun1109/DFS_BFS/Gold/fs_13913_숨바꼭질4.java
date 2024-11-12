package DFS_BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class fs_13913_숨바꼭질4 {

    static int N, K;
    static Queue<Integer> queue;
    static int[] near;
    static int[] time; // 시간을 기록할 배열
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        near = new int[100001];
        time = new int[100001];
        Arrays.fill(near, -1); // 경로 추적용 배열 초기화
        Arrays.fill(time, -1); // 시간 기록 배열 초기화
        bfs();

        // 경로 역추적
        List<Integer> path = new ArrayList<>();
        for (int at = K; at != N; at = near[at]) {
            path.add(at);
        }
        path.add(N);
        Collections.reverse(path);

        // 결과 출력
        sb.append(time[K]).append('\n');
        for (int pos : path) {
            sb.append(pos).append(' ');
        }
        System.out.println(sb);
    }

    static void bfs() {
        queue = new LinkedList<>();
        queue.offer(N);
        time[N] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : new int[]{now - 1, now + 1, now * 2}) {
                if (next >= 0 && next < 100001 && time[next] == -1) {
                    queue.offer(next);
                    time[next] = time[now] + 1;
                    near[next] = now;

                    if (next == K) return; // 목표 위치 도달 시 즉시 종료
                }
            }
        }
    }
}