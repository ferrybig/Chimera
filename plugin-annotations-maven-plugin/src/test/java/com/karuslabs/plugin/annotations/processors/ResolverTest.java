/*
 * The MIT License
 *
 * Copyright 2018 Karus Labs.
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
package com.karuslabs.plugin.annotations.processors;

import com.karuslabs.plugin.annotations.plugin.TestPlugin;

import java.util.*;

import org.bukkit.plugin.java.JavaPlugin;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ResolverTest {
    
    Resolver resolver = spy(new Resolver(asList(TestPlugin.class.getResource("").getPath())));
    
    
    @Test
    void resolve() {
        doReturn(singleton(TestPlugin.class)).when(resolver).load();
        
        assertEquals(TestPlugin.class, resolver.resolve());
    }
    
    @Test
    void resolve_empty_ThrowsException() {
        doReturn(EMPTY_SET).when(resolver).load();
        
        assertEquals(
            "Failed to find JavaPlugin subclass annotated with @Plugin",
            assertThrows(ProcessorException.class, resolver::resolve).getMessage()
        );
    }
    
    
    @Test
    void resolve_multiple_ThrowsException() {
        Set<Class<? extends JavaPlugin>> classes = new HashSet<>();
        classes.add(JavaPlugin.class);
        classes.add(TestPlugin.class);
        
        doReturn(classes).when(resolver).load();
        
        assertEquals(
            "Conflicting main classes, project must contain only 1 JavaPlugin subclass annotated with @Plugin",
            assertThrows(ProcessorException.class, resolver::resolve).getMessage()
        );
    }
    
    
    @Test
    void load() {
        Set<Class<? extends JavaPlugin>> classes = resolver.load();
        
        assertEquals(1, classes.size());
        assertEquals(TestPlugin.class, classes.toArray(new Class[0])[0]);
    }
    
}
