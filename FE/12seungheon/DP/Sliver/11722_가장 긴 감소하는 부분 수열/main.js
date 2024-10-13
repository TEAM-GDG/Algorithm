const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/DP/Sliver/11722_가장 긴 감소하는 부분 수열/test.txt').toString().trim().split('\n');
let n = parseInt(input[0]);
let data = input[1].split(' ').map(Number);

let sum = new Array(n).fill(1);  // bookbook 배열을 1로 초기화 (최소 길이는 1이기 때문)

for (let i = 1; i < n; i++) {
    for (let j = 0; j < i; j++) {
        if (data[i] < data[j]) {
            sum[i] = Math.max(sum[i], sum[j] + 1);
        }
    }
}

console.log(Math.max(...sum));  // 가장 긴 증가하는 부분 수열의 길이 출력
