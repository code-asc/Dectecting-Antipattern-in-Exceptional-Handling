package patterns;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import beans.PatternInfo;

/**
 * This class is used to record the ThrowsKitchen antipattern 
 * in the code.
 * @author sandeepchowdaryannabathuni
 *
 */
public class ThrowsKitchen extends ASTVisitor{
	
	private final PatternInfo obj;
	
	
	public ThrowsKitchen(PatternInfo info) {
		obj = info;
	}
	
	
	@Override
	public boolean visit(MethodDeclaration node ) {
		
		if(hasThrowsKitchen(node))
			obj.numOfThrowKitchen = obj.numOfThrowKitchen + 1;
		
		return super.visit(node);
	}
	
	/**
	 * @param node
	 * @return true if the method throws more than two
	 * exceptions, otherwise false.
	 */
	private boolean hasThrowsKitchen(MethodDeclaration node) {
		
		if(node.thrownExceptionTypes().size() > 2) 
			return true;
		
		return false;
	}
}
