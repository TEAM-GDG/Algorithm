let input = readLine()!.split(separator: " ").map({ Int($0)! })
let N = input[0], M = input[1]
var answer = 0 // 결과 값

var visited = [Bool](repeating: false, count: N + 1)
var friend = [[Int]](repeating: [], count: M)

for i in 0..<M {
    let input = readLine()!.split(separator: " ").map({ Int($0)! })
    let a = input[0]
    let b = input[1]
    friend[i] = [a, b]
}

private func DFS(_ depth: Int, _ count: Int) {
    if depth >= M {     //모든 친구의 쌍을 다 찾았다면
        answer = max(answer, count)
        return
    }
    //현재 탐색 중인 친구 쌍 중 하나라도 이미 짝지어져 있다면, 해당 친구 쌍을 건너뜀
    if visited[friend[depth][0]] || visited[friend[depth][1]] {
        DFS(depth + 1, count)
    } else {
        //두 친구 모두 아직 짝지어지지 않은 경우에만 이 쌍을 선택
        visited[friend[depth][0]] = true
        visited[friend[depth][1]] = true
        
        DFS(depth + 1, count + 1)
        //짝지어주었던 친구들을 다시 방문하지 않은 상태로 되돌림
        visited[friend[depth][0]] = false
        visited[friend[depth][1]] = false
        //현재 친구 쌍을 짝지어주지 않는 경우를 탐색하기 위해 다음 친구 쌍으로 넘어감
        DFS(depth + 1, count)
    }
}

DFS(0, 0)

// 결과 값 계산
answer *= 2
if answer < N {
    answer += 1     //로봇춤 추는 친구
}

// 결과 출력
print(answer)
