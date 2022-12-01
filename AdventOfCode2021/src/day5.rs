use std::cmp::max;
use std::collections::HashMap;

fn parse_input(input: String) -> Vec<((u16, u16), (u16, u16))> {
    let lines = input.lines();

    lines.map(|line| {
        let f_t: Vec<(&str, &str)> = line.split("->").map(|from_to| { from_to.split_at(from_to.find(",").unwrap()) }).collect::<Vec<(&str, &str)>>();
        let from = *f_t.get(0).unwrap();
        let to = *f_t.get(1).unwrap();
        (
            (from.0.trim().parse::<u16>().unwrap(), from.1.get(1..).unwrap().trim().parse::<u16>().unwrap()),
            (to.0.trim().parse::<u16>().unwrap(), to.1.get(1..).unwrap().trim().parse::<u16>().unwrap())
        )
    }).collect()
}

fn count_overlaps(data: Vec<((u16, u16), (u16, u16))>) -> usize {
    let mut map = HashMap::<(i16, i16), u8>::new();

    for from_to in data {   //(from row,col),(to row, col)
        let rf = from_to.0.0 as i16;
        let rt = from_to.1.0 as i16;
        let cf = from_to.0.1 as i16;
        let ct = from_to.1.1 as i16;

        let dr = (rt - rf).signum();    //row direction
        let dc = (ct - cf).signum();    //col direction
        let len = max((rt - rf).abs(), (ct - cf).abs());        //vent length

        for i in 0..len + 1 {
            *map.entry((rf + dr * i, cf + dc * i)).or_insert(0) += 1;   //add + 1 to key (row,col)
        }
    }

    map.values().filter(|v| *v >= &2u8).count()

}

pub fn day5_task1(input: String) {
    let mut from_to_vec = parse_input(input);

    //only horizontal or vertical lines
    from_to_vec = from_to_vec
        .iter()
        .filter(|p| p.0.0 == p.1.0 || p.0.1 == p.1.1)
        .map(|x| *x)
        .collect::<Vec<((u16, u16), (u16, u16))>>();

    let count = count_overlaps(from_to_vec);
    println!("day 5 task 1: {}", count);
}


pub fn day5_task2(input: String) {
    let from_to_vec = parse_input(input);
    let count = count_overlaps(from_to_vec);
    println!("day 5 task 2: {}", count);
}

