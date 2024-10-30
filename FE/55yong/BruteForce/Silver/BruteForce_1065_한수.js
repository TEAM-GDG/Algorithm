const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs").readFileSync(filepath).toString().trim();

let answer = 0;

for (let i = 1; i <= input; i++) {
  let temp = i.toString().split("").map(Number);
  if (i < 100) {
    answer++;
  } else {
    for (let j = temp.length - 1; j >= 0; j--) {
      if (temp[j - 1] - temp[j - 2] === temp[j] - temp[j - 1]) {
        answer++;
      }
    }
  }
  if (i === 1000) answer--;
}

console.log(answer);
