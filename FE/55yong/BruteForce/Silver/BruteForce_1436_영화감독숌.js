const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const N = +require("fs").readFileSync(filepath).toString().trim();

let cnt = 0;
let num = 0;
const regExp = /\d*666\d*/;

while (N !== cnt) {
  num++;
  if (regExp.test(num)) {
    cnt++;
  }
}
console.log(num);
