import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, m;
    static int[][] city;
    static List<Pair> chickenShops = new ArrayList<>();
    static List<Pair> homes = new ArrayList<>();
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    homes.add(new Pair(i, j));
                } else if (city[i][j] == 2) {
                    chickenShops.add(new Pair(i, j));
                }
            }
        }

        selectChickenShops(0, new Stack<>());

        System.out.println(minDistance);
    }

    static void selectChickenShops(int start, Stack<Pair> selectedShops) {
        // 치킨가게를 m개 선정했으면 치킨거리를 계산
        if (selectedShops.size() == m) {
            calcChickenDistance(selectedShops);
            return;
        }

        // 치킨 가게 리스트들을 순회하면서 m개의 치킨가게를 선정
        for (int i = start; i < chickenShops.size(); i++) {
            // i번째 치킨가게를 선정
            selectedShops.push(chickenShops.get(i));
            // i+1, 다음 치킨가게를 선정
            selectChickenShops(i + 1, selectedShops);
            // i번째 치킨가게를 방출
            selectedShops.pop();
        }
    }

    static void calcChickenDistance(Stack<Pair> selectedShops) {
        // 도시의 치킨거리
        int cityDistance = 0;

        for (Pair home : homes) {
            // 치킨가게부터 집의 거리
            int homeDistance = Integer.MAX_VALUE;
            for (Pair shop : selectedShops) {
                // 거리 계산
                int distance = Math.abs(home.x - shop.x) + Math.abs(home.y - shop.y);
                // 집에서부터 모든 치킨가게의 거리를 계산하고 그 중 제일 작은 거리를 기록
                if (distance < homeDistance) {
                    homeDistance = distance;
                }
            }
            // 집에서부터 치킨가게의 거리중 가장 적은 거리를 도시의 치킨거리에 합산
            cityDistance += homeDistance;
        }

        // 이때까지 기록된 가장 작은 도시의 치킨거리와 이번에 계산한 도시의 치킨거리를 비교해 값을 업데이트
        if (cityDistance < minDistance) {
            minDistance = cityDistance;
        }
    }
}
