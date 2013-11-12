package parser;

import intermediatecode.Background;
import intermediatecode.Chapter;
import intermediatecode.Characters;
import intermediatecode.Command;
import intermediatecode.ErrorCheck;
import intermediatecode.Graphic;
import intermediatecode.Image;
import intermediatecode.ObjectTracker;
import intermediatecode.Statement;
import java.util.ArrayList;

public class RecursiveDescent {

    //VARIABLE DECLARATION
    final ArrayList<String> listOfTokens;
    final ArrayList<String> listOfTokenTypes;
    private String tok;
    private String beforeTok = "";
    private int i = 0;
    private int errorFlag = 0;
    private Image tempImage;
    final ObjectTracker tracker = new ObjectTracker();
    private String variable;
    private String fileName;
    private Command comm;
    private ArrayList<Image> tempListOfCharImg = new ArrayList();
    private ArrayList<Statement> tempListOfStmt = new ArrayList();
    private ArrayList<Command> tempListOfCommand = new ArrayList();
    private Statement stmt;
    private boolean cameFromChar = false;
    private int numChap = 0;
    private int numChar = 0;
    private int numBackground = 0;
    private int numGraphic = 0;

    public RecursiveDescent(ArrayList<String> listOfTokens, ArrayList<String> listOfTokenTypes) {
        this.listOfTokenTypes = listOfTokenTypes;
        this.listOfTokens = listOfTokens;

        System.out.println("\n\n--PARSE RESULTS--");
        parse();
    }

    private void panic() {
        while (!(tok.equals(";")) && !(tok.equals("ADD_CHARACTER")) && i < listOfTokenTypes.size()) {
            //System.out.println("Skipping: " + tok);
            beforeTok = listOfTokens.get(i);
            tok = listOfTokenTypes.get(i++);
        }

        if ((tok.equals(";"))) {
            beforeTok = listOfTokens.get(i);
            tok = listOfTokenTypes.get(i++);
        }
    }

    private boolean match(String t) {
        if (tok.equals(t)) {
            if (i + 1 < listOfTokenTypes.size()) {
                beforeTok = listOfTokens.get(i);
                i++;
                tok = listOfTokenTypes.get(i);
                return true;
            }
        } else {
            System.out.println("Error before \"" + beforeTok + "\". Expected: " + t + " but got " + tok);
            panic();
            errorFlag++;
            return false;
        }
        return false;
    }

    private void parse() {
        tok = listOfTokenTypes.get(i);
        start();
        if (errorFlag != 0) {
            System.out.println("Parse Complete: You have " + errorFlag + " errors.");
        } else {
            System.out.println("Parse Complete: Code is accepted.");
            new ErrorCheck(tracker);
        }
    }

    private void start() {
        if ("ADD_BACKGROUND".equals(tok)) {
            if (match("ADD_BACKGROUND")) {
                imageDef();

                Background b = new Background();
                b.setImage(tempImage);
                b.setName(variable);
                tracker.getListBackground().add(b);
                b.setBackgroundID(numBackground);

                numBackground++;

                start();
            }
        } else if ("ADD_CG".equals(tok)) {
            if (match("ADD_CG")) {
                imageDef();

                Graphic g = new Graphic();
                g.setImage(tempImage);
                g.setName(variable);
                tracker.getListGraphic().add(g);
                g.setGraphicID(numGraphic);

                numGraphic++;

                start();
            }
        } else if ("ADD_CHAPTER".equals(tok)) {
            if (match("ADD_CHAPTER")) {
                if (match("<variable>")) {
                    variable = beforeTok;

                    if (match(";")) {

                        Chapter chap = new Chapter();
                        chap.setChapterName(variable);
                        tracker.getListChapter().add(chap);
                        chap.setChapterID(numChap);

                        numChap++;

                        start();
                    }
                }
            }
        } else if ("ADD_CHARACTER".equals(tok)) {
            if (match("ADD_CHARACTER")) {
                if (match("<variable>")) {
                    variable = beforeTok;
                    Characters c = new Characters();
                    cameFromChar = true;
                    c.setName(variable);

                    if (match("{")) {
                        imageDef();
                        c.setListImage(tempListOfCharImg);
                        tracker.getListCharacters().add(c);
                        c.setCharID(numChar);
                        cameFromChar = false;
                        tempListOfCharImg = new ArrayList();

                        numChar++;

                        if (match("}")) {
                            if (match(";")) {
                            }
                        }
                    }
                }
                start();
            }
        } else if ("START_CHAPTER".equals(tok)) {
            if (match("START_CHAPTER")) {
                if (match("<variable>")) {
                    String chapVar = beforeTok;
                    if (match(";")) {
                        block();
                        boolean chapMatch = true;
                        int iterate = 0;

                        while (chapMatch) {

                            if (tracker.getListChapter().get(iterate).getChapterName().equals(chapVar)) {

                                chapMatch = false;
                                tracker.getListChapter().get(iterate).setStatements(tempListOfStmt);
                                tempListOfStmt = new ArrayList();

                            } else {
                                iterate++;
                            }
                        }
                        if (match("END_CHAPTER")) {
                            if (match(";")) {

                            }
                        }
                    }
                }
            }
            start();
        } else {
        }
    }

    private void block() {
        if ("<variable>".equals(tok) || "SET_BACKGROUND".equals(tok) || "ADD_BRANCH".equals(tok) || "CHANGE_BACKGROUND_BY_FADE_TO".equals(tok)
                || "CHANGE_BACKGROUND_BY_SCROLL_TO".equals(tok) || "HIDE_CHARACTERS".equals(tok) || "RESET_POSITIONS".equals(tok)
                || "DISPLAY_CG".equals(tok) || "CG_FADE_IN".equals(tok) || "CG_FADE_OUT".equals(tok) || "CG_ZOOM_IN".equals(tok)
                || "CG_ZOOM_OUT".equals(tok) || "SHAKE_SCREEN".equals(tok) || "{".equals(tok)) {

            stmt = new Statement();
            comm = new Command();

            a();
            and();
            if (match(";")) {
                stmt.setCommands(tempListOfCommand);
                tempListOfStmt.add(stmt);
                tempListOfCommand = new ArrayList();

            }
            block();
        } else {
        }
    }

    private void and() {
        if ("AND".equals(tok)) {
            if (match("AND")) {
                a();
            }
        } else {
        }
    }

    private void imageDef() {
        if ("<variable>".equals(tok)) {
            if (match("<variable>")) {
                variable = beforeTok;
                if (match("=")) {
                    if (match("\"string\"")) {
                        if (cameFromChar) {
                            fileName = beforeTok;

                            tempImage = new Image();
                            tempImage.setImageName(variable);
                            tempImage.setFileName(fileName);

                            tracker.getListImage().add(tempImage);
                            tempImage.setImageID(tracker.getListImage().size() - 1);

                            tempListOfCharImg.add(tempImage);
                        } else {
                            fileName = beforeTok;

                            tempImage = new Image();
                            tempImage.setImageName(variable);
                            tempImage.setFileName(fileName);

                            tracker.getListImage().add(tempImage);
                            tempImage.setImageID(tracker.getListImage().size() - 1);
                        }
                        if (match(";")) {
                        }

                    }
                }
            }
            imageDef();
        } else {
        }

    }

    private void charRule() {
        if ("WILL_PANIC".equals(tok)) {
            match("WILL_PANIC");
            comm.setCommandName("WILL_PANIC");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("WILL_SHAKE".equals(tok)) {
            match("WILL_SHAKE");
            comm.setCommandName("WILL_SHAKE");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("WILL_NOD".equals(tok)) {
            match("WILL_NOD");
            comm.setCommandName("WILL_NOD");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("SCROLL_IN".equals(tok)) {
            match("SCROLL_IN");
            comm.setCommandName("SCROLL_IN");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("FADE_IN".equals(tok)) {
            match("FADE_IN");
            comm.setCommandName("FADE_IN");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("SCROLL_OUT".equals(tok)) {
            match("SCROLL_OUT");
            comm.setCommandName("SCROLL_OUT");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("FADE_OUT".equals(tok)) {
            match("FADE_OUT");
            comm.setCommandName("FADE_OUT");
            tempListOfCommand.add(comm);
            comm = new Command();
        } else if ("MOVES_TO".equals(tok)) {
            if (match("MOVES_TO")) {
                comm.setCommandName("MOVES_TO");
                if (match("int")) {
                    comm.setxCoordinate(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();
                }
            }
        } else if ("CHANGE_IMAGE".equals(tok)) {
            match("CHANGE_IMAGE");
            comm.setCommandName("CHANGE_IMAGE");
            match("<variable>");
            comm.setObjectVariable(beforeTok);
            tempListOfCommand.add(comm);
            comm = new Command();

        } else if ("=".equals(tok)) {
            if (match("=")) {
                comm.setCommandName("=");
                if (match("\"string\"")) {

                    comm.setEqualString(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();
                }
            }

        }
    }

    private void a() {
        if ("{".equals(tok)) {
            if (match("{")) {
                variable();
                if (match("}")) {
                    if (match("SAYS")) {

                        comm.setCommandName("SAYS");

                        if (match("\"string\"")) {

                            comm.setDialogue("beforeTok");
                            tempListOfCommand.add(comm);

                            comm = new Command();

                            and();
                        }
                    }
                }
            }
        } else if ("SET_BACKGROUND".equals(tok)) {
            if (match("SET_BACKGROUND")) {

                comm.setCommandName("SET_BACKGROUND");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("ADD_BRANCH".equals(tok)) {
            if (match("ADD_BRANCH")) {

                comm.setCommandName("ADD_BRANCH");

                if (match("\"string\"")) {
                    comm.setBranchingTitle(beforeTok);

                    transition();
                    if (match("<variable>")) {

                        comm.setObjectVariable(beforeTok);
                        tempListOfCommand.add(comm);
                        comm = new Command();

                        and();
                    }
                }
            }
        } else if ("CHANGE_BACKGROUND_BY_FADE_TO".equals(tok)) {
            if (match("CHANGE_BACKGROUND_BY_FADE_TO")) {

                comm.setCommandName("CHANGE_BACKGROUND_BY_FADE_TO");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("CHANGE_BACKGROUND_BY_SCROLL_TO".equals(tok)) {
            if (match("CHANGE_BACKGROUND_BY_SCROLL_TO")) {

                comm.setCommandName("CHANGE_BACKGROUND_BY_SCROLL_TO");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("HIDE_CHARACTERS".equals(tok)) {
            if (match("HIDE_CHARACTERS")) {

                comm.setCommandName("HIDE_CHARACTERS");
                tempListOfCommand.add(comm);
                comm = new Command();

                and();
            }
        } else if ("RESET_POSITIONS".equals(tok)) {
            if (match("RESET_POSITIONS")) {

                comm.setCommandName("RESET_POSITIONS");
                tempListOfCommand.add(comm);
                comm = new Command();

                and();
            }
        } else if ("DISPLAY_CG".equals(tok)) {
            if (match("DISPLAY_CG")) {

                comm.setCommandName("DISPLAY_CG");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("CG_FADE_IN".equals(tok)) {
            if (match("CG_FADE_IN")) {

                comm.setCommandName("CG_FADE_IN");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("CG_FADE_OUT".equals(tok)) {
            if (match("CG_FADE_OUT")) {

                comm.setCommandName("CG_FADE_OUT");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("CG_ZOOM_IN".equals(tok)) {
            if (match("CG_ZOOM_IN")) {

                comm.setCommandName("CG_ZOOM_IN");

                if (match("<variable>")) {

                    comm.setObjectVariable(beforeTok);

                    if (match("int")) {

                        comm.setStartSize(Integer.parseInt(beforeTok));

                        if (match("int")) {

                            comm.setEndSize(Integer.parseInt(beforeTok));
                            tempListOfCommand.add(comm);
                            comm = new Command();

                            and();
                        }
                    }
                }
            }
        } else if ("CG_ZOOM_OUT".equals(tok)) {
            if (match("CG_ZOOM_OUT")) {
                if (match("<variable>")) {

                    comm.setCommandName("CG_ZOOM_OUT");
                    tempListOfCommand.add(comm);
                    comm = new Command();

                    and();
                }
            }
        } else if ("SHAKE_SCREEN".equals(tok)) {
            if (match("SHAKE_SCREEN")) {

                comm.setCommandName("SHAKE_SCREEN");
                tempListOfCommand.add(comm);
                comm = new Command();

                and();
            }
        } else if ("<variable>".equals(tok)) {
            if (match("<variable>")) {
                comm.getSubjectVariable().add(beforeTok);
                charRule();

                and();
            }
        }
    }

    private void transition() {
        if ("JUMP_TO_CHAPTER".equals(tok)) {
            if (match("JUMP_TO_CHAPTER")) {

                comm.setCommandForJumpingChapter("JUMP_TO_CHAPTER");

            }
        } else if ("NEXT_CHAPTER".equals(tok)) {
            if (match("NEXT_CHAPTER")) {

                comm.setCommandForJumpingChapter("NEXT_CHAPTER");

            }
        }
    }

    private void variable() {
        if ("<variable>".equals(tok)) {
            if (match("<variable>")) {
                comm.getSubjectVariable().add(beforeTok);
                variable();
            }
        } else {
        }

    }
}
