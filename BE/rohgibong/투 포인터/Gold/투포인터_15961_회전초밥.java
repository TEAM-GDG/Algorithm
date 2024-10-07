import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       //N: 회전초밥 접시수
        int d = Integer.parseInt(st.nextToken());       //d: 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken());       //k: 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken());       //c: 쿠폰 번호

        int[] arr = new int[N+k];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int chkArr[] = new int[d+1];    //먹은 초밥 기록하는 배열
        int variety = 0;        //먹은 초밥의 종류 수

        for(int i = 0; i < k; i++) {    //처음 k개 접시를 배열에 담음
            if(chkArr[arr[i]] == 0) {
                variety++;
            }
            chkArr[arr[i]]++;
        }

        int result = variety;   //초반에 먹은 초밥 가짓수를 결과에 저장

        for(int i = 0; i < N; i++){
            if(chkArr[arr[i]] == 1){    //슬라이딩 윈도우의 왼쪽 빼줌
                variety--;
            }
            chkArr[arr[i]]--;

            int next = arr[(i+k) % N];  //슬라이딩 윈도우의 오른쪽 더해줌
            if(chkArr[next] == 0){
                variety++;
            }
            chkArr[next]++;

            if(chkArr[c] == 0){     //쿠폰 계산해서 비교 후 저장
                result = Math.max(result, variety+1);
            } else {
                result = Math.max(result, variety);
            }
        }

        System.out.println(result);
    }
}
