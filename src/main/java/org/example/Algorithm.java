package org.example;

import java.util.Arrays;

public class Algorithm {
    public void doDjikstra(int[][] matrix){

        //matrix for marking visited nodes
        int[][] markedNodes = new int[matrix.length][matrix[0].length];
        for (int[] row: markedNodes)
            Arrays.fill(row, 0);
        markedNodes[0][0] = matrix[0][0];

        //variable for storing actual position in matrix
        int actualRow = 0;
        int actualCol = 0;

        //do while we are not in down right corner of matrix
        while (markedNodes[matrix.length-1][matrix[0].length-1] == 0){
            boolean x = false;
            //step down
            actualRow = 0;
            //step right
            actualCol = 0;

            //passing matrix by already visited nodes//
            //in this doWhile we are going trough matrix step by step on visited nodes until we are not at the end of visited nodes and after each step we
            // choose the cheaper path and at the end we are going to choose another best node
            do {
                x = false;
                //next step down is possible and there is already visited node
                if (actualRow + 1 <= matrix.length - 1 && markedNodes[actualRow + 1][actualCol] != 0) {
                    //to right from actual node is already visited node and is bigger weight then the one below
                    if (actualCol + 1 <= matrix[0].length - 1 &&
                            markedNodes[actualRow][actualCol + 1] != 0 &&
                            markedNodes[actualRow][actualCol + 1] >= markedNodes[actualRow + 1][actualCol]
                    ) {
                        actualRow++;
                        x = true;
                        //node by right side is not visited yet and  is bigger weight
                    } else if (actualCol + 1 <= matrix[0].length - 1 &&
                            markedNodes[actualRow][actualCol + 1] == 0 &&
                            matrix[actualRow][actualCol + 1] + markedNodes[actualRow][actualCol] >= markedNodes[actualRow + 1][actualCol]
                    ) {
                        actualRow++;
                        x = true;
                    //move to right is not possible
                    } else if (!(actualCol + 1 <= matrix[0].length - 1)) {
                        actualRow++;
                        x = true;
                    }
                }
                //next step to right is possible and that node is already visited
                //analogicly same logic as for movement down
                if (actualCol + 1 <= matrix[0].length - 1 && markedNodes[actualRow][actualCol + 1] != 0) {

                    if (actualRow + 1 <= matrix.length - 1 &&
                            markedNodes[actualRow + 1][actualCol] != 0 &&
                            markedNodes[actualRow + 1][actualCol] >= markedNodes[actualRow][actualCol + 1]
                    ) {
                        actualCol++;
                        x = true;

                    } else if (actualRow + 1 <= matrix.length - 1 &&
                            markedNodes[actualRow + 1][actualCol] == 0 &&
                            matrix[actualRow + 1][actualCol] + markedNodes[actualRow][actualCol] >= markedNodes[actualRow][actualCol + 1]
                    ) {
                        actualCol++;
                        x = true;

                    } else if (!(actualRow + 1 <= matrix.length - 1)) {
                        actualCol++;
                        x = true;
                    }
                }

            } while (x);

            //variable for actual made movement to right or left
            boolean visitedRight = false;
            boolean visitedDown = false;
            //weight of node that we just have visited
            int nodeDown = 0;
            int nodeRight = 0;

            //choosing new node after end of path already visited nodes
            //visiting unvisited nodes, if both nodes are accessible
            if (actualRow < matrix.length-1 && actualCol < matrix[0].length-1) {
                //if node below is already  visited: we can not go trough
                if (markedNodes[actualRow + 1][actualCol] != 0)
                    nodeDown = Integer.MAX_VALUE;
                else //if node below is not visited yet, we take weight from original matrix
                //we have to change weight of newly visited node to this: weight_of_previous_node + weight_Of_new_node, except the node[0][0]
                    nodeDown = matrix[actualRow + 1][actualCol] + markedNodes[actualRow][actualCol];

                //analogicly same for node right
                if (markedNodes[actualRow][actualCol + 1] != 0)
                    nodeRight = Integer.MAX_VALUE;
                else
                    nodeRight = matrix[actualRow][actualCol + 1] + markedNodes[actualRow][actualCol];

                //choosing best next node, the cheapest one
                //if node below is cheaper as the one by right side
                if ((nodeDown <=
                        nodeRight) && (nodeDown < Integer.MAX_VALUE)) {
                    markedNodes[actualRow + 1][actualCol] = nodeDown;
                    visitedDown = true;
                // else if node by right is cheaper
                } else if(nodeRight < Integer.MAX_VALUE) {
                    markedNodes[actualRow][actualCol + 1] = nodeRight;
                    visitedRight = true;
                }
            } //if one of next node is out of bounds
            else {
                //if node below is out of bounds
                if (!(actualRow < matrix.length-1) && actualCol < matrix[0].length && nodeRight < Integer.MAX_VALUE) {
                    markedNodes[actualRow][actualCol + 1] = matrix[actualRow][actualCol + 1] + markedNodes[actualRow][actualCol];
                    visitedRight = true;
                } //if node to right is out of bounds
                else if (!(actualCol < matrix[0].length-1) && actualRow < matrix.length && nodeDown < Integer.MAX_VALUE) {
                    markedNodes[actualRow + 1][actualCol] = matrix[actualRow + 1][actualCol] + markedNodes[actualRow][actualCol];
                    visitedDown = true;
                }


            }


            int actualWeight = 0;
            int previousWeight = 0;

            //now we need to change weight of every previous node that lead to newly choosen node to weight of the new one node
            if (visitedRight) {
                previousWeight = markedNodes[actualRow][actualCol];
                actualCol++;
                actualWeight = markedNodes[actualRow][actualCol];
            } else if (visitedDown) {
                previousWeight = markedNodes[actualRow][actualCol];
                actualRow++;
                actualWeight = markedNodes[actualRow][actualCol];
            } else {
                actualWeight = Integer.MAX_VALUE;

            }

            while (actualCol != 0 || actualRow != 0) {
                if (actualCol != 0 || actualRow != 0) {
                    if ((actualCol - 1 > 0 || (actualRow > 0 && actualCol - 1 >= 0)) && markedNodes[actualRow][actualCol - 1] == previousWeight) {
                        markedNodes[actualRow][actualCol - 1] = actualWeight;
                        actualCol--;
                    } else if ((actualRow - 1 > 0 || (actualCol > 0 && actualRow - 1 >= 0))  && markedNodes[actualRow - 1][actualCol] == previousWeight) {
                        markedNodes[actualRow - 1][actualCol] = actualWeight;
                        actualRow--;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(markedNodes[actualRow][actualCol]);

     }


}
