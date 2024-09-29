const fs = require("fs");
const filepath = process.platform === "linux" ? "/dev/stdin" : "FE/12seungheon/구현/Silver/12871_무한 문자열/test.txt";
const input = fs.readFileSync(filepath).toString().trim().split("\n");

let s = input.shift().trim();
let t = input.shift().trim();

function GCD(a, b) {
  while (b != 0) {
    let n = a % b;
    a = b;
    b = n;
  }
  return a;
}

let gcd = GCD(s.length, t.length);

// s와 t를 최소공배수 길이로 늘려 비교
let lcm = (s.length * t.length) / gcd; 

let repeatedS = s.repeat(lcm / s.length);
let repeatedT = t.repeat(lcm / t.length);

// 두 문자열이 같다면 1, 다르다면 0 출력
if (repeatedS === repeatedT) {
  console.log(1);
} else {
  console.log(0);
}


// 이해
// 문자열 길이를 같게 해서 비교해서 맞으면 1 아니면 0


// 문제
// 문자열 s가 있을 때, f(s)는 s를 무한번 붙인 문자열로 정의한다. 예를 들어, s = "abc" 인 경우에 f(s) = "abcabcabcabc..."가 된다.

// 다른 문자열 s와 t가 있을 때, f(s)와 f(t)가 같은 문자열인 경우가 있다. 예를 들어서, s = "abc", t = "abcabc"인 경우에 f(s)와 f(t)는 같은 문자열을 만든다.

// s와 t가 주어졌을 때, f(s)와 f(t)가 같은 문자열을 만드는지 아닌지 구하는 프로그램을 작성하시오.

// 입력
// 첫째 줄에 s, 둘째 줄에 t가 주어진다. 두 문자열 s와 t의 길이는 50보다 작거나 같은 자연수이고, 알파벳 소문자로만 이루어져 있다. 

// 출력
// 첫째 줄에 f(s)와 f(t)가 같으면 1을, 다르면 0을 출력한다.