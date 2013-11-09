/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediatecode;

/**
 *
 * @author janvillarosa
 */
public class StartTranslation {

    private ObjectTracker tracker;

    public StartTranslation(ObjectTracker tracker) {
        System.out.println("\n--INTERMEDIATE CODE LOGS--");
        this.tracker = tracker;
        printAllObjects();
        printAllStatements();
    }

    public void printAllObjects() {
        System.out.println("You have " + tracker.getListBackground().size() + " Background objects");
        System.out.println("You have " + tracker.getListChapter().size() + " Chapter objects");
        System.out.println("You have " + tracker.getListCharacters().size() + " Character objects");
        System.out.println("You have " + tracker.getListGraphic().size() + " Graphic objects");
        System.out.println("You have " + tracker.getListImage().size() + " Image objects");
        
        for(int i = 0; i<tracker.getListBackground().size(); i++){
            System.out.println(tracker.getListBackground().get(i).getName());
        }
    }
    
    public void printAllStatements() {
        
        for(int i = 0; i<tracker.getListChapter().size(); i++){
            Chapter c = tracker.getListChapter().get(i);
            System.out.println("Chapter " + c.getChapterName()+ ":");
            for(int j = 0; j<c.getStatements().size(); j++){
                Statement s = c.getStatements().get(j);
                System.out.print("Statement " + j+ ":");
                for(int k = 0; k<s.getCommands().size(); k++){
                    System.out.print(s.getCommands().get(k).getCommandName()+",");
                }
                System.out.println(".");
            }
                
        }
    }
}
