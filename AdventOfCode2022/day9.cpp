#include <fstream>
#include <iostream>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

void solve(string inputFileName) {
    ifstream file(inputFileName);
    string line; 

    int hx {0}, hy {0}, tx {0}, ty {0};

    set<string> positions;
    positions.insert("0,0");
    while (getline(file, line)) {
        char dir = line.at(0);
        int steps = atoi(line.substr(1).c_str());
        switch (dir) {
            case 'L':
                hx += (-1 * steps);
                break;
            case 'R':
                hx += steps;
                break;
            case 'U':
                hy += steps;
                break;
            case 'D':
                hy += (-1 * steps);
                break;
        }
        if (abs(hx - tx) > 1) {
            for (int i = min(hx, tx) + 1; i < max(hx, tx); i++) {
                positions.insert(to_string(i) + "," + to_string(hy));
            }
            tx = tx > hx ? hx + 1 : hx - 1;
            ty = hy;
        } else if (abs(hy - ty) > 1) {
            for (int i = min(hy, ty) + 1; i < max(hy, ty); i++) {
                positions.insert(to_string(hx) + "," + to_string(i));
            }
            tx = hx;
            ty = ty > hy ? hy + 1 : hy - 1; 
        }
            
    }


    cout << "part 1: " << positions.size() << endl;
    cout << "part 2: " << 0 << endl;
    

}



int main(int argc, char const *argv[])
{
    solve("day9.input");
    return 0;
}
