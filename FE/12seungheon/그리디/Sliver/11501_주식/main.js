const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/11501_주식/test.txt').toString().trim().split('\n');

let T = Number(input[0]); // 첫 줄: 테스트 케이스의 개수
let results = []; // 각 테스트 케이스별 결과 저장 배열
let line = 1; // 입력 줄 인덱스

for (let i = 0; i < T; i++) {
    let N = Number(input[line]); // 주식 가격의 개수 (사용하지 않음)
    let prices = input[line + 1].split(' ').map(Number); // 주식 가격 배열
    line += 2; // 다음 테스트 케이스로 이동

    let maxPrice = 0;
    let profit = 0;

    // 주식 가격 배열을 뒤에서부터 순회하며 최대 이익 계산
    for (let j = prices.length - 1; j >= 0; j--) {
        if (prices[j] > maxPrice) {
            maxPrice = prices[j]; // 최대 가격 갱신
        } else {
            profit += maxPrice - prices[j]; // 최대 가격에서 현재 가격을 뺀 이익을 누적
        }
    }

    results.push(profit); // 계산된 이익을 결과 배열에 추가
}

console.log(results.join('\n')); // 각 테스트 케이스의 결과 출력
