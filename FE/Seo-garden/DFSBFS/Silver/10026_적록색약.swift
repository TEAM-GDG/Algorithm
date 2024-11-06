let N = Int(readLine()!)!
var originalmap = [[Character]]()

for _ in 0..<N {
    let input = Array(readLine()!)
    originalmap.append(input)
}
var map = originalmap

var result = [Int]()
var count = 0

private func DFS(_ x: Int, _ y: Int, _ currentColor: Character) {
    if x < 0 || y < 0 || x >= N || y >= N || map[x][y] != currentColor { return }
    map[x][y] = "X"
    
    DFS(x+1, y, currentColor)
    DFS(x-1, y, currentColor)
    DFS(x, y+1, currentColor)
    DFS(x, y-1, currentColor)
}

for i in 0..<N {
    for j in 0..<N {
        if map[i][j] != "X" {
            let currentColor = map[i][j]

            DFS(i, j, currentColor)
            count += 1
        }
    }
}
result.append(count)

map = originalmap       //기존맵으로 초기화

//적색녹약인 사람
for i in 0..<N {
    for j in 0..<N {
        if map[i][j] == "G" {
            map[i][j] = "R"
        }
    }
}

count = 0
for i in 0..<N {
    for j in 0..<N {
        if map[i][j] != "X" {
            let currentColor = map[i][j]
            DFS(i, j, currentColor)
            count += 1
        }
    }
}
result.append(count)

print(result.map { String($0)}.joined(separator: " "))
