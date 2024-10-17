let NK = readLine()!.split(separator: " ").compactMap { Int($0)! }
let N = NK[0]
var K = NK[1]

var arr = [Int]()
var result = 0
var count = 0
for _ in 0..<N {
    let input = Int(readLine()!)!
    arr.append(input)
    arr.sort(by: >)         //내림차순으로 정렬해서 가장 큰 값부터 계산
}

for i in 0..<N {
    if K >= arr[i] {
        count += K / arr[i]
        K %= arr[i]
    }
}
print(count)
