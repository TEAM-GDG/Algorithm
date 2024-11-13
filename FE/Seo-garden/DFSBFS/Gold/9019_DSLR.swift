private func D(_ n: Int) -> Int { (n * 2) % 10000 }
private func S(_ n: Int) -> Int { n == 0 ? 9999 : n - 1 }
private func L(_ n: Int) -> Int { (n % 1000) * 10 + n / 1000 }
private func R(_ n: Int) -> Int { (n % 10) * 1000 + n / 10 }

let T = Int(readLine()!)!

for _ in 0..<T {
    var visited = Array(repeating: false, count: 10001)
    var queue: [(Int, String)] = []
    var index = 0       //큐에서 꺼낼 인덱스 추적
    
    let AB = readLine()!.split(separator: " ").map { Int($0)! }
    let (A, B) = (AB[0], AB[1])
    queue.append((A, ""))       //먼저 입력 받는 값
    visited[A] = true
    
    while true {
        //큐에서 현재 상태를 꺼냄
        let select = queue[index]
        let start = select.0, end = B, result = select.1
        index += 1
        if start != end {
            //queue 에 모든 함수를 실행하면서 추가하고 B에 도달한다면 출력
            let d = D(start)
            let s = S(start)
            let l = L(start)
            let r = R(start)
            
            if visited[d] == false {
                visited[d] = true
                queue.append((d, result + "D"))
            }
            if visited[s] == false {
                visited[s] = true
                queue.append((s, result + "S"))
            }
            if visited[l] == false {
                visited[l] = true
                queue.append((l, result + "L"))
            }
            if visited[r] == false {
                visited[r] = true
                queue.append((r, result + "R"))
            }
        } else {
            print(result)
            break
        }
    }
}
