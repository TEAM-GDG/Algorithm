package Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Greedy_11497_통나무건너뛰기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N;
        Integer[] logs;
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            logs = new Integer[N];
            int j = 0;
            while (st.hasMoreTokens()) {
                logs[j++] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(logs, Collections.reverseOrder());  //내림차순 정렬
            int[] sortedLogs = SortByMinimumLevel(N, logs);
            sb.append(searchByMinimumLevel(N, sortedLogs)).append('\n');
        }
        System.out.println(sb);
    }

    private static int searchByMinimumLevel(int N, int[] sortedLogs) {
        int MinLevel = 0;
        for (int i = 0; i < N; i++) {
            int abs = Math.abs(sortedLogs[i]-sortedLogs[(i+1)%N]);
            MinLevel = Math.max(MinLevel,abs);
        }
        return MinLevel;
    }

    static int[] SortByMinimumLevel(int N, Integer[] logs){
        int[] sortingLogs = new int[N];
        sortingLogs[(N / 2 + N % 2)-1] = logs[0];
        boolean sign = true;
        int location = (N/2 + N%2) -1;
        for (int i = 1; i < N; i++) {
            if (sign){
                location+= i;
                sortingLogs[location] = logs[i];
                sign = false;
            }else{
                location-=i;
                sortingLogs[location] = logs[i];
                sign = true;
            }
        }
        return sortingLogs;
    }
}
