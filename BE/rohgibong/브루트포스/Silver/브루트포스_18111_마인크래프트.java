import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 브루트포스_18111_마인크래프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       //N: 세로
        int M = Integer.parseInt(st.nextToken());       //M: 가로
        int B = Integer.parseInt(st.nextToken());       //B: 인벤토리에 있는 블럭
        int[][] ground = new int[N][M];     //땅 높이값을 받을 배열

        int minNum = Integer.MAX_VALUE;     //땅 높이 중 최소값
        int maxNum = Integer.MIN_VALUE;     //땅 높이 중 최대값
        int resultTime = Integer.MAX_VALUE;     //걸린 시간을 출력할 변수
        int resultHeight = 0;   //결과적으로 나온 높이를 출력할 변수

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());     //땅 높이 정보 받아서
                ground[i][j] = num;     //배열에 저장
                minNum = Math.min(minNum, num);     //최소값 저장
                maxNum = Math.max(maxNum, num);     //최대값 저장
            }
        }

        for(int n = minNum; n <= maxNum; n++) {     //최소값부터 최대값이 될때까지 반복돌면서 최적의 값 찾기
            int time = 0;       //걸린 시간 받을 변수
            int b = B;      //인벤토리에 들고있는 블럭 저장
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(ground[i][j] < n) {      //기준이되는 땅높이보다 지금 선택된 땅높이가 낮으면
                        time += (n-ground[i][j]);      //차이나는만큼 시간 더하기
                        b -= (n-ground[i][j]);      //블럭 채워주기
                    }
                    else {
                        time += (ground[i][j]-n)*2;    //지금 선택된 땅높이가 높으면 그만큼 제거하고 *2 (시간)
                        b += (ground[i][j]-n);      //블럭 회수하기
                    }
                }
            }
            if(b < 0) continue;     //블럭의 갯수가 음수이면 건너뛰기
            if(time <= resultTime) {    //걸린 시간이 최종적으로 출력할 시간보다 적거나 같을경우 최신화
                resultTime = time;
                resultHeight = n;
            }
        }

        System.out.println(resultTime + " " + resultHeight);
    }
}
