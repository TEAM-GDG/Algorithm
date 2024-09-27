const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("");

// 10 크기의 0으로 가득찬 배열 생성
const arr = new Array(10).fill(0);

// 입력된 값 for문으로 순회하며 만약 1이 있다면 arr[1]에 +1 하는 식으로 값 구별
for (let i = 0; i < input.length; i++) {
  arr[input[i]]++;
}

// 만약 arr[9]에 값이 있다면 제거 후 arr[6]에 다 추가
if (arr[9]) {
  arr[6] += arr.pop();
}

// 그 후 arr[6]을 2로 나눈 후 소수점 올림
arr[6] = Math.ceil(arr[6] / 2);

// 최대값 찾기 (최대값이 방 번호를 만드는데 필요한 세트 갯수이니까)
const max = Math.max.apply(null, arr);

// 출력
console.log(max);
