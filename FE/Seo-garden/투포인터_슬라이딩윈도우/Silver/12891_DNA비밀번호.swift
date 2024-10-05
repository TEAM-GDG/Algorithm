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


let S = fileIO.readInt()
let P = fileIO.readInt()

let DNA = Array(fileIO.readString())


var ACGT = [Int]()

for item in 0..<ACGT.count {
    ACGT[item] = fileIO.readInt()
}


let nucleotides = ["A", "C", "G", "T"]

// 현재 슬라이딩 윈도우 내에서의 A, C, G, T의 개수를 카운트하는 배열
var currentCount = [0, 0, 0, 0]

// 첫 번째 부분 문자열의 A, C, G, T 개수를 카운트
for i in 0..<P {
    switch DNA[i] {
    case "A":
        currentCount[0] += 1
    case "C":
        currentCount[1] += 1
    case "G":
        currentCount[2] += 1
    case "T":
        currentCount[3] += 1
    default:
        break
    }
}

// 현재 슬라이딩 윈도우 내의 A, C, G, T 개수가 조건을 만족하는지 확인하는 함수
func isValid() -> Bool {
    for i in 0..<4 {
        if currentCount[i] < ACGT[i] { // 필요한 개수보다 적으면 false 반환
            return false
        }
    }
    return true
}

var count = 0

// 첫 번째 부분 문자열이 조건을 만족하는지 확인하고 카운트를 증가
if isValid() {
    count += 1
}

// 슬라이딩 윈도우를 이용하여 문자열을 처리
for i in P..<S {
    // 슬라이딩 윈도우에서 빠지는 문자
    switch DNA[i - P] {
    case "A":
        currentCount[0] -= 1
    case "C":
        currentCount[1] -= 1
    case "G":
        currentCount[2] -= 1
    case "T":
        currentCount[3] -= 1
    default:
        break
    }

    // 슬라이딩 윈도우에 새로 추가되는 문자
    switch DNA[i] {
    case "A":
        currentCount[0] += 1
    case "C":
        currentCount[1] += 1
    case "G":
        currentCount[2] += 1
    case "T":
        currentCount[3] += 1
    default:
        break
    }

    // 조건을 만족하면 카운트를 증가
    if isValid() {
        count += 1
    }
}

// 최종 결과 출력
print(count)
