package com.paide.gui;

import com.paide.Main;
import com.paide.gui.layout.MainLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBar extends JMenuBar {

    private static final List<String> FILE_MENU_ITEMS = List.of("new", "open...", "", "save", "save.as...", "", "settings...", "", "close", "exit");
    private static final List<String> EDIT_MENU_ITEMS = List.of("undo", "redo", "", "cut", "copy", "paste", "delete", "", "select.all");
    private static final List<String> BUILD_MENU_ITEMS = List.of("assemble", "assemble.and.load");
    private static final List<String> RUN_MENU_ITEMS = List.of("run", "debug", "", "print.stack", "print.gpr", "print.fpr");
    private static final List<String> HELP_MENU_ITEMS = List.of("help...", "", "license...", "", "about...");

    private Map<String, JMenuItem> menuItems;
    private Map<String, JMenu> menus;

    public MenuBar() {
        menuItems = new HashMap<>();
        menus = new HashMap<>();
        menus.put("file", this.add(createMenu(Main.I18N.getString("file"), FILE_MENU_ITEMS, menuItems)));
        menus.put("edit", this.add(createMenu(Main.I18N.getString("edit"), EDIT_MENU_ITEMS, menuItems)));
        menus.put("assemble", this.add(createMenu(Main.I18N.getString("assemble"), BUILD_MENU_ITEMS, menuItems)));
        menus.put("run", this.add(createMenu(Main.I18N.getString("run"), RUN_MENU_ITEMS, menuItems)));
        menus.put("help", this.add(createMenu(Main.I18N.getString("help"), HELP_MENU_ITEMS, menuItems)));
    }

    @org.jetbrains.annotations.NotNull
    private JMenu createMenu(String name, @NotNull List<String> menuItems, Map<String, JMenuItem> mappedMenuItems) {
        JMenu menu = new JMenu(name);
        menu.setFont(MainLayout.DEFAULT_FONT);
        for (String item : menuItems) {
            if(item.isEmpty())menu.addSeparator();
            else {
                JMenuItem menuItem = createMenuItem(Main.I18N.getString(item));
                mappedMenuItems.put(item.replace("...", ""), menuItem);
                menu.add(menuItem);
            }
        }
        return menu;
    }

    @NotNull
    private JMenuItem createMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setFont(MainLayout.DEFAULT_FONT);
        return menuItem;
    }

    public JMenuItem getMenuItem(String item) {
        return menuItems.get(item);
    }

    public JMenu getMenu(String menu) {
        return menus.get(menu);
    }
}