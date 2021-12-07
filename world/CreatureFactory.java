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
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.ElementScanner6;

import asciiPanel.AsciiPanel;

import screen.*;

/**
 *
 * @author Aeranythe Echosong
 */
public class CreatureFactory implements Runnable {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Player newPlayer(Screen screen, List<String> messages) {
        Player player = new Player(screen, this.world, (char) 2, AsciiPanel.brightWhite, 10, 0, 0, 9, messages);
        world.addAtEmptyLocation(player);
        // new PlayerAI(player, messages);
        return player;
    }

    public Player newPlayerAtLocation(Screen screen, List<String> messages, int x, int y) {
        Player player = new Player(screen, this.world, (char) 2, AsciiPanel.brightWhite, 10, 0, 0, 9, messages);
        world.addAtLocation(player, x, y);
        // new PlayerAI(player, messages);
        return player;
    }

    public Enemy newEnemy(Player prey) {
        Enemy enemy = new Enemy(this.world, (char) 1, AsciiPanel.red, 5, 2, 0, 9, prey);
        world.addAtEmptyLocation(enemy);
        return enemy;
    }

    public Block newBlock(int mtype, int x, int y) {
        Block block;
        if (mtype == 1)
            block = new Block(this.world, (char) 176, AsciiPanel.cyan, 3);
        else
            block = new Block(this.world, (char) 178, AsciiPanel.brightBlack, 4);
        world.addAtLocation(block, x, y);
        return block;
    }

    public Coin newCoin() {
        Coin coin = new Coin(this.world, (char) 36, AsciiPanel.yellow);
        world.addAtEmptyLocation(coin);
        return coin;
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(500);
                this.newCoin();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
}
