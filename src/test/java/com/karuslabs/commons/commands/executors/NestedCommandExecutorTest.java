/*
 * Copyright (C) 2017 Karus Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.karuslabs.commons.commands.executors;

import com.karuslabs.commons.commands.Command;

import junitparams.*;
import org.bukkit.command.CommandSender;

import org.bukkit.plugin.Plugin;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;


@RunWith(JUnitParamsRunner.class)
public class NestedCommandExecutorTest {
    
    private static final String[] EMPTY = new String[0];
    
    private NestedCommandExecutor executor;
    private Command command;
    private Command subcommand;
    
    
    public NestedCommandExecutorTest() {
        executor = mock(NestedCommandExecutor.class, CALLS_REAL_METHODS);
        
        command = new Command("", mock(Plugin.class), null, null);
        command.setPermission("");
        
        subcommand = mock(Command.class);
        
        command.getNestedCommands().put("subcommand", subcommand);
    }
    
    
    @Test
    @Parameters
    public void execute(boolean hasPermission, String[] args, int commandTimes, int executeTimes, int messageTimes) {
        CommandSender sender = mock(CommandSender.class);
        when(sender.hasPermission(any(String.class))).thenReturn(hasPermission);
        
        executor.execute(sender, command, null, args);
        
        verify(subcommand, times(commandTimes)).execute(any(), any(), any());
        verify(executor, times(executeTimes)).onExecute(any(), any(), any(), any());
        verify(sender, times(messageTimes)).sendMessage((String) null);
    }
    
    public Object[] parametersForExecute() {
        return new Object[] {
            new Object[] {true, EMPTY, 0, 1, 0},
            new Object[] {true, new String[] {"argument"}, 0, 1, 0},
            new Object[] {true, new String[] {"subcommand"}, 1, 0, 0},
            new Object[] {false, new String[] {"subcommand"}, 1, 0, 0},
            new Object[] {false, EMPTY, 0, 0, 1}
        };
    }
    
}
