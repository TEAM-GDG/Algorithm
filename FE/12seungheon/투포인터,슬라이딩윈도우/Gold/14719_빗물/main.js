const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Gold/14719_빗물/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const [H, W] = input[0].split(" ").map(Number);  
const height = input[1].split(" ").map(Number);  

let answer = 0;
let left = 0;
let right = height.length - 1;
let maxLeft = 0;
let maxRight = 0;

while (left < right) {
  // 왼쪽 기둥이 오른쪽 기둥보다 작으면 왼쪽 기둥 기준으로 계산
  if (height[left] < height[right]) {
    // 현재 왼쪽 기둥이 maxLeft보다 크면 maxLeft를 업데이트
    if (height[left] >= maxLeft) {
      maxLeft = height[left];
    } else {
      // maxLeft가 더 크면 물이 고이므로 maxLeft - height[left]만큼 물이 채워짐
      answer += maxLeft - height[left];
    }
    left++; // 왼쪽 포인터를 오른쪽으로 이동
  } else {
    // 오른쪽 기둥이 왼쪽 기둥보다 작거나 같으면 오른쪽 기둥 기준으로 계산
    if (height[right] >= maxRight) {
      maxRight = height[right];
    } else {
      // maxRight가 더 크면 물이 고이므로 maxRight - height[right]만큼 물이 채워짐
      answer += maxRight - height[right];
    }
    right--; // 오른쪽 포인터를 왼쪽으로 이동
  }
}

console.log(answer);
