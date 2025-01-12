package Barista;

class Barista implements Runnable{
    private final BaristaQueue baristaQueue;

    public Barista(BaristaQueue baristaQueue){
        this.baristaQueue = baristaQueue;
    }

    //override the method for the barista to take the order
    @Override
    public void run(){
        try{
            while (true){
                String orderId = baristaQueue.takeOrder();
                Thread.sleep(2000);
                System.out.println("Your order " + orderId + " has been prepared!");
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Hold a moment we are having an issue.");
        }
    }
}
