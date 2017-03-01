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
package com.karuslabs.commons.menu;

import java.util.*;

import org.bukkit.inventory.*;


public abstract class Region implements InventoryHolder {
    
    protected Inventory inventory;
    protected Map<Integer, Button> buttons;
    protected Button defaultButton;
    
    
    public Region(Inventory inventory) {
        this(inventory, Button.CANCEL);
    }
    
    public Region(Inventory inventory, Button defaultButton) {
        this.inventory = inventory;
        buttons = new HashMap<>();
        this.defaultButton = defaultButton;
    }
    
    
    public abstract boolean within(int slot);
    
    
    public Region bind(ItemStack item, Button button, int... slots) {
        for (int slot : slots) {
            if (within(slot)) {
                inventory.setItem(slot, item);
                buttons.put(slot, button);
            }
        }
        return this;
    }
    
    public Region bind(ItemStack item, int... slots) {
        for (int slot : slots) {
            if (within(slot)) {
                inventory.setItem(slot, item);
            }
        }
        return this;
    }
    
    public Region bind(Button button, int... slots) {
        for (int slot : slots) {
            if (within(slot)) {
                buttons.put(slot, button);
            }
        }
        return this;
    }
    
    
    public Button getButton(int slot) {
        return buttons.getOrDefault(slot, defaultButton);
    }
    
    
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    
    public Map<Integer, Button> getButtons() {
        return buttons;
    }

    public Button getDefaultButton() {
        return defaultButton;
    }

    public void setDefaultButton(Button defaultButton) {
        this.defaultButton = defaultButton;
    }
    
}
