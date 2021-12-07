package world;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class Bullet extends Creature {
    private int dir = 0;
    boolean b = false; // judge whether to break while

    public Bullet(World world, char glyph, Color color, int attack, int dir) {
        super(world, glyph, color, 1, attack, 0, 0, 5, 0);
        this.dir = dir;
    }

    @Override
    public void moveBy(int mx, int my) {
        Creature other = world.creature(x + mx, y + my);

        if (other == null || other.type == 2 || other.type == 5) {
            if (world.tile(x + mx, y + my).isGround()) {
                this.setX(x + mx);
                this.setY(y + my);
            } else if (world.tile(x + mx, y + my).isDiggable()) {
                this.dig(x + mx, y + my);
            }
        } else if (other.type <= 1) {
            other.modifyHP(-attackValue);
            b = true;
            world.remove(this);
        } else if (other.type() == 3) {
            b = true;
            world.remove(other);
            world.remove(this);
        } else if (other.type == 4) {
            b = true;
            world.remove(this);
        } else if (other.type == 6) {
            b = true;
            other.count = 0;
            world.remove(this);
        }
    }

    @Override
    public void run() {
        try {
            while (b == false) {
                TimeUnit.MILLISECONDS.sleep(100);
                Creature other;
                switch (dir) {
                    // W
                    case 0:
                        moveBy(0, -1);
                        break;
                    // A
                    case 1:
                        moveBy(-1, 0);
                        break;
                    // S
                    case 2:
                        moveBy(0, 1);
                        break;
                    // D
                    case 3:
                        moveBy(1, 0);
                        break;
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        } finally {
            world.remove(this);
        }
    }
}