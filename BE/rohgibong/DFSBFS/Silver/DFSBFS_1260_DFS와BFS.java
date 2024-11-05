import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSBFS_1260_DFS와BFS {
    public static int N;
    public static int M;
    public static int V;
    public static int[][] arr;
    public static boolean[] visit;
    public static StringBuilder sb = new StringBuilder();
    public static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       //N: 정점의 개수
        M = Integer.parseInt(st.nextToken());       //M: 간선의 개수
        V = Integer.parseInt(st.nextToken());       //V: 탐색을 시작할 정점의 번호

        arr = new int[N+1][N+1];
        visit = new boolean[N+1];       //방문유무를 체크하기위한 배열

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = arr[y][x] = 1;
        }

        dfs(V);     //V에서 DFS 탐색 시작
        Arrays.fill(visit, false);  //방문 배열 초기화
        bfs(V);     //V에서 BFS 탐색 시작
        System.out.println(sb);     //결과 출력
    }

    public static void dfs(int num){
        visit[num] = true;      //정점에 방문했다는 표시
        sb.append(num).append(" ");     //출력할 sb에 추가
        for(int i = 1; i <= N; i++){        //반복돌면서
            if(arr[num][i] == 1 && !visit[i]){  //인접한 수이면서 아직 방문하지 않았으면
                dfs(i);     //해당 수를 정점으로 다시 탐색
            }
        }
    }

    public static void bfs(int num){
        queue.add(num);     //시작 정점을 큐에 추가
        visit[num] = true;  //정점에 방문했다는 표시
        sb.append('\n').append(num).append(" ");    //새로운 줄로 sb에 추가
        while(!queue.isEmpty()){    //큐가 빌때까지 반복
            int idx = queue.poll();     //큐에서 정점을 꺼냄
            for(int i = 1; i <= N; i++){        //1부터 N까지 반복
                if(arr[idx][i] == 1 && !visit[i]){      //인접한 수이면서 아직 방문하지 않았으면
                    queue.add(i);       //큐에 추가
                    visit[i] = true;    //해당 정점을 방문했다고 표시
                    sb.append(i).append(" ");       //결과에 추가
                }
            }
        }
    }
}
