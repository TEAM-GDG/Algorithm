package BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bt_15686_치킨배달 {

    static int N, M; // 도시의 크기와 선택할 치킨집의 수
    static List<Chicken> chickens; // 치킨집 위치 목록
    static List<Home> homes; // 집 위치 목록
    static boolean[] selected; // 선택된 치킨집 표시 배열
    static int minCityDistance = Integer.MAX_VALUE; // 도시의 최소 치킨거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()) + 1; // 도시 크기 (1~N)
        M = Integer.parseInt(st.nextToken()); // 선택할 치킨집 수

        chickens = new ArrayList<>();
        homes = new ArrayList<>();

        // 도시의 정보 입력
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) { // 집인 경우
                    homes.add(new Home(i, j));
                } else if (num == 2) { // 치킨집인 경우
                    chickens.add(new Chicken(i, j));
                }
            }
        }

        selected = new boolean[chickens.size()];
        findMinimumChickenDistance(0, M); // 치킨 거리의 최소값을 찾는 백트래킹 시작
        System.out.println(minCityDistance);
    }

    // 치킨집 조합을 찾아 도시 치킨거리 계산하는 메소드
    static void findMinimumChickenDistance(int start, int remaining) {
        if (remaining == 0) { // 선택된 치킨집이 M개인 경우
            calculateCityChickenDistance(); // 도시의 치킨거리 계산
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            if (!selected[i]) {
                selected[i] = true; // 현재 치킨집 선택
                findMinimumChickenDistance(i + 1, remaining - 1); // 다음 치킨집 선택
                selected[i] = false; // 선택 취소 후 다음 치킨집 탐색
            }
        }
    }

    // 도시의 치킨거리 계산 메소드
    private static void calculateCityChickenDistance() {
        int cityDistance = 0; // 현재 조합에서의 도시 치킨거리

        // 선택된 치킨집마다 집과의 최소 치킨거리를 갱신
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                Chicken chicken = chickens.get(i);
                for (Home home : homes) {
                    int distance = Math.abs(chicken.x - home.x) + Math.abs(chicken.y - home.y);
                    home.minDistance = Math.min(home.minDistance, distance); // 최소 거리 갱신
                }
            }
        }

        // 모든 집의 최소 치킨거리를 합산하여 도시 치킨거리 계산
        for (Home home : homes) {
            cityDistance += home.minDistance;
            home.minDistance = Integer.MAX_VALUE; // 다음 계산을 위해 최소거리 초기화
        }

        // 도시의 최소 치킨거리 갱신
        minCityDistance = Math.min(minCityDistance, cityDistance);
    }

    // 집 위치를 나타내는 클래스
    static class Home {
        int x;
        int y;
        int minDistance = Integer.MAX_VALUE;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 치킨집 위치를 나타내는 클래스
    static class Chicken {
        int x;
        int y;

        public Chicken(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}