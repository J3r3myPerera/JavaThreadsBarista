package Barista;

class Customer implements Runnable{
    private final BaristaQueue baristaQueue;
    private final String orderId;

    public Customer(BaristaQueue baristaQueue, String orderId){
        this.baristaQueue = baristaQueue;
        this.orderId = orderId;
    }
    //override the run method for customer to place the order
    @Override
    public void run() {
        try {
            baristaQueue.placeOrder(orderId);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Order " +orderId+ " was not placed successfully please try again.");
        }
    }
}
