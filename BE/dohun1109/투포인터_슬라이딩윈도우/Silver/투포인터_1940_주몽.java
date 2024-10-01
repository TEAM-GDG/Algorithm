package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 투포인터_1940_주몽 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    //재료의 개수
        int[] arr = new int[N];

        int M = Integer.parseInt(br.readLine());    //갑옷을 만드는데 필요한 수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);   //오름 차순으로 정렬   1 2 3 4 5 7

        int count = 0;
        int MIN_INDEX = 0;
        int MAX_INDEX = N-1;

        while (MIN_INDEX < MAX_INDEX) {     //포인터가 곂치면 더이상 탐색할 필요가 없음
            if (arr[MIN_INDEX] + arr[MAX_INDEX] < M) MIN_INDEX++;   //min + max 가 M보다 작으면 min++ (왜냐면 max는 더이상 커질 수 없으니까)
            else if (arr[MIN_INDEX] + arr[MAX_INDEX] >M) MAX_INDEX--; //min + max 가 M보다 크면 max-- (min 은 더이상 작아질 수없으니까)
            else{
                MIN_INDEX++;MAX_INDEX--;
                count++;
            }
        }
        
        System.out.println(count);


        





    }
}
