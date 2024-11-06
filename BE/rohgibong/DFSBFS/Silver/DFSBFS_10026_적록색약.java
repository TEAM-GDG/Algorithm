import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DFSBFS_10026_적록색약 {
    public static int[][] colorArr;
    public static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int insertNum = 0;      //색깔 값을 지정 (R: 1 / G: 2 / B: 3)
        int result1 = 0;        //적록색약이 아닌 사람이 봤을 때 구역의 수
        int result2 = 0;        //적록색약인 사람이 봤을 때 구역의 수

        colorArr = new int[N][N];       //색깔 값을 받을 배열
        visit =  new int[N][N];         //방문 여부를 받을 배열

        for(int i = 0; i < N; i++) {        //색깔 값을 받아와서 각 색깔에 맞는 정수를 배열에 넣음
            String colorString = br.readLine();
            for(int j = 0; j < N; j++) {
                char color = colorString.charAt(j);
                if (color == 'R') insertNum = 1;
                else if (color == 'G') insertNum = 2;
                else if (color == 'B') insertNum = 3;
                colorArr[i][j] = insertNum;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(colorArr[i][j] != 0 && visit[i][j] == 0) {   //i,j번째 색깔값이 0이 아니고 방문한적이 없으면
                    searchColor(i, j, colorArr[i][j]);      //searchColor 메소드에 대입
                    result1++;      //result1 1 증가
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visit[i][j] != 0) {      //방문했던 적 있으면
                    searchColor2(i, j, visit[i][j]);        //searchColor2 메소드에 대입
                    result2++;      //result2 1 증가
                }
            }
        }

        System.out.println(result1 + " " + result2);
    }

    public static void searchColor(int x, int y, int color) {       //x값, y값, 색깔값 받아옴
        visit[x][y] = color;        //색깔값을 방문 배열에 넣음

        if (x - 1 >= 0 && colorArr[x - 1][y] == color && visit[x - 1][y] == 0) {        //범위 안이고, 색깔이 같고, 방문한적 없으면
            searchColor(x - 1, y, color);       //searchColor 메소드에 넣음 (아레 세개도 좌표만 다르고 다 같음)
        }

        if (x + 1 < colorArr.length && colorArr[x + 1][y] == color && visit[x + 1][y] == 0) {
            searchColor(x + 1, y, color);
        }

        if (y - 1 >= 0 && colorArr[x][y - 1] == color && visit[x][y - 1] == 0) {
            searchColor(x, y - 1, color);
        }

        if (y + 1 < colorArr[0].length && colorArr[x][y + 1] == color && visit[x][y + 1] == 0) {
            searchColor(x, y + 1, color);
        }
    }

    public static void searchColor2(int x, int y, int color) {
        visit[x][y] = 0;        //방문 배열을 다시 0으로 돌리면서 적록색약의 기준으로 색을 검색

        if(color == 1 || color == 2){       //1(R)이랑 2(G)랑 묶어서 찾음
            for(int i = 1; i <= 2; i++){
                if (x - 1 >= 0 && visit[x - 1][y] == i) {
                    searchColor2(x - 1, y, i);
                }

                if (x + 1 < visit.length && visit[x + 1][y] == i) {
                    searchColor2(x + 1, y, i);
                }

                if (y - 1 >= 0 && visit[x][y - 1] == i) {
                    searchColor2(x, y - 1, i);
                }

                if (y + 1 < visit[0].length && visit[x][y + 1] == i) {
                    searchColor2(x, y + 1, i);
                }
            }
        } else {        //색깔값이 3일때
            if (x - 1 >= 0 && visit[x - 1][y] == color) {
                searchColor2(x - 1, y, color);
            }

            if (x + 1 < visit.length && visit[x + 1][y] == color) {
                searchColor2(x + 1, y, color);
            }

            if (y - 1 >= 0 && visit[x][y - 1] == color) {
                searchColor2(x, y - 1, color);
            }

            if (y + 1 < visit[0].length && visit[x][y + 1] == color) {
                searchColor2(x, y + 1, color);
            }
        }

    }
}
