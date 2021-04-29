package model;

import notifiers.ISubscriber;

public class Student extends User implements ISubscriber<String> {

    @Override
    public void Handle(String notification) {
        System.out.println("Hi");
    }

    @Override
    public Class<String> getT() {
        return String.class;
    }
}
