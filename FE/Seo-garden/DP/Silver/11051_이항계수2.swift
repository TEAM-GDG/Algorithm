let nk = readLine()!.split(separator: " ").compactMap { Int($0) }
let n = nk[0]
let k = nk[1]

var arr = Array(repeating: Array(repeating: 0, count: k + 1), count: n + 1)

for i in 0..<n+1 {
    arr[i][0] = 1       //0! 을 1로 처리하기 위함
}

for i in 0..<min(n+1, k+1) {        //n 이 k 보다 큰 경우 인덱스 초과가 날 수 있다.
    arr[i][i] = 1
}

for i in 1..<n+1 {
    for j in 1..<min(n+1, k+1) {        //n 이 k 보다 큰 경우 인덱스 초과가 날 수 있다.
        arr[i][j] = (arr[i-1][j] + arr[i-1][j-1]) % 10007
    }
}

print(arr[n][k])
