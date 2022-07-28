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

            actualRow = 0;
            actualCol = 0;
            System.out.println("k");
            while (true) {
                System.out.println("t");
                if (actualRow+1 <= matrix.length-1 && markedNodes[actualRow+1][actualCol] != 0 ){
                    if (markedNodes[actualRow][actualCol+1] != 0 && markedNodes[actualRow][actualCol+1] >= markedNodes[actualRow+1][actualCol] ||
                            (markedNodes[actualRow+1][actualCol] <= matrix[actualRow][actualCol+1])){
                        actualRow++;
                    } else if (markedNodes[actualRow][actualCol+1] == 0) {
                        actualRow++;
                    }
                }
                else if (actualCol+1 < matrix[0].length-1 && markedNodes[actualRow][actualCol+1] != 0){
                    if (markedNodes[actualRow+1][actualCol] != 0 && markedNodes[actualRow+1][actualCol] >= markedNodes[actualRow][actualCol+1] ||
                            (markedNodes[actualRow][actualCol+1] <= matrix[actualRow+1][actualCol])){
                        actualCol++;
                    } else if (markedNodes[actualRow+1][actualCol] == 0 ) {
                        actualCol++;
                    }
                } else {
                    break;
                }

            }
            if (actualRow < matrix.length-1 && actualCol < matrix[0].length-1)
                if ((matrix[actualRow+1][actualCol] + markedNodes[actualRow][actualCol] <=
                        matrix[actualRow][actualCol+1] + markedNodes[actualRow][actualCol])) {

                    markedNodes[actualRow+1][actualCol] = matrix[actualRow+1][actualCol] + markedNodes[actualRow][actualCol];

                } else {
                    markedNodes[actualRow][actualCol+1] = matrix[actualRow][actualCol+1] + markedNodes[actualRow][actualCol];
                }
            else {
                if (!(actualRow < matrix.length-1))
                    markedNodes[actualRow][actualCol+1] = matrix[actualRow][actualCol+1] + markedNodes[actualRow][actualCol];
                else if (!(actualCol < matrix[0].length-1))
                    markedNodes[actualRow+1][actualCol] = matrix[actualRow+1][actualCol] + markedNodes[actualRow][actualCol];


            }
        }
        System.out.println(actualRow + " , " + actualCol);
        System.out.println(markedNodes[actualRow][actualCol]);

    }


}
