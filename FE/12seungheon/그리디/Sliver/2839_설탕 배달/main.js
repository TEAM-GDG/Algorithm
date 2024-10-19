const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/2839_설탕 배달/test.txt').toString().trim().split('\n');

let N = Number(input) 
let answer = 0 

while (true) { 
  if (N % 5 === 0) { // N이 5로 나누어떨어지면
    answer += N / 5 // 5로 나눈 몫을 더해줌 (최소 개수 구하기 위해)
    break 
  }

  N -= 3 // N이 5로 나누어떨어지지 않으면 N에서 3을 뺌
  answer += 1 // 3kg 봉지 하나 사용했으니 개수를 1 추가

  if (N < 0) { // N이 음수가 되면 (정확히 나눌 수 없는 경우)
    answer = -1 // -1을 출력하기 위해 answer를 -1로 설정
    break 
  }
}

console.log(answer) 