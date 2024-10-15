const filepath =
  process.platform === "linux" ? "/dev/stdin" : "../../input.txt";
const input = require("fs").readFileSync(filepath).toString().trim();

console.log(
  input.split("0") < input.split("1")
    ? input.split("0").filter((e) => e !== "").length
    : input.split("1").filter((e) => e !== "").length
);
