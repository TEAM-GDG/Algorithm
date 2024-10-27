import Foundation

let input = readLine()!.split(separator: " ").map { Int($0)! }
let N = input[0], M = input[1], B = input[2]

var ground = [Int]()
for _ in 0..<N {
    ground += readLine()!.split(separator: " ").map { Int($0)! }
}

var time = Array(repeating: 0, count: 257)
var height = 0

for g in 0..<257 {
    var block = B
    var currentTime = 0
    
    for i in ground {
        if i <= g {
            // 현재 땅 높이(i)가 목표 높이(g)보다 낮을 경우
            currentTime += g - i  // 블록을 쌓아야 하므로 (g - i)만큼의 시간 추가
            block -= g - i  // 사용한 블록 개수만큼 인벤토리에서 차감
        } else {
            currentTime += 2 * (i - g)  // 블록을 제거해야 하므로 (i - g) * 2만큼의 시간 추가
            block += i - g  // 제거한 블록 개수만큼 인벤토리에 추가
        }
    }
    
    // 인벤토리에 남은 블록이 0 이상이고, 현재 계산된 시간이 기존 최저 시간보다 적거나 같을 때
    if block >= 0 && (time[height] == 0 || currentTime <= time[height]) {
        time[g] = currentTime
        height = g
    }
}

print(time[height], height)
