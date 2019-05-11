package patterns;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;

import beans.PatternInfo;

/**
 * This class is used to check if the block is empty or not.
 * If the catch block is empty, we record it.
 * @author sandeepchowdaryannabathuni
 *
 */
public class NullCatchBlock extends ASTVisitor {
	
	private final PatternInfo obj;
	
	public NullCatchBlock(PatternInfo info) {
		obj = info;
	}
	
	@Override
	public boolean visit(CatchClause node) {
		
		if(isCatchBlockEmpty(node))
			obj.numOfNullCatch = obj.numOfNullCatch + 1;
			
		return super.visit(node);
	}
	
	
	/**
	 * @param node
	 * @return true, if the catch block is empty, otherwise false.
	 */
	private boolean isCatchBlockEmpty(CatchClause node) {
		
		String body = node.getBody().toString()
				.replaceAll("[\\{\\}]", " ");
		
		if(body.isBlank() || body.isEmpty() || body.strip() == "")
			return true;

		return false;
	}
}
