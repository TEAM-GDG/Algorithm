let nk = readLine()!.split(separator: " ").map { Int($0)! }
let n = nk[0], k = nk[1]

private func BFS(_ n: Int, _ depth: Int) {
    var queue = [(n, 0)]
    var visited = [Bool](repeating: false, count: 100001)
    visited[n] = true
    
    var parent = [Int](repeating: -1, count: 100001)
    var index = 0
    
    while index < queue.count {
        let temp = queue[index]
        let place = temp.0
        let depth = temp.1
        var nextPlace = 0
        
        if place == k {     //목적지에 도달했다면
            print(depth)
            
            var path = [Int]()
            var current = place
            
            while current != -1 {
                path.append(current)        //현재 위치를 경로에 추가
                current = parent[current]   //부모 노드를 따라간다.
            }
            print(path.reversed().map { String($0) }.joined(separator: " "))
            break
        }
        
        for i in 0..<3 {
            switch i {
            case 0:
                nextPlace = place - 1
            case 1:
                nextPlace = place + 1
            default:
                nextPlace = place * 2
            }
            
            if nextPlace >= 0 && nextPlace <= 100000 && !visited[nextPlace] {
                visited[nextPlace] = true
                queue.append((nextPlace, depth + 1))
                parent[nextPlace] = place           //부모 노드 기록함으로 다음 탐색 대상사용
            }
        }
        index += 1
    }
}

BFS(n, 0)
