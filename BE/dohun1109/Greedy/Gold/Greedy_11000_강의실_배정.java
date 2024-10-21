package Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Greedy_11000_강의실_배정 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] startTimes = new int[N];  // 시작 시간을 저장하는 배열
        int[] endTimes = new int[N];    // 종료 시간을 저장하는 배열

        // 각 강의의 시작 시간과 종료 시간을 배열에 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            startTimes[i] = Integer.parseInt(st.nextToken());  // 시작 시간
            endTimes[i] = Integer.parseInt(st.nextToken());    // 종료 시간
        }

        // 시작 시간과 종료 시간을 각각 오름차순으로 정렬
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        System.out.println(Arrays.toString(startTimes));
        System.out.println(Arrays.toString(endTimes));
        int roomCount = 0;  // 필요한 강의실의 수
        int endIdx = 0;     // 종료 시간 배열의 인덱스 (제일 먼저 끝나는 강의실)

        // 시작 시간을 순차적으로 처리
        for (int i = 0; i < N; i++) {
            if (startTimes[i] >= endTimes[endIdx]) {
                // 현재 강의의 시작 시간이 가장 빨리 끝나는 강의실의 종료 시간보다 늦다면,
                // 그 강의실을 재사용할 수 있음 (강의실 추가 안 함)
                System.out.println("startTimes = " + startTimes[i]);
                System.out.println("endTimes = " + endTimes[endIdx]);
                endIdx++;
            } else {
                // 그렇지 않다면, 새로운 강의실이 필요함

                System.out.println("startTimes x = " + startTimes[i]);
                System.out.println("endTimes X = " + endTimes[endIdx]);
                roomCount++;
            }
        }

        // 필요한 강의실의 개수 출력
        System.out.println(roomCount);
    }
}