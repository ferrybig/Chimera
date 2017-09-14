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
package com.karuslabs.commons.command;

import com.karuslabs.commons.command.parser.*;

import org.bukkit.plugin.Plugin;

import static com.karuslabs.commons.configuration.Configurations.from;


public class Commands {
    
    private Plugin plugin;
    private ProxiedCommandMap map;
    
    
    public Commands(Plugin plugin) {
        this(plugin, new ProxiedCommandMap(plugin.getServer()));
    }
    
    public Commands(Plugin plugin, ProxiedCommandMap map) {
        this.plugin = plugin;
        this.map = map;
    }
    
    
    public void load(String path) {
        CompletionElement completion = new CompletionElement();
        TranslationElement translations = new TranslationElement(plugin.getDataFolder());
        
        load(path, new Parser(new CommandElement(plugin, completion, translations), completion, translations));
    }
    
    public void load(String path, Parser parser) {
        map.registerAll(plugin.getName(), parser.parse(from(getClass().getClassLoader().getResourceAsStream(path))));
    }
    
    
    public Command getCommand(String name) {
        return map.getCommand(name);
    }
    
    public ProxiedCommandMap getProxiedCommandMap() {
        return map;
    }
    
}
