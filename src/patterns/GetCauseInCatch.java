package patterns;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

import beans.PatternInfo;
import helpers.DetectMethodInBlock;

/**
 * This class is used to detect the use of getCause()
 * in the catch block.
 * @author sandeepchowdaryannabathuni
 *
 */
public class GetCauseInCatch extends ASTVisitor {
	
	private final PatternInfo obj;
	
	
	public GetCauseInCatch(PatternInfo info) {
		obj = info;
	}
	
	
	@Override
	public boolean visit(CatchClause node) {
		
		final DetectMethodInBlock detectMethodInBlock = new DetectMethodInBlock();
		
		node.accept(detectMethodInBlock);
		
		for(MethodInvocation mi : detectMethodInBlock.getMethodsInBlock()) {
			if(isGetCause(mi)) {
				obj.numOfGetCause = obj.numOfGetCause + 1; 
			}
		}
		return super.visit(node);
	}
	
	
	/**
	 * @param method_invoke It is the MethodInvocation object
	 * @return
	 */
	private boolean isGetCause(MethodInvocation method_invoke) {
		
		if(method_invoke != null) {
			if(method_invoke.resolveMethodBinding() != null) {
				IMethodBinding binding = method_invoke.resolveMethodBinding();
				if(binding.getName() != null) {
					
					String value = binding.getName().toString();
					if(value != null && value.equals("getCause"))
						return true;
					}
			}
		
		}
		return false;
	}

}
