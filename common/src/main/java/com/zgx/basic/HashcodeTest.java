package com.zgx.basic;


public class HashcodeTest {
    public static void main(String[] args) {
        int hash = 0;
        String s = "ok";
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.hashCode() + " "+sb.hashCode());

        String t = new String("ok");
        StringBuilder tb =new StringBuilder(s);
        System.out.println(t.hashCode()+"  "+tb.hashCode());

    }
}
