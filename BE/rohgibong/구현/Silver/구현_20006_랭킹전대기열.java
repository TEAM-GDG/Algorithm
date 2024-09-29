import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] roomPlayerInfo = br.readLine().split(" ");        //roomPlayerInfo[0] : 플레이어의 수  roomPlayerInfo[1] : 방의 정원
        int playerCount = Integer.parseInt(roomPlayerInfo[0]);          //플레이어의 수, 방의 정원 각각 변수에 저장
        int roomLimit = Integer.parseInt(roomPlayerInfo[1]);

        String[][] roomInfo = new String[playerCount][roomLimit];       //방 갯수를 최대크기(player의 수)만큼 만들어두고, 각각의 방들은 방의 정원 수 만큼 들어가게 설정

        for(int i = 0; i < playerCount; i++){
            String playerInfo = br.readLine();      //플레이어의 정보 받아옴
            int playerLevel = Integer.parseInt(playerInfo.split(" ")[0]);   //플레이어 레벨을 변수에 저장

            for(int j = 0; j < playerCount; j++){       //받아온 플레이어를 이제 방 전체 돌아다니면서 빈 방이 아닌데 들어갈 수 있는 곳 있는지 찾는 반복문
                if(roomInfo[j][0] != null){     //빈 방이 아닌경우
                    for(int k = 1; k <=roomLimit-1; k++){       //그 방에 1번째부터 4번째 (0번째를 제외한 나머지 네 자리)에
                        if(roomInfo[j][k] == null){     //자리가 비어있는지 확인
                            //자리가 비어있으면 아래 조건문 실행
                            //이 방에 0번째 플레이어의 점수 - 10보다 새로 들어오는 플레이어의 점수가 크거나 같고,
                            //이 방에 0번째 플레이어의 점수 + 10보다 새로 들어오는 플레이어의 점수가 작거나 같은지 확인
                            if(Integer.parseInt(roomInfo[j][0].split(" ")[0]) - 10 <= playerLevel && Integer.parseInt(roomInfo[j][0].split(" ")[0]) + 10 >= playerLevel){
                                roomInfo[j][k] = playerInfo;    //위 조건에 맞으면 그 자리에 새로 들어온 플레이어 정보 저장
                                playerInfo = "x";   //플레이어 정보를 x로 덮어버림
                                break;  //더이상 반복하면 안되니까 반복문 나감
                            }
                        }
                    }
                    if(playerInfo.equals("x")) break;       //플레이어 정보가 x로 덮였으면 그 플레이어는 더 이상 빈 방이 아닌데 들어갈 수 있는곳이 있는지 안 돌아다녀도 됨. 반복문 종료
                }
            }

            if(!playerInfo.equals("x")){        //위에 반복문을 다 돌았는데도, 플레이어 정보가 x가 아니면 빈 방이 아닌데 들어갈 자리를 못 찾은 경우임. 이 경우에 여기 조건문에 해당
                for(int j = 0; j < playerCount; j++) {  //또 전체 방을 돌아다님
                    if (roomInfo[j][0] == null) {   //이번엔 빈방이 발견되면 들어감 (빈 방 아닌경우는 다 못들어가니까)
                        roomInfo[j][0] = playerInfo;    //빈방 0번째에 이 플레이어 정보를 넣음
                        playerInfo = "x";   //플레이어 정보를 x로 덮어버림
                        break;  //더 이상 빈방을 안 찾아도 되니까 반복문 종료
                    }
                }
            }
        }

        for(int i = 0; i < playerCount; i++){       //방 전체를 다시 돌아다님
            int lengthNum = 0;      //배열의 길이를 정하기 위한 변수
            for(int num = 0; num < roomInfo[i].length; num++){      //i번째 방에 있는 플레이어 수 만큼 반복 (사실 한 방에 roomLimit명만큼 고정해둬서 그만큼 돌것임)
                if(roomInfo[i][num] != null){       //i번째방에 플레이어가 있는 만큼
                    lengthNum++;        //lengthNum을 증가시킴
                }
            }
            String saveName[] = new String[lengthNum];      //위에서 증가된 수 만큼 변수 크기를 지정함
            if(roomInfo[i][roomLimit-1] != null){       //그 방에 마지막번째 인원이 null이 아니면
                System.out.println("Started!");         //그 방은 꽉찬거라 Started!를 출력
            } else if(roomInfo[i][0] != null && roomInfo[i][roomLimit-1] == null) {     //빈 방이 아니고, 마지막 인원이 null이면
                System.out.println("Waiting!");     //그 방은 덜 찬거라 Waiting!을 출력
            }

            if(roomInfo[i][0] != null){     //i번째 방에 0번째 플레이어가 null이 아니면 (빈 방이 아니면)
                for(int j = 0; j < roomLimit; j++){     //roomLimit만큼 반복돌아서 (그 방 전체만큼 반복)
                    if(roomInfo[i][j] != null){     //플레이어가 null이 아니면
                        saveName[j] = roomInfo[i][j].split(" ")[1];     //saveName이라는 변수에 플레이어 '이름'을 저장
                    }
                }
                Arrays.sort(saveName);      //이름 저장한거 정렬
                for(int n = 0; n < saveName.length; n++){       //saveName 배열 크기만큼 반복
                    for(int j = 0; j < roomLimit; j++){     //다시 roomLimit(방 전체)만큼 반복돌아서
                        //i번째방에 j번째 플레이어가 null이 아니고, saveName의 j번째가 null이 아니고, saveName의 n번째가 i번째방 j번째플레이어의 이름과 같으면
                        if(roomInfo[i][j] != null && saveName[j] != null && saveName[n].equals(roomInfo[i][j].split(" ")[1])){
                            System.out.println(roomInfo[i][j]); //그 플레이어 정보를 출력
                            break;  //j반복 돌던건 멈춤 (다시 saveName 배열에 저장한 다음 플레이어 이름값을 가지고 반복돌면서 찾으러 감)
                        }
                    }
                }
            }
        }
    }
}
