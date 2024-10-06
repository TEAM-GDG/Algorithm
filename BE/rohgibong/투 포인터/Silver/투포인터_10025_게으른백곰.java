import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_10025_게으른백곰 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //N: 양동이 갯수
        int K = Integer.parseInt(st.nextToken());   //K: 이동할 수 있는 거리

        int[] buckets = new int[1000001];       //얼음을 저장할 배열 (좌표의 최대값은 1000000)
        int maxCoordinate = 0;          //양동이가 놓인 최대 좌표

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int ice = Integer.parseInt(st.nextToken());
            int coordinate = Integer.parseInt(st.nextToken());
            buckets[coordinate] += ice;
            maxCoordinate = Math.max(maxCoordinate, coordinate);
        }

        int sumOfIce = 0;       //현재 윈도우 내의 얼음 합
        int maxIce = 0;         //최대 얼음 양

        for (int i = 0; i <= 2*K && i <= maxCoordinate; i++){       //0부터 2*K까지 범위이고 maxCoordinate를 넘지않는 범위 안에서 반복
            sumOfIce += buckets[i];     //범위 안에서 얼음들의 합을 구함
        }

        maxIce = sumOfIce;      //초기 얼음값 설정

        for(int i = 1; i + 2 * K <= maxCoordinate; i++){    //i가 1부터 i + 2*K 를 넘지않는동안 반복
            sumOfIce -= buckets[i - 1];     //왼쪽값 빼주고
            sumOfIce += buckets[i + 2*K];   //오른쪽값 더해서
            maxIce = Math.max(maxIce, sumOfIce);    //얼음 값 더 큰걸 넣어줌
        }

        System.out.println(maxIce);
    }
}
