import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투포인터_12891_DNA비밀번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());   //S: DNA 문자열 길이
        int P = Integer.parseInt(st.nextToken());   //P: 비밀번호로 사용할 문자열 길이

        String dnaString = br.readLine();
        char[] charArr = dnaString.toCharArray();   //dna값들 입력받아서 char 형식으로 하나씩 배열에 저장

        st = new StringTokenizer(br.readLine());
        int[] needNum = new int[4];     //4 크기의 정수형 배열 선언
        for(int i = 0; i < 4; i++) {
            needNum[i] = Integer.parseInt(st.nextToken());  //한칸에 하나씩 필요한 갯수 저장
            // needNum[0]: A의 최소 갯수 / needNum[1]: C의 최소 갯수 / needNum[2]: G의 최소 갯수 / needNum[3]: T의 최소 갯수
        }

        int[] chkNum = new int[4];  //위에서 최소갯수와 비교할 배열

        int left = 0;   //왼쪽 포인터는 0부터 시작 (왼쪽 문자 뺄때 사용)
        int right = P;  //오른쪽 포인터는 P부터 시작 (오른쪽 문자 더할때 사용)
        int result = 0; //결과값 출력할 변수

        for(int i = 0; i < P; i++) {
            inputArr(chkNum, charArr[i]);   //초반 0부터 P-1까지의 문자열에 대해서 값을 넣음
        }

        result = checkDna(needNum, chkNum, result); //초반 P-1까지의 문자열에 대해서 체크 후 결과 받아옴

        if(S > P){  //DNA 문자열 길이(체크해야할 전체 문자열)가 비밀번호로 사용 할 문자열 길이보다 길 경우
            while(right < S){       //오른쪽 포인터가 S(전체 문자열의 크기)보다 작은 동안 반복
                //right번째 문자열 더하고, left번째 문자열 빼기
                inputArr(chkNum, charArr[right]);
                removeArr(chkNum, charArr[left]);

                //문자열 확인(조건대로)
                result = checkDna(needNum, chkNum, result);

                //왼쪽과 오른쪽 포인터 한 칸 이동
                left++;
                right++;
            }
        }
        System.out.println(result);     //결과값 출력
    }

    public static void inputArr(int[] arr, char ch){    //문자 배열에 더하는 메소드
        switch(ch){
            case 'A':       //A인 경우
                arr[0]++;   //배열의 0번째 값 1 증가
                break;
            case 'C':       //C인 경우
                arr[1]++;   //배열의 1번째 값 1 증가
                break;
            case 'G':       //G인 경우
                arr[2]++;   //배열의 2번째 값 1 증가
                break;
            case 'T':       //T인 경우
                arr[3]++;   //배열의 3번째 값 1 증가
                break;
        }
    }

    public static void removeArr(int[] arr, char ch){    //문자 배열에 빼는 메소드
        switch(ch){
            case 'A':       //A인 경우
                arr[0]--;   //배열의 0번째 값 1 감소
                break;
            case 'C':       //C인 경우
                arr[1]--;   //배열의 1번째 값 1 감소
                break;
            case 'G':       //G인 경우
                arr[2]--;   //배열의 2번째 값 1 감소
                break;
            case 'T':       //T인 경우
                arr[3]--;   //배열의 3번째 값 1 감소
                break;
        }
    }

    public static int checkDna(int[] needNum, int[] chkNum, int result){    //비밀번호로 적합한지 확인하는 메소드
        int point = 0;      //point라는 변수 선언
        for(int i = 0; i < 4; i++) {
            if(needNum[i] <= chkNum[i]){    //A, C, G, T 각각 필요한 조건이 맞는지 확인하고
                point++;        //조건에 맞으면 point + 1
            }
        }
        if(point == 4){     //포인트가 4면 4개 다 조건에 맞는 경우
            result++;       //result + 1
        }
        return result;      //result 반환
    }
}
