import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFSBFS_15270_친구팰린드롬 {
    public static int N;
    public static int M;
    public static int result;
    public static int[][] MArr;
    public static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       //N: 총 반친구 수
        M = Integer.parseInt(st.nextToken());       //M: 관계도 수
        MArr = new int[M][2];       //MArr: 친구관계 저장하는 배열

        for(int i = 0; i < M; i++){     //배열에 친구관계 저장
            st = new StringTokenizer(br.readLine());
            MArr[i][0] = Integer.parseInt(st.nextToken());
            MArr[i][1] = Integer.parseInt(st.nextToken());
        }

        result = 0;     //최대 쌍 출력할 변수
        visit = new int[N+1];       //방문 여부를 저장할 배열

        findFriend(0, 0);       //쌍 찾기

        result *= 2;        //'쌍'의 개수이니까 곱하기 2를 해줌

        if(result < N) result++;        //result가 N보다 작으면 홀수명의 친구가 남아서 한 명은 짝을 이루지 않음. 그래서 1을 더해줌.

        System.out.println(result);

    }

    public static void findFriend(int index, int count){
        if(index >= M){     //모든 관계를 탐색했을 때 현재까지의 쌍의 수와 최대값을 비교해서 업데이트
            result = Math.max(result, count);
            return;
        }

        if(visit[MArr[index][0]] == 1 || visit[MArr[index][1]] == 1){       //한 쪽 이라도 쌍을 이미 이뤘으면 건너뜀
            findFriend(index+1, count);
        } else {        //둘 다 쌍이 없으면
            visit[MArr[index][0]] = 1;      //지금 선택된 두 개를 쌍으로 선택
            visit[MArr[index][1]] = 1;

            findFriend(index+1, count+1);   //count+1해주고 다음거 탐색함

            //백트래킹: 다른 조합을 찾기위해
            visit[MArr[index][0]] = 0;
            visit[MArr[index][1]] = 0;

            findFriend(index+1, count);     //현재 관계를 건너뛰고 다음 관계를 탐색함
        }
    }
}
