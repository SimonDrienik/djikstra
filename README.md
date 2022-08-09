# task-job-matrix-shortest-path
- input of this program is matrix in matrix.txt file
- output is sum of all vertexes that are included in shortest path

# way of finding shortest path
- choose lighter from one oncoming vertex
- make sum of actual vertex and the one oncoming vertex and put it to weight of that oncoming wertex
- go back until you are not at the begining of matrix and set that calculated weight to every previously visited vertex, except the very first one.
- go to the end of already marked path and always choose lighter visited vertex until you are not at the end of visited path
- continue with first step until and os on until you are not at the end of matrix
