package patterns;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import beans.PatternInfo;

public class ThrownGeneric extends ASTVisitor{
	
	private final PatternInfo info;
	
	public ThrownGeneric(PatternInfo info) {
		super();
		this.info = info;
	}
	
	@Override
	public boolean visit(MethodDeclaration md) {
		
		List exceptions = md.thrownExceptionTypes();
		
		for(Object obj : exceptions) {
			
			if(obj.toString().equals("Exception"))
				info.numThrownGeneric++;
		}
		
		return super.visit(md);
	}
}
