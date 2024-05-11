package Berkeley;

import java.util.ArrayList;
import java.util.List;

public class BerkeleyClock {
    private int id;
    private int time;
    private int totalDiff; //Only Server

    public BerkeleyClock(int id, int masterTime) {
        this.id= id;
        this.time = masterTime;
        this.totalDiff= 0;
    }

    public int getId(){
        return id;
    }

    public synchronized int getTime() {
        return time;
    }

    public synchronized void setTime(int newMasterTime) {
        this.time = newMasterTime;
    }

    public synchronized int getTotalDifferenceTime() {
        return totalDiff;
    }

    public synchronized int addDifferenceTime(int clientTime) {
        int diffN= clientTime - this.time;
        this.totalDiff += (diffN);
        return diffN;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        BerkeleyClock serverClock = new BerkeleyClock(0,0);
        List<BerkeleyClock> clientClocks = new ArrayList<>();

        // Creando 5 nodosCliente
        for (int i = 0; i < 5; i++) {
            BerkeleyClock clientClock = new BerkeleyClock(i + 1,i * 5); //Iniciando cada nodo cliente
            clientClocks.add(clientClock);
        }

        // Sending time requests to slave clocks
        for (BerkeleyClock clientClock : clientClocks) {
            Thread thread = new Thread(new Runnable() {                
                @Override
                public void run(){
                    int currentTime = clientClock.getTime(); // Current time of the master clock
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Client clock id-" + clientClock.getId() + " with time " + currentTime + " received time request.");

                    // Simulating network latency with sleep
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Sending current time to master clock
                    int diffN= serverClock.addDifferenceTime(clientClock.getTime());
                    System.out.println("Thread " + Thread.currentThread().getId() +  ": Client clock id-" + clientClock.getId() + " difference is " + diffN);
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Waiting for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Calculating average time from slave clocks
        int serverTime = serverClock.getTime();
        int nclients = clientClocks.size();
        int diffAverage = serverClock.getTotalDifferenceTime() / (nclients + 1);
        for (BerkeleyClock clientClock : clientClocks) {
            int diffR= diffAverage - clientClock.getTime() + serverTime;
            clientClock.setTime(clientClock.getTime() + diffR);
            System.out.println("Client clock id-" + clientClock.getId() + " correction is " + diffR);
        }

        // Updating master clock with the new average time
        serverClock.setTime(serverClock.getTime() + diffAverage);
        System.out.println("Server clock updated to new time: " + serverClock.getTime());
    }
}
