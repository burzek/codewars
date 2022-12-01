use std::fs;
use crate::day13::day13_task1;


//mod day1;
//mod day2;
//mod day3;
//mod day5;
// mod day6;
// mod day7;
// mod day9;
// mod day10;
mod day13;

fn main() {
    // day1_task1(read_input("src/inputs/day1.input"));
    // day1_task2(read_input("src/inputs/day1.input"));
    // day2_task1(read_input("src/inputs/day2.input"));
    // day2_task2(read_input("src/inputs/day2.input"));
    //day3_task1(read_input("src/inputs/day3.input"));
    //day3_task2(read_input("src/inputs/day3.input"));
    // day6_task1(read_input("src/inputs/day6.input"));
    // day6_task2(read_input("src/inputs/day6.input"));
    // day9_task1(read_input("src/inputs/day9.input"));
    // day9_task2(read_input("src/inputs/day9.input"));
    //day10_task1(read_input("src/inputs/day10.input"));
    //day10_task2(read_input("src/inputs/day10.input"));
    day13_task1(read_input("src/inputs/day13.input"));
}

pub fn read_input(file_name : &str) -> String {
    fs::read_to_string(file_name)
        .expect("Something went wrong reading the file")
}
