import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그리디_13305_주유소 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());        //N: 도시의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] cityLen = new long[N-1];     //도시 사이의 거리들이 담긴 배열
        for(int i = 0; i < N-1; i++) {
            cityLen[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        long[] price = new long[N];     //각 도시별 기름 가격이 담긴 배열
        for(int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        long currentPrice = price[0];       //0번째 도시의 가격을 초기값으로 설정
        long totalPrice = 0;
        for(int i = 0; i < N-1; i++) {
            if(price[i] < currentPrice) {       //i번째 도시의 가격이 currentPrice 가격보다 적으면
                currentPrice = price[i];        //더 적은값으로 바꿈
            }
            totalPrice += currentPrice * cityLen[i];        //가격 * 거리를 결과값에 대입
        }
        System.out.println(totalPrice);
    }
}
