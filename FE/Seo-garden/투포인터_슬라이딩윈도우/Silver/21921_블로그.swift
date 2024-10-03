import Foundation

final class FileIO {
    private let buffer:[UInt8]
    private var index: Int = 0

    init(fileHandle: FileHandle = FileHandle.standardInput) {
        
        buffer = Array(try! fileHandle.readToEnd()!)+[UInt8(0)] // 인덱스 범위 넘어가는 것 방지
    }

    @inline(__always) private func read() -> UInt8 {
        defer { index += 1 }

        return buffer[index]
    }

    @inline(__always) func readInt() -> Int {
        var sum = 0
        var now = read()
        var isPositive = true

        while now == 10
                || now == 32 { now = read() } // 공백과 줄바꿈 무시
        if now == 45 { isPositive.toggle(); now = read() } // 음수 처리
        while now >= 48, now <= 57 {
            sum = sum * 10 + Int(now-48)
            now = read()
        }

        return sum * (isPositive ? 1:-1)
    }

    @inline(__always) func readString() -> String {
        var now = read()

        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시

        let beginIndex = index-1

        while now != 10,
              now != 32,
              now != 0 { now = read() }

        return String(bytes: Array(buffer[beginIndex..<(index-1)]), encoding: .ascii)!
    }

    @inline(__always) func readByteSequenceWithoutSpaceAndLineFeed() -> [UInt8] {
        var now = read()

        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시

        let beginIndex = index-1

        while now != 10,
              now != 32,
              now != 0 { now = read() }

        return Array(buffer[beginIndex..<(index-1)])
    }
}

let fileIO = FileIO()

let N = fileIO.readInt()
let X = fileIO.readInt()
//여기 위에는 시간초과로 인해 사용하는 라이노사마의 FileIO 입니다.
var visit = [Int](repeating: 0, count: N)

for item in 0..<visit.count {
    visit[item] = fileIO.readInt()
}

var arr = [Int]()

var start = 0
var end = 0
var sum = 0
var count = 0

//for end in 0..<(N - X + 1){
//    for i in 0..<X {
//        sum += visit[end + i]
//    }
//    arr.append(sum)
//    sum = 0
//}

//위의 주석을 아래의 for문 2개로 분리했다.
for i in 0..<X {
    sum += visit[i]         //구간 합 구하기
}

arr.append(sum)

for i in X..<N {
    sum += visit[i] - visit[i - X]          //구간 이동
    arr.append(sum)
}

if arr.max()! == 0 {
    print("SAD")
} else {
    let result = arr.max()!
    print(result)
    
    let count = arr.filter { $0 == result}.count
    print(count)
}
