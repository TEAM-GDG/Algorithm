const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

const s = input[0];
const t = input[1];

// 최대공배수 구하는 함수
const GCD = (a, b) => {
  while (b != 0) {
    let n = a % b;
    a = b;
    b = n;
  }
  return a;
};

// 최대공배수 구해서 변수에 담기
let gcd = GCD(s.length, t.length);

// 임시로 담을 temp 배열 생성
let temp = [];

// 문자열 s를 최대공배수만큼 자르기
for (let i = 0; i < s.length; i += gcd) {
  temp.push(s.substring(i, i + gcd));
}

// 문자열 t를 최대공배수만큼 자르기
for (let i = 0; i < t.length; i += gcd) {
  temp.push(t.substring(i, i + gcd));
}

// 자른 문자열을 모두 비교해서 모두 내용이 같으면 1, 아니면 0
for (let i = 0; i < temp.length; i++) {
  if (temp[i] !== temp[0]) {
    console.log(0);
    return 0;
  }
}

console.log(1);
