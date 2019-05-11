package patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;

import beans.PatternInfo;

public class CatchReturnNull extends ASTVisitor {
	
	private final String regex = "(return)\\s*;|return\\s*null;";
	private final PatternInfo info;
	
	
	public CatchReturnNull(PatternInfo info) {
		super();
		this.info = info;
	}
	
	
	@Override
	public boolean visit(CatchClause node) {
		
		String src = node.getBody().toString();
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(src);
		
		if(match.find()) 
			info.numCatchReturnNull++;
		
		return super.visit(node);
	}
}
