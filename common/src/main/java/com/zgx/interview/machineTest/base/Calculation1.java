package com.zgx.interview.machineTest.base;

import org.springframework.expression.spel.ast.Operator;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

public class Calculation1 {
    public static void main(String[] args) {
        String computeExpr = "1 + 5 * 6 + 3 * (2 + 3*2+2-1+3*3) + 10/5 - 6*1";
        Calculation1 test = new Calculation1();
        double result1 = test.computeWithVector(computeExpr);
//        double result2 = test.computeWithStack(computeExpr);
        System.out.println(result1 + "=======" /*+ result2*/);
    }



    public double computeWithVector(String computeRegex){
        StringTokenizer tokenizer= new StringTokenizer(computeRegex, "+-*/()", true);
        Vector<Double> nums = new Vector<>();
        Vector<Operator> operators = new Vector<>();
        Map<String, Operator> computeOpt = this.getCompute();
        Operator curOperator;
        String curEle;
        while(tokenizer.hasMoreTokens()){
             curEle = tokenizer.nextToken().trim();
             //判断是否为数字
            if(isNum(curEle)){
                nums.add(Double.valueOf(curEle));
            }
            //判断是否为运算符(不是运算符视为括号)
            else
            {
                 curOperator = computeOpt.get(curEle);
                if(curOperator != null){
                    if(!operators.isEmpty() &&   operators.lastElement().priority() >=curOperator.priority()){
                        compute(nums,operators);
                    }
                    operators.add(curOperator);
                }else{
                    //括号操作，优先计算括号李曼的内容
                    if("(".equals(curEle)){
                        operators.add(Operator.BRACKETS);
                    }else{
                        while(!operators.lastElement().equals(Operator.BRACKETS)){
                            compute(nums,operators);
                        }
                        //移除左边括号
                        operators.remove(operators.size() -1);
                    }

                }

            }
        }
        while (!operators.isEmpty()){
            compute(nums,operators);
        }
        return nums.firstElement();
    }

    /**
     * 取nums中的最后两个数字，operators中的最后一个运算符进行计算，然后将结果放到nums末端
     * @param nums
     * @param operators
     */
    private void compute(Vector<Double> nums,Vector<Operator> operators){
        Double num2 = nums.remove(nums.size() - 1);
        Double num1 = nums.remove(nums.size() - 1);
        Operator operator = operators.remove(operators.size() - 1);
        double computeResult = operator.compute(num1, num2);
        nums.add(computeResult);
    }



    /**
     * 判断一个字符串是否为数字类型
     * @param str
     * @return
     */
    private boolean isNum(String str){
        String regex = "^\\d+(\\.\\d+)?$";
        return Pattern.matches(regex,str);
    }


    /**
     * 获取运算操作符
     * @return
     */
    private  Map<String,Operator> getCompute(){
        return new HashMap<String,Operator>()
        {
            {

                put("+",Operator.PLUS);
                put("-",Operator.MINUS);
                put("*",Operator.MULTIPLY);
                put("/",Operator.DIVIDE);
            }
        };
    }

    private enum  Operator{
        //加
        PLUS{
            @Override
            public int priority() {
                return 1;
            }

            @Override
            public double compute(double num1, double num2) {
                return num1 + num2;
            }
        },
        //减
        MINUS{
            @Override
            public int priority() {
                return 1;
            }

            @Override
            public double compute(double num1, double num2) {
                return num1 -num2;
            }
        },
        //乘
        MULTIPLY{
            @Override
            public int priority() {
                return 2;
            }

            @Override
            public double compute(double num1, double num2) {
                return num1*num2;
            }
        },
        //除
        DIVIDE{
            @Override
            public int priority() {
                return 2;
            }

            @Override
            public double compute(double num1, double num2) {
                return num1/num2;
            }
        },
        //括号
        BRACKETS{
            @Override
            public int priority() {
                return 0;
            }

            @Override
            public double compute(double num1, double num2) {
                return 0;
            }
        }
        ;

        public abstract int priority();
        public abstract double compute(double num1,double num2);
        }
}
