package 구현.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 구현_17144_미세먼지안녕 {
    static int[][] originArr;
    static int[][] changeArr;
    static ArrayList<Cleaner> cleaners;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    //좌 하 우 상     // 우 하 좌 상
    static int R;
    static int C;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());   // x
        C = Integer.parseInt(st.nextToken());   // y
        int T = Integer.parseInt(st.nextToken());   // time

        originArr = new int[R+1][C+1];  // 1번 부터 할꺼임 
        changeArr = new int[R+1][C+1];
        cleaners = new ArrayList<>();

        for (int i = 1; i < R+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < C+1; j++) {
                originArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 1; i < R+1; i++) {
            for (int j = 1; j < 2; j++) {
                if(originArr[i][j] == -1){
                   Cleaner c = new Cleaner(i,j);
                   cleaners.add(c);
                }
            }
        }

        for (int i = 0; i < T; i++) {
            diffusion();    //확산
            cleaning1(cleaners.get(0).x, cleaners.get(0).y); //위쪽 청소
            cleaning2(cleaners.get(1).x, cleaners.get(1).y); //아래쪽 청소
            OriginalInit();     //원본 배열로 이동
        }

        int result = 0;
        for (int i = 1; i < R+1; i++) {
            for (int j = 1; j < C+1; j++) {
                result += originArr[i][j];
            }
        }
        System.out.println(result+2);   // -1 2개 있어서 +2

    }

    static private void diffusion(){    //확산

        for (int i = 1; i < R+1; i++) {
            for (int j = 1; j < C+1; j++) {
                if (originArr[i][j]>0 && originArr[i][j] != -1){     //확산 가능
                    int count = quest(i,j);
                    changeArr[i][j] += originArr[i][j] - (originArr[i][j]/5)*count;
                }
            }
        }

    }
    static private int quest(int i, int j){   //확산 가능 탐색
        int count = 0;
        for (int k = 0; k < 4; k++) {
            int now_x = i + dx[k];
            int now_y = j + dy[k];
            if(0<now_x && now_x< R+1 && 0<now_y && now_y<C+1 && originArr[now_x][now_y] != -1){
                changeArr[now_x][now_y] += originArr[i][j]/5;
                count++;
            }
        }
        return count;
    }

    static private void cleaning1(int x, int y){    //네가지 방향 모두 한 칸씩 청소(청소기 빨려들어가는 부분부터)
        for (int i = x-1; i>1; i--){
            changeArr[i][1] = changeArr[i-1][1];
        }
        for (int i = 1; i<C; i++){
            changeArr[1][i] = changeArr[1][i+1];
        }
        for (int i = 1; i<x; i++){
            changeArr[i][C] = changeArr[i+1][C];
        }
        for (int i = C; i>1; i--){
            changeArr[x][i] = changeArr[x][i-1];
        }
    }

    static private void cleaning2(int x, int y){  //네가지 방향 모두 한 칸씩 청소(청소기 빨려들어가는 부분부터)
        for (int i = x+1; i<R; i++){
            changeArr[i][1] = changeArr[i+1][1];
        }
        for (int i = 1; i<C; i++){
            changeArr[R][i] = changeArr[R][i+1];
        }
        for (int i = R; i>x; i--){
            changeArr[i][C] = changeArr[i-1][C];
        }
        for (int i = C; i>1; i--){
            changeArr[x][i] = changeArr[x][i-1];
        }
    }
    static private void OriginalInit(){ //1초후 원본 배열 초기셋
        for (int i = 1; i < R+1; i++) {
            for (int j = 1; j < C+1; j++) {
                originArr[i][j] = changeArr[i][j];
                changeArr[i][j] = 0;
            }
        }
        originArr[cleaners.get(0).x][cleaners.get(0).y] = -1;
        originArr[cleaners.get(1).x][cleaners.get(1).y] = -1;

    }




    static class Cleaner{
        int x;
        int y;

        public Cleaner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }














}
