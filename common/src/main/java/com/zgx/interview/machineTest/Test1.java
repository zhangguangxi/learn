package com.zgx.interview.machineTest;

public class Test1 {
    public static void main(String[] args) {
        String s = "AA|BB|CC|555|DDD";
        //将所有数字替换为空(通过正则表达式匹配并去掉数字部分)
        String s1 = s.replaceAll("\\d+", "");
        //兼容处理"1"
        String s2 = s1.replaceAll("\\|+", "|");
        //将"|"替换为"],["
        String s3 = s2.replaceAll("\\|", "],[");
        //在字符串首尾加上"]"和"["
        String s4 = "["+s3+"]";
        System.out.println(s4);
    }
}

