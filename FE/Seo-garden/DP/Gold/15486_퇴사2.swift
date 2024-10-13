import Foundation

let N = Int(readLine()!)!
var dp = Array(repeating: 0, count: N+1)

for now in 0..<N {
    let input = readLine()!.split(separator: " ").map { Int($0)! }
    let T = input[0]
    let P = input[1]
    
    dp[now + 1] = max(dp[now + 1], dp[now])
    
    if now + T < N + 1 {        // 현재 상담을 진행할 수 있을 때만 (상담을 끝내는 날짜가 퇴사 날짜를 넘지 않을 경우)
        dp[now + T] = max(dp[now + T], dp[now] + P)
    }
}

print(dp[N])
