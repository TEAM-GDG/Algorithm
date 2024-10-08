const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Sliver/9095_1, 2, 3 더하기/test.txt').toString().trim().split('\n').map(Number);

const T = input[0];  // 테스트 케이스 개수
const results = [];
const maxN = Math.max(...input.slice(1));  // 입력 중 가장 큰 숫자를 구함

let dp = new Array(maxN + 1).fill(0);  // 가장 큰 숫자까지 처리할 수 있는 dp 배열 생성
dp[1] = 1;  // 1을 만드는 방법
dp[2] = 2;  // 2를 만드는 방법
dp[3] = 4;  // 3을 만드는 방법

// 4 이상부터는 dp[i] = dp[i-1] + dp[i-2] + dp[i-3]로 계산
for (let i = 4; i <= maxN; i++) {
    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
}

for (let i = 1; i <= T; i++) {
    const n = input[i];
    results.push(dp[n]);  // 각 테스트 케이스의 결과를 저장
}

console.log(results.join('\n'));  // 결과 출력
