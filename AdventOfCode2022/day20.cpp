#DNF

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


struct Item {
    int val;
    Item* prev;
    Item* next;
};

vector<Item*> original;
Item* zero;


void move(Item *current, Item *p, Item *n) {
    current->prev->next = current->next;
    current->next->prev = current->prev;
    current->prev = p;
    current->next = n;
    n->prev = current;
    p->next = current;
}


void solve(string inputFileName) {


    ifstream file(inputFileName);
    string line;

    int count = 0;
    while (getline(file, line)) {
        Item *i = new Item();
        i->val = atoi(line.c_str());
        if (i->val == 0) {
            zero = i;
        }
        if (count > 0) {
            Item *prev = original.at(count - 1);
            prev->next = i;
            i->prev = prev;
        }
        count++;
        original.push_back(i);
    }
    original[0]->prev = original[count - 1];
    original[count - 1]->next = original[0];

    for (auto i = original.begin(); i != original.end(); i++) {
        Item *current = *i;
        Item *n = current->next;
        int shift = current->val % count;
        if (shift == 0) {
            continue;
        }

        if (shift > 0) {
            while (shift-- > 0) {
                n = n->next;
            }
        } else if (shift < 0) {
            while (shift++ < 0) {
                n = n->prev;
            }
            n = n->prev;
        }
        
        move(current, n->prev, n);
     }

    int sum = 0;
    for (int i = 0; i <= 3100; i++) {
        sum += ((i % 1000 == 0) ? zero->val : 0);
        zero = zero->next;
    }
    
   

    cout << "Part 1: " << sum << endl;

}





int main(int argc, char const *argv[])
{
    solve("day20.input");
    return 0;
}
