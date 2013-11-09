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
public class Characters {

    private String name;
    private ArrayList<Image> listImage = new ArrayList();
    private int charID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Image> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<Image> listImage) {
        this.listImage = listImage;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }
    
}
