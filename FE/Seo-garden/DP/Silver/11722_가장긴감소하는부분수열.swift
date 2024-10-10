let input = Int(readLine()!)!
var arr = [Int]()
var dp = [Int](repeating: 1, count: input + 1)
let A = readLine()!.split(separator: " ").compactMap{ Int($0)! }

var n = 0
var answer = 0

for i in 1..<A.count {
    for j in 0..<i {
        if A[j] > A[i] {        // A[j]가 A[i]보다 크다면, 즉 A[i]를 포함하는 감소하는 수열이 될 수 있다면
            dp[i] = max(dp[i], dp[j] + 1)       // dp[i] 값 갱신: dp[j] + 1과 현재 dp[i] 값 중 더 큰 값
        }
    }
}
print(dp.max()!)
