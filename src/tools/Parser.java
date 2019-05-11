package tools;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * This class is used to parse the ICompilation unit to generate
 * the AST. This class has static method "parse".
 * @author sandeepchowdaryannabathuni
 *
 */
public class Parser {
	
	/**
	 * @param src This is ICompilationUnit object.
	 * @return CompilationUnit object.
	 */
	public static CompilationUnit parse(ICompilationUnit src) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS11);
		parser.setSource(src);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		
		return (CompilationUnit)parser.createAST(null);
	}
}
