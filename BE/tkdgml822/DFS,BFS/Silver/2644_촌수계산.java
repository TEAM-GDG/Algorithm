package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 촌수계산_2644 {

    static List<Integer>[] relation;
    static boolean[] checked;
    static int res = -1; // 기본 값
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        relation = new ArrayList[n + 1];
        checked = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            relation[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int l = Integer.parseInt(br.readLine());

        // 인접 리스트를 만든다.
        // 1 - 2, 3
        // 2 - 1, 7, 8, 9
        // 3 - 1
        // 4 - 5, 6
        // 5 - 4
        // 6 - 4
        // 7 - 2
        for(int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            relation[p].add(c);
            relation[c].add(p);
        }

        dfs(x,y, 0);
        System.out.println(res);
    }

    static void dfs(int start, int end, int cnt) {
        if(start == end) {
            res = cnt;
            return;
        }

        // 7일 경우 바로 방문 한거로 친다.
        checked[start] = true;

        // 인접 리스트를 하는 씩 돈다
        for (int i : relation[start]) {
            if (!checked[i]) { // 방문 하지 않은 경우
                dfs(i, end, cnt + 1);
            }
        }

    }
}
