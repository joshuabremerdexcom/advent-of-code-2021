const fs = require("fs");

const fileContents = fs.readFileSync("./1/fixture.txt");

const depths = fileContents.asciiSlice().split("\n").map(Number);

function getWindowAmount(array, idx) {
  return array[idx - 1] + array[idx] + array[idx + 1];
}
let increases = 0;
for (i = 0; i < depths.length; i++) {
  if (i === 0) continue;
  if (i === depths.length) continue;
  if (getWindowAmount(depths, i) > getWindowAmount(depths, i - 1)) {
    increases++;
  }
}

console.log(`Increases: ${increases}`);
