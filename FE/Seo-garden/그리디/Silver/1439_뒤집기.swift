let input = String(readLine()!)

var zero = input.split(separator: "0").count
var one = input.split(separator: "1").count

print(min(zero, one))
