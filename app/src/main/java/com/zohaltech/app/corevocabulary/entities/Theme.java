package com.zohaltech.app.corevocabulary.entities;

import com.zohaltech.app.corevocabulary.classes.CoreSec;

import java.io.Serializable;

public class Theme implements Serializable {
    private int    id;
    private int    level;
    private String name;

    private String encName;
    private String iconName;

    public Theme(int id, int level, String name, String encName, String iconName) {
        this(level, name, encName, iconName);
        this.id = id;
    }

    public Theme(int level, String name, String encName, String iconName) {
        setLevel(level);
        setName(name);
        setIconName(iconName);
        setEncName(encName);
    }

    public Theme(int id, int level, String name, String iconName) {
        this(level, name, iconName);
        this.id = id;
    }

    public Theme(int level, String name, String iconName) {
        setLevel(level);
        setName(name);
        setIconName(iconName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getEncName() {
        return CoreSec.decrypt(encName);
    }

    public String getEncName1() {
        return encName;
    }

    public void setEncName(String encName) {
        this.encName = encName;
    }
}
