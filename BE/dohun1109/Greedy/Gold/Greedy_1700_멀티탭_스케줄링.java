package Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Greedy_1700_멀티탭_스케줄링 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        /** 멀티탭 구멍의 개수의 개수가 넉넉할 경우에는 그냥 콘센트를 꽂으면 되지만 자리가 없는경우에 어떻게 꽃혀있는 콘센트를 빼야하는지가 관건이다.
         * 1. 사용하려는 전기 용품이 꽃혀있는 경우, 아무런 행동도 취할 필요 없다.
         * 2. 사용하려는 전기 용품의 콘센트가 꽃혀있지 않은 경우
         * 2-1 멀티탭의 구멍이 넉넉하다면, 비어있는 아무 공간에 콘센트를 꽃는다.
         *
         * 2-2 멀티탭의 구멍이 넉넉하지 않은 경우, 현재 꽃혀있는 콘센트들이 나중에도 사용되는지 확인한다.
         * 2-2-1 현재 꽃혀있는 콘센트들 중 일부만 나중에 사용된다면, 나중에도 사용되지 않는 콘센트를 제거하고, 현재 사용하려는 콘센트를 꽃는다.
         * 2-2-2 현재 꽃혀있는 콘센트 모두 나중에 사용된다면, 그 중 그나마 가장 늦게 사용되는 콘센트를 제거하고, 현재 사용하려는 콘센트를 꽃는다.
         */

        boolean[] use = new boolean[101];
        int put = 0;
        int ans = 0;
        for (int i = 0; i < K; i++) {
            int temp = order[i];

            if (!use[temp]) { //콘센트가 꽂혀있지 않은 경우
                if (put < N) {  //콘센트를 꽂을 공간이 있는 경우
                    use[temp] = true;
                    put++;
                } else {//콘센트를 꽂을 공간이 없는 경우
                    ArrayList<Integer> arrList = new ArrayList<>();
                    for (int j = i; j < K; j++) {   //현재 꽂혀 있는 콘센트가 나중에도 사용되는지 확인
                        if (use[order[j]] && !arrList.contains(order[j])) arrList.add(order[j]);
                    }

                    if (arrList.size() != N) {  //나중에도 사용되는 콘센트가 구멍의 개수보다 작을 경우
                        for (int j = 0; j < use.length; j++) {
                            if (use[j] && !arrList.contains(j)) {   //그 콘센트를 제거
                                use[j] = false;
                                break;
                            }
                        }
                    }else { // 현재 꽂혀 있는 모든 콘센트가 나중에도 사용될 경우
                        int remove = arrList.get(arrList.size() - 1); // 가장 마지막에 사용될 콘센트를 제거
                        use[remove] = false;
                    }

                    use[temp] = true;
                    
                    ans++;
                }

            }

        }

        System.out.println(ans);
    }
}
