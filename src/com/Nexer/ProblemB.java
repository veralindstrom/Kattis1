package com.Nexer;

import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int diskBlocksUsedByFile = 0;
        int diskBlocksUsedByPreviousFile = 0;
        int emptyDiskBlocksPerDisk;
        boolean isNotFirstDiskMap = false;

        StringBuilder output = new StringBuilder();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            int lineLength = line.length();
            if (line.isEmpty()) {
                diskBlocksUsedByPreviousFile = 0;
            }
            else if (lineLength == 1){
                output.append(line).append("\n");
            }
            else{
                if(isNotFirstDiskMap) {
                    output.append("\n");
                    isNotFirstDiskMap = true;
                }

                for(int i = 0; i < line.length(); i++){
                    Character diskBlock = line.charAt(i);
                    if(diskBlock.equals('*'))
                        diskBlocksUsedByFile++;
                }

                emptyDiskBlocksPerDisk = lineLength - diskBlocksUsedByFile - diskBlocksUsedByPreviousFile;

                output.append(appendTokens(".", emptyDiskBlocksPerDisk));
                output.append(appendTokens("*", diskBlocksUsedByFile));
                output.append(appendTokens(".", diskBlocksUsedByPreviousFile));

                diskBlocksUsedByPreviousFile += diskBlocksUsedByFile;
                diskBlocksUsedByFile = 0;
            }
            output.append("\n");
        }
        System.out.print(output.deleteCharAt(output.length()-1));
    }

    private static StringBuilder appendTokens(String token, int max){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < max; i++){
            output.append(token);
        }
        return output;
    }
}
