import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그리디_1931_회의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] time = new int[N][2];       //시작시간, 종료시간을 담을 2차원 배열

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());  //시작시간
            time[i][1] = Integer.parseInt(st.nextToken());  //종료시간
        }

        Arrays.sort(time, (o1, o2) -> {
            if(o1[1] == o2[1]) {    //종료시간이 같을 경우 시작 시간이 빠른 순서대로
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int result = 0;    //결과값
        int end = 0;        //종료시간 담을 변수

        for(int i = 0; i < N; i++){
            if(end <= time[i][0]){
                end = time[i][1];
                result++;
            }
        }

        System.out.println(result);
    }
}
