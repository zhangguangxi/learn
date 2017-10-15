package com.zgx.design_pattern.future;

public class Client {
    public Data request(final String queryStr){
        FutureData future = new FutureData();
        //realData的构建很慢,所以在单独的县城进行
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        //Future Data会被立即返回
        return future;
    }

    public static void main(String[] args) {
        Client client = new Client();
        //这里会被立即返回,因为得到的FutureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {
            //这里可以用一个sleep代替了对其他业务逻辑的处理，在处理业务罗的过程中，RealData被创建，从而利用了等待时间
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用真实的数据
        System.out.println("数据="+data.getResult());
    }
}
