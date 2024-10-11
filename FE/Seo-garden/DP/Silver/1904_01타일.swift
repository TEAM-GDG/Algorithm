let N = Int(readLine()!)!

var arr = [Int](repeating: 0, count: N+1)
var result = 0

func dp(_ num: Int) -> Int {
    if arr[num] != 0 {
        return arr[num]
    }
    if num == 0 {
        return 1
    }
    if num == 1 {
        return 1
    }
    if num == 2 {
        return 2
    }
    if num == 3 {
        return 3
    }
    if num == 4 {
        return 5
    }
    arr[num] = (dp(num-1) + dp(num-2)) % 15746
    return arr[num]
}

result = dp(N)
print(result)
