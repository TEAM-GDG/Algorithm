/* 초기 세팅 */

// 파일 읽기 기능 사용
const fs = require("fs");

// 실행 환경에 따라 입력 경로 다르게 설정
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";

// 입력을 읽고, 문자열로 변환한 뒤, 공백을 제거하고, 각 문자를 배열로 나눔
const input = +fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀기 */
let result = 0; // 자릿수를 모두 합한 값을 저장하는 변수
let num = 1; // 현재 계산 중인 숫자 (1부터 input까지 증가)
let gijoon = 10; // 자릿수를 구분하는 기준 (10, 100, 1000, ...)
let jari = 1; // 현재 숫자의 자릿수 (1자리, 2자리, 3자리, ...)

while (num <= input) {
  // num이 input보다 작거나 같을 때까지 반복
  if (Math.floor(num / gijoon) == 0) {
    // num이 현재 자릿수 범위 안에 있으면
    num++; // num을 1 증가
    result += jari; // 현재 자릿수 만큼 result에 더함
  } else {
    // num이 현재 자릿수 범위를 넘으면
    gijoon *= 10; // 자릿수 기준을 10배로 늘림 (1자리 -> 2자리 -> 3자리, ...)
    jari++; // 자릿수 1 증가
  }
}

console.log(result);
