package com.Nexer;

import java.util.HashMap;
import java.util.Scanner;

public class ProblemA {
    public static HashMap<String, String> employees = new HashMap<String, String>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        sc.nextLine();
        String log = new String();
        for(int i = count; i > 0; i--){
            log += (sc.nextLine()) + "\n";
        }

        String[] lines = log.split("\n");
        for(String s: lines){
            checkAction(s);
        }
    }

    private static void checkAction(String s){
        String [] ss = s.split(" ", 2);
        String name = ss[1];
        if(s.contains("entry")){
            checkEmployee("entered", name);
        } else if(s.contains("exit")) {
            checkEmployee("exited", name);
        }
    }

    private static void checkEmployee(String action, String name){
        Boolean exists = employees.containsKey(name);
        if(exists) {
            if (employees.get(name).equals(action))
                System.out.println(name + " " + action + " (ANOMALY)");
            else{
                employees.replace(name, action);
                System.out.println(name + " " + action);
            }
        }
        else{
            employees.put(name, action);
            if(action.equals("exited"))
                System.out.println(name + " " + action + " (ANOMALY)");
            else
                System.out.println(name + " " + action);
        }
    }
}
