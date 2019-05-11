package patterns;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.TryStatement;

import beans.PatternInfo;

public class GenericCatch extends ASTVisitor{

	private final PatternInfo info;
	private final String HANDLER_REGEX = "\"[{\\\\r\\\\n]*.*(})\"";
	private final String CATCH_REGEX = "catch.?\\(";
	private final String CLEAR_REGEX = "[a-zA-Z]*.?\\)";
	
	
	public GenericCatch(PatternInfo obj) {
		super();
		this.info = obj;
	}
	
	@Override
	public boolean visit(CatchClause node) {
		
		return super.visit(node);
	}
	
	@Override
	public boolean visit(TryStatement node) {
		List list = node.catchClauses();
		
		
		for(Object obj : list) {
			String afterRemovingHandler = obj.toString().replaceAll(HANDLER_REGEX, "");
			String afterRemovingCatch = afterRemovingHandler.replaceAll(CATCH_REGEX, "");
			String afterClear = afterRemovingCatch.replaceAll(CLEAR_REGEX, "");
			
			if((afterClear.strip()).equalsIgnoreCase("Exception")) {
				info.numOfGenericCatch++;
			}
		}
			
		return super.visit(node);
	}
}
