import Foundation

let input = readLine()!.split(separator: " ").map { Int($0)! }      //공백을 기준으로 값을 받고
let playerCount = input[0], roomCapacity = input[1]                //레벨과 닉네임을 받는다.

var rooms: [[(level: Int, nickname: String)]] = []                  //2차원 튜플 배열로 받고

for _ in 0..<playerCount {
    let playerInput = readLine()!.split(separator: " ")
    let playerLevel = Int(playerInput[0])!
    let playerNickname = String(playerInput[1])
    
    var enteredRoom = false
    
    // 방 탐색
    for i in 0..<rooms.count {
        let firstPlayer = rooms[i].first! //튜플의 첫번째 요소인 플레이어의 레벨을 받고
        // 첫 번째 플레이어의 레벨 기준으로 -10 ~ +10 범위 확인
        if playerLevel >= firstPlayer.level - 10 && playerLevel <= firstPlayer.level + 10 && rooms[i].count < roomCapacity {//마지막 조건은 방인원을 초과하지 않게 하기 위함
            rooms[i].append((level: playerLevel, nickname: playerNickname))     //주의할 껀 여긴 방 안에 추가를 하는 코드고
            enteredRoom = true
            break
        }
    }
    // 입장 가능한 방이 없다면 새로운 방 생성
    if !enteredRoom {
        rooms.append([(level: playerLevel, nickname: playerNickname)])          //이건 방을 추가하는 코드다
    }
}

// 결과 출력
for room in rooms {
    // 방 내 플레이어 정렬 (닉네임 기준) a -> z
    let sortedRoom = room.sorted { $0.nickname < $1.nickname }
    
    // 방 상태 출력
    if sortedRoom.count == roomCapacity {
        print("Started!")
    } else {
        print("Waiting!")
    }
    
    // 정렬된 플레이어 출력
    for player in sortedRoom {
        print("\(player.level) \(player.nickname)")
    }
}
