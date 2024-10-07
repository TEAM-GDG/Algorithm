//4ms
let T = Int(readLine()!)!
var arr = [(Int, Int)](repeating: (0, 0), count: 41)

// 미리 0과 1에 대한 경우 초기화
arr[0] = (1, 0) // 0을 호출할 때 (0의 횟수, 1의 횟수)
arr[1] = (0, 1) // 1을 호출할 때 (0의 횟수, 1의 횟수)

func DP(_ num: Int) -> (Int, Int) {
    if arr[num] != (0, 0) { // 이미 값이 계산되어 있다면
        return arr[num]
    }
    
    let (count0_1, count1_1) = DP(num - 1)
    let (count0_2, count1_2) = DP(num - 2)
    
    // 두 값을 더해 num에 대한 결과를 저장
    arr[num] = (count0_1 + count0_2, count1_1 + count1_2)
    
    return arr[num]
}

for _ in 0..<T {
    let input = Int(readLine()!)!
    let result = DP(input)
    print(result.0, result.1) // 0의 호출 횟수, 1의 호출 횟수 출력
}

//8ms
let t = Int(readLine()!)!
for _ in 0..<t {
    var dp = [(1, 0), (0, 1)]
    let n = Int(readLine()!)!

    for i in 0..<n {
        let (zero, one) = (dp[i].0 + dp[i+1].0, dp[i].1 + dp[i+1].1)
        dp.append((zero, one))
    }
    print(dp[n].0, dp[n].1)
}
