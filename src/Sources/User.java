/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

/**
 *
 * @author mmh
 */
public class User {
    private final int level;
    private final boolean addable;
    private final boolean editable;
    private final boolean viewable;
    private final boolean checkable;
    private final boolean supevisable;
    private final boolean confirmable;

    public User(int level, boolean addable, boolean editable, boolean viewable, boolean checkable, boolean supevisable, boolean confirmable) {
        this.level = level;
        this.addable = addable;
        this.editable = editable;
        this.viewable = viewable;
        this.checkable = checkable;
        this.supevisable = supevisable;
        this.confirmable = confirmable;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAddable() {
        return addable;
    }

    public boolean isEditable() {
        return editable;
    }

    public boolean isViewable() {
        return viewable;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public boolean isSupevisable() {
        return supevisable;
    }

    public boolean isConfirmable() {
        return confirmable;
    }
    
}
