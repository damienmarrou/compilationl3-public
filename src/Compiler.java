import c3a.C3a;
import c3a.C3aEval;
import fg.Fg;
import fg.FgSolution;
import ig.Ig;
import nasm.Nasm;
import sa.Sa2Xml;
import sa.SaNode;
import sc.lexer.Lexer;
import sc.node.Start;
import sc.parser.Parser;
import ts.Ts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;

public class Compiler {
    public static void main(String[] args) throws IOException {
        List<String> fileNames = new ArrayList<>();
        //File folder = new File("test\\input");
        File folder = new File("test/input");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".l")) {
                fileNames.add(listOfFiles[i].getAbsolutePath());
            }
        }
        int i = 1;
        int j = 0;
        for (String fileName : fileNames) {
            PushbackReader br = null;
            String baseName = null;

            try {
                //if (0 < args.length) {
                br = new PushbackReader(new FileReader(fileName), 1024);
                baseName = removeSuffix(fileName, ".l");
				/*} else {
					System.out.println("il manque un argument");
				}*/
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                j++;
                // Create a Parser instance.
                Parser p = new Parser(new Lexer(br));
                // Parse the input.
                Start tree = p.parse();

                //System.out.println("[SC]");
                tree.apply(new Sc2Xml(baseName));

                //System.out.println("[SA]");
                Sc2sa sc2sa = new Sc2sa();
                tree.apply(sc2sa);
                SaNode saRoot = sc2sa.getRoot();
                new Sa2Xml(saRoot, baseName);

                //System.out.println("[TABLE SYMBOLES]");
                Ts table = new Sa2ts(saRoot).getTableGlobale();
                table.afficheTout(baseName);

                //System.out.println("[C3A]");
                C3a c3a = new Sa2c3a(saRoot, table).getC3a();
                c3a.affiche(baseName);

                //System.out.println("[PRINT C3A OUT]");
                C3aEval c3aEval = new C3aEval(c3a, table);
                c3aEval.affiche(baseName);

                //System.out.print("[BUILD PRE NASM] ");
                Nasm nasm = new C3a2nasm(c3a, table).getNasm();
                // System.out.println("[PRINT PRE NASM] ");
                nasm.affichePre(baseName);
                nasm.affiche(baseName);

                //System.out.print("[BUILD FG] ");
                Fg fg = new Fg(nasm);
                //System.out.print("[PRINT FG] ");
                fg.affiche(baseName);

                //System.out.println("[SOLVE FG]");
                FgSolution fgSolution = new FgSolution(nasm, fg);
                fgSolution.affiche(baseName);

                //System.out.print("[BUILD IG] ");
                Ig ig = new Ig(fgSolution);
                ig.allocateRegisters();

                // System.out.println("[PRINT NASM] ");
                nasm.affiche(baseName);

            } catch (Exception e) {
                //System.out.println("fileName = " + fileName);
                System.out.println("\nFichier n° " + j + "  " + fileName.substring(fileName.lastIndexOf('\\') + 1) + " erreur n° " + i);
                i++;
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println();
        List<String> fileSA = new ArrayList<>();
        List<String> fileTS = new ArrayList<>();
        List<String> fileC3A = new ArrayList<>();
        List<String> filePRENASM = new ArrayList<>();
        List<String> fileNASM = new ArrayList<>();
        List<String> fileFG = new ArrayList<>();
        List<String> fileFGS = new ArrayList<>();
        File folderInput = new File("test/input");
        File[] listOfFilesInput = folderInput.listFiles();

       /* fileSA = makeListOfFiles(".sa", fileSA, listOfFilesInput);
        fileTS = makeListOfFiles(".ts", fileTS, listOfFilesInput);
        fileC3A = makeListOfFiles(".c3a", fileC3A, listOfFilesInput);
        filePRENASM = makeListOfFiles(".pre-nasm", filePRENASM, listOfFilesInput);*/
        fileNASM = makeListOfFiles(".nasm", fileNASM, listOfFilesInput);
        fileFG = makeListOfFiles(".fg", fileFG, listOfFilesInput);
        fileFGS = makeListOfFiles(".fgs", fileFGS, listOfFilesInput);
        // printCompare("SA", fileSA);
        //printCompare("TS", fileTS);
        //printCompare("C3A", fileC3A);
        //printCompare("PRENASM", filePRENASM);
        printCompare("FG", fileFG);
        printCompare("FGS", fileFGS);
        printCompare("NASM", fileNASM);
    }

    public static List<String> makeListOfFiles(String type, List<String> fileType, File[] listOfFiles) {
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile() && listOfFile.getName().endsWith(type)) {
                fileType.add(listOfFile.getAbsolutePath().substring(listOfFile.getAbsolutePath().lastIndexOf("\\") + 1));
            }
        }
        return fileType;
    }

    public static void printCompare(String type, List<String> fileName) throws IOException {
        System.out.println(type);
        if (fileName.size() == 0) {
            System.out.println("pas de fichier input");
        } else {
            for (String file : fileName) {
                System.out.print(new String(Runtime.getRuntime().exec("python test/CompareArbre.py " + file + " " + file).getErrorStream().readAllBytes()));
            }
        }
    }

    public static String removeSuffix(final String s, final String suffix) {
        if (s != null && suffix != null && s.endsWith(suffix)) {
            return s.substring(0, s.length() - suffix.length());
        }
        return s;
    }
}
