package Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Greedy_13305_주유소 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] roads = new long[N-1];
        long[] citys = new long[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            if (i<N-1){
                roads[i] = Long.parseLong(st1.nextToken());
            }
            citys[i] = Long.parseLong(st2.nextToken());
        }


        long sum = 0;
        long minCost = citys[0];


        for (int i = 0; i < N-1; i++) {
            if (citys[i] < minCost ){
                minCost = citys[i];
            }
            sum += (minCost * roads[i]);
        }

        System.out.println(sum);

        

        


    }
}
