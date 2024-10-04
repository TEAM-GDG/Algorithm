import Foundation

let HW = readLine()!.split(separator: " ").map { Int($0)! }
let H = HW[0], W = HW[1]
let heights = readLine()!.split(separator: " ").map { Int($0)! }

// 두 포인터 초기화
var left = 0
var right = W - 1
var leftMax = heights[left]
var rightMax = heights[right]
var totalWater = 0

while left < right {        // 두 포인터가 만나기 전까지 반복
    if heights[left] < heights[right] {     // 왼쪽 높이가 오른쪽 높이보다 작은 경우
        left += 1  // 왼쪽 포인터를 오른쪽으로 이동
        leftMax = max(leftMax, heights[left])  // 현재 왼쪽 포인터 위치에서의 최대 높이 갱신
        totalWater += max(0, leftMax - heights[left])  // 현재 위치에서 고일 수 있는 물의 양 계산 후 추가
    } else {  // 오른쪽 높이가 왼쪽 높이보다 작거나 같은 경우
        right -= 1  // 오른쪽 포인터를 왼쪽으로 이동
        rightMax = max(rightMax, heights[right])  // 현재 오른쪽 포인터 위치에서의 최대 높이 갱신
        totalWater += max(0, rightMax - heights[right])  // 현재 위치에서 고일 수 있는 물의 양 계산 후 추가
    }
}

print(totalWater)
