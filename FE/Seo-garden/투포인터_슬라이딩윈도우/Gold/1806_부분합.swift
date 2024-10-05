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
let S = fileIO.readInt()

var arr = [Int](repeating: 0, count: N)

for item in 0..<N {
    arr[item] = fileIO.readInt()
}
//Thanks to Rhino, 위 부분은 Swift 입출력시간을 줄이기 위한 코드로 이 코드가 없어도 시간초과는 안뜨지만, 시간이 적게 걸림, 코드리뷰할 때 볼 필요 없음!
var end = 0
var sum = 0
var count = 0
var result = Int.max

for start in 0..<N {
    while sum < S && end < N {
        sum += arr[end]
        end += 1
        count += 1
    }
    //만약 합이 S 이상인 경우를 무시하고, 정확히 S일 때만 최소 길이를 갱신한다면, 그보다 큰 합을 가지면서 더 짧은 부분 배열이 나올 가능성을 놓치게 됨. S 이상인 경우에도 더 짧은 배열이 나올 수 있기 때문에, 합이 S 이상일 때 최소 길이를 갱신함
    if sum >= S {
        result = min(result, count)
    }
    sum -= arr[start]
    count -= 1
}
print(result == Int.max ? 0 : result)
