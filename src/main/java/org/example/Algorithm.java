package org.example;

import java.util.Arrays;

public class Algorithm {
    public void doDjikstra(int[][] matrix){
        int pathSum = 0;
        int pathSumTemp = 0;
        int[][] markedNodes = new int[matrix.length][matrix[0].length];

        for (int[] row: markedNodes)
            Arrays.fill(row, 0);

        markedNodes[0][0] = matrix[0][0];
        pathSum = matrix[0][0];
        int actualRow = 0;
        int actualCol = 0;

        while (markedNodes[matrix.length-1][matrix[0].length-1] == 0){
            boolean x = false;
            //step down
            actualRow = 0;
            //step right
            actualCol = 0;
            System.out.println("k");
            while (true) {
                x = false;
                System.out.println("t");
                //next step down is possible and there is already visited node
                if (actualRow+1 <= matrix.length-1 && markedNodes[actualRow+1][actualCol] != 0 ){
                    //to right from actual node is already visited node and is bigger weight then the one below
                    if (actualCol+1 <= matrix[0].length-1 && markedNodes[actualRow][actualCol+1] != 0 && markedNodes[actualRow][actualCol+1] >= markedNodes[actualRow+1][actualCol]){
                        actualRow++;
                        x = true;
                    //node by right side is not visited yet
                    } else if (actualCol+1 <= matrix[0].length - 1 && markedNodes[actualRow][actualCol+1] == 0 && matrix[actualRow][actualCol+1] + markedNodes[actualRow][actualCol] >= markedNodes[actualRow+1][actualCol]) {
                        actualRow++;
                        x = true;
                    } else if (!(actualCol+1 <= matrix[0].length-1)) {
                        actualRow++;
                        x = true;
                    }
                }
                //next step to right is possible and that node is already visited
                if (actualCol+1 <= matrix[0].length-1 && markedNodes[actualRow][actualCol+1] != 0){
                    //next step down is not possible or if it is then must not be in 0 value AND
                    if (actualRow+1 <= matrix.length-1 && markedNodes[actualRow+1][actualCol] != 0 && markedNodes[actualRow+1][actualCol] >= markedNodes[actualRow][actualCol+1]){
                        actualCol++;
                        x = true;
                        //node by right side is not visited yet
                    } else if (actualRow+1 <= matrix.length - 1 && markedNodes[actualRow+1][actualCol] == 0 && matrix[actualRow+1][actualCol]+markedNodes[actualRow][actualCol] >= markedNodes[actualRow][actualCol+1]) {
                        actualCol++;
                        x = true;
                    } else if (!(actualRow+1 <= matrix.length-1)) {
                        actualCol++;
                        x = true;
                    }
                } if (!x){
                    break;
                }

            }

            boolean visitedRight = false;
            boolean visitedDown = false;
            int nodeDown = 0;
            int nodeRight = 0;

            //visiting unvisited nodes, if both nodes are accessible
            if (actualRow < matrix.length-1 && actualCol < matrix[0].length-1) {
                //if node by right is more suitable
                if (markedNodes[actualRow + 1][actualCol] != 0)
                    nodeDown = markedNodes[actualRow + 1][actualCol];
                else
                    nodeDown = matrix[actualRow + 1][actualCol] + markedNodes[actualRow][actualCol];
                if (markedNodes[actualRow][actualCol + 1] != 0)
                    nodeRight = markedNodes[actualRow][actualCol + 1];
                else
                    nodeRight = matrix[actualRow][actualCol + 1] + markedNodes[actualRow][actualCol];

                if ((nodeDown <=
                        nodeRight)) {

                    markedNodes[actualRow + 1][actualCol] = nodeDown;
                    visitedDown = true;

                } else {
                    markedNodes[actualRow][actualCol + 1] = nodeRight;
                    visitedRight = true;
                }
            }
            else {
                //if node abow is out of bounds
                if (!(actualRow < matrix.length-1) && actualCol < matrix[0].length) {
                    markedNodes[actualRow][actualCol + 1] = matrix[actualRow][actualCol + 1] + markedNodes[actualRow][actualCol];
                    visitedRight = true;
                }
                else if (!(actualCol < matrix[0].length-1) && actualRow < matrix.length) {
                    markedNodes[actualRow + 1][actualCol] = matrix[actualRow + 1][actualCol] + markedNodes[actualRow][actualCol];
                    visitedDown = true;
                }


            }
            int actualWeight = 0;
            int previousWeight = 0;

            if (visitedRight) {
                previousWeight = markedNodes[actualRow][actualCol];
                actualCol++;
                actualWeight = markedNodes[actualRow][actualCol];
            } else if (visitedDown) {
                previousWeight = markedNodes[actualRow][actualCol];
                actualRow++;
                actualWeight = markedNodes[actualRow][actualCol];
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
        System.out.println(actualRow + " , " + actualCol);
        System.out.println(markedNodes[actualRow][actualCol]);

     }


}
