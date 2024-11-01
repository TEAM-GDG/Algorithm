import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구현_17144_미세먼지안녕 {
    static int R;
    static int C;
    static int[][] sumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());       //R: 행
        C = Integer.parseInt(st.nextToken());       //C: 열
        int T = Integer.parseInt(st.nextToken());       //T: 공기청정기가 가동되는 시간
        int[][] dustArr = new int[R+1][C+1];       //미세먼지 값을 받을 2차원 배열
        sumArr = new int[R+1][C+1];     //미세먼지 확산된 값을 나중에 더하기 위해 임시로 저장해두는 배열
        int result = 0;

        int machineR1 = 0;   //machineR1: 공기청정기의 첫번째 행
        int machineR2 = 0;   //machineR1: 공기청정기의 두번째 행

        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C; j++) {
                int num = Integer.parseInt(st.nextToken());
                dustArr[i][j] = num;    //미세먼지 값을 배열에 저장
                if(num == -1){      //공기청정기 체크
                    if(machineR1 == 0){     //공기청정기 행의 첫번째 값이 비어있으면
                        machineR1 = i;      //R1에 값저장
                    } else {            //첫번째 값이 비어있지 않으면
                        machineR2 = i;      //R2에 값저장
                    }
                }
            }
        }

        for(int repeat = 0; repeat < T; repeat++) {     //T번만큼 반복
            //확산
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    spread(i, j, dustArr);
                }
            }

            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    dustArr[i][j] += sumArr[i][j];      //확산된 먼지를 sumArr에 저장해놨다가 나중에 더함
                    sumArr[i][j] = 0;       //sumArr 배열값 초기화
                }
            }

            //----- 공기청정기 위쪽 -----
            //공기청정기 위쪽
            for (int i = machineR1 - 2; i >= 1; i--) {
                dustArr[i + 1][1] = dustArr[i][1];
            }

            //공기청정기 젤위쪽줄
            for (int j = 2; j <= C; j++) {
                dustArr[1][j - 1] = dustArr[1][j];
            }

            //공기청정기 위 젤오른쪽줄
            for (int i = 2; i <= machineR1; i++) {
                dustArr[i - 1][C] = dustArr[i][C];
            }

            //공기청정기 기준 위 오른쪽줄
            for (int j = C - 1; j > 1; j--) {
                dustArr[machineR1][j + 1] = dustArr[machineR1][j];
            }

            //맨 마지막꺼 0으로 초기화
            dustArr[machineR1][2] = 0;

            //----- 공기청정기 아래쪽 -----
            //공기청정기 아래쪽
            for (int i = machineR2 + 2; i <= R; i++) {
                dustArr[i - 1][1] = dustArr[i][1];
            }

            //공기청정기 젤아래줄
            for (int j = 2; j <= C; j++) {
                dustArr[R][j - 1] = dustArr[R][j];
            }

            //공기청정기 밑 젤오른쪽줄
            for (int j = R - 1; j >= machineR2; j--) {
                dustArr[j + 1][C] = dustArr[j][C];
            }

            //공기청정기 기준 아래 오른쪽줄
            for (int j = C - 1; j > 1; j--) {
                dustArr[machineR2][j + 1] = dustArr[machineR2][j];
            }

            //맨 마지막꺼 0으로 초기화
            dustArr[machineR2][2] = 0;
        }

        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(dustArr[i][j] != -1){
                    result += dustArr[i][j];
                }
            }
        }
        System.out.println(result);
    }

    public static void spread(int r, int c, int[][] dustArr){
        //확산되는 먼지
        int spreadDust = dustArr[r][c] / 5;
        //확산되는 횟수
        int count = 0;

        //왼쪽
        if(c > 1 && dustArr[r][c-1] != -1){
            count++;
            sumArr[r][c-1] += spreadDust;
        }

        //오른쪽
        if(c < C && dustArr[r][c+1] != -1){
            count++;
            sumArr[r][c+1] += spreadDust;
        }

        //위쪽
        if(r > 1 && dustArr[r-1][c] != -1){
            count++;
            sumArr[r-1][c] += spreadDust;
        }

        //아래쪽
        if(r < R && dustArr[r+1][c] != -1){
            count++;
            sumArr[r+1][c] += spreadDust;
        }

        //해당자리 남은먼지 계산
        dustArr[r][c] -= count * spreadDust;

    }
}
