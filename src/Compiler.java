import c3a.C3a;
import c3a.C3aEval;
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
//import fg.*;

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

                /*System.out.print("[BUILD FG] ");
                Fg fg = new Fg(nasm);
                System.out.print("[PRINT FG] ");
                fg.affiche(baseName);

                System.out.println("[SOLVE FG]");
                FgSolution fgSolution = new FgSolution(nasm, fg);
                fgSolution.affiche(baseName);*/

            } catch (Exception e) {
                //System.out.println("fileName = " + fileName);
                System.out.println("\nFichier n° " + j + "  " + fileName.substring(fileName.lastIndexOf('\\') + 1) + " erreur n° " + i);
                i++;
                //System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println();
        List<String> fileSA = new ArrayList<>();
        List<String> fileTS = new ArrayList<>();
        List<String> fileC3A = new ArrayList<>();
        List<String> filePRENASM = new ArrayList<>();
        List<String> fileNASM = new ArrayList<>();
        File folderSA = new File("test/input");
        File folderTS = new File("test/input");
        File folderC3A = new File("test/input");
        File folderPRENASM = new File("test/input");
        File folderNASM = new File("test/input");
        File[] listOfFilesSA = folderSA.listFiles();
        File[] listOfFilesTS = folderTS.listFiles();
        File[] listOfFilesC3A = folderC3A.listFiles();
        File[] listOfFilePRENASM = folderPRENASM.listFiles();
        File[] listOfFileNASM = folderNASM.listFiles();


        for (int x = 0; x < listOfFilesSA.length; x++) {
            if (listOfFilesSA[x].isFile() && listOfFilesSA[x].getName().endsWith(".sa")) {
                fileSA.add(listOfFilesSA[x].getAbsolutePath().substring(listOfFilesSA[x].getAbsolutePath().lastIndexOf('\\') + 1));
            }
        }

        for (int x = 0; x < listOfFilesTS.length; x++) {
            if (listOfFilesTS[x].isFile() && listOfFilesTS[x].getName().endsWith(".ts")) {
                fileTS.add(listOfFilesTS[x].getAbsolutePath().substring(listOfFilesTS[x].getAbsolutePath().lastIndexOf('\\') + 1));
            }
        }


        for (int x = 0; x < listOfFilesC3A.length; x++) {
            if (listOfFilesC3A[x].isFile() && listOfFilesC3A[x].getName().endsWith(".c3a")) {
                fileC3A.add(listOfFilesC3A[x].getAbsolutePath().substring(listOfFilesC3A[x].getAbsolutePath().lastIndexOf('\\') + 1));
            }
        }

        for (int x = 0; x < listOfFilePRENASM.length; x++) {
            if (listOfFilePRENASM[x].isFile() && listOfFilePRENASM[x].getName().endsWith(".pre-nasm")) {
                filePRENASM.add((listOfFilePRENASM[x].getAbsolutePath().substring(listOfFilePRENASM[x].getAbsolutePath().lastIndexOf("\\") + 1)));
            }
        }
        for (int x = 0; x < listOfFileNASM.length; x++) {
            if (listOfFileNASM[x].isFile() && listOfFileNASM[x].getName().endsWith(".nasm")) {
                fileNASM.add((listOfFileNASM[x].getAbsolutePath().substring(listOfFileNASM[x].getAbsolutePath().lastIndexOf("\\") + 1)));
            }
        }

        System.out.println("SA");
        if (fileSA.size() == 0) {
            System.out.println("pas de fichier input");
        } else {
            for (String file : fileSA) {
                //System.out.println(file);
                System.out.print(new String(Runtime.getRuntime().exec("python test/CompareArbre.py " + file + " " + file).getErrorStream().readAllBytes()));
            }
        }

        System.out.println("TS");
        if (fileTS.size() == 0) {
            System.out.println("pas de fichier input");
        } else {
            for (String file : fileTS) {
                //System.out.println(file);
                System.out.print(new String(Runtime.getRuntime().exec("python test/CompareArbre.py " + file + " " + file).getErrorStream().readAllBytes()));
            }
        }


        System.out.println("C3A");
        if (fileC3A.size() == 0) {
            System.out.println("pas de fichier input");
        } else {
            for (String file : fileC3A) {
                //System.out.println(file);
                System.out.print(new String(Runtime.getRuntime().exec("python test/CompareArbre.py " + file + " " + file).getErrorStream().readAllBytes()));
            }
        }
        System.out.println("PRENASM");
        if (filePRENASM.size() == 0) {
            System.out.println("pas de fichier input");
        } else {
            for (String file : filePRENASM) {
                System.out.print(new String(Runtime.getRuntime().exec("python test/CompareArbre.py " + file + " " + file).getErrorStream().readAllBytes()));
            }
        }

        System.out.println("NASM");
        if (fileNASM.size() == 0) {
            System.out.println("pas de fichier input");
        } else {
            for (String file : fileNASM) {
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
