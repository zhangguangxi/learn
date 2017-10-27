package com.zgx.interview.machineTest;

public class Test2 {
    public static void main(String[] args) {
        String source = "AA|BB|CC|555|DDD|666|EEE";
        System.out.println(lineProcess(source));
    }


    public static String lineProcess(String line){
        StringBuilder newLine = new StringBuilder();
        String[] items = line.split("\\|");
        for (int i = 0; i < items.length; i++) {
            if(items[i].matches("\\d+")){
                items[i] =String.valueOf(Integer.valueOf(items[i])+1);
            }
            newLine.append(items[i]).append("|");
        }
        return newLine.deleteCharAt(newLine.length()-1).toString();
    }

}

