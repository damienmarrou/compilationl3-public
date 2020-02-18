import org.junit.Test;
import sc.lexer.Lexer;
import sc.lexer.LexerException;
import sc.parser.Parser;
import sc.parser.ParserException;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

public class Sa2tsTest {

    private void buildTs(String code) throws ParserException, IOException, LexerException, Sa2ts.TsException {
        var reader = new PushbackReader(new StringReader(code));
        var parser = new Parser(new Lexer(reader));
        var tree = parser.parse();
        var sc2sa = new Sc2sa();
        tree.apply(sc2sa);
        new Sa2ts(sc2sa.getRoot());
    }


    /******************************************VARIABLES**********************************************/
    @Test(expected = Sa2ts.TsException.class)
    public void testNoDuplicateVariableGlobalScope() {
        try {
            buildTs("entier a, entier a; main() {}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testNoDuplicateArrayGlobalScope() {
        try {
            buildTs("entier a[5], entier a[5]; main() {}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testNoDuplicateVariableArgumentScope() {
        try {
            buildTs("main(entier a, entier a) {}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)//V1.1
    public void testNoDuplicateVariableFunctionScope() {
        try {
            buildTs("main() entier a, entier a; {}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)//V1.3
    public void testNoFunctionVariableSameNameAsArgument() {
        try {
            buildTs("main(entier a) entier a; {}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)//V1.4
    public void testArraysAreGlobals() {
        try {
            buildTs("main() entier a[5]; { main();}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    /*@Test(expected = Sa2ts.TsException.class)
    public void testNoFunctionSameNameAsVariableLocalScope() {//A voir si le test est nécessaire
        try {
            buildTs("main() entier test; {retour 0;} test() {retour 0;}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }*/

    @Test(expected = Sa2ts.TsException.class)
    public void testNoFunctionSameNameAsVariableGlobalScope() {
        try {
            buildTs("entier main; main() {main();}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testNoFunctionSameNameAsArrayGlobalScope() {
        try {
            buildTs("entier main[2]; main() {main();}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testUsedVariableIsDeclared() {
        try {
            buildTs("main() { a = 1; }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testUsedArrayIsDeclared() {
        try {
            buildTs("main() { a[0] = 1; }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)//V2.4
    public void testNoCastsFromArrayToInteger() {
        try {
            buildTs("entier a[2], entier b; main() { b = a; }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)//V2.4
    public void testNoCastFromIntegerToArray() {
        try {
            buildTs("entier a, entier b[10]; main(){a=b;}");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }


    /************************************************FUNCTIONS***************************************************************/
    @Test(expected = Sa2ts.TsException.class)
    public void testNoCastsFromIntegerToFunction() {
        try {
            buildTs("entier a[2]; g() { retour 0; } main() { a = g(); }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }


    @Test(expected = Sa2ts.TsException.class)
    public void testAllFunctionsHaveUniqueNames() {
        try {
            buildTs("main() { retour 0; } main() { retour 0; }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testCalledFunctionIsDeclared() {
        try {
            buildTs("main() { g(); }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testCalledFunctionIsDeclaredBefore() {
        try {
            buildTs("main() { g(); } g() { retour 0; }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testFunctionCallHasCorrectNumberOfArguments() {//todo corriger le cas du NPE
        try {
            buildTs("g(entier a) { retour 0; } main() { g(); }");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }

    @Test(expected = Sa2ts.TsException.class)
    public void testMainExists() {
        try {
            buildTs("g() { retour 0; } ");
        } catch (Sa2ts.TsException e) {
            e.printStackTrace();
        } finally {
            throw new Sa2ts.TsException("pas ok \n");
        }
    }
}
