/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediatecode;

import java.util.ArrayList;

/**
 *
 * @author Louis
 */
public class Statement {

    private ArrayList<Command> commands = new ArrayList();

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
}
