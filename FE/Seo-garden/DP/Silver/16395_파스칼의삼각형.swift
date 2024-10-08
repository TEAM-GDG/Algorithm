func binomial(_ n: Int, _ r: Int) -> Int {
    var dp = Array(repeating: [Int](), count: 30)
    for i in 0..<n {
        for j in 0..<min(i,r) {
            if j == 0 || j == i {
                dp[i][j] = 1
            } else {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
            }
        }
    }
    return dp[n-1][r-1]
}
//위에는 이항계수 구현 코드
//아래는 코드
let input = readLine()!.split(separator: " ").compactMap { Int($0) }
let n = input[0]
let k = input[1]

var pascal = Array(repeating: [Int](), count: 30)           //2차원 배열을 이렇게 초기화하는 방법이 있구나..       [[],[],[],[],[],[],[],[],[]] 이런식으로 초기화된다.

for i in 0..<30 {
    pascal[i] = Array(repeating: 1, count: i + 1)           //가장자리는 1 로 초기화
}

for i in 2..<30 {       //이미 가장자리는 1로 채워져 있기 때문에 0이 아닌 2부터 시작
    for j in 1..<i {
        pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j]        //파스칼 삼각형의 규칙
    }
}

print(pascal[n-1][k-1])     //nCk 의 값을 구하려면 index-1
