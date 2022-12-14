#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <stack>
#include <deque>


using namespace std;
#define START_MARKER 100

void parse_line(string line, deque<deque<int>> &packet) {
    stack<int> s;
    int val = -1;

    int p = 0;
    while (p < line.size()) {
        if (line.at(p) == '[') { 
            p++;
            deque<int> sub;
            int val = -1;
            while (line.at(p) != '[' && line.at(p) != ']') {
                if (line.at(p) == ',') {
                    sub.push_back(val);
                    val = 0;
                } else {
                    val = (val == -1 ? (line.at(p) - '0') : (val * 10 + (line.at(p) - '0')));
                }
                p++;
                if (line.at(p) == ']') {
                    sub.push_back(val);
                }
            }
            if (!sub.empty()) {
                packet.push_back(sub);
            }
        } else {
            p++;
        }
    }

    //count empty


    cout << "parsed line " << line << endl;
    for (auto x : packet) {
        for (auto y : x) {
            cout << y << " ";
        }
        cout << "    ";
    }
    cout << endl;
    
}

bool ordered(deque<deque<int>> packet_1, deque<deque<int>> packet_2) {
    for(int i = 0; i< packet_1.size(); i++) {
        for (int j = 0; j < packet_1[i].size(); j++) {
            if (packet_2.size() > i && packet_2[i].size() > j) {
                if (packet_1[i][j] < packet_2[i][j]) {
                    return true;
                } else if (packet_1[i][j] > packet_2[i][j]) {
                    return false;
                }
            }
            //if packet_1 ran out of items, packet is ok
            if (packet_1[i].size() < packet_2[i].size()) {
                return true;
            }
        }
    }
    return false;
}

void solve(string file_name) {
    ifstream file(file_name);
    string line;

    int pair_index = 1;
    int sum = 0;
    do {
        deque<deque<int>> packet_1;
        deque<deque<int>> packet_2;
        getline(file, line);
        parse_line(line, packet_1);
        getline(file, line);
        parse_line(line, packet_2);
        if (ordered(packet_1, packet_2)) {
            sum += pair_index;
        }
        pair_index++;
        getline(file, line);    //empty line
    } while (!file.eof());
    cout << "Part1: " << sum << endl;
}


int main(int argc, char const *argv[])
{
    solve("day13.input");
    return 0;
}
