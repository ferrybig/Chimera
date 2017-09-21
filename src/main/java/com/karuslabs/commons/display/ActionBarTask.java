/*
 * The MIT License
 *
 * Copyright 2017 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.karuslabs.commons.display;

import com.karuslabs.commons.locale.Translation;

import java.util.Set;
import java.util.function.*;

import org.bukkit.entity.Player;

import static net.md_5.bungee.api.ChatMessageType.ACTION_BAR;
import static net.md_5.bungee.api.chat.TextComponent.fromLegacyText;


public class ActionBarTask extends BarTask<Set<Player>> {
    
    protected Set<Player> players;
    protected BiFunction<ActionBarTask, Player, String> function;
    
    
    public ActionBarTask(long iterations, Translation translation,Set<Player> players, BiFunction<ActionBarTask, Player, String> function) {
        super(iterations, translation);
        this.players = players;
        this.function = function;
    }
    
    
    @Override
    protected void process() {
        players.forEach(player -> player.spigot().sendMessage(ACTION_BAR, fromLegacyText(function.apply(this, player))));
    }
    
    @Override
    protected Set<Player> value() {
        return players;
    }
    
    
    public Set<Player> getPlayers() {
        return players;
    }
    
}