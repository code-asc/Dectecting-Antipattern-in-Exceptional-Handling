package helpers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

/**
 * This class is used to check the methods that are called in
 * the block of code.
 * @author sandeepchowdaryannabathuni
 *
 */
public class DetectMethodInBlock extends ASTVisitor {
	
	private Set<MethodInvocation> methodsInBlock = null;
	
	public DetectMethodInBlock() {
		
		methodsInBlock = new HashSet<MethodInvocation>();
	}
	
	
	@Override
	public boolean visit(MethodInvocation node) {
		
		methodsInBlock.add(node);
		return super.visit(node);
	}
	
	/**
	 * @return MethodInvocation Object
	 */
	public Set<MethodInvocation> getMethodsInBlock() {
		return methodsInBlock;
	}
	
}
