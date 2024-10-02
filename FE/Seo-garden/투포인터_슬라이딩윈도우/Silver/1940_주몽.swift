//구간합의 값이 M 보다 작으면 right 포인트를 증가시키고
//구간합의 값이 M 보다 크면 left 포인트를 증가시키고
//구간합의 값이 M 과 동일하면 count += 1 하고 구간합에서 left 값을 빼준다.
let N = Int(readLine()!)!
let M = Int(readLine()!)!
let inputArr = readLine()!.split(separator: " ").map { Int($0)! }.sorted()

var left = 0
var right = inputArr.count - 1          //배열의 마지막 값은 index - 1
var count = 0

while left < right {
    if inputArr[left] + inputArr[right] == M {
        count += 1
        left += 1
        right -= 1
        
        continue
    }
    
    if inputArr[left] + inputArr[right] < M {
        left += 1
    } else { right -= 1 }
}

print(count)
