package world;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Enemy extends Creature {

    private Player prey;

    public Enemy(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius,
            Player player) {
        super(world, glyph, color, maxHP, attack, defense, visionRadius, 1, 0);
        this.prey = player;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            while (hp > 0) {
                TimeUnit.MILLISECONDS.sleep(500);
                if (prey.x() > x) {
                    if (prey.y() > y) {
                        int dir = rand.nextInt(2);
                        if (dir == 0) {
                            Creature other = world.creature(x + 1, y);
                            if (other == null || other.type() == 2)
                                moveBy(1, 0);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x, y + 1);
                                if (other == null || other.type() == 2)
                                    moveBy(0, 1);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(-1, 0);
                                    } else
                                        moveBy(0, -1);
                                }
                            }
                        } else {
                            Creature other = world.creature(x, y + 1);
                            if (other == null || other.type() == 2)
                                moveBy(0, 1);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x + 1, y);
                                if (other == null || other.type() == 2)
                                    moveBy(1, 0);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(-1, 0);
                                    } else
                                        moveBy(0, -1);
                                }
                            }
                        }
                    }
                    else if (prey.y() < y) {
                        int dir = rand.nextInt(2);
                        if (dir == 0) {
                            Creature other = world.creature(x + 1, y);
                            if (other == null || other.type() == 2)
                                moveBy(1, 0);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x, y - 1);
                                if (other == null || other.type() == 2)
                                    moveBy(0, -1);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(-1, 0);
                                    } else
                                        moveBy(0, 1);
                                }
                            }
                        } else {
                            Creature other = world.creature(x, y - 1);
                            if (other == null || other.type() == 2)
                                moveBy(0, -1);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x + 1, y);
                                if (other == null || other.type() == 2)
                                    moveBy(1, 0);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(-1, 0);
                                    } else
                                        moveBy(0, 1);
                                }
                            }
                        }
                    } else {
                        Creature other = world.creature(x + 1, y);
                        if (other == null || other.type() == 2)
                            moveBy(1, 0);
                        else if (other.type() == 0)
                            this.attack(other);
                        else {
                            int dir = rand.nextInt(3);
                            if (dir == 0)
                                moveBy(-1, 0);
                            else if (dir == 1)
                                moveBy(0, 1);
                            else
                                moveBy(0, -1);
                        }
                    }
                } 
                else if (prey.x() < x) {
                    if (prey.y() > y) {
                        int dir = rand.nextInt(2);
                        if (dir == 0) {
                            Creature other = world.creature(x - 1, y);
                            if (other == null || other.type() == 2)
                                moveBy(-1, 0);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x, y + 1);
                                if (other == null || other.type() == 2)
                                    moveBy(0, 1);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(1, 0);
                                    } else
                                        moveBy(0, -1);
                                }
                            }
                        } else {
                            Creature other = world.creature(x, y + 1);
                            if (other == null || other.type() == 2)
                                moveBy(0, 1);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x - 1, y);
                                if (other == null || other.type() == 2)
                                    moveBy(-1, 0);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(1, 0);
                                    } else
                                        moveBy(0, -1);
                                }
                            }
                        }
                    }
                    else if (prey.y() < y) {
                        int dir = rand.nextInt(2);
                        if (dir == 0) {
                            Creature other = world.creature(x - 1, y);
                            if (other == null || other.type() == 2)
                                moveBy(-1, 0);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x, y - 1);
                                if (other == null || other.type() == 2)
                                    moveBy(0, -1);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(1, 0);
                                    } else
                                        moveBy(0, 1);
                                }
                            }
                        } else {
                            Creature other = world.creature(x, y - 1);
                            if (other == null || other.type() == 2)
                                moveBy(0, -1);
                            else if (other.type() == 0)
                                this.attack(other);
                            else {
                                other = world.creature(x - 1, y);
                                if (other == null || other.type() == 2)
                                    moveBy(-1, 0);
                                else if (other.type() == 0)
                                    this.attack(other);
                                else {
                                    dir = rand.nextInt(2);
                                    if (dir == 0) {
                                        moveBy(1, 0);
                                    } else
                                        moveBy(0, 1);
                                }
                            }
                        }
                    } else {
                        Creature other = world.creature(x - 1, y);
                        if (other == null || other.type() == 2)
                            moveBy(-1, 0);
                        else if (other.type() == 0)
                            this.attack(other);
                        else {
                            int dir = rand.nextInt(3);
                            if (dir == 0)
                                moveBy(1, 0);
                            else if (dir == 1)
                                moveBy(0, 1);
                            else
                                moveBy(0, -1);
                        }
                    }
                } else if (prey.y() > y) {
                    Creature other = world.creature(x, y + 1);
                    if (other == null || other.type() == 2)
                        moveBy(0, 1);
                    else if (other.type() == 0)
                        this.attack(other);
                    else {
                        int dir = rand.nextInt(3);
                        if (dir == 0)
                            moveBy(1, 0);
                        else if (dir == 1)
                            moveBy(-1, 0);
                        else
                            moveBy(0, -1);
                    }
                } else {
                    Creature other = world.creature(x, y - 1);
                    if (other == null || other.type() == 2)
                        moveBy(0, -1);
                    else if (other.type() == 0)
                        this.attack(other);
                    else {
                        int dir = rand.nextInt(3);
                        if (dir == 0)
                            moveBy(1, 0);
                        else if (dir == 1)
                            moveBy(-1, 0);
                        else
                            moveBy(0, 1);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("interrputed");
        }
    }

}