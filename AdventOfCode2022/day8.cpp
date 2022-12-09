#include <fstream>
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

// void print_map(unsigned char **f, unsigned char ** visible, int rows, int cols) {
//     for (int i = 0; i < rows; i++) {
//         for (int j = 0; j < cols; j++) {
//             cout << (visible[i][j] ? "_" : " ") << static_cast<char>(f[i][j]+ '0') << (visible[i][j] ? "_": " ") << " ";
//         }
//         cout << endl;
//     }
// };

void solve(string inputFileName) {
    ifstream file(inputFileName);
    string line; 

    getline(file, line);
    int rows = atoi(line.c_str());
    getline(file, line);
    int cols = atoi(line.c_str());

    //map
    unsigned char **forrest = new unsigned char*[rows];
    unsigned char **visible = new unsigned char*[rows];
    int row = 0;
    while (getline(file, line)) {
        forrest[row] = new unsigned char[cols];
        visible[row] = new unsigned char[cols];
        for (int i = 0; i < cols; i++) {
            forrest[row][i] = line.at(i) - '0';
            visible[row][i] = 0;
        }
        row++;
    }


    //scan w-e
    for (int i = 0; i < rows; i++) {
        int wm = -1;
        int em = -1;
        for (int j = 0; j < cols; j++) {
            if (!visible[i][j] && forrest[i][j] > wm) {
                wm = forrest[i][j];
                visible[i][j] = 1;
            }
            if (!visible[i][cols - j - 1] && forrest[i][cols - j - 1] > em) {
                em = forrest[i][cols - j - 1];
                visible[i][cols - j - 1] = 1;
            }
        }
    }
    
    //scan n-s
    for (int i = 0; i < cols; i++) {
        int nm = -1;
        int sm = -1;
        for (int j = 0; j < rows; j++) {
            if (!(visible[j][i] & 2) && forrest[j][i] > nm) {
                nm = forrest[j][i];
                visible[j][i] |= 2;
            }
            if (!(visible[rows - j - 1][i] & 2) && forrest[rows - j - 1][i] > sm) {
                sm = forrest[rows - j- 1][i];
                visible[rows - j - 1][i] |= 2;
            }
        }
    }
 
    int count = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            count += (visible[i][j] != 0 ? 1 : 0);
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
