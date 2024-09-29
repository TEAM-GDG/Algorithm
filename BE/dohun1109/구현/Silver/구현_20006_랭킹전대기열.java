package 구현.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 구현_20006_랭킹전대기열 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        

        int pLen = Integer.parseInt(st.nextToken());   //플레이어 수
        int m = Integer.parseInt(st.nextToken());   //방의 정원
        ArrayList<Room> rooms = new ArrayList<>();  //전체 방들

        //처음 들어온 플레이어는 항상 방을 생성하고 기준점을 잡기 때문에
        Room room = new Room(); //방생성
        st = new StringTokenizer(br.readLine());
        Player player = new Player(Integer.parseInt(st.nextToken()), st.nextToken());   //플레이어 생성
        room.addPlayer(player); //플레이어 추가
        room.setStandard(player.level); //방 레벨 기준점 정함
        room.addpCount();   //플레이어 수 증가
        rooms.add(room);    //생성한 방을 전체 방에 추가(방자체는 인스턴스 값이으로 시점의 문제X)


        for (int i = 1; i < pLen; i++) {    //처음 플레이어는 이미 추가됬음으로 1부터시작
            st = new StringTokenizer(br.readLine());
            player = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
            boolean check = false;      //플레이어가 어떤 방에든 들어갔는지 체크
            for (int j =0; j< rooms.size(); j++) {  //모든 방들을 반복
                Room r = rooms.get(j);  //방 인스턴스 값을 가져와서
                if(r.minLevel<=player.level && player.level<=r.maxLevel && r.pCount<m){ //체크) 방의 레벨(최소,최대)값가 현재 플레이어의 레벨 이 만족하는가 + 해당 방에 플레이어가 들어갈 공간이 있는지
                    r.addPlayer(player);    //해당 방에 플레이어 추가
                    r.addpCount();          //해당 방에 플레이어 수 증가
                    check = true;           //어떤 방에든 들어감 true
                    break;  //플레이어가 방에 들어갔음으로 더 이상 반복할 필요가 없음
                }
            }
            if (!check){        //플레이가 어떤 방에도 들어가지 못했다면
                room = new Room();      //방생성 하고
                room.addPlayer(player); //기존과 같이 방에 들어가서 방설정을 함
                room.setStandard(player.level);
                room.addpCount();
                rooms.add(room);        //새로운 방 추가
            }
        }

        StringBuilder sb = new StringBuilder(); //한번에 담아서 출력하기 위해 StringBuilder 사용
        for (int i = 0; i < rooms.size(); i++) {
            Collections.sort(rooms.get(i).players, Comparator.comparing(Player::getName));  //플레이어의 이름을 기준으로 오름차순 정렬(메소드 참조)
            if (rooms.get(i).pCount == m ){
                sb.append("Started!").append("\n");
                for (Player p : rooms.get(i).players){
                    sb.append(p.level).append(" ").append(p.name).append("\n");
                }
            }else{
                sb.append("Waiting!").append("\n");
                for (Player p : rooms.get(i).players){
                    sb.append(p.level).append(" ").append(p.name).append("\n");
                }
            }
        }
        System.out.println(sb); //최종 출력



    }
    static class Room{  //Room Class
        int minLevel;       //방 진입 최소레벨
        int maxLevel;       //방 진입 최대레벨
        int pCount = 0;
        ArrayList<Player> players = new ArrayList<>();//방에 참가한 플레이어

        public void setStandard(int level){ //레벨 기준점을 정하는 메소드
            minLevel = level - 10;
            maxLevel = level + 10;
        }

        public void addpCount() {   //플레이어 수 증가 메소드
            this.pCount++;
        }

        public void addPlayer(Player player){   //플레이어 추가 메소드
            players.add(player);
        }

    }

    static class Player {       //Player Class
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }


        public String getName() {
            return name;
        }
    }

}
