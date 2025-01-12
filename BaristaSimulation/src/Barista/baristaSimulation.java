package Barista;

class baristaSimulation {
    public static void main(String args[]){

        //initialize the shared queue
        BaristaQueue baristaQueue = new BaristaQueue(3);
        //initialize the customers placing their orders
        Thread customer1 = new Thread(new Customer(baristaQueue, "Mocha"));
        Thread customer2 = new Thread(new Customer(baristaQueue, "Latte"));
        Thread customer3 = new Thread(new Customer(baristaQueue, "Iced Americano"));
        Thread customer4 = new Thread(new Customer(baristaQueue, "Cappuccino"));
        Thread customer5 = new Thread(new Customer(baristaQueue, "Latte"));
        Thread customer6 = new Thread(new Customer(baristaQueue, "Flat White"));

        //initialize the two baristas working and sharing the same order queue
        Thread barista1 = new Thread(new Barista(baristaQueue));
        Thread barista2 = new Thread(new Barista(baristaQueue));

        //starting the customer threads
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        customer5.start();
        customer6.start();

        //starting the barista threads
        barista1.start();
        barista2.start();

        try{
            //once the threads are done putting them to the waiting state
            customer1.join();
            customer2.join();
            customer3.join();
            customer4.join();
            customer5.join();
            customer6.join();

            //once the baristas are done putting them to the waiting state
            barista1.interrupt();
            barista2.interrupt();
            barista1.join();
            barista2.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Simulation Completed!");
    }
}
