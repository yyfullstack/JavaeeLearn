package com.yckjsoft.javaee.listener.obj;

/**
 * Created by yong on 2016/11/22 0022.
 * Person(事件源)
 * @Description: 设计一个Person类作为事件源，这个类的对象的行为(比如吃饭、跑步)可以被其他的对象监听
 */
public class Person {
    //在Person类中定义一个PersonListener变量来记住传递进来的监听器
    private PersonListener listener;

    /**
     * 设计Person的行为：吃
     */
    public void eat() {
        if (listener != null) {
            /**
             * 调用监听器的doeat方法监听Person类对象eat(吃)这个动作，将事件对象Event传递给doeat方法，
             * 事件对象封装了事件源(Person)，new Event(this)中的this代表的就是事件源
             */
            listener.doeat(new Event(this));
        }
    }

    /**
     * 设计Person的行为：跑
     */
    public void run() {
        if (listener != null) {
            /**
             * 调用监听器的dorun方法监听Person类对象run(跑)这个动作，将事件对象Event传递给doeat方法，
             * 事件对象封装了事件源，new Event(this)中的this代表的就是事件源
             */
            listener.dorun(new Event(this));
        }
    }

    /**
     * 这个方法是用来注册对Person类对象的行为进行监听的监听器
     * @param listener
     */
    public void registerListener(PersonListener listener) {
        this.listener = listener;
    }
}

/**
 *  PersonListener(事件监听器)
 * @Description 设计Person类(事件源)的监听器接口
 */
interface PersonListener {
    /**
     * 这个方法是用来监听Person对象eat(吃)这个行为动作，
     * 当实现类实现doeat方法时就可以监听到Person类对象eat(吃)这个动作
     *
     * @param e
     */
    void doeat(Event e);

    /**
     * 这个方法是用来监听Person对象run(跑)这个行为动作，
     * 当实现类实现dorun方法时就可以监听到Person类对象run(跑)这个动作
     *
     * @param e
     */
    void dorun(Event e);
}

/**
 * Event(事件对象)
 * @Description 设计事件类，用来封装事件源
 */
class Event {
    //事件源(Person就是事件源)
    private Person source;

    public Event() {

    }

    public Event(Person source) {
        this.source = source;
    }

    /**
     * Getter for property 'source'.
     *
     * @return Value for property 'source'.
     */
    public Person getSource() {
        return source;
    }

    /**
     * Setter for property 'source'.
     *
     * @param source Value to set for property 'source'.
     */
    public void setSource(Person source) {
        this.source = source;
    }
}