import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] startTimes = new int[N];
        int[] endTimes = new int[N];

        // 시작 시간과 종료 시간 각각 배열로 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            startTimes[i] = Integer.parseInt(st.nextToken());
            endTimes[i] = Integer.parseInt(st.nextToken());
        }

        // 시작 시간과 종료 시간 각각 정렬
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        // 강의실 개수 카운팅
        int roomCount = 0;
        int endIdx = 0;  // 종료 시간 배열의 인덱스

        // 문제에서는 강의실을 사용하는 최소갯수만 구하면 되기때문에
        // 강의를 시작 시간과 종료 시간을 쪼개서 정렬해버린뒤
        // 시작 시간 순으로 순회하면서 강의실 배정한다.

        // 왜 시작시간과 종료시간을 쪼개서 정렬해도 되는것인가?
        // 정렬된 시작시간배열[1,2,3,4,5], 정렬된 종료시간배열[3,4,6,7,8] 이 존재할때
        // 1번째 반복. (startTimes[0] = 1 >= endTimes[0] = 3) = false
        // 3시에 끝나는 수업이 있고 1시에 시작하는 수업이 있다면 이 둘은 겹치기때문에
        // roomCount++ 해주어야한다.
        // 2번째 반복. (startTimes[1] = 2 >= endTimes[0] = 3) = false
        // 마찬가지로 수업이 겹치기 때문에 roomCount++
        // 3번째 반복. (startTimes[2] = 3 >= endTimes[0] = 3) = true
        // 3시에 끝나는 수업이 있고 3시에 시작하는 수업이라서 해당 강의실을 바로 사용하면 된다.
        // 그렇기때문에 roomCount를 증가하지 않고 endIdx를 증가시켜서 다음 끝나는 수업과 비교한다.
        // 4번째 반복. (startTimes[3] = 4 >= endTimes[1] = 4) = true
        // 마찬가지로 4시에 끝나서 4시에 시작하기때문에 해당 강의실을 바로 사용.
        // 5번째 반복. (startTimes[4] = 5 >= endTimes[2] = 6) = false;
        // 5시에 시작하는 수업이 6시에 끝나므로 새로운 강의실을 사용해야한다.

        // 남은 종료시간 배열[7,8]은 다 돌지 않았더라도 시작시간을 기준으로
        // 모든 강의 스케쥴을 확인했기때문에 전부 확인하지 않아도 괜찮은것이다.

        // 즉, 이 문제는 강의실의 스케쥴을 짜는것이 핵심이 아닌 최소 강의실 사용 개수를 구하는것이기 때문에
        // 각 강의가 언제 시작하든 특정 강의가 종료되는시점보다 빠르다면 어쩔수없이 새로운 강의실을 사용하는것이 문제의 핵심이다.
        // 그리고 어떤 강의가 특정 강의 종료시점보다 늦게 시작한다면 해당 강의실을 재사용하고 새로운 특정 강의 종료시점을 정한다.
        // 특정 강의 종료시점을 새로 정하는것은 어떤 강의가 특정 강의 종료시점보다 늦게 시작한다는 시점에서 이미 특정 강의는 이미
        // 강의가 종료가 되었기 때문에 새로운 특정 강의 종료시점을 정해야한다.

        for (int i = 0; i < N; i++) {
            // 시작시간이 종료시간보다 크다면(즉, 종료시간이 시작시간보다 빨리 끝난다면)
            if (startTimes[i] >= endTimes[endIdx]) {
                // 해당 강의실을 바로 사용하면되고
                endIdx++;
            }
            // 시작시간이 종료시간보다 작다면(즉, 종료시간이 시작시간보다 늦게 끝난다면)
            else {
                // 해당 강의실은 사용중이므로 새로운 강의실을 추가한다.
                roomCount++;
            }
        }

        System.out.println(roomCount);
    }
}