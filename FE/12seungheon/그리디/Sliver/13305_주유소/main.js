const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/13305_주유소/test.txt').toString().trim().split('\n');

let N = Number(input[0]);  // 도시의 개수
let A = input[1].split(' ').map(BigInt);  // 도로의 길이 (BigInt로 변환)
let B = input[2].split(' ').map(BigInt);  // 주유소의 가격 (BigInt로 변환)

let money = BigInt(0);
let current = B[0];  // 첫 번째 주유소 가격을 BigInt로 초기화

for (let i = 0; i < N - 1; i++) {
    // 현재 주유소 가격으로 도로 주행 (BigInt 사용)
    money += current * A[i];
    
    // 다음 주유소가 더 저렴하면 주유 가격을 갱신
    if (current > B[i + 1]) {
        current = B[i + 1];
    }
}

console.log(String(money));  // BigInt는 반드시 String으로 변환해서 출력
