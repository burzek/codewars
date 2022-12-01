use std::borrow::BorrowMut;
use std::collections::{BTreeSet, HashSet, VecDeque};

pub fn day13_task1(input: String) {
    let mut points:Vec<(i16,i16)> = Vec::new();

    let mut was_fold = false;
    let mut was_first_fold = true;

    input.lines().map(|line| {
        //finish
        if line.contains(",") {
            let (x, y):(i16,i16) = line.split_once(",").map(|(s1, s2) | (s1.parse().unwrap(), s2.parse().unwrap())).unwrap();
            points.push((x,y));
        } else if line.starts_with("fold along x=") {
            let val:i16 = line[13..].parse().unwrap();
            points.iter_mut().filter(|(x, _)| *x > val).for_each(|v| v.0 = 2 * val - v.0);
            was_fold = true;
        } else if line.starts_with("fold along y=") {
            let val:i16 = line[13..].parse().unwrap();
            points.iter_mut().filter(|(_, y)| *y > val).for_each(|v| v.1 = 2 * val - v.1);
            was_fold = true;
        }
        points.sort();
        points.dedup();
        if was_fold && was_first_fold {
            println!("day 13 task 1:{}", points.len());
            was_first_fold = false;
        }
    }).count();

    let max_x = points.iter().map(|(x,_)| *x).max().unwrap();
    let max_y = points.iter().map(|(_, y)| *y).max().unwrap();
    for y in 0..max_y+1 {
        for x in 0..max_x+1 {
            print!("{}", if points.contains(&(x,y)) {"##"} else {"  "});
        }
        println!();
    }
}





