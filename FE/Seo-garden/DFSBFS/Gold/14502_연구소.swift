let nm = readLine()!.split(separator: " ").map{Int(String($0))!}
let n = nm[0]
let m = nm[1]
var arr = [[Int]]()


let dx = [-1, 1, 0, 0]
let dy = [0, 0, 1, -1]

var result = 0

for _ in 0..<n{
    arr.append(readLine()!.split(separator: " ").map{ Int(String($0))!} )
}

private func dfs(_ depth: Int){
    if depth == 3 {         //벽을 모두 세웠다면 바이러스 확산
        bfs()
        return
    }
    //가능한 모든 좌표에 벽을 세운다.
    for i in 0..<n {
        for j in 0..<m {
            if arr[i][j] == 0 {
                arr[i][j] = 1
                dfs(depth + 1)
                arr[i][j] = 0       //백트래킹
            }
        }
    }
}

private func bfs(){     //바이러스 확산
    var visited = Array(repeating: Array(repeating: false, count: 9), count: 9)
    var temp = arr
    var queue = [(Int, Int)]()
    var count = 0
    
    for i in 0..<n {
        for j in 0..<m {
            if temp[i][j] == 2 {
                //ex (2, 3) 좌표는 23으로 저장되고
                queue.append((i, j))
            }
        }
    }
    
    while !queue.isEmpty {
        let (x, y) = queue.removeFirst()
        for i in 0..<4 {        //상하좌우 4방향
            let nx = x + dx[i]
            let ny = y + dy[i]
            if nx >= 0 && nx < n && ny >= 0 && ny < m {     //맵 밖에 나가지 않게 하기 위함
                //빈칸 이면서 아직 방문하지 않은 곳만 확산
                if temp[nx][ny] == 0 && !visited[nx][ny] {
                    visited[nx][ny] = true
                    temp[nx][ny] = 2        //바이러스 확산
                    queue.append((nx, ny))  //새로 퍼진 좌표를 큐에 추가
                }
                
            }
        }
    }
    //안전 영역의 개수 계산
    for i in 0..<n {
        for j in 0..<m {
            if temp[i][j] == 0 {
                count += 1
            }
        }
    }
    result = max(result, count)
}

dfs(0)
print(result)
