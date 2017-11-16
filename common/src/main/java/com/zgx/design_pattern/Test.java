package com.zgx.design_pattern;

import java.util.regex.Pattern;

public class Test {
    static Pattern pattern = Pattern.compile(",");

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append("slkd,jfl,sk?jsdfsdf?lskjdflk,sdf,sdf,s,df,ss?sds");
        }
//        String str = sb.toString();
        String str = "slkd,jfl,sk?jsdfsdf?lskjdflk,sdf,sdf,s,df,ss?sds";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            if (str.contains(",")) {
                str.replaceAll(",", "");
            }
//            pattern.matcher(str).replaceAll("");
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(time);
    }
}
