const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/투포인터,슬라이딩윈도우/Gold/2470_두 용액/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const N = Number(input.shift());
const solution = input
  .shift()
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);

let left = 0;
let right = N - 1;
let tempSum = Infinity;
let answer = [];

while (left < right) {
  let sum = solution[left] + solution[right];

  if (sum < 0) {
    if (tempSum > -sum) {
      tempSum = -sum;
      answer = [solution[left], solution[right]];
    }
    left++; // sum이 음수이면 0에 더 가까워지도록 left 증가
  } else if (sum >= 0) {
    if (tempSum > sum) {
      tempSum = sum;
      answer = [solution[left], solution[right]];
    }
    right--; // sum이 양수이면 0에 더 가까워지도록 right 감소
  }
}

console.log(answer[0], answer[1])