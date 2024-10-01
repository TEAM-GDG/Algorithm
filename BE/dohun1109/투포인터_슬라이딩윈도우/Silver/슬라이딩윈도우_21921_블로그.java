package 투포인터_슬라이딩윈도우.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 슬라이딩윈도우_21921_블로그 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] A = new int[N+1];
        int[] S = new int[N+1];
        ArrayList<Integer> xDayVisitant = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N+1; i++) {
            S[i] = S[i-1] + A[i];
        }

        for (int i = 0; i < S.length-X; i++) {
            System.out.println("i="+ i + " 합="+ (S[i+X]-S[i]) + "i+x = " + (i+X) );
            xDayVisitant.add(S[i+X]-S[i]);
        }

        int MAX = Integer.MIN_VALUE;
        for (Integer visit : xDayVisitant) {
            MAX = Math.max(MAX, visit);
        }

        
        if (MAX == 0) {
            System.out.println("SAD");
        }else {
            int count = 0;
            for (Integer num : xDayVisitant) {
                if (MAX == num) {
                    count++;
                }
            }
            System.out.println(MAX+"\n"+count);
        }
        

    }
}
