import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSBFS_2644_촌수계산 {
    public static int[][] arr;
    public static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        //n: 전체 사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int human1 = Integer.parseInt(st.nextToken());  //human1: 촌수를 구해야 할 사람 1
        int human2 = Integer.parseInt(st.nextToken());  //human2: 촌수를 구해야 할 사람 2
        int m = Integer.parseInt(br.readLine());    //m: 부모 자식들 간의 관계의 개수

        arr = new int[n+1][n+1];
        visit = new boolean[n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = arr[y][x] = 1;
        }

        System.out.println(bfs(human1, human2));
    }

    public static int bfs(int human1, int human2){
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[arr.length];

        queue.add(human1);
        visit[human1] = true;
        distance[human1] = 0;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i = 1; i < arr.length; i++) {
                if(arr[current][i] == 1 && !visit[i]) {
                    queue.add(i);
                    visit[i] = true;
                    distance[i] = distance[current] + 1;

                    if(i == human2) {
                        return distance[i];
                    }
                }
            }
        }
        return -1;
    }
}
