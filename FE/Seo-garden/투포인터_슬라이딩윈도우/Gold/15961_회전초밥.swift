import Foundation

final class FileIO {
    private let buffer: [UInt8]
    private var index: Int = 0

    init(fileHandle: FileHandle = FileHandle.standardInput) {
        buffer = Array(try! fileHandle.readToEnd()!) + [UInt8(0)]
    }

    @inline(__always) private func read() -> UInt8 {
        defer { index += 1 }
        return buffer[index]
    }

    @inline(__always) func readInt() -> Int {
        var sum = 0
        var now = read()
        var isPositive = true

        while now == 10 || now == 32 { now = read() }
        if now == 45 { isPositive.toggle(); now = read() }
        while now >= 48, now <= 57 {
            sum = sum * 10 + Int(now - 48)
            now = read()
        }

        return sum * (isPositive ? 1 : -1)
    }
}

let fileIO = FileIO()

let Ndkc = [fileIO.readInt(), fileIO.readInt(), fileIO.readInt(), fileIO.readInt()]
//Thanks to Rhyno
let N = Ndkc[0], d = Ndkc[1], k = Ndkc[2], c = Ndkc[3]
var arr = [Int](repeating: 0, count: N)
for item in 0..<N {
    arr[item] = fileIO.readInt()
}
arr.append(contentsOf: arr[0..<N-1])
var check = Array(repeating: 0, count: d + 1)
var left = 0
var count = 0
var result = 0

for right in 0..<2*N-k {
    check[arr[right]] += 1
    
    if check[arr[right]] == 1 {
        count += 1
    }

    if right-left+1 > k {               // 3. 슬라이딩 윈도우의 크기가 k 를 초과하는 경우, left 를 이동시켜 윈도우 크기를 유지
        check[arr[left]] -= 1
        
        if check[arr[left]] == 0 {      //만약 윈도우에서 제거된 초밥의 개수가 0이 되면, 초밥의 수를 감소시킴
            count -= 1
        }
        left += 1
    }

    if check[c] == 0 {                      // 4. 쿠폰 초밥(`c`)을 아직 먹지 않은 경우, 그 초밥을 추가로 고려하여 최대 가짓수를 갱신
        result = max(result, count+1)       // 현재 고유 초밥의 수 + 쿠폰 초밥을 추가로 먹을 수 있으므로 `count + 1`을 최대값으로 비교
    } else {
        result = max(result, count)         // 이미 쿠폰 초밥을 먹은 경우에는 `count`만으로 최대값을 비교
    }
}
print(result)
