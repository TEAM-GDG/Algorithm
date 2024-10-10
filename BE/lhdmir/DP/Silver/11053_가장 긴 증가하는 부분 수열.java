import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // LIS를 저장할 List
        List<Integer> lis = new ArrayList<>();

        for (int num : nums) {
            // 이진탐색으로 num의 위치를 찾음
            int pos = Collections.binarySearch(lis, num);
            // num이 lis에 존재하지 않을경우 음수값을 반환
            // pos의 위치를 다시 계산
            // 리턴값 = -(예상삽입위치 + 1)
            if (pos < 0) {
                pos = -(pos + 1);
            }

            // 해당 위치가 lis.size()보다 크거나 같다면
            // 즉, lis의 끝에 넣어야한다면 num을 추가
            if (pos >= lis.size()) {
                lis.add(num);
            }
            // lis안에 있는 값을 업데이트 해야할 경우.
            else {
                lis.set(pos, num);
            }
        }

        return lis.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(lengthOfLIS(arr));
    }
}
