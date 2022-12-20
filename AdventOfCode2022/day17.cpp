#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <set>
#include <algorithm>
#include <chrono>
#include <map>
#include <boost/algorithm/string.hpp>

using namespace std;

bool is_filled(char shape[4], int r, int c) {
    return shape[r] & (8 >> c);
}


bool move(vector<uint> &lines, char* s, string jet, int &jp, int &ypos) {
    int sl = 0;
    bool can_move_x = true;
    bool can_move_y = true;
    int jet_dir = (jet.at(jp) == '<' ? -1 : 1);
    jp = (jp + 1) % jet.size();


    for (int sl = 3; sl >= 0; sl--) {
        if (s[sl] == 0) continue;

        if (jet_dir == -1) {
            can_move_x &= ((s[sl] & 64) == 0) &&
                (lines.size() == 0 || (ypos - sl >= lines.size()) || lines.at(ypos - sl) & ((s[sl] << 1) == 0));
        } else {
            can_move_x &= ((s[sl] & 1) == 0) &&
            (lines.size() == 0 || (ypos - sl >= lines.size()) || lines.at(ypos - sl) & ((s[sl] >> 1) == 0));
        }
    }

    if (can_move_x) {

        s[0] = (int)((float) s[0] * (jet_dir == 1 ? 0.5 : 2));
        s[1] = (int)((float) s[1] * (jet_dir == 1 ? 0.5 : 2));
        s[2] = (int)((float) s[2] * (jet_dir == 1 ? 0.5 : 2));
        s[3] = (int)((float) s[3] * (jet_dir == 1 ? 0.5 : 2));
    }


    can_move_y = (ypos - 3 > 0) && (ypos - 4 >= lines.size() || ((lines.at(ypos - 4) & s[3]) == 0));
    if (!can_move_y) {
        for (int i = 3; i >= 0; i--) {
            if (s[i] != 0) {
                if (ypos - i > (int) lines.size() - 1) {
                    lines.push_back(0);
                }
                lines[ypos - i] |= s[i];
            }
        }
    } else {
        ypos--;
    }
    
    return can_move_y;
}

void solve(string inputFileName) {

    ifstream file(inputFileName);
    string jet;
    getline(file, jet);

    char shapes[][4] = {
        {0, 0, 0, 30}, 
        {0, 8, 28, 8},
        {0, 4, 4, 28},
        {16, 16, 16, 16},
        {0, 0, 24, 24},
    };

    vector<uint> lines;
    
    int jp = 0;
    for (int rock = 0; rock < 2022; rock++) {
         int ypos = lines.size() + 6;
         char s[4];
         s[0] = shapes[rock % 5][0];
         s[1] = shapes[rock % 5][1];
         s[2] = shapes[rock % 5][2];
         s[3] = shapes[rock % 5][3];

         while(move(lines, &s[0], jet, jp, ypos));

        // for (int l = lines.size() - 1; l >= 0; l--) {
        //     cout << "|";
        //     for (int i = 6; i >= 0; i--) {
        //         cout << ((lines[l] & (1 << i)) != 0 ? "#" : ".");
        //     }
        //     cout << "|" << endl;
        // }
        // cout << "+-------+" << endl;

    }


    cout << "Part 1: " << lines.size() << endl;

}



int main(int argc, char const *argv[])
{
    solve("day17.input");
    return 0;
}
