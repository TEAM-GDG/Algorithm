let T = Int(readLine()!)!
var arr = Array(repeating: Array(repeating: 0, count: 30), count: 30)

//점화식 = nCr = n-1Cr-1 + n-1Cr

for i in 1..<30 {
    for j in 0...i {
        if j == 0 || i == j {
            arr[i][j] = 1
            continue
        }
            
        arr[i][j] = arr[i-1][j] + arr[i-1][j-1]
    }
}

for i in 0..<T {
    let input = readLine()!.split(separator: " ").compactMap { Int($0)! }
    print(arr[input[1]][input[0]])
}
