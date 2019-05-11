package patterns;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TryStatement;

import beans.PatternInfo;

public class AbortCatchBlock extends ASTVisitor {
	
	private final PatternInfo info;
	
	public AbortCatchBlock(PatternInfo info) {
		super();
		this.info = info;
	}

	
	@Override
	public boolean visit(TryStatement node) {
		List list = node.catchClauses();
		
		for(Object obj : list) {
			if(obj.toString().contains("System.exit")) {
				info.numOfAbortInCatch++;
			}
		}
		return super.visit(node);
	}
}
