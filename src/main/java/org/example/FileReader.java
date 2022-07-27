package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileReader {

    public void fileReader() throws IOException {

        List<String> lines = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new java.io.FileReader("src/main/resources/matrix.txt"));
        String line = reader.readLine();

        int countCollons = 0;
        for (int i = 0; i < (line.length() - 1); i++){
            if (line.substring(i,i+1).matches(","))
                countCollons++;
        }

        while (line != null) {
            assert false;
            lines.add(line);
            line = reader.readLine();
        }

        int[][] matrix = new int[lines.size()][countCollons+1];
        int linesCount = 0;

        for (String actualLine : lines) {

            int columnsCount = 0;
            String[] valuesString = null;
            valuesString  =  actualLine.split(",");

            for (String temp : valuesString){
                matrix[linesCount][columnsCount] = Integer.parseInt(temp);
                columnsCount++;
            }

            linesCount++;
        }
//efefefefeergrgrgrfrfrfrf
        System.out.println(Arrays.deepToString(matrix));
    }

}
