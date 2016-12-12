package com.yckjsoft.javaee.listener.obj;

/**
 * Created by yong on 2016/11/22 0022.
 * 测试Person类
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p = new Person();
        //注册监听p对象行为的监听器
        p.registerListener(new PersonListener() {
            //监听p吃东西这个行为
            public void doeat(Event e) {
                Person person = e.getSource();
                System.out.println(person + "在吃东西");
            }

            //监听p跑步这个行为
            public void dorun(Event e) {
                Person person = e.getSource();
                System.out.println(person + "在跑步");
            }
        });
        //p在吃东西
        p.eat();
        //p在跑步
        p.run();
    }
}
