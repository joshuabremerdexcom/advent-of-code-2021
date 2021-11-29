const fs = require("fs");

const fileContents = fs.readFileSync("./1/fixture.txt");

const depths = fileContents.asciiSlice().split("\n").map(Number);

let increases = 0;
for (i = 0; i < depths.length; i++) {
  if (i === 0) continue;
  if (depths[i] > depths[i - 1]) {
    increases++;
  }
}

console.log(`Increases: ${increases}`);
