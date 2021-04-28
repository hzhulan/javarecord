package com.hz.design.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pp_lan on 2020/3/25.
 * 使用场景：进行广播通知。如：拍卖出价，通知其他人进行出价
 */
public class ObserveDemo {

    static class Subject {
        private List<Observer> observers = new ArrayList<>();

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }

        public void attach(Observer observer) {
            observers.add(observer);
        }

        public void notifyAllObservers() {
            for (Observer observer : this.observers) {
                observer.update();
            }
        }
    }


    static abstract class Observer {
        public Observer(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        protected Subject subject;

        public abstract void update();
    }

    static class BinaryObserver extends Observer {

        public BinaryObserver(Subject subject) {
            super(subject);
        }

        @Override
        public void update() {
            System.out.println("binary update: state " + this.subject.getState());
        }
    }

    static class HexObserver extends Observer {

        public HexObserver(Subject subject) {
            super(subject);
        }

        @Override
        public void update() {
            System.out.println("Hex update: state " + this.subject.getState());
        }
    }

    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexObserver(subject);
        new BinaryObserver(subject);

        subject.setState(15);

    }
}
