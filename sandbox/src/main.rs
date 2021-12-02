use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    let mut depths = vec!["123".to_owned()]; // Fix This
    let mut counter = 0;

    // File hosts must exist in current path before this produces output
    if let Ok(lines) = read_lines("./src/input.txt") {
        // Consumes the iterator, returns an (Optional) String
        for line in lines {
            if let Ok(ip) = line {
                depths.push(ip);
                // println!("{}", ip);
            }
        }
    }
    println!("{}", "alsjd");

    // for depth in depths {
    //         println!("{}", depth);
    // }

    for (i, _depth) in depths.iter().enumerate() {
        if i == 0 {
            println!("{}", "Skip");

            // Do nothing
        } else if depths[i] > depths[i - 1] {
            counter += 1;
        }
    }

    println!("{}", "increases");

    println!("{}", counter);
    // counter += 1;

    // Ok(depths)
}

// The output is wrapped in a Result to allow matching on errors
// Returns an Iterator to the Reader of the lines of the file.
fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where
    P: AsRef<Path>,
{
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}
