package com.paide.gui;

import com.paide.gui.Layout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBar extends JMenuBar {

    private static final List<String> FILE_MENU_ITEMS = List.of("New", "Open...", "", "Save", "Save as...", "", "Settings...", "", "Close", "Exit");
    private static final List<String> EDIT_MENU_ITEMS = List.of("Undo", "Redo", "", "Cut", "Copy", "Paste", "Delete", "", "Select all");
    private static final List<String> BUILD_MENU_ITEMS = List.of("Assemble", "Assemble and load");
    private static final List<String> RUN_MENU_ITEMS = List.of("Run", "Debug");
    private static final List<String> HELP_MENU_ITEMS = List.of("Help...", "", "License...", "", "About...");

    private Map<String, JMenuItem> menuItems;
    private Map<String, JMenu> menus;

    public MenuBar() {
        menuItems = new HashMap<>();
        menus = new HashMap<>();
        menus.put("File", this.add(createMenu("File", FILE_MENU_ITEMS, menuItems)));
        menus.put("Edit", this.add(createMenu("Edit", EDIT_MENU_ITEMS, menuItems)));
        menus.put("Build", this.add(createMenu("Build", BUILD_MENU_ITEMS, menuItems)));
        menus.put("Run", this.add(createMenu("Run", RUN_MENU_ITEMS, menuItems)));
        menus.put("Help", this.add(createMenu("Help", HELP_MENU_ITEMS, menuItems)));
    }

    @org.jetbrains.annotations.NotNull
    private JMenu createMenu(String name, @NotNull List<String> menuItems, Map<String, JMenuItem> mappedMenuItems) {
        JMenu menu = new JMenu(name);
        menu.setFont(Layout.DEFAULT_FONT);
        for (String item : menuItems) {
            if(item.isEmpty())menu.addSeparator();
            else {
                JMenuItem menuItem = createMenuItem(item);
                mappedMenuItems.put(item.replace("...", ""), menuItem);
                menu.add(menuItem);
            }
        }
        return menu;
    }

    @NotNull
    private JMenuItem createMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setFont(Layout.DEFAULT_FONT);
        return menuItem;
    }

    public JMenuItem getMenuItem(String item) {
        return menuItems.get(item);
    }

    public JMenu getMenu(String menu) {
        return menus.get(menu);
    }
}