/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import java.util.ArrayList;

/**
 *
 * @author Louis
 */
public class KeywordLookup {
    
    public KeywordLookup(){
        this.initialize();
    }
    private ArrayList<String> keywordTable = new ArrayList<String>();
    private String matchedKeyword= "";
    
    public void initialize(){
    	keywordTable.add("ADD_CHARACTER");
        keywordTable.add("SCROLL_IN");
        keywordTable.add("FADE_IN");
        keywordTable.add("SCROLL_OUT");
        keywordTable.add("FADE_OUT");
        keywordTable.add("SAYS");
        keywordTable.add("CHANGE_IMAGE");
        keywordTable.add("MOVES_TO");
        keywordTable.add("RESET_CHARACTERS");
        keywordTable.add("RESET_POSITIONS");
        keywordTable.add("ADD_BACKGROUND");
        keywordTable.add("CHANGE_BACKGROUND_BY_FADE_TO");
        keywordTable.add("CHANGE_BACKGROUND_BY_SCROLL_TO");
        keywordTable.add("ADD_CHAPTER");
        keywordTable.add("END_CHAPTER");
        keywordTable.add("ADD_BRANCH");
        keywordTable.add("NEXT_CHAPTER");
        keywordTable.add("JUMP_TO_CHAPTER");
        keywordTable.add("ADD_CG");
        keywordTable.add("DISPLAY_CG");
        keywordTable.add("AND");
        keywordTable.add("WILL_SHAKE");
        keywordTable.add("WILL_PANIC");
        keywordTable.add("WILL_NOD");
        keywordTable.add("CG_FADE_IN");
        keywordTable.add("CG_FADE_OUT");
        keywordTable.add("CG_ZOOM_IN");
        keywordTable.add("CG_ZOOM_OUT");
        keywordTable.add("SHAKE_SCREEN");
        keywordTable.add("HIDE_CHARACTERS");
        keywordTable.add("START_CHAPTER");
    }

    public String getMatchedKeyword() {
        return matchedKeyword;
    }
    
    public boolean isValidKeyword(String keyword){
        keyword=keyword.replace(" ", "");
        if(keywordTable.contains(keyword + " ") || keywordTable.contains(keyword)){
            matchedKeyword = keyword;
            return true;
        }
        else{
            return false;
        }
    }
}
