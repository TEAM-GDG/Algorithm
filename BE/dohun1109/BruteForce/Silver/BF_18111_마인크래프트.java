package BruteForce.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BF_18111_마인크래프트 {
    static int[][] landHeights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. 인벤토리 +1 (1초) 2. 가장위 블록 +1 (2초)
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //x
        int M = Integer.parseInt(st.nextToken());  //y
        int B = Integer.parseInt(st.nextToken());  //inventory

        landHeights = new int[N][M];
        int average = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                landHeights[i][j] = Integer.parseInt(st.nextToken());
                average+= landHeights[i][j];
            }
        }
        int currentAverage = average/(N*M); //최소 값
        int maxAverage = (average + B) / (N * M);   //최대값


        ArrayList<HouseSite> houseSites = new ArrayList<>();
        while (currentAverage <= maxAverage) {
            int currentTime = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int height = landHeights[i][j];
                    if (currentAverage <height){
                        currentTime += 2*(height-currentAverage);
                    }else if (currentAverage> height){
                        currentTime += currentAverage - height;
                    }
                }
            }
            
            HouseSite hs = new HouseSite(currentTime, currentAverage);
            houseSites.add(hs);
            currentAverage++;
        }
        houseSites.sort(Comparator.comparing(HouseSite::getTime).thenComparing(HouseSite::getHeight, Collections.reverseOrder()));
        System.out.println(houseSites.get(0).time + " " + houseSites.get(0).height);
    }
    static class HouseSite{
        int time;
        int height;

        public HouseSite(int time, int height) {
            this.time = time;
            this.height = height;
        }

        public int getTime() {
            return time;
        }

        public int getHeight() {
            return height;
        }
    }
}
