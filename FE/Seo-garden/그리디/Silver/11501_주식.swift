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
//Thanks to Rhyno

let fileIO = FileIO()
let T = fileIO.readInt()

for _ in 0..<T {
    let input = fileIO.readInt()
    var fund = [Int](repeating: 0, count: input)
    for i in 0..<input {
        fund[i] = fileIO.readInt()
    }
    
    var result = 0
    var maxPrice = 0
    
    // 배열의 마지막 요소부터 시작해서 최대값을 추적
    for j in (0..<input).reversed() {
        if fund[j] > maxPrice {
            maxPrice = fund[j]  // 현재 값이 maxPrice보다 크면 maxPrice 갱신
        } else {
            result += maxPrice - fund[j]  // maxPrice보다 작으면 이익을 계산
        }
    }
    
    print(result)
}
