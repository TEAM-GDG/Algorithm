import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 멀티탭에 꽂을 순서
        int[] schedule = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
        }

        // 현재 멀티탭에 꽂혀 있는 전기용품
        ArrayList<Integer> pluggedIn = new ArrayList<>();
        // 플러그를 뽑은 횟수
        int count = 0;

        int deviceToUnplug;
        int farthestIdx;
        int nextUseIdx;

        for (int i = 0; i < k; i++) {
            int currentDevice = schedule[i];

            // 현재 전기용품이 이미 멀티탭에 꽂혀 있으면 넘어감
            if (pluggedIn.contains(currentDevice)) {
                continue;
            }

            // 멀티탭에 빈 구멍이 있으면 그냥 꽂음
            if (pluggedIn.size() < n) {
                pluggedIn.add(currentDevice);
                continue;
            }

            // 플러그를 뽑아야 할 경우
            deviceToUnplug = -1; // 뽑을 전기용품
            farthestIdx = -1; // 가장 나중에 사용될 전기용품의 인덱스

            // 멀티탭에 꽂혀 있는 전기용품 중 가장 나중에 사용될 것을 찾음
            // 멀티탭에 꽂혀 있는 전기용품들을 순회하면서
            // 가장 나중에 사용될 전기용품을 찾음
            for (int pluggedDevice : pluggedIn) {
                // 나중에도 사용할 수 있는 전기용품의 인덱스
                // 만약 전기용품이 사용되지 않을경우 문제에서 제시된 k(1<=k<=100)보다 크게
                // 즉 101로 설정
                nextUseIdx = 101;
                for (int j = i + 1; j < k; j++) {
                    // 멀티탭에 꽂을 순서(schedule[i])과 현재 pluggedDevice(현재 플러그에 꽂혀있는 전기용품) 와 비교해서
                    // 같다면 나중에도 사용될 전기용품이라서 nextUse에 해당 인덱스를 저장
                    if (pluggedDevice == schedule[j]) {
                        nextUseIdx = j;
                        break;
                    }
                }

                // 가장 나중에 사용될 전기용품을 선택
                // 나중에도 사용할 수 있는 전기용품의 인덱스(nextUseIdx)가 가장 나중에 사용될 전기용품의 인덱스(farthestIdx)
                // 보다 크다면(즉, 스케쥴상 가장 나중에 사용되는 전기용품이라면)
                if (nextUseIdx > farthestIdx) {
                    // farthestIdx 값을 nextUseIdx로 업데이트
                    farthestIdx = nextUseIdx;
                    // 뽑을 전기용품(deviceToUnplug)를 현재 전기용품(pluggedDevice)로 업데이트
                    deviceToUnplug = pluggedDevice;
                }
            }

            // 선택된 전기용품을 뽑고, 현재 전기용품을 꽂음
            pluggedIn.remove((Integer) deviceToUnplug);
            pluggedIn.add(currentDevice);
            count++;
        }

        System.out.println(count);
    }
}
