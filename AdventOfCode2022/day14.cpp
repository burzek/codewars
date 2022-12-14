#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <stack>
#include <deque>

#include <boost/algorithm/string.hpp>

using namespace std;

#define AIR '.'
#define WALL '#'
#define ROCK 'o'

#define MAP_SIZE 600
#define COL_SHIFT 300

int read_map(string file_name, char (&map)[][MAP_SIZE]) {
    ifstream file(file_name);
    string line;

    memset(map, AIR, sizeof(map[0][0]) * MAP_SIZE * MAP_SIZE);
    
    int max_r = 0;
    while (getline(file, line)) {
        vector<string> points;
        
        boost::split(points, line, boost::is_any_of(" -> "));
        points.erase(remove_if(points.begin(), points.end(), [](string x){return x.length() == 0;}), points.end());
        int from_r {0}, from_c {0}, to_r {0}, to_c {0};
        
        for (int pi = {0}; pi < points.size() - 1; pi++) {
            from_c = atoi(points[pi].substr(0, points[pi].find_first_of(',')).c_str());
            from_r = atoi(points[pi].substr(points[pi].find_first_of(',') + 1).c_str());
            to_c = atoi(points[pi + 1].substr(0, points[pi + 1].find_first_of(',')).c_str());
            to_r = atoi(points[pi + 1].substr(points[pi + 1].find_first_of(',') + 1).c_str());
            max_r = max(to_r, max_r);
            from_c > to_c ? swap(from_c, to_c) : void(0);
            from_r > to_r ? swap(from_r, to_r) : void(0);
            for (int r = from_r; r <= to_r; r++) {
                for (int c = from_c; c <= to_c; c++) {
                    map[r][c - COL_SHIFT] = WALL;
                }
            }
        }
    }

    //add bottom line
    for (int c = {0}; c < MAP_SIZE; c++) {
        map[max_r + 2][c] = WALL;
    }

    return max_r;
}

void solve(string file_name) {
    char map[MAP_SIZE][MAP_SIZE];
    int max_r = read_map(file_name, map);

    int rock_count_to_touch_floor = {0};
    int rock_count_to_source = {0};

    //simulate
    int rock_count = 0;
    while (rock_count_to_source == 0 || rock_count_to_touch_floor == 0) {
        rock_count++;
        int c = {500 - COL_SHIFT};
        int r = {0};
        bool falling = true;
        if (map[r][c] == ROCK) {
            rock_count_to_source = rock_count - 1;
            falling = false;
        } 
        while (falling) {
            if (r >= max_r && rock_count_to_touch_floor == 0) {
                rock_count_to_touch_floor = rock_count - 1;
            }
            if (map[r + 1][c] != WALL && map[r + 1][c] != ROCK) {
                r++;
            } else if (c > 0 && map[r + 1][c - 1] != WALL && map[r + 1][c - 1] != ROCK) {
                r++; c--;
            } else if (map[r + 1][c + 1] != WALL && map[r + 1][c + 1] != ROCK) {
                r++; c++;
            } else {
                falling = false;
                map[r][c] = ROCK;
            }
        }
    }
    
    cout << "Part1:" << rock_count_to_touch_floor << endl;
    cout << "Part2:" << rock_count_to_source << endl;

    
}


int main(int argc, char const *argv[])
{
    solve("day14.input");
    return 0;
}
