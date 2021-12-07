package updater;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class RepaintTask implements Runnable {
    private JFrame jf;

    public RepaintTask(JFrame jf) {
        this.jf = jf;
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(1000 / 60);
                jf.repaint();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

}