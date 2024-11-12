import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int[][] map;
    static boolean[][] visited;
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.charAt(j) + "");
            }
        }

        visited = new boolean[n][n];

        // 단지 수
        int count1 = 0;
        // 단지에 속한 집의 수들을 저장할 리스트
        List<Integer> count2 = new ArrayList<>();
        // 단지에 속한 집의 수
        int temp;

        // map을 순회하면서 단지수와 단지수가 속한 집의 수를 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 현재 위치가 집이고 방문하지 않은곳이라면
                if (map[i][j] == 1 && !visited[i][j]) {
                    // 현재 위치에서 bfs를 실행해서 단지에 속한 집의 수를 계산
                    temp = bfs(i, j);
                    // 단지에 속한 집의 수가 0 이상이라면
                    if (temp > 0) {
                        // 단지 수 증가
                        count1++;
                        // 단지에 속한 집의 수를 리스트에 추가
                        count2.add(temp);
                    }
                }

            }
        }

        System.out.println(count1);
        Collections.sort(count2);
        for (Integer i : count2) {
            System.out.println(i);
        }


    }

    static int bfs(int x, int y) {
        // 방문한곳이라면 0리턴
        if (visited[x][y]) {
            return 0;
        }

        Queue<Pair> q = new LinkedList<>();
        // 현재 위치 큐에 추가
        q.add(new Pair(x, y));
        // 현재 위치 방문처리
        visited[x][y] = true;

        // 단지에 속한 집의 수를 1로 설정
        // 처음에 리턴을 하지 않았다면 집이 1개는 무조건 있는것이기 때문에 1로 설정
        int count = 1;

        int nowX, nowY;
        int nextX, nextY;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            nowX = p.x;
            nowY = p.y;

            for (int i = 0; i < 4; i++) {
                nextX = nowX + dx[i];
                nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length) {
                    continue;
                }

                // 다음 탐색할 위치가 집이고 방문하지 않은곳이라면
                if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    // 다음 탐색할 위치 추가
                    q.add(new Pair(nextX, nextY));
                    // 다음 탐색할 위치 방문 처리
                    visited[nextX][nextY] = true;
                    // 단지에 속한 집의 수 증가
                    count++;
                }
            }
        }

        return count;
    }
}
