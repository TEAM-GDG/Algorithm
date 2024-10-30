const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/1931_회의실 배정/test.txt')
  .toString().trim().split('\n');

const [n, ...arr] = input;
let answer = 0;

// 각 회의의 시작 시간과 종료 시간을 파싱하여 배열로 만든 후, 종료 시간을 기준으로 오름차순 정렬
// 종료 시간이 같다면 시작 시간을 기준으로 오름차순 정렬하여 가장 빠르게 끝나는 회의를 먼저 선택
const times = arr
  .map((num) => num.split(' ').map((num) => +num))
  .sort((a, b) => {
    if (a[1] === b[1]) {
      return a[0] - b[0];
    } else {
      return a[1] - b[1];
    }
  });

// et는 마지막으로 선택된 회의의 종료 시간을 저장
let et = 0;
times.forEach((time) => {
  // 현재 회의의 시작 시간이 et보다 크거나 같으면, 이 회의를 선택
  if (time[0] >= et) {
    answer++; // 선택한 회의 수 증가
    et = time[1]; // et를 현재 회의의 종료 시간으로 업데이트
  }
});

console.log(answer);
