let mnh = readLine()!.split(separator: " ").map { Int($0)! }
let m = mnh[0], n = mnh[1]
var map = [[Int]](repeating: [], )
//동남서북하상
let dx = [1, 0, -1, 0]
let dy = [0, 1, 0, -1]

for i in 0..<h {
    for _ in 0..<n {
        map[i].append(readLine()!.split(separator: " ").map { Int($0)! })
    }
}

var queue: [(Int, Int, Int)] = []

for z in 0..<h {
    for y in 0..<n {
        for x in 0..<m {
            if map[z][y][x] == 1 {      //익은 토마토
                queue.append((z,y,x))
            }
        }
    }
}

var index = 0

while index < queue.count {
    let z = queue[index].0
    let y = queue[index].1
    let x = queue[index].2
    
    for i in 0..<6 {
        let nz = z + dz[i]
        let ny = y + dy[i]
        let nx = x + dx[i]
        // 범위 내에 있으면서, 익지 않은 토마토일 경우
        if (0 <= nx && nx < m) && (0 <= ny && ny < n) && (0 <= nz && nz < h) && map[nz][ny][nx] == 0 {
            map[nz][ny][nx] = map[z][y][x] + 1
            queue.append((nz, ny, nx))
        }
    }
    index += 1
}


print(map.flatMap { $0 }.flatMap { $0 }.contains(0) ? -1 : map.flatMap { $0 }.flatMap { $0 }.max()! - 1)
