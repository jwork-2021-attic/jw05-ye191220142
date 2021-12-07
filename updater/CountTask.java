package updater;

import java.util.concurrent.TimeUnit;

import screen.PlayScreen;
import world.Creature;
import world.World;

public class CountTask implements Runnable {
    private World world;
    private PlayScreen ps;

    public CountTask(World world, PlayScreen ps){
        this.world = world;
        this.ps = ps;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(50);
                int enemyCount = 0;
                for (Creature creature : world.getCreatures()) {
                    if (creature.type() == 1){
                        enemyCount++;
                    }
                }
                ps.checkEnemyCount(enemyCount);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
}