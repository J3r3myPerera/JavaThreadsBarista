package Barista;

import java.util.LinkedList;
import java.util.Queue;

class BaristaQueue {
    //shared queue for the barista and the customer where the orders are placed
    private final Queue<String> queue;
    private final int maxSize;

    //maximum order size is needed so that the queue would not be much more than the value
    public BaristaQueue(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public synchronized Queue<String> getQueue(){
        return queue;
    }

    //method for barista to take the order
    public synchronized String takeOrder() throws InterruptedException {
        //if the queue is empty prompting the customer to place the order
        while (queue.isEmpty()) {
            System.out.println("Queue is empty, please place your orders.");
            wait(1500);
        }
        //order id being prepared
        String orderId = queue.poll();
        System.out.println("Order " + orderId + " is being prepared for customer.");
        //notify the other threads
        notifyAll();
        return orderId;
    }

    //method for customer to take the order
    public synchronized void placeOrder(String orderId) throws InterruptedException {
        //no orders can be taken if the order queue is full
        while (queue.size() == maxSize) {
            System.out.println("Be patient we are full of orders.");
            wait();
        }
        //if order is placed the order id
        queue.add(orderId);
        System.out.println("Order :" + orderId + " is placed and is brewing.");
        //notify the other threads
        notifyAll();
    }
}
