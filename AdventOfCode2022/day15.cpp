#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <set>

#include <boost/algorithm/string.hpp>

using namespace std;


struct BSPair {
    long bx, by, sx, sy;
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

#define ROW 10
//#define ROW 2000000
void solve(string file_name) {
    vector<BSPair> bs_vector;
    read_map(file_name, bs_vector);

    set<long> beacon_forbidden = {};
    for (auto bs : bs_vector) {
        long hd = abs(bs.bx - bs.sx) + abs(bs.by - bs.sy);
        long ydist = abs(hd - abs(ROW - bs.sy));
        if (ydist > ROW) {
            continue;
        }
        cout << (bs.sx - ydist) << "...." << (bs.sx + ydist) << endl;
        for (long x = bs.sx - ydist; x <= bs.sx + ydist; x++) {
            beacon_forbidden.insert(x);
        }
    }


    cout << "Part1: " << beacon_forbidden.size() - 1 << endl;
    cout << "Part2: " << 0 << endl;


    
}


int main(int argc, char const *argv[])
{
    solve("day15.input");
    return 0;
}
