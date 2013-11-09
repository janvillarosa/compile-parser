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
public class Chapter {

    private ArrayList<Statement> statements = new ArrayList();
    private String chapterName;
    private int chapterID;

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }
}
