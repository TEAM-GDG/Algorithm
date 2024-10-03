import Foundation

let n = Int(readLine()!)!
let a = readLine()!.split(separator: " ").map { Int($0)! }.sorted()
let sum = Int(readLine()!)!

var left = 0
var right = a.count - 1
var count = 0

while left < right {                //왼쪽 좌표와 오른쪽 좌표가 만날 때 까지
    if a[left] + a[right] == sum {  //sum 와 값이 동일하면
        count += 1
        left += 1                   //좌표 한칸씩 이동
        right -= 1
    } else if a[left] + a[right] < sum {
        left += 1
    } else if a[left] + a[right] > sum {
        right -= 1
    }
}

print(count)
