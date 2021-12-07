package world;

import java.awt.Color;

public class Block extends Creature {

    public Block(World world, char glyph, Color color, int type) {
        super(world, glyph, color, 1, 0, 0, 0, type, 0);
    }
    
}