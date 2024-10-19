let fs = require("fs");
let input = fs.readFileSync("/dev/stdin").toString().split("\n"); 

// "-"로 그룹을 나눈다
const group = input[0].split("-");

let answer = 0;
for (let i = 0; i < group.length; i++) {
    // "+"로 나눠 각 그룹의 합을 구한다
    let current = group[i]
        .split("+")
        .map(Number)
        .reduce((a, b) => a + b, 0);

    // 첫 번째 그룹은 더하고, 나머지는 뺀다
    if (i == 0) {
        answer += current;
    } else {
        answer -= current;
    }
}

console.log(answer);