import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSBFS_7576_토마토 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());   //M: 상자의 가로 칸의 수
        int N = Integer.parseInt(st.nextToken());   //N: 상자의 세로 칸의 수

        int[][] tomatoInfo = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        int notChanged = 0;     //익지 않은 토마토
        int result = 0;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                tomatoInfo[i][j] = value;        //1: 익은 토마토 / 0: 익지 않은 토마토 / -1: 토마토가 없는 상태
                if(value == 1) queue.add(new int[]{i, j});      //익은 토마토이면 queue에 저장
                else if(value == 0) notChanged++;       //익지않은 토마토이면 익지 않은 토마토 수 +1
            }
        }

        if(notChanged == 0){        //익지 않은 토마토 수가 없으면
            System.out.println(result);     //result의 초기값인 0을 출력
            return;     //종료
        }

        while(!queue.isEmpty()){        //queue가 빌때까지 반복
            int size = queue.size();
            result++;

            for(int i = 0; i < size; i++) {     //queue의 사이즈만큼 반복
                int[] position = queue.poll();      //queue에서 하나 빼옴
                int y = position[0];        //빼온값의 y좌표
                int x = position[1];        //빼온값의 x좌표

                for(int num = 0; num < 4; num++) {      //4번 반복해서
                    int ny = y + dy[num];       //y좌표 +1 -1
                    int nx = x + dx[num];       //x좌표 +1 -1

                    if (ny >= 0 && ny < N && nx >= 0 && nx < M && tomatoInfo[ny][nx] == 0){     //상하좌우 문제없고 0이면
                        tomatoInfo[ny][nx] = 1;     //그 자리에 1을 넣고
                        queue.add(new int[]{ny, nx});       //queue에 값 넣음
                        notChanged--;       //익지않은 토마토 수 -1
                    }
                }
            }

            if(notChanged == 0){
                System.out.println(result);
                return;
            }
        }
        //여기까지 온건, queue의 값을 다 썼는데도 익지 않은 토마토가 남아있는 경우. -1을 출력
        System.out.println(-1);
    }
}
