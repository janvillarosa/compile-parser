/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediatecode;

import java.util.ArrayList;

/**
 *
 * @author janvillarosa
 */
public class ErrorCheck {

    private ObjectTracker tracker;
    private StartTranslation translate = new StartTranslation();
    private Chapter c;
    private Statement s;
    private Command comm;

    public ErrorCheck(ObjectTracker tracker) {
        System.out.println("\n--INTERMEDIATE CODE LOGS--");
        this.tracker = tracker;
        printAllObjects();
        printAllStatements();
    }

    public void checkCommands(String commandName) {
        if (commandName.equals("SAYS")) {

            boolean commMatch = false;

            for (int iterate = 0; iterate <= tracker.getListCharacters().size(); iterate++) {
                for (int l = 0; l <= tracker.getListCharacters().size() - 1; l++) {
                    if (tracker.getListCharacters().get(l).getName().equals(comm.getSubjectVariable().get(l))) {
                        commMatch = true;
                    }
                }
            }

            if (commMatch) {
                translate.say(comm.getSubjectVariable(), comm.getDialogue());
            } else {
                System.out.println("Translation Error: No character with that name");
            }

        }
    }

    public void checkChapters() {

        for (int i = 0; i < tracker.getListChapter().size(); i++) {
            c = tracker.getListChapter().get(i);

            for (int j = 0; j < c.getStatements().size(); j++) {
                Statement s = c.getStatements().get(j);

                for (int k = 0; k < s.getCommands().size(); k++) {
                    comm = s.getCommands().get(k);

                    checkCommands(comm.getCommandName());

                }
            }
        }
    }

    public void printAllObjects() {
        System.out.println("You have " + tracker.getListBackground().size() + " Background objects");
        System.out.println("You have " + tracker.getListChapter().size() + " Chapter objects");
        System.out.println("You have " + tracker.getListCharacters().size() + " Character objects");
        System.out.println("You have " + tracker.getListGraphic().size() + " Graphic objects");
        System.out.println("You have " + tracker.getListImage().size() + " Image objects");

        for (int i = 0; i < tracker.getListBackground().size(); i++) {
            System.out.println(tracker.getListBackground().get(i).getName());
        }
    }

    public void printAllStatements() {

        for (int i = 0; i < tracker.getListChapter().size(); i++) {
            Chapter c = tracker.getListChapter().get(i);
            System.out.println("Chapter " + c.getChapterName() + ":");
            for (int j = 0; j < c.getStatements().size(); j++) {
                Statement s = c.getStatements().get(j);
                System.out.print("Statement " + j + ":");
                for (int k = 0; k < s.getCommands().size(); k++) {
                    System.out.print(s.getCommands().get(k).getCommandName() + ",");
                }
                System.out.println(".");
            }

        }
    }
}
