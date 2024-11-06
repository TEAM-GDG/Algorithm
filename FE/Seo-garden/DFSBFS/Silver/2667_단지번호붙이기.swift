import Foundation

let n = Int(readLine()!)! // 입력되는 한 변의 크기
var map = [[Int]]()
var count = 0
var block = [Int]()

for _ in 0..<n {
    let input = readLine()!.split(separator: "").map { Int($0)! }
    map.append(input)
}

// dfs 함수
func dfs(_ x: Int, _ y: Int) {
    //if x < 0 || y < 0 || x >= n || y >= n 맵 밖으로 나가지 않게 하기 위함
    if x < 0 || y < 0 || x >= n || y >= n || map[x][y] == 0 { return }
    
    count += 1 // 단지 넓이 누적
    map[x][y] = 0 // 이미 체크한 자리는 0으로 바꿔서 중복 검사 피해주기
    
    // 재귀 호출
    dfs(x+1, y)
    dfs(x-1, y)
    dfs(x, y+1)
    dfs(x, y-1)
}

// 메인 실행
for i in 0..<n {
    for j in 0..<n {
        if map[i][j] == 1 {
            count = 0 // count 초기화
            dfs(i, j) // dfs 호출
            block.append(count) // block 배열에 방금 구한 단지 크기 추가
        }
    }
}

print(block.count)
print(block.sorted().map({String($0)}).joined(separator: "\n"))
