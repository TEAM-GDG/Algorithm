/* 초기 세팅 */
const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

/* 문제 풀이 */
// 문자열 가져와서 str에 저장
const str1 = input.shift();
const str2 = input.shift();

// 결과를 저장할 빈 문자열 변수 선언
let result1 = "";
let result2 = "";

// str1을 str2의 길이만큼 반복해서 result1에 붙이기
for (let i = 0; i < str2.length; i++) {
  result1 += str1;
}

// str2를 str1의 길이만큼 반복해서 result에 붙이기
for (let i = 0; i < str1.length; i++) {
  result2 += str2; // str2를 반복해서 result2에 추가
}

// 두 결과 문자열이 같은지 비교하고 같으면 1, 다르면 0 출력
console.log(result1 === result2 ? 1 : 0);
