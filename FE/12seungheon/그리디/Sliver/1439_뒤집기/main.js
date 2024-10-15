const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : 'FE/12seungheon/그리디/Sliver/1439_뒤집기/test.txt').toString().trim().split('\n');

let str = input[0];
let one = 0
let zero = 0

if (str[0] === '0') {
    zero++
} else {
    one++
}

for (i = 1; i < str.length; i++) {
    if (str[i] !== str[i - 1]) {
        if (str[i] === '0') {
            zero++
        }
        else {
        one++
        }
    }                                                   
}



console.log(Math.min(zero, one))