import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DFSBFS_2667_단지번호붙이기 {
    public static int[][] home;
    public static int[][] visit;
    public static StringBuilder sb = new StringBuilder();
    public static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        home = new int[N][N];       //집이 있는지 여부를 받을 배열
        visit =  new int[N][N];     //방문했는지 안했는지 여부를 받을 배열

        for(int i = 0; i < N; i++) {
            String num = br.readLine();
            for(int j = 0; j < N; j++) {
                home[i][j] = num.charAt(j) - '0';       //집 여부 값 받기
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(home[i][j] == 1 && visit[i][j] == 0) {   //집이 있고 방문하지 않았으면
                    searchHome(i, j);       //그 좌표를 시작점으로 찾음
                    sb.append(num).append(" ");     //증가된 num을 sb에 추가
                    num = 0;        //num값 초기화
                }
            }
        }

        int[] resultArr = new int[sb.toString().split(" ").length];     //결과값들을 받을 배열 생성 (처음부터 생성해놓으면 배열의 크기를 모르기 때문)
        for(int i = 0; i < resultArr.length; i++) {
            resultArr[i] = Integer.parseInt(sb.toString().split(" ")[i]);   //결과값들을 정수화해서 넣음
        }
        Arrays.sort(resultArr);     //오름차순 정렬
        System.out.println(resultArr.length);       //배열의 길이 출력
        for(int i = 0; i < resultArr.length; i++) {
            System.out.println(resultArr[i]);       //배열의 값들을 출력
        }
    }

    public static void searchHome(int x, int y){        //방문하지 않고 인접한 집들을 찾는 메소드
        visit[x][y] = 1;        //방문했다는 표시
        num++;      //num값 증가

        if (x - 1 >= 0 && home[x - 1][y] == 1 && visit[x - 1][y] == 0) {        //배열의 크기를 넘지않고 인접한 값이 1이고 방문하지 않았으면
            searchHome(x - 1, y);       //searchHome에 그 좌표값을 넣음 (아래 세개도 다 같음, 좌표값만 다름)
        }

        if (x + 1 < home.length && home[x + 1][y] == 1 && visit[x + 1][y] == 0) {
            searchHome(x + 1, y);
        }

        if (y - 1 >= 0 && home[x][y - 1] == 1 && visit[x][y - 1] == 0) {
            searchHome(x, y - 1);
        }

        if (y + 1 < home[0].length && home[x][y + 1] == 1 && visit[x][y + 1] == 0) {
            searchHome(x, y + 1);
        }
    }
}
