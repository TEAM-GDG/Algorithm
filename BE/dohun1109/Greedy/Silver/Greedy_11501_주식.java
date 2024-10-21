package Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy_11501_주식 {
    public static void main(String[] args) throws IOException {

        /** 1. 주식 하나를 산다
         * 2. 원하는 만큼 가지고 있는 주식을 판다.
         * 3. 아무것도 안한다.
         * N = 3
         *  10 7 6
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N;
        int[] prices;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
             N = Integer.parseInt(br.readLine());
             st = new StringTokenizer(br.readLine());
             prices= new int[N];
             int j = 0;
             while (st.hasMoreTokens()) {
                 prices[j++] = Integer.parseInt(st.nextToken());
             }
             
            sb.append(maximumProfit(N, prices)).append('\n');
        }
        System.out.println(sb);
    }

    static long maximumProfit(int N,int[] prices){
        long sum = 0;
        long max = prices[N-1];
        for (int i = N-1; i >=0; i--) {
            if (prices[i] > max){
                max = prices[i];
            }else{
                sum+=max - prices[i];
            }
        }
        return sum;
    }
}
