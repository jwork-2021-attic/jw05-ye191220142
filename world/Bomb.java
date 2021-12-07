package world;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import asciiPanel.AsciiPanel;

public class Bomb extends Creature {

    public Bomb(World world, char glyph, Color color, int attack) {
        super(world, glyph, color, 1, attack, 0, 0, 6, 0);
        count = 40;
    }

    @Override
    public void run() {
        try {
            while (count > 0) {
                TimeUnit.MILLISECONDS.sleep(100);
                count--;
                if (count <= 10) {
                    color = AsciiPanel.brightRed;
                } else if (count <= 20) {
                    color = AsciiPanel.brightMagenta;
                } else if (count <= 30) {
                    color = AsciiPanel.red;
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Creature other = world.creature(x - 1 + i, y - 1 + j);
                    if (other == null) {
                        continue;
                    } else if (other.type() <= 1) {
                        other.modifyHP(-attackValue);
                    } else if (other.type <= 5) {
                        world.remove(other);
                    } else if (other.type == 6) {
                        other.count = 0;
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        } finally {
            world.remove(this);
        }
    }
}