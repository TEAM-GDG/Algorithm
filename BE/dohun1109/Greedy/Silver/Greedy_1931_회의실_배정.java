package Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Greedy_1931_회의실_배정 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<TimeTable> timeTables = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            TimeTable time = new TimeTable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            timeTables.add(time);
        }

        timeTables.sort(Comparator.comparingInt((TimeTable t) -> t.end).thenComparingInt((TimeTable t) -> t.start));

        int count = 0;
        int Min = 0;
        for (TimeTable time : timeTables) {
            if (time.start >= Min) {
                Min = time.end;
                count++;
            }
        }

        System.out.println(count);


    }


    static class TimeTable {
        int start;
        int end;



        public TimeTable(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

}
