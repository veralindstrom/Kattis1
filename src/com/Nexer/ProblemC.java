package com.Nexer;

import java.util.HashMap;
import java.util.Scanner;

public class ProblemC {
    public static HashMap<String, String> definitions = new HashMap();
    public static boolean unknown = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String input = sc.nextLine();
            String [] inputArray = input.split(" ", 3);
            String command = inputArray[0];
            Integer sumPerCalculation = 0;
            String firstVariableOfInput = new String();
            switch (command) {
                case "def":
                    firstVariableOfInput = inputArray[1];
                    if (checkVariable(firstVariableOfInput))
                        definitions.remove(definitions.get(firstVariableOfInput));

                    definitions.put(inputArray[2], firstVariableOfInput);
                    definitions.put(firstVariableOfInput, inputArray[2]);
                    unknown = false;
                    continue;
                case "calc":
                    firstVariableOfInput = inputArray[1];
                    if (!checkVariable(firstVariableOfInput)) {
                        unknown = true;
                    } else {
                        sumPerCalculation += Integer.parseInt(definitions.get(firstVariableOfInput));

                        String restOfCalculations = inputArray[2];

                        String[] rest1 = restOfCalculations.split(" ", 3);
                        sumPerCalculation = calculate(rest1, sumPerCalculation);
                    }
                    break;
                case "clear":
                    definitions.clear();
                    continue;
            }

            StringBuilder output = buildOutput(inputArray);
            if(!definitions.containsKey(sumPerCalculation.toString()))
                unknown = true;
            if(!unknown){
                output.append(definitions.get(sumPerCalculation.toString()));
                System.out.println(output);
                continue;
            }

            output.append("unknown");
            System.out.println(output);
            unknown = false;
        }
    }

    private static Integer calculate(String[] rest, Integer sum){
        if(rest[0].equals("=")) {
            return sum;
        }
        if(checkVariable(rest[1])) {
            if (rest[0].equals("+")) {
                sum += Integer.parseInt(definitions.get(rest[1]));
            }
            else {
                sum -= Integer.parseInt(definitions.get(rest[1]));
            }
        } else {
            unknown = true;
        }
        String[] newRest = rest[2].split(" ", 3);
        return calculate(newRest, sum);
    }

    private static boolean checkVariable(String variable){
        return (definitions.containsKey(variable));
    }

    private static StringBuilder buildOutput(String [] inputArray){
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < inputArray.length; i++) {
            output.append(inputArray[i]).append(" ");
        }
        return output;
    }
}

