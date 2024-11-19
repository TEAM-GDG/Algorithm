let n = Int(readLine()!)!
//var arr = Array(repeating: Array(repeating: 0, count: n), count: n)
var arr = [[Int]]()
var visited = Array(repeating: false, count: n)
var resultMin = Int.max

var startTeam = 0
var linkTeam = 0

for i in 0..<n {
    let input = readLine()!.split(separator: " ").map { Int($0)! }
        arr.append(input)
//    arr[i] = input
}

private func DFS(_ depth: Int, _ start: Int) {
    if depth == n/2 {       // n/2 명이 스타트팀 혹은 링크팀으로 선택됐을 때
        //DFS로 모든 가능한 조합을 탐색하기 때문에 이전에 계산했던 팀 구성의 능력치를 0으로 초기화를 해줘야 한다.
        startTeam = 0
        linkTeam = 0
        
        for i in 0..<n {
            for j in 0..<n {
                //방문하지 않은 사람들끼리의 능력치를 합산(링크팀)
                if !visited[i] && !visited[j] {
                    linkTeam += arr[i][j]
                }
                //방문한 사람들끼리의 능력치를 합산
                if visited[i] && visited[j] {
                    startTeam += arr[i][j]
                }
            }
        }
        resultMin = min(resultMin, abs(linkTeam - startTeam))
        return
    }
    for i in start..<n {
        //이미 선택된 팀원이 아니라면
        if !visited[i] {
            visited[i] = true
            DFS(depth + 1, i)
            visited[i] = false
        }
    }
}
DFS(0,0)
print(resultMin)
