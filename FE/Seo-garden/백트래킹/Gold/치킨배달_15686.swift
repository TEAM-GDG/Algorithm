let nm = readLine()!.split(separator: " ").map { Int($0)! }
let n = nm[0], m = nm[1] // n: 도시 크기, m: 선택 가능한 치킨집 수
var G = [[Character]]()  // 도시 정보 (지도)
var order = [Int]()      // 선택된 치킨집의 인덱스 목록
var ans = Int.max        // 최소 치킨 거리, 초기값은 매우 큰 값으로 설정

// 도시의 각 행을 입력받아 2차원 배열 G로 변환
for _ in 0..<n {
    let input = Array(readLine()!) // 한 줄의 입력을 배열로 변환
    var rowList = [Character]()   // 치킨집이나 집 정보를 담을 배열
    var sdx = 0
    while sdx < input.count {     // 공백이 포함된 입력에서 데이터를 추출
        rowList.append(input[sdx])
        sdx += 2                  // 공백 무시, 격자 정보만 저장
    }
    G.append(rowList)             // 각 행을 G에 추가
}

var hList = [(Int, Int)]() // 집의 위치 리스트
var cList = [(Int, Int)]() // 치킨집의 위치 리스트

// 도시를 순회하며 집과 치킨집의 위치를 각각 저장
for idx in G.indices {
    for jdx in G[idx].indices {
        if G[idx][jdx] == "1" {       // 집
            hList.append((idx, jdx))
        } else if G[idx][jdx] == "2" {// 치킨집
            cList.append((idx, jdx))
        }
    }
}

// 선택된 치킨집들로부터 모든 집의 치킨 거리를 계산
private func getChickenDist() -> Int {
    var result = 0 // 모든 집의 치킨 거리 합
    for hPos in hList {
        var tDist = Int.max     //현재 집에서 가장 가까운 치킨집 거리
        for idx in order {
            let oPos = cList[idx]
            let diffX = abs(hPos.0 - oPos.0)
            let diffY = abs(hPos.1 - oPos.1)
            let ttDist = diffX + diffY      //현재 치킨집까지의 거리
            tDist = min(tDist, ttDist)      //최소 거리 갱신
        }
        result += tDist     // 현재 집의 최소 거리를 결과에 누적
    }
    return result // 모든 집의 치킨 거리 합 반환
}


private func DFS(_ n: Int) {
    // 남은 치킨집이 부족하면 가지치기
    if order.count + cList.count - n < m { return }
    if n == m { // 치킨집 m개를 선택했을 경우
        ans = min(ans, getChickenDist()) // 최소 치킨 거리 갱신
        return
    }
    
    var index = order.isEmpty ? 0 : order.last! + 1 // 다음 치킨집 선택 위치
    while index < cList.count {
        order.append(index)     // 현재 치킨집 인덱스 추가
        DFS(n+1)                // 다음 치킨집 선택 (재귀 호출)
        order.removeLast()      // 선택 취소 (백트래킹)
        index += 1              // 다음 치킨집 탐색
    }
}

DFS(0)
print(ans)
