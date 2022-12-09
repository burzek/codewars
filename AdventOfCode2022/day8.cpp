#include <fstream>
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

#define WE_VISIBLE 128
#define NS_VISIBLE 64

void solve(string inputFileName) {
    ifstream file(inputFileName);
    string line; 

    getline(file, line);
    int rows = atoi(line.c_str());
    getline(file, line);
    int cols = atoi(line.c_str());

    //map
    unsigned char **forrest = new unsigned char*[rows];
    int row = 0;
    while (getline(file, line)) {
        forrest[row] = new unsigned char[cols];
        for (int i = 0; i < cols; i++) {
            forrest[row][i] = line.at(i) - '0';
        }
        row++;
    }


    //scan w-e
    for (int i = 0; i < rows; i++) {
        int wm = -1;
        int em = -1;
        for (int j = 0; j < cols; j++) {
            if (!(forrest[i][j] & WE_VISIBLE) && forrest[i][j]  > wm) {
                wm = forrest[i][j];
                forrest[i][j] |= WE_VISIBLE;
            }
            if (!(forrest[i][cols - j - 1] & WE_VISIBLE) && forrest[i][cols - j - 1] > em) {
                em = forrest[i][cols - j - 1];
                forrest[i][cols - j - 1] |= WE_VISIBLE;
            }
        }
    }
    
    //scan n-s
    for (int i = 0; i < cols; i++) {
        int nm = -1;
        int sm = -1;
        for (int j = 0; j < rows; j++) {
            if (!(forrest[j][i] & NS_VISIBLE) && (forrest[j][i] & (NS_VISIBLE - 1)) > nm) {
                nm = forrest[j][i] & (NS_VISIBLE - 1);
                forrest[j][i] |= NS_VISIBLE;
            }
            if (!(forrest[rows - j - 1][i] & NS_VISIBLE) && (forrest[rows - j - 1][i] & (NS_VISIBLE - 1)) > sm) {
                sm = forrest[rows - j- 1][i] & (NS_VISIBLE - 1);
                forrest[rows - j - 1][i] |= NS_VISIBLE;
            }
        }
    }
 
    int count = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            count += (forrest[i][j] > 9 ? 1 : 0);
        }
    }

    cout << "part 1: " << count << endl;
    cout << "part 2: " << 0 << endl;
    

}



int main(int argc, char const *argv[])
{
    solve("day8.input");
    return 0;
}
