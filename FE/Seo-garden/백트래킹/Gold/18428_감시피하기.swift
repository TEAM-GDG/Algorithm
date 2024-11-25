let n = Int(readLine()!)!
var graph = Array(repeating: Array(repeating: Character(" "), count: n), count: n) // 맵 정보
var teacherLocations = [[Int]]()
var roads = [[Int]]()
let cnt = 0
let dx = [-1, 1, 0, 0]
let dy = [0, 0, -1, 1]
var ans = "NO"

// 맵 정보 입력
for i in 0..<n {
    let line = readLine()!.split(separator: " ").map { $0.first! }
    for j in 0..<n {
        graph[i][j] = line[j]
        if line[j] == "T" {
            teacherLocations.append([i, j]) // 선생님 좌표 저장
        } else if line[j] == "X" {
            roads.append([i, j]) // 빈 복도 좌표 저장
        }
    }
}

// 좌표가 맵의 경계를 벗어나지 않는지 확인하는 함수
func boundary(_ i: Int, _ j: Int) -> Bool {
    return 0 <= i && i < n && 0 <= j && j < n
}

// 각 선생님이 학생을 감시할 수 있는지 판별하는 함수
func isFind() -> Bool {
    // 모든 선생님의 위치를 확인
    for teacherLocation in teacherLocations {
        for direction in 0..<4 { // 4개의 방향으로 감시
            var ni = teacherLocation[0]
            var nj = teacherLocation[1]
            
            while true {
                ni += dx[direction]
                nj += dy[direction]
                
                // 경계를 벗어나거나 장애물("B")을 만나면 감시 종료
                if !boundary(ni, nj) || graph[ni][nj] == "B" {
                    break
                }
                // 학생("S")을 만나면 감시 가능
                else if graph[ni][nj] == "S" {
                    return true
                }
            }
        }
    }
    return false // 감시할 수 있는 학생이 없음
}

// DFS를 사용해 장애물을 설치하는 함수
func dfs(_ index: Int, _ cnt: Int) {
    if cnt == 3 { // 장애물 3개 설치 완료 시
        if !isFind() {      // 모든 선생님이 한 명의 학생도 감시할 수 없는 경우 "YES"
            ans = "YES"
        }
        return
    }
    
    for i in index..<roads.count {      // 현재 위치 이후의 모든 빈 복도를 순회
        let ni = roads[i][0]
        let nj = roads[i][1]
        
        graph[ni][nj] = "B"
        dfs(i + 1, cnt + 1)
        // 백트래킹: 설치한 장애물 제거
        graph[ni][nj] = "X"
    }
}

dfs(0, 0)
print(ans)
