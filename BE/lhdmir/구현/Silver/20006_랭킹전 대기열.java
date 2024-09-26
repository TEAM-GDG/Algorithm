import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Player implements Comparable<Player> {
    int level;
    String nickname;

    public Player(int level, String nickname) {
        this.level = level;
        this.nickname = nickname;
    }

    @Override
    public int compareTo(Player other) {
        return this.nickname.compareTo(other.nickname); // 닉네임 사전순 정렬
    }
}

class Room {
    // 해당 방의 기준 레벨
    int baseLevel;

    List<Player> players;

    public Room(int level) {
        this.baseLevel = level;
        this.players = new ArrayList<>();
    }

    // 레벨 제한(-10 ~ +10)
    public boolean levelLimit(int level) {
        return Math.abs(level - baseLevel) <= 10;
    }

    public boolean isFull(int maxSize) {
        return players.size() == maxSize;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void printRoom(int maxSize) {
        if (players.size() == maxSize) {
            System.out.println("Started!");
        } else {
            System.out.println("Waiting!");
        }
        Collections.sort(players); // 플레이어를 닉네임 순으로 정렬
        for (Player player : players) {
            System.out.println(player.level + " " + player.nickname);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int p = Integer.parseInt(firstLine[0]); // 플레이어 수
        int m = Integer.parseInt(firstLine[1]); // 방 정원

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            String[] playerInfo = br.readLine().split(" ");
            int level = Integer.parseInt(playerInfo[0]);
            String nickname = playerInfo[1];

            Player newPlayer = new Player(level, nickname);

            // 기존 방에 입장이 가능한지 판별할 변수
            boolean match = false;

            // 기존 방에 입장 가능한지 확인
            for (Room room : rooms) {
                if (room.levelLimit(level) && !room.isFull(m)) {
                    room.addPlayer(newPlayer);
                    match = true;
                    break;
                }
            }

            // 입장 가능한 방이 없으면 새로운 방 생성
            if (!match) {
                Room newRoom = new Room(level);
                newRoom.addPlayer(newPlayer);
                rooms.add(newRoom);
            }
        }

        // 방 상태 출력
        for (Room room : rooms) {
            room.printRoom(m);
        }
    }
}
