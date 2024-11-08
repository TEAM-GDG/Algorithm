import Foundation

var dx = [-1, 0, 1, 0]
var dy = [0, 1, 0, -1]

let line = readLine()!.split(separator: " ").map { Int($0)! }
let n = line[0]
let m = line[1]

let line2 = readLine()!.split(separator: " ").map { Int($0)! }
let r = line2[0]
let c = line2[1]
let dir = line2[2]

var cnt = 1 // 현재까지 cnt
var map = [[Int]]()
var visited: [[Bool]] = Array(repeating: Array(repeating: false, count: m), count: n)

for _ in 0..<n {
    let input = readLine()!.split(separator: " ").map { Int(String($0))! }
    map.append(input)
}

//현재 위치 방문
visited[r][c] = true
//현재 방향을 기준 왼쪽으로 회전하는 함수
//현재 방향과 회전 횟수를 받아서 왼쪽 방향을 반환
func leftTurn(_ now: Int, _ i: Int) -> Int {
   return (now + 3 - i) % 4 // 북 - 0, 동 - 1, 남 - 2, 서 - 3
}

func DFS(_ cnt: Int, _ now: Int, _ x: Int, _ y: Int) {
    for i in 0..<4 {
        let nextD = leftTurn(now, i)
        let nextX = dx[nextD] + x
        let nextY = dy[nextD] + y
        //이동하려는 위치가 방을 벗어나면 무시
        if (nextX >= n || nextX < 0 || nextY >= m || nextY < 0) { continue }
        //이미 방문했거나 벽인 경우
        if (visited[nextX][nextY] == true || map[nextX][nextY] == 1) { continue }
        visited[nextX][nextY] = true
        DFS(cnt + 1, nextD, nextX, nextY)
    }
    
    //4번 연속 실행을 끝내고
    //바로 뒤가 벽이면 멈춤
    // 아니면 후진
    
    let backIdx = now > 1 ? now - 2: now + 2
    let backX = dx[backIdx] + x
    let backY = dy[backIdx] + y
    //뒤로 갈 수 있는지 확인
    if backX < n || backX > 0 || backY < m || backY > 0  {
        if map[backX][backY] == 1 {     //뒤가 벽이면 현재까지 청소한 칸 출력 후 종료
            print(cnt)
            exit(0)
        } else {
            DFS(cnt, now, backX, backY) //후진 시 방향은 바뀌지 x
        }
    }
}

DFS(cnt, dir, r, c)
