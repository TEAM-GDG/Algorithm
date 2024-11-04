import Foundation

struct Queue<T> {
    private var queue: [T] = []
    
    public var count: Int {
        return queue.count
    }
    
    public var isEmpty: Bool {
        return queue.isEmpty
    }
    
    public mutating func enqueue(_ element: T) {
        queue.append(element)
    }
    
    public mutating func dequeue() -> T? {
        return isEmpty ? nil : queue.removeFirst()
    }
}
//Thanks to 소들이
let input = readLine()!.split(separator: " ").map { Int($0)! }
let N = input[0], M = input[1], V = input[2]
var graph = [[Int]](repeating: [], count: N+1)
var visited = [Bool](repeating: false, count: N+1)
var queue = Queue<Int>()
var result = ""

for _ in 1...M {            //정점 번호는 1번부터 N번까지
    let inputArr = readLine()!.split(separator: " ").map { Int($0)! }
    let a = inputArr[0], b = inputArr[1]
    graph[a].append(b)      //무방향 그래프
    graph[b].append(a)      //무방향 그래프
}
    
for i in 1...N {        //그래프 정렬
    graph[i].sort()
}

private func DFS(_ node: Int) {
    visited[node] = true            //시작 노드를 true
    result += "\(node) "
    for nextnode in graph[node] {   //인접 노드
        if !visited[nextnode] {     //방문하지 않은 노드가 있다면
            DFS(nextnode)
        }
    }
}

private func BFS(_ node: Int) {
    visited[node] = true
    
    queue.enqueue(node)             //큐에 노드 추가
    while !queue.isEmpty {          //비어 있지 않다면
        let q = queue.dequeue()!    //큐에서 노드를 꺼냄
        print(q, terminator: " ")
        for nextnode in graph[q] {
            if !visited[nextnode] {
                visited[nextnode] = true
                queue.enqueue(nextnode)
            }
        }
    }
}

DFS(V)
print(result)
visited = [Bool](repeating: false, count: N+1)          //방문배열 초기화
BFS(V)

//5 5 3: 노드 개수는 5개, 간선 개수는 5개, 시작 노드는 3번입니다.

//5 4 → 5번 노드와 4번 노드가 연결
//5 2 → 5번 노드와 2번 노드가 연결
//1 2 → 1번 노드와 2번 노드가 연결
//3 4 → 3번 노드와 4번 노드가 연결
//3 1 → 3번 노드와 1번 노드가 연결
