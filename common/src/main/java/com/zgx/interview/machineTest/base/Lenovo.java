package com.zgx.interview.machineTest.base;

public class Lenovo
{
    public static void main(String[] args) {
        System.out.println(getValue(2));
    }

    public static int getValue(int i){
        int result = 0;
        switch (i){
            case 2:
                result = result +i;
              ;
            case 3:
                result = result + i *2;
                ;
            case  4:
                result = result + i * 3;
                 ;
        }

        return result;
    }
}
