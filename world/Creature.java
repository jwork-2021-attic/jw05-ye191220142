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

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Aeranythe Echosong
 */
public class Creature implements Runnable {
    protected int count;
    protected int type;

    public int type() {
        return type;
    }

    protected World world;

    protected int x;

    public void setX(int x) {
        this.x = x;
    }

    public int x() {
        return x;
    }

    protected int y;

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    protected char glyph;

    public char glyph() {
        return this.glyph;
    }

    protected Color color;

    public Color color() {
        return this.color;
    }

    // private CreatureAI ai;

    // public void setAI(CreatureAI ai) {
    // this.ai = ai;
    // }

    protected int maxHP;

    public int maxHP() {
        return this.maxHP;
    }

    protected int hp;

    public int hp() {
        return this.hp;
    }

    public void modifyHP(int amount) {
        this.hp += amount;
        if (this.hp > maxHP)
            this.hp = maxHP;
        else if (this.hp < 0)
            this.hp = 0;

        if (this.hp < 1) {
            world.remove(this);
        }
    }

    protected int attackValue;

    public int attackValue() {
        return this.attackValue;
    }

    protected int defenseValue;

    public int defenseValue() {
        return this.defenseValue;
    }

    protected int visionRadius;

    public int visionRadius() {
        return this.visionRadius;
    }

    public boolean canSee(int wx, int wy) {
        // return ai.canSee(wx, wy);
        if ((x - wx) * (x - wx) + (y - wy) * (y - wy) > this.visionRadius() * this.visionRadius()) {
            return false;
        }
        for (Point p : new Line(x, y, wx, wy)) {
            if (this.tile(p.x, p.y).isGround() || (p.x == wx && p.y == wy)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public Tile tile(int wx, int wy) {
        return world.tile(wx, wy);
    }

    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }

    protected int money;

    public int money() {
        return money;
    }

    public void moveBy(int mx, int my) {
        Creature other = world.creature(x + mx, y + my);

        if (other == null) {
            if (world.tile(x + mx, y + my).isGround()) {
                this.setX(x + mx);
                this.setY(y + my);
            } else if (world.tile(x + mx, y + my).isDiggable()) {
                this.dig(x + mx, y + my);
            }
        } else if (other.type == 2) {
            world.remove(other);
            money += 10;
            if (world.tile(x + mx, y + my).isGround()) {
                this.setX(x + mx);
                this.setY(y + my);
            } else if (world.tile(x + mx, y + my).isDiggable()) {
                this.dig(x + mx, y + my);
            }
        }
    }

    public void attack(Creature other) {
        int damage = Math.max(1, this.attackValue() - other.defenseValue());
        // damage = (int) (Math.random() * damage) + 1;

        other.modifyHP(-damage);

        this.notify("You attack the '%s' for %d damage.", other.glyph, damage);
        other.notify("The '%s' attacks you for %d damage.", glyph, damage);
    }

    public void update() {
        // this.ai.onUpdate();
    }

    public boolean canEnter(int x, int y) {
        return world.tile(x, y).isGround();
    }

    public void notify(String message, Object... params) {
        // ai.onNotify(String.format(message, params));
    }

    public Creature(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius,
            int type, int money) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.visionRadius = visionRadius;
        this.type = type;
        this.money = money;
        this.count = 0;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
