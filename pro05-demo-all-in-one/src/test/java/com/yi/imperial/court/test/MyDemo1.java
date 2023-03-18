package com.yi.imperial.court.test;

public class MyDemo1 {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private String content;

    public String getContent() {
        return threadLocal.get();
    }

    public void setContent(String content) {
        threadLocal.set(content );
    }

    public static void main(String[] args) {
        MyDemo1 demo = new MyDemo1();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("------------------");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
