let N = Int(readLine()!)!
var count = 0
var result = 665

while true {
    result += 1
    let resultChar = String(result)
    var check = 0
    for i in resultChar {
        if i == "6" {
            check += 1
        } else {
            check = 0
        }

        if check == 3 {     //6이 연속으로 3개가 나온다면
            count += 1
        }
    }

    if N == count {
        print("\(result)")
        break
    }
}
