let N = Int(readLine()!)!
var tupleArr = [(Int, Int)]()
var num = 0
for _ in 0..<N {
    let tuple = readLine()!.split(separator: " ").map { Int($0)! }
    tupleArr.append((tuple[0], tuple[1]))
}

// 종료 시간을 기준으로 튜플 배열 정렬
tupleArr.sort {
    if $0.1 == $1.1 {
        return $0.0 < $1.0
    } else {
        return $0.1 < $1.1
    }
}

var result = [(Int, Int)]()  // 결과로 선택된 구간을 저장할 배열
var lastEndTime = -1  // 마지막으로 선택된 구간의 종료 시간 (초기값은 구간에 포함되지 않도록 -1로 설정)

// 구간을 선택
for interval in tupleArr {
    if interval.0 >= lastEndTime {  // 현재 구간의 시작 시간이 마지막 종료 시간 이후라면 선택
        result.append(interval)
        lastEndTime = interval.1  // 종료 시간을 갱신
    }
}
print(result.count)
