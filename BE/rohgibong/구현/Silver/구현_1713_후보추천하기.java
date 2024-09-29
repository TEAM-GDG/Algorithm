package com.rohgibong.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 구현_1713_후보추천하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int photoFrameCount = Integer.parseInt(br.readLine());      //photoFrameCount: 입력받은 사진틀의 갯수
        int[] photoFrameArr = new int[photoFrameCount];     //photoFrameArr: 사진틀이 들어갈 배열
        int[] recommendCountArr = new int[101];     //recommendCountArr: 각 학생 번호별 추천수가 담길 배열 (101로 해야 0~100번째까지 생성되는데, 1~100번만 쓸거임)

        int recommendation = Integer.parseInt(br.readLine());       //recommendation: 전체 학생의 총 추천 횟수
        String allRecommendations = br.readLine();      //allRecommendations: 추천받은 학생을 나타내는 번호가 쭉 적힌 문자열
        String[] recommendationArrStr = allRecommendations.split(" ");  //위 문자열을 빈 칸 기준으로 나눔
        int[] recommendationArr = new int[recommendationArrStr.length];     //나눠진 숫자 갯수만큼 정수형 배열 생성
        for(int i = 0; i < recommendationArrStr.length; i++){       //반복문 돌면서
            recommendationArr[i] = Integer.parseInt(recommendationArrStr[i]);       //정수형 배열에 정수로 바꿔서 데이터를 넣음
        }

        for(int i = 0; i < recommendation; i++){        //전체 추천수만큼 반복
            recommendCountArr[recommendationArr[i]]++;      //i번째 학생 추천수 +1
            if(photoFrameArr[photoFrameCount-1] != 0){      //photoFrameCount-1번째가 0이 아니면, 사진틀이 꽉 찬 상태
                for(int j = 0; j < photoFrameCount; j++){   //전체 사진틀만큼 돌아다님 (이미 존재하는지 찾기위해)
                    if(photoFrameArr[j] == recommendationArr[i]){       //이미 존재하는 학생이면
                        recommendationArr[i] = 0;       //학생 데이터를 0으로 바꿈 (이미 추천수는 위에서 올렸고, 더 이상 필요한 학생 정보가 아님을 알리기위해 0으로 바꿈)
                        break;
                    }
                }

                if(recommendationArr[i] != 0){      //recommendationArr[i]이 0이 아니면 사진틀은 꽉찼지만, 사진틀에 들어가있지 않은 새로운 학생
                    int minRecommendation = 1001;   //추천횟수는 1000 이하 값이니, 1001보단 처음엔 무조건 작을것이다.
                    int resetRecommendNum = 0;      //가장 추천횟수가 적은 학생의 번호가 담길 변수
                    int searchToChangeNum = 0;      //가장 추천횟수가 적은 학생이 배열에서 몇번째인지 담을 변수
                    for(int j = 0; j < photoFrameCount; j++){       //사진틀의 갯수만큼 반복
                        if(recommendCountArr[photoFrameArr[j]] < minRecommendation){        //minRecommendation에 담긴 추천횟수보다 적은 추천횟수이면
                            minRecommendation = recommendCountArr[photoFrameArr[j]];        //추천횟수 변수에 대입
                            resetRecommendNum = photoFrameArr[j];       //학생번호 변수에 대입
                            searchToChangeNum = j;      //j번째임을 변수에 대입
                        }
                    }

                    if(minRecommendation != 1001 && resetRecommendNum != 0){        //최저 추천이 1001이 아니고 학생번호가 0이 아니면 추천점수가 가장 낮은 학생의 정보를 담은 상태
                        for(int j = searchToChangeNum; j < photoFrameArr.length; j++){      //아까 배열에서 몇번째인지 담았는데, 그 순번부터 배열의 끝까지 반복
                            if(j+1 < photoFrameArr.length){     //그 j번째 다음꺼의 순번이 마지막순번보다 작은경우
                                if(recommendCountArr[photoFrameArr[j+1]] >= recommendCountArr[recommendationArr[i]]){       //걔가 새로 들어오려는 애보다 추천이 높거나 같으면
                                    photoFrameArr[j] = photoFrameArr[j+1];      //순번 앞으로 당김
                                } else {        //걔가 추천이 더 낮으면
                                    photoFrameArr[j] = recommendationArr[i];        //그 자리에 새로 들어오려는 애 넣고
                                    break;      //반복 종료
                                }
                            } else {        //j번째 다음꺼의 순번이 마지막 순번보다 같은경우
                                photoFrameArr[j] = recommendationArr[i];        //그 자리에 새로 들어오려는 애 넣고
                                break;      //반복 종료
                            }
                        }
                        recommendCountArr[resetRecommendNum] = 0;       //사진틀에서 나간애 추천횟수 0으로 초기화
                    }
                    //위에서 안걸리면 꽉찼지만, 새로 들어오려는애 보다 낮은게 없는거
                }
            } else {        //사진틀이 꽉 찬 상태가 아닌 경우
                for(int j = 0; j < photoFrameCount; j++){       //반복 돌면서
                    if(photoFrameArr[j] == recommendationArr[i]){       //사진틀에 이미 들어가있는 학생이면
                        break;      //반복 종료 (위에서 이미 추천횟수를 올렸기 때문에 그냥 종료)
                    }
                    if(photoFrameArr[j] == 0){      //반복 돌다가 0을 마주치면, 사진틀에 이미 존재하는 학생이 아닌경우이므로
                        photoFrameArr[j] = recommendationArr[i];        //새로 추가해주고
                        break;      //반복 종료
                    }
                }

            }
        }
        Arrays.sort(photoFrameArr);     //학생 번호 순서대로 정렬
        for(int i = 0; i < photoFrameArr.length; i++){      //반복 돌면서
            if(photoFrameArr[i] != 0){      //학생 번호가 0이 아니면
                System.out.print(photoFrameArr[i] + " ");   //학생 번호 출력
            }
        }
    }
}