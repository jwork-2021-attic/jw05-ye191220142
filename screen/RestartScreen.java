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
package screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;


/**
 *
 * @author Aeranythe Echosong
 */
public abstract class RestartScreen implements Screen {

    protected Screen screen;

    @Override
    public void setScreen(Screen screen) {
        this.screen = screen;
    }    

    @Override
    public abstract void displayOutput(AsciiPanel terminal);

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                PlayScreen ps = new PlayScreen();
                return ps;
            default:
                return this;
        }
    }

}
