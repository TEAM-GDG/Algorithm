let T = Int(readLine()!)!
for _ in 0..<T {
    let N = Int(readLine()!)!
    let arr = readLine()!.split(separator: " ").map { Int($0)! }
    var rst = Array(repeating: Array(repeating: 0, count: N), count: N)
    
    for j in 1..<N {
        for i in stride(from: j - 1, through: 0, by: -1) {
            var small = Int.max
            // i부터 j까지 구간을 분할하면서 최소값 계산
            for k in 0..<(j - i) {
                // 현재 구간에서 최소 비용을 계산
                small = min(small, rst[i][i + k] + rst[i + k + 1][j])
            }
            // 현재 구간의 최소 비용에 arr[i]부터 arr[j]까지의 합을 더하여 저장
            rst[i][j] = small + arr[i...j].reduce(0, +)
        }
    }
    print(rst[0][N - 1])
}

//각 요소 검사:
//
//j = 1부터 시작하여 j = 3까지의 요소를 검사합니다.
//
//j = 1일 때:
//
//i = 0에서 시작하고, small은 Int.max로 초기화됩니다.
//k = 0일 때 rst[0][0] + rst[1][1] = 0 + 0 = 0이므로, small = 0.
//rst[0][1] = small + arr[0...1].reduce(0, +) = 0 + (40 + 30) = 70.
//따라서 rst[0][1]은 70이 됩니다.
//j = 2일 때:
//
//i = 1부터 시작하여 i = 0까지 역순으로 검사합니다.
//i = 1, j = 2인 경우:
//small = min(rst[1][1] + rst[2][2]) = 0 + 0 = 0.
//rst[1][2] = small + arr[1...2].reduce(0, +) = 0 + (30 + 30) = 60.
//i = 0, j = 2인 경우:
//small = min(rst[0][0] + rst[1][2], rst[0][1] + rst[2][2]) = min(0 + 60, 70 + 0) = 60.
//rst[0][2] = small + arr[0...2].reduce(0, +) = 60 + (40 + 30 + 30) = 160.
//j = 3일 때:
//
//i = 2, j = 3인 경우:
//small = min(rst[2][2] + rst[3][3]) = 0 + 0 = 0.
//rst[2][3] = small + arr[2...3].reduce(0, +) = 0 + (30 + 50) = 80.
//i = 1, j = 3인 경우:
//small = min(rst[1][1] + rst[2][3], rst[1][2] + rst[3][3]) = min(0 + 80, 60 + 0) = 60.
//rst[1][3] = small + arr[1...3].reduce(0, +) = 60 + (30 + 30 + 50) = 170.
//i = 0, j = 3인 경우:
//small = min(rst[0][0] + rst[1][3], rst[0][1] + rst[2][3], rst[0][2] + rst[3][3]) = min(0 + 170, 70 + 80, 160 + 0) = 150.
//rst[0][3] = small + arr[0...3].reduce(0, +) = 150 + (40 + 30 + 30 + 50) = 300.
