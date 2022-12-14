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
#define TRACE 'x'

//160
//#define X_DIFF 490
#define X_DIFF 160

void clear_trace(unsigned char map[][200]) {
    for (int x = 0; x < 20; x++) {
        for (int y = 0; y < 20; y++) {
            if (map[y][x] == 'x') {
                map[y][x] = '.';
            }
        }
    }

}


void print_map(unsigned char map[][200], int fy) {
    for (int r = fy - 10; r < fy + 10; r++) {
        for (int c = 0; c < 150; c++) {
            cout << (map[r][c]);
        }
    cout << endl;
    }
    cout << "-------------------------------------------" << endl;

}

void solve(string file_name) {
    ifstream file(file_name);
    string line;

    unsigned char map[200][200];
    memset(map, AIR, sizeof(map[0][0]) * 200 * 200);

    
    int max_r = 0;
    while (getline(file, line)) {
        vector<string> points;
        
        boost::split(points, line, boost::is_any_of(" -> "));
        points.erase(remove_if(points.begin(), points.end(), [](string x){return x.length() == 0;}), points.end());
        int from_r {0}, from_c {0}, to_r {0}, to_c {0};
        
        for (int pi = 0; pi < points.size() - 1; pi++) {
            from_c = atoi(points[pi].substr(0, points[pi].find_first_of(',')).c_str());
            from_r = atoi(points[pi].substr(points[pi].find_first_of(',') + 1).c_str());
            to_c = atoi(points[pi + 1].substr(0, points[pi + 1].find_first_of(',')).c_str());
            to_r = atoi(points[pi + 1].substr(points[pi + 1].find_first_of(',') + 1).c_str());
            from_c > to_c ? swap(from_c, to_c) : void(0);
            from_r > to_r ? swap(from_r, to_r) : void(0);
            max_r = max(to_r, max_r);
            for (int r = from_r; r <= to_r; r++) {
                for (int c = from_c; c <= to_c; c++) {
                    map[r][c - X_DIFF] = WALL;
                }
            }
        }
    }


    print_map(map, max_r);
    for (int x = 0; x < 200; x++) {
     ///   map[max_r + 2][x] = WALL;
    }
    cout << endl << endl;
    print_map(map, max_r);

    
    int rock_count_to_touch_floor = 0;
    int rock_count_to_source = 0;

    //simulate
    int rock_count = 0;
    while (rock_count_to_source == 0 || rock_count_to_touch_floor == 0) {
        rock_count++;
        int c = 500 - X_DIFF;
        int r = 0;
        bool falling = true;
        while (falling) {
            // if (map[0][x] == ROCK) {
            //     rock_count_to_source = rock_count - 1;
            //     falling = false;
            // } 

            if (r == max_r) {
                if (rock_count_to_touch_floor == 0) {
                    rock_count_to_touch_floor = rock_count - 1;
                    cout << "PART1:" << rock_count_to_touch_floor << endl;
                }
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

            //cout << "y=" << y << ", max_y=" << max_y << endl;
            //print_map(map);
        }

        //clear_trace(map);
    }
    
    cout << "Part1:" << rock_count_to_touch_floor << endl;
    cout << "Part2:" << rock_count_to_source << endl;

    
}


int main(int argc, char const *argv[])
{
    solve("day14.input");
    return 0;
}
