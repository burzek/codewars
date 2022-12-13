#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <map>
#include <vector>
#include <list>
#include <climits>


using namespace std;


void add_edge(vector<unsigned> adj[], unsigned src, unsigned dest, char src_val, char dest_val) {
    if (src_val <= dest_val || src_val == dest_val + 1) {
        adj[src].push_back(dest);
    }
    if (dest_val <= src_val || dest_val == src_val + 1) {
        adj[dest].push_back(src);
    }
}


void BFS(vector<unsigned> adj[], vector<char> start_points, unsigned src, unsigned dest, unsigned v) {
    unsigned dist[v];
    bool visited[v];
    list<unsigned> queue;
 
    for (int i = 0; i < v; i++) {
        visited[i] = false;
        dist[i] = INT_MAX;
    }
 
    visited[src] = true;
    dist[src] = 0;
    queue.push_back(src);
    
    while (!queue.empty()) {
        unsigned u = queue.front();
        queue.pop_front();
        for (int i = 0; i < adj[u].size(); i++) {
            if (visited[adj[u][i]] == false) {
                visited[adj[u][i]] = true;
                dist[adj[u][i]] = dist[u] + 1;
                queue.push_back(adj[u][i]);
            }
        }
    }

    //min dist
    unsigned min = INT_MAX;
    for (int i = 0; i < start_points.size(); i++) {
        if (start_points[i] == 'a' && dist[i] < min) {
            min = dist[i];
        }
    }
    cout << "Part1: " << dist[dest] << endl;
    cout << "Part2: " << min << endl;

}

void read(string inputFileName,
                    vector<unsigned> adj[],
                    vector<char> &start_points,
                    unsigned rows, unsigned cols,
                    unsigned& start_vertex, unsigned& end_vertex) {

    ifstream file(inputFileName);
    string line;

    getline(file, line);
    start_vertex = -1;
    end_vertex = -1;
    
    for (int r = 0; r < rows; r++) {
        string line_p_1;
        getline(file, line_p_1);

        for (int c = 0; c < cols; c++) {
            int vertex_index = r * cols + c;        
            //search from E-S (its better for part 2)
            if (start_vertex == -1) {
                start_vertex = (line[c] == 'E' ? vertex_index : -1);
            }
            if (end_vertex == -1) {
                end_vertex = (line[c] == 'S' ? vertex_index : -1);  
            }
            line[c] = (line[c] == 'S' ? 'a' : line[c] == 'E' ? 'z' : line[c]);
            start_points.push_back(line[c]);

            if (c > 0) {
                add_edge(adj, vertex_index - 1 , vertex_index, line.at(c - 1), line.at(c));
            }
            if (line_p_1.length() > 0) {
                add_edge(adj, vertex_index, vertex_index + cols, line.at(c), line_p_1.at(c));
            }
        }
        line = line_p_1;
    }

}

void solve(string file_name, int rows, int cols) {
    unsigned start_index;
    unsigned end_index;
    vector<unsigned> adj[rows * cols];
    vector<char> start_points;
    read(file_name, adj, start_points, rows, cols, start_index, end_index);
    BFS(adj, start_points, start_index, end_index, cols * rows);
}


int main(int argc, char const *argv[])
{
    solve("day12.input", 41, 143);
    return 0;
}
