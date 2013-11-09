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
public class ObjectTracker {

    private ArrayList<Background> listBackground = new ArrayList();
    private ArrayList<Characters> listCharacters = new ArrayList();
    private ArrayList<Chapter> listChapter = new ArrayList();
    private ArrayList<Graphic> listGraphic = new ArrayList();
    private ArrayList<Image> listImage = new ArrayList();

    public ArrayList<Background> getListBackground() {
        return listBackground;
    }

    public void setListBackground(ArrayList<Background> listBackground) {
        this.listBackground = listBackground;
    }

    public ArrayList<Characters> getListCharacters() {
        return listCharacters;
    }

    public void setListCharacters(ArrayList<Characters> listCharacter) {
        this.listCharacters = listCharacter;
    }

    public ArrayList<Chapter> getListChapter() {
        return listChapter;
    }

    public void setListChapter(ArrayList<Chapter> listChapter) {
        this.listChapter = listChapter;
    }

    public ArrayList<Graphic> getListGraphic() {
        return listGraphic;
    }

    public void setListGraphic(ArrayList<Graphic> listGraphic) {
        this.listGraphic = listGraphic;
    }

    public ArrayList<Image> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<Image> listImage) {
        this.listImage = listImage;
    }
    
    

}
