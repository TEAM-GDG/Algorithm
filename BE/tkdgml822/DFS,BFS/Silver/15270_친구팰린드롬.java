package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 친구팰린드롬_15270 {

    static int N, M, max;
    static int[][] friends;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            friends[i][0] = Integer.parseInt(st.nextToken());
            friends[i][1] = Integer.parseInt(st.nextToken());
        }

        max = 0;
        visit = new boolean[N + 1];

        find(0, 0);

        max *= 2; // 두 쌍의 친구가 있기 때문에 2를 곱한다. 만약 3명의 친구를 짝을 지으라고 했다면 *3을 해야한다.

        // 로봇 댄스를 출 친구를 고려
        // 만약 홀수로 짝이 지어진다면 +1을 해준다. 예를 들어 사람이 3명인데 2명이 짝이 되면 1명이 남는다.
        // 그래서 ++을 해준다.
        if (max < N) {
            max++;
        }

        System.out.println(max);

    }

    // [[1, 2], [2, 3], [3, 4]]
    static void find(int index, int count) {

        // 모든 관계를 확인이 완료 했으면 최대 수를 갱신한다.
        if (index >= M) {
            max = Math.max(max, count);
            return;
        }

        // 이미 짝이 있는 경우 그냥 넘어간다.
        if (visit[friends[index][0]] || visit[friends[index][1]]) {
            find(index + 1, count);
        } else {
            // 짝이 될 경우
            visit[friends[index][0]] = true;
            visit[friends[index][1]] = true;

            // 짝을 찾았으니깐 count을 1증가 시킨다.
            find(index + 1, count + 1);

            /**
             * [[1, 2], [2, 3], [3, 4]]에서 friend[0] 인 경우를 살펴보면
             * 경우의 수는 두개로 나타 낼 수 있다.
             * [1, 2]를 페어링 하는 경우 또는 넘어가서 다른 경우의 수를 살펴보는 형식 그러므로 false를 한 후
             * count를 증가 시키지 않고 index를 증가 시키는 경우이다.
             */
            visit[friends[index][0]] = false;
            visit[friends[index][1]] = false;

            find(index + 1, count);
        }

    }

}
