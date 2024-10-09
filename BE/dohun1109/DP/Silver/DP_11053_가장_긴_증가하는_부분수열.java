package DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_11053_가장_긴_증가하는_부분수열 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] bigDp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        bigDp[0] = 1;
        int MAX = 1;
        
        for (int i = 1; i < N; i++) {
            int left = i-1;
            int maxCount = 0;
            while(true){
                if (A[left] < A[i]) {
                    maxCount= Math.max(maxCount,bigDp[left] + 1);
                    bigDp[i] = maxCount;
                    left--;
                } else{
                    left--;
                }
                if (left < 0) {
                    bigDp[i] = Math.max(maxCount,1);
                    break;
                }
            }
            MAX = Math.max(MAX, bigDp[i]);
        }
        System.out.println(MAX);
    }
}
