package Berkeley;

import java.util.ArrayList;
import java.util.List;

public class BerkeleyClock {
    private int masterTime;

    public BerkeleyClock(int masterTime) {
        this.masterTime = masterTime;
    }

    public synchronized int getMasterTime() {
        return masterTime;
    }

    public synchronized void setMasterTime(int newMasterTime) {
        this.masterTime = newMasterTime;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        BerkeleyClock masterClock = new BerkeleyClock(0);
        List<BerkeleyClock> slaveClocks = new ArrayList<>();

        // Creating 5 slave clocks
        for (int i = 0; i < 5; i++) {
            BerkeleyClock slaveClock = new BerkeleyClock(i * 10); // Initializing each slave clock with a different time
            slaveClocks.add(slaveClock);
        }

        // Sending time requests to slave clocks
        for (BerkeleyClock slaveClock : slaveClocks) {
            Thread thread = new Thread(() -> {
                int currentTime = slaveClock.getMasterTime(); // Current time of the master clock
                System.out.println("Slave clock with time " + currentTime + " received time request.");

                // Simulating network latency with sleep
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Sending current time to master clock
                masterClock.setMasterTime(currentTime);
                System.out.println("Slave clock updated master time to " + currentTime);
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
        int totalMasterTime = masterClock.getMasterTime();
        int numSlaveClocks = slaveClocks.size();
        for (BerkeleyClock slaveClock : slaveClocks) {
            totalMasterTime += slaveClock.getMasterTime();
        }
        int newMasterTime = totalMasterTime / (numSlaveClocks + 1); // Adding 1 for the master clock

        // Updating master clock with the new average time
        masterClock.setMasterTime(newMasterTime);
        System.out.println("Master clock updated to new time: " + newMasterTime);
    }
}
