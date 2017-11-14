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
package com.karuslabs.commons.locale.resources;

import java.io.InputStream;
import javax.annotation.*;


/**
 * Represents a {@code Resource} which references files from which {@code ResourceBundle}s may be loaded.
 */
public interface Resource {
    
    /**
     * Returns an {@code InputStream} for the file with the specified name, or {@code null} if this {@code Resource}
     * contains no such file with the specified name.
     * 
     * @param name the file name
     * @return an InputStream for the file, or null if no such file exists
     */
    public @Nullable InputStream load(@Nonnull String name);
    
    /**
     * Checks if this {@code Resource} contains a file with the specified name.
     * 
     * @param name the file name
     * @return true if this Resource contains a file with the specified name; else false
     */
    public boolean exists(@Nonnull String name);
    
}
