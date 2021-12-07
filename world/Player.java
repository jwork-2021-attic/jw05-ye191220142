/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package world;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.ElementScanner6;

import asciiPanel.AsciiPanel;

import java.awt.Color;

import screen.*;

/**
 *
 * @author Aeranythe Echosong
 */
public class Player extends Creature {

    private Screen screen;

    private List<String> messages;

    private ExecutorService exec;

    public Player(Screen screen, World world, char glyph, Color color, int maxHP, int attack, int defense,
            int visionRadius, List<String> messages) {
        super(world, glyph, color, maxHP, attack, defense, visionRadius, 0, 100);
        this.messages = messages;
        this.screen = screen;
        exec = Executors.newCachedThreadPool();
    }

    @Override
    public void notify(String message, Object... params) {
        this.messages.add(String.format(message, params));
    }

    public void heal(int h) {
        if (hp > 0 && hp < maxHP && money >= 20) {
            hp = Math.min(maxHP, hp + h);
            money -= 20;
        }
    }

    public void setBomb() {
        if (money >= 50) {
            Bomb bomb = new Bomb(world, (char) 9, AsciiPanel.magenta, 5);
            world.addAtLocation(bomb, x, y);
            exec.execute(bomb);
            money -= 50;
        }
    }

    public void shoot(int dir) {
        if (money >= 10) {
            Bullet bullet;
            if (dir == 0 || dir == 2)
                bullet = new Bullet(world, (char) 124, AsciiPanel.green, 1, dir);
            else
                bullet = new Bullet(world, (char) 45, AsciiPanel.green, 1, dir);
            world.addAtLocation(bullet, x, y);
            exec.execute(bullet);
            money -= 10;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                if (hp <= 0) {
                    exec.shutdown();
                    screen = new LoseScreen();
                    screen.setScreen(screen);
                    world.remove(this);
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
}
