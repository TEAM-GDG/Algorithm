import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belt = new int[n];
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        // 초밥의 종류별로 먹은 개수를 저장할 배열
        int[] sushiCount = new int[d + 1];

        // 슬라이딩 윈도우 초기 설정
        // 현재 먹은 초밥 종류 수
        int currentKinds = 0;
        for (int i = 0; i < k; i++) {
            // 새로운 종류의 초밥 등장
            if (sushiCount[belt[i]] == 0) {
                currentKinds++;
            }
            sushiCount[belt[i]]++;
        }

        // 첫 번째 쿠폰 초밥 추가
        int maxKinds = currentKinds;
        if (sushiCount[c] == 0) {
            // 쿠폰 초밥이 포함되지 않으면 추가
            maxKinds++;
        }

        // 슬라이딩 윈도우 이동
        for (int i = 1; i < n; i++) {
            // 이전 접시에서 빠지는 초밥
            int removeSushi = belt[i - 1];
            sushiCount[removeSushi]--;
            if (sushiCount[removeSushi] == 0) {
                currentKinds--;  // 초밥이 완전히 빠지면 종류 감소
            }

            // 새로 추가되는 접시의 초밥
            int addSushi = belt[(i + k - 1) % n];
            if (sushiCount[addSushi] == 0) {
                currentKinds++;  // 새로운 종류의 초밥 등장
            }
            sushiCount[addSushi]++;

            // 쿠폰 초밥 포함한 종류 계산
            int kindsWithCoupon = currentKinds;
            if (sushiCount[c] == 0) {
                kindsWithCoupon++;  // 쿠폰 초밥이 포함되지 않으면 추가
            }

            // 최댓값 갱신
            maxKinds = Math.max(maxKinds, kindsWithCoupon);
        }

        System.out.println(maxKinds);
    }
}