import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFSBFS_14503_로봇청소기 {
    public static int N;
    public static int M;
    public static int[][] roomStatus;
    public static int[][] roomDirty;

    public static int d;
    public static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       //N: 방 크기 (세로)
        M = Integer.parseInt(st.nextToken());       //M: 방 크기 (가로)

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());       //r: 청소기의 좌표 (세로)
        int c = Integer.parseInt(st.nextToken());       //c: 청소기의 좌표 (가로)
        d = Integer.parseInt(st.nextToken());       //d: 청소기의 방향 (0: 북 / 1: 동 / 2: 남 / 3: 서)

        roomStatus = new int[N][M];     //방의 상태를 담을 배열
        roomDirty = new int[N][M];      //방의 청소 상태를 담을 배열 (0: 청소 되어있는 상태 / 1: 청소 해야함)
        result = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int value = Integer.parseInt(st.nextToken());
                roomStatus[i][j] = value;        //방의 상태를 배열에 담음
                if(value == 0) roomDirty[i][j] = 1;     //벽이 아니면 roomDirty에 해당 좌표에다가 1 대입 (청소해야하는 상태)
            }
        }

        moveMachine(r, c);
        System.out.println(result);
    }


    public static void moveMachine(int r, int c) {
        //현재 칸 청소 안되어있으면 청소, 청소하는 칸의 개수 +1
        if (roomDirty[r][c] == 1) {
            roomDirty[r][c] = 0;
            result++;
        }

        // 주변 4칸 중 청소되지 않은 칸이 있는지 확인 및 탐색
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4; // 반시계 방향으로 90도 회전
            int nr = r + (d == 0 ? -1 : d == 2 ? 1 : 0); // 방향에 따른 세로
            int nc = c + (d == 1 ? 1 : d == 3 ? -1 : 0); // 방향에 따른 가로

            if (nr >= 0 && nr < N && nc >= 0 && nc < M && roomDirty[nr][nc] == 1) {     //좌표가 전체 방 범위 안이고, 청소해야하는 상태이면
                moveMachine(nr, nc);        //바라보는 방향으로 한 칸 전진 후 메소드 다시 호출
                return;
            }
        }

        // 여기까지 왔다는건, 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
        int backDirection = (d + 2) % 4;        //방향 180도 회전
        int br = r + (backDirection == 0 ? -1 : backDirection == 2 ? 1 : 0);    //방향에 따른 세로
        int bc = c + (backDirection == 1 ? 1 : backDirection == 3 ? -1 : 0);    //방향에 따른 가로

        if (br >= 0 && br < N && bc >= 0 && bc < M && roomStatus[br][bc] == 0) {    //좌표가 전체 방 범위 안이고, 벽이 아니면
            moveMachine(br, bc);        //청소기 움직이고 메소드 다시 호출
        }
    }
}
