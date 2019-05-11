package patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TryStatement;

import beans.PatternInfo;

public class NestedTry extends ASTVisitor{
	
	private final String REGEX = "(\\s)*try(\\s|\\n|\\r){0,}\\{";
	private final PatternInfo obj;
	
	public NestedTry(PatternInfo info) {
		obj = info;
	}
	
	@Override
	public boolean visit(TryStatement node) {
		
		String body = node.getBody().toString();
		
		if(hasNestedTry(body))
			obj.numOfNestedTry = obj.numOfNestedTry + 1;
		
		return super.visit(node);
	}
	
	private boolean hasNestedTry(String body) {
		
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(body);
		
		while(matcher.find())
			return true;
		
		return false;
	}
}
