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
public class Command {

    private ArrayList<String> subjectVariable = new ArrayList();
    private String commandName;
    private String dialogue;
    private String branchingTitle;
    private String equalString;
    private String commandForJumpingChapter;
    private String objectVariable;
    private int startSize;
    private int endSize;
    private String xCoordinate;

    public ArrayList<String> getSubjectVariable() {
        return subjectVariable;
    }

    public void setSubjectVariable(ArrayList<String> subjectVariable) {
        this.subjectVariable = subjectVariable;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public String getBranchingTitle() {
        return branchingTitle;
    }

    public void setBranchingTitle(String branchingTitle) {
        this.branchingTitle = branchingTitle;
    }

    public String getCommandForJumpingChapter() {
        return commandForJumpingChapter;
    }

    public void setCommandForJumpingChapter(String commandForJumpingChapter) {
        this.commandForJumpingChapter = commandForJumpingChapter;
    }

    public String getObjectVariable() {
        return objectVariable;
    }

    public void setObjectVariable(String objectVariable) {
        this.objectVariable = objectVariable;
    }

    public int getStartSize() {
        return startSize;
    }

    public void setStartSize(int startSize) {
        this.startSize = startSize;
    }

    public int getEndSize() {
        return endSize;
    }

    public void setEndSize(int endSize) {
        this.endSize = endSize;
    }

    public String getEqualString() {
        return equalString;
    }

    public void setEqualString(String equalString) {
        this.equalString = equalString;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

}
