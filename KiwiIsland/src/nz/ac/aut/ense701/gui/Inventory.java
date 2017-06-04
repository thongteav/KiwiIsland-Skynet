/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import nz.ac.aut.ense701.gameModel.Food;
import nz.ac.aut.ense701.gameModel.Item;
import nz.ac.aut.ense701.gameModel.Tool;
import nz.ac.aut.ense701.gui.Assets;
import nz.ac.aut.ense701.gui.Text;

/**
 *
 * @author ZJ
 */
public class Inventory {
    private boolean active = false;
    private ArrayList<Item> items;

    private final int invX = 65, invY = 30, invListSpacing = 30;
    private final int invWidth = 512, invHeight = 384;
    private final int invListCenterY = invY + invHeight / 2 + 5, invListCenterX = invWidth / 2;
    private int invImageX, invImageY, invImageWidth = 64, invImageHeight = 64;
    private int selectedItem = 0;

    public Inventory() {
        items = new ArrayList<Item>();
        invImageX = invWidth * 22 / 25;
        invImageY = invHeight * 15 / 100;
    }

    public void update() {
        if (selectedItem < 0) {
            selectedItem = items.size() - 1;
        } else if (selectedItem >= items.size()) {
            selectedItem = 0;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);

        if (items.size() > 0) {
            for (int i = -5; i < 6; i++) {
                if (selectedItem + i < 0 || selectedItem + i >= items.size()) {
                    continue;
                }
                if (i == 0) {
                    Text.drawString(g, "> " + items.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, new Font("Serif", Font.PLAIN, 24));
                } else {
                    Text.drawString(g, items.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, new Font("Serif", Font.PLAIN, 24));
                }
            }
            
            Item item = items.get(selectedItem);
            if(item instanceof Tool){
                g.drawImage(Assets.toolMap.get(item.getName()), invImageX, invImageY, invImageWidth, invImageHeight, null);
            }
            else if (item instanceof Food) {
                g.drawImage(Assets.foodMap.get(item.getName()), invImageX, invImageY, invImageWidth, invImageHeight, null);
            }
        }       
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setItems(Collection<Item> items) {
        this.items = new ArrayList<Item>(items);
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

}
