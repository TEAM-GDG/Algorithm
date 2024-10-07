const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Sliver/1003_피보나치 함수/test.txt').toString().trim().split('\n');

const T = parseInt(input[0]);  // 테스트 케이스 개수
const data = input.slice(1).map(Number);  // 각 테스트 케이스 값들

// 각 테스트 케이스에서 f(0)과 f(1)의 호출 횟수를 미리 계산하기 위한 DP 테이블
const max = Math.max(...data);  // 입력된 값들 중 가장 큰 값 찾기
const dp = Array.from({ length: max + 1 }, () => [0, 0]);  // DP 테이블 초기화

// f(0)과 f(1)에 대한 초기값 설정
dp[0] = [1, 0];  // f(0) 호출 시 f(0) 한 번, f(1) 호출 안됨
dp[1] = [0, 1];  // f(1) 호출 시 f(0) 호출 안되고 f(1) 한 번 호출됨

// DP를 사용해 각 값에 대해 f(0)과 f(1)의 호출 횟수 구하기
for (let i = 2; i <= max; i++) {
    dp[i][0] = dp[i - 1][0] + dp[i - 2][0];  // f(n)에서 f(0)의 호출 횟수
    dp[i][1] = dp[i - 1][1] + dp[i - 2][1];  // f(n)에서 f(1)의 호출 횟수
}

// 각 테스트 케이스에 대한 결과 출력
for (let i = 0; i < T; i++) {
    const n = data[i];
    console.log(dp[n][0], dp[n][1]);  // f(n)의 f(0) 호출 횟수와 f(1) 호출 횟수 출력
}
