package com.zgx.design_pattern.future;

public class RealData implements Data {
    protected  final String result;

    public RealData(String param) {
        //RealData的构造可能很慢，需要用户等待很久，这里使用sleep进行模拟
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(param);
            try {
                //这里使用sleep代替一个很慢的操作过程
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }

}
