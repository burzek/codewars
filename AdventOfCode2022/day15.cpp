#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <set>
#include <algorithm>
#include <chrono>

#include <boost/algorithm/string.hpp>

using namespace std;


struct BSPair {
    long bx, by, sx, sy;
};

struct FTPair {
    long f, t;
};

void read_map(string file_name, vector<BSPair> &bs) {
    ifstream file(file_name);
    string line;

    while (getline(file, line)) {
        vector<string> split;
        boost::split(split, line, boost::is_any_of("=,:"));
        BSPair item {
            .bx = atol(split[5].c_str()),
            .by = atol(split[7].c_str()),
            .sx = atol(split[1].c_str()),
            .sy = atol(split[3].c_str())
        };
        bs.push_back(item);
    }
}

bool compare_function(FTPair p1, FTPair p2) {
    return p1.f == p2.f ? p1.t <= p2.t : p1.f <= p2.f;
}

#define ROW 2000000
void solve(string file_name) {
    auto start = std::chrono::high_resolution_clock::now();

    vector<BSPair> bs_vector;
    read_map(file_name, bs_vector);

    vector<FTPair> range;
    for (auto bs : bs_vector) {
        long hd = abs(bs.bx - bs.sx) + abs(bs.by - bs.sy);
        long ydist = abs(hd - abs(ROW - bs.sy));
        if (ydist > ROW) {
            continue;
        }
        range.push_back(FTPair {.f = bs.sx - ydist, .t = bs.sx + ydist});
    }

    sort(range.begin(), range.end(), compare_function);
    long min = range[0].f;
    long max = range[0].t;
    long  fcount = 0;
    for (auto item : range) {
        if (item.f <= max) {
            max = std::max(item.t, max);
        } else {
            fcount += (max - min + 1);
            min = item.f;
            max = item.t;
        }
    }
    fcount += (max - min + 1);
    
    auto finish = std::chrono::high_resolution_clock::now();

    cout << "Part1: " << fcount - 1 << endl;
    cout << " time: " << std::chrono::duration_cast<std::chrono::nanoseconds>(finish-start).count()/1000/1000 << " ms" << endl;
    
}

int main(int argc, char const *argv[])
{
    solve("day15.input");
    return 0;
}
