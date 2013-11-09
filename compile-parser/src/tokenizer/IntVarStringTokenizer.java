package tokenizer;

import java.util.ArrayList;

public class IntVarStringTokenizer {

    private String input;
    private String lastTokenizedWord = "";
    private ArrayList<String> listOfTokens = new ArrayList();
    private ArrayList<String> listOfTokenTypes = new ArrayList();
    private String currState = "startState";
    int i = 0;

    public IntVarStringTokenizer(String input) {
        this.input = input.replace("\n", "").replace("\r", "").replace("\t", "");
        this.input = this.input.replace("“", "\"").replace("”", "\"");
        this.input = this.input.replace(";", " ;");
        this.input = this.input.replace("{", " { ");
        this.input = this.input.replace("}", " } ");
        this.input = this.input.replace("=", " = ");
        stateSelector();

    }

    public ArrayList<String> getListOfTokens() {
        return listOfTokens;
    }

    public ArrayList<String> getListOfTokenTypes() {
        return listOfTokenTypes;
    }

    private void saveToken(String kind) {
        String sLastTokenizedWord = lastTokenizedWord;
        listOfTokens.add(sLastTokenizedWord);
        listOfTokenTypes.add(kind);
        lastTokenizedWord = "";

    }

    private void stateSelector() {
        listOfTokens.clear();
        listOfTokenTypes.clear();
        char inputChar;
        for (i = 0; i < input.length(); i++) {
            inputChar = input.charAt(i);
            
            if (i == 0) {
                lastTokenizedWord = Character.toString(inputChar);
            } else {
                lastTokenizedWord = lastTokenizedWord + Character.toString(inputChar);
            }

            if (inputChar == ';') {
                endStatement();
            } else {
                if ("startState".equals(currState)) {
                    startState(inputChar);
                } else if ("integer".equals(currState)) {
                    integer(inputChar);
                } else if ("string".equals(currState)) {
                    string(inputChar);
                } else if ("variable".equals(currState)) {
                    variable(inputChar);
                } else if ("keyword".equals(currState)) {
                    keyword(inputChar);
                } else if ("deadState".equals(currState)) {
                    deadState(inputChar);
                } else if ("backslashString".equals(currState)) {
                    backslashString();
                } else if ("backslashVar".equals(currState)) {
                    backslashVar();
                } else if ("curlyBraceStart".equals(currState)) {
                    curlyBraceStart();
                } else if ("curlyBraceEnd".equals(currState)) {
                    curlyBraceEnd();
                } else if ("equalsState".equals(currState)) {
                    equalsState();
                }
            }

        }

        //Print Token & Type Table
        //System.out.println("\n ---TOKEN TABLE---");
        for (int i = 0; i < listOfTokens.size(); i++) {
            //System.out.println("Token:" + listOfTokens.get(i) + " Type: " + listOfTokenTypes.get(i));
        }
    }

    private void startState(char inputChar) {
        if (inputChar >= 65 && inputChar <= 90) {
            currState = "keyword";
        } else if (inputChar == '<') {
            currState = "variable";
        } else if (inputChar >= 48 && inputChar <= 57) {
            currState = "integer";
        } else if (inputChar == '"') {
            currState = "string";
        } else if (inputChar == '{') {
            currState = "curlyBraceStart";
        } else if (inputChar == '}') {
            currState = "curlyBraceEnd";
        } else if (inputChar == '=') {
            currState = "equalsState";
        } else if (inputChar == ' ') {
            currState = "startState";
        } else {
            currState = "deadState";
        }
    }

    private void integer(char inputChar) {
        if (!(inputChar >= 48 && inputChar <= 57)) {
            saveToken("int");
            startState(inputChar);
        }
    }

    private void string(char inputChar) {
        if (inputChar == '\\') {
            currState = "backslashString";
        } else if ((inputChar >= 32 && inputChar <= 126) && inputChar != 34) {
            currState = "string";
        } else if (inputChar == '"') {
            saveToken("\"string\"");
            currState = "startState";
        }
    }

    private void variable(char inputChar) {
        if (inputChar == '\\') {
            currState = "backslashVar";
        } else if ((inputChar >= 65 && inputChar <= 90) || inputChar == 32) {
            currState = "variable";
        } else if (inputChar == '>') {
            saveToken("<variable>");
            currState = "startState";
        }

    }

    private void keyword(char inputChar) {
        KeywordLookup keywordLookup = new KeywordLookup();
        String sLastTokenizedWord = lastTokenizedWord;

        if (!((inputChar >= 65 && inputChar <= 90) || inputChar == 95)) {
            if (keywordLookup.isValidKeyword(sLastTokenizedWord)) {
                lastTokenizedWord = lastTokenizedWord.replace(" ","");
                saveToken(keywordLookup.getMatchedKeyword());
            } else {
                System.out.println("Invalid Keyword: " + sLastTokenizedWord);
                lastTokenizedWord = "";
            }
            startState(inputChar);
        }
    }

    private void endStatement() {
        saveToken(";");
    }

    private void backslashString() {
        lastTokenizedWord = lastTokenizedWord.replace("\\", "");
        currState = "string";
    }

    private void backslashVar() {
        lastTokenizedWord = lastTokenizedWord.replace("\\", "");
        currState = "variable";
    }

    private void curlyBraceStart() {
        saveToken("{");
        currState = "startState";
    }

    private void curlyBraceEnd() {
        saveToken("}");
        currState = "startState";
    }
    
    private void equalsState() {
        saveToken("=");
        currState = "startState";
    }

    private void deadState(char inputChar) {
        String invalidToken = lastTokenizedWord;
        if (inputChar >= 65 && inputChar <= 90) {
            currState = "keyword";
            System.out.println("Invalid Token: " + invalidToken);
            lastTokenizedWord = "";
        } else if (input.charAt(i + 1) == '<') {
            currState = "startState";
            System.out.println("Invalid Token: " + invalidToken);
            lastTokenizedWord = "";
        } else if (inputChar >= 48 && inputChar <= 57) {
            currState = "integer";
            System.out.println("Invalid Token: " + invalidToken);
            lastTokenizedWord = "";
        } else if (input.charAt(i + 1) == '"') {
            currState = "startState";
            System.out.println("Invalid Token: " + invalidToken);
            lastTokenizedWord = "";
        } else if (inputChar == ' ') {
            currState = "startState";
            System.out.println("Invalid Token: " + invalidToken);
            lastTokenizedWord = "";
        } else {
        }
    }
}
