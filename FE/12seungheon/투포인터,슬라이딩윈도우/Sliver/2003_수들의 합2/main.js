let input = require('fs').readFileSync("/dev/stdin").toString().split('\n');

// 입력 값 처리
let [N, M] = input[0].split(' ').map(Number);  // N: 수열의 길이, M: 목표 합
let data = input[1].split(' ').map(Number);    // 수열 데이터

let start = 0;
let end = 0;
let currentSum = 0;  // 현재 구간의 합
let cnt = 0;         // 조건을 만족하는 구간의 수

// 투 포인터 알고리즘 적용
while (end <= N) {
    if (currentSum === M) {
        cnt++;                     // 목표 합과 같으면 경우의 수 증가
        currentSum -= data[start];  // 시작 포인터 값을 빼고 구간을 축소
        start++;
    } else if (currentSum < M) {
        if (end === N) break;      // 끝 포인터가 배열 범위를 넘으면 종료
        currentSum += data[end];   // 구간을 확장
        end++;
    } else {
        currentSum -= data[start];  // 구간 합이 목표 값보다 크면 시작 포인터 이동 (구간 축소)
        start++;
    }
}

console.log(cnt);  // 목표 합을 만족하는 구간의 수 출력