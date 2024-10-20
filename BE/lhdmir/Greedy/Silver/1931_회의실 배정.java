import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Meeting {
    int start;
    int end;

    Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Meeting[] meetings = new Meeting[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meetings, Comparator.comparingInt((Meeting m) -> m.end).thenComparingInt(m -> m.start));

        int count = 0;
        // 마지막으로 회의가 끝나는 시간을 기록
        int lastEndTime = 0;
        // meetings를 순회하면서 현재 회의 시작시간이 마지막으로 회의가 끝난 시간보다 크다면
        // 마지막으로 회의가 끝난 시간을 업데이트하고 카운트를 증가
        // 회의가 끝나는 시간이 빠른순으로 정렬해서 카운트해야 최대한 많은 일정을 잡을수있다
        for (Meeting m : meetings) {
            if(m.start >= lastEndTime) {
                lastEndTime = m.end;
                count++;
            }
        }
        System.out.println(count);
    }
}
