let N = Int(readLine()!)!
let input = readLine()!.split(separator: " ").compactMap{ Int($0) }

if N == 1 {
    print(1)        // 수열의 길이가 1인 경우, 결과는 1
} else {
    var increaseCount = 1
    var decreaseCount = 1
    var maxCount = 1
    
    for i in 1..<N {
        if input[i] > input[i - 1] {
            increaseCount += 1
            decreaseCount = 1
        } else if input[i] < input[i - 1] {
            decreaseCount += 1
            increaseCount = 1
        } else {
            // 연속된 숫자의 경우 두 카운트 모두 증가
            increaseCount += 1
            decreaseCount += 1
        }
        maxCount = max(maxCount, increaseCount, decreaseCount)
    }
    print(maxCount)
}

//아래의 코드는 반례도 다 맞는데 왜 틀렸다고 하는지 모르겠다.

let N = Int(readLine()!)!
let input = readLine()!.split(separator: " ").compactMap{ Int($0) }
var arr = [Int]()
var increaseCount = 2
var decreaseCount = 2
var countArr = [Int]()
arr.insert(contentsOf: input, at: 0)

var i = 0

while i <= N - 3 {
    if arr[i] >= arr[i+1] && arr[i+1] >= arr[i+2] {
        increaseCount += 1
        i += 1
    } else if arr[i] <= arr[i+1] && arr[i+1] <= arr[i+2] {
        decreaseCount += 1
        i += 1
    } else { i += 1 }
}

if N == 1 {
    print(1)
} else {
    print(max(increaseCount, decreaseCount))
}
