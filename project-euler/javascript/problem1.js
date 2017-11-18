var isDivisible = function(dividend, divisor) {
  if (dividend % divisor === 0) {
    return true;
  } else {
    return false;
  }
}


var main = function() {
  var  sum = 0;
  for (var i = 1; i < 1000; i++) {
    if (isDivisible(i, 3) || isDivisible(i, 5)) {
      sum = sum + i;
    }
  }
  return sum;
}

var output = main();
console.log(output);
