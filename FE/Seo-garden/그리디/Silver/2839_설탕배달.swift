var input = Int(readLine()!)!
var count = Int.max

for x in 0...input / 5 {
    let remaining = input - (5 * x)
    if remaining % 3 == 0 {
        let y = remaining / 3
        count = min(count, x + y)
    }
}

if count == Int.max {
    print(-1)
} else {
    print(count)
}
