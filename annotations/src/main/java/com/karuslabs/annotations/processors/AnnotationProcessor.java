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
package com.karuslabs.annotations.processors;

import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.util.Types;


public abstract class AnnotationProcessor extends AbstractProcessor {
    
    private static final TypeElement[] ARRAY = new TypeElement[0];
    
    protected Messager messager;
    protected Types types;
    
    
    @Override
    public synchronized void init(ProcessingEnvironment environment) {
        super.init(environment);
        messager = environment.getMessager();
        types = environment.getTypeUtils();
        load(environment);
    }
    
    protected void load(ProcessingEnvironment environment) {
        
    }
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        for (Element element : environment.getElementsAnnotatedWithAny(annotations.toArray(ARRAY))) {
            preprocess(element);
            process(element);
        }
        
        return false;
    }
    
    protected void preprocess(Element element) {
        
    }
    
    protected abstract void process(Element element);
    
}