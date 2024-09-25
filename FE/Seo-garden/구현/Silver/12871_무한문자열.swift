import Foundation

let input1 = Array(readLine()!)
let input2 = Array(readLine()!)

var result1 = input1        //입력받은 문자열을 result1
var result2 = input2        //입력받은 문자열을 result2

for i in input2 {
    result1.append(i)       //input2 의 문자열을 result1 에 넣고
}

for i in input1 {
    result2.append(i)       //input1 의 문자열을 result2 에 넣어서
}

if result1 == result2 {     //비교해서 맞으면 1 아니면 0
    print("1")
} else {
    print("0")
}
