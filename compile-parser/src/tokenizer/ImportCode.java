package tokenizer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportCode {
    
    String fileDirectory;
    
    public ImportCode(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Import");
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
         fileDirectory = chooser.getCurrentDirectory() + "\\" + chooser.getSelectedFile().getName();
        }
    }
    
    public String readFromFile() {
        String inputStream = "";
        BufferedReader br = null;
        
        try {
            String sCurrentLine;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirectory),"Unicode"));
            
            while ((sCurrentLine = br.readLine()) != null) {
                inputStream = inputStream + sCurrentLine;
                inputStream += "\n";
            }
            
            return inputStream;
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
