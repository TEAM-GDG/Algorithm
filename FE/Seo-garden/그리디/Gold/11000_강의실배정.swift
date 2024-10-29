let N = Int(readLine()!)!

var startTimes = [Int]()
var endTimes = [Int]()

for _ in 0..<N {
    let input = readLine()!.split(separator: " ").compactMap { Int($0) }
    startTimes.append(input[0])
    endTimes.append(input[1])
}

// 시작 시간과 종료 시간을 각각 정렬
startTimes.sort()
endTimes.sort()

var startPointer = 0
var endPointer = 0
var currentRooms = 0
var maxRooms = 0

while startPointer < N {
    if startTimes[startPointer] < endTimes[endPointer] {
        // 새로운 강의실이 필요하므로 현재 강의실 수 증가
        currentRooms += 1
        maxRooms = max(maxRooms, currentRooms)
        startPointer += 1
    } else {
        // 기존 강의실이 비게 되므로 강의실 수 감소
        currentRooms -= 1
        endPointer += 1
    }
}

print(maxRooms)
