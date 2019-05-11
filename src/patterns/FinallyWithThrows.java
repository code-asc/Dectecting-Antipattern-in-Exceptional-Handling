package patterns;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TryStatement;

import beans.PatternInfo;
import helpers.DetectMethodInBlock;

/**
 * This class is used to detect the pattern that has method in the
 * finally block throwing the exception.
 * @author sandeepchowdaryannabathuni
 *
 */
public class FinallyWithThrows extends ASTVisitor {
	
	private final PatternInfo obj;
	
	
	public FinallyWithThrows(PatternInfo info) {
		obj = info;
	}
	
	
	@Override
	public boolean visit(TryStatement node) {
		
		final DetectMethodInBlock detectMethodInBlock = new DetectMethodInBlock();
		Block block = node.getFinally();
		
		if(block != null) {
			
			block.accept(detectMethodInBlock);
			
			for(MethodInvocation mi : detectMethodInBlock.getMethodsInBlock()) {
				if(mi != null && isMethodThrowingException(mi)) {
					obj.numOfFinallyBlockWithThrows = obj.numOfFinallyBlockWithThrows + 1;
				}
			}
		}
		
		
		return super.visit(node);
	}
	
	
	private boolean isMethodThrowingException(MethodInvocation node) {
		if(node.resolveMethodBinding() != null && node.resolveMethodBinding().getExceptionTypes() != null && 
				node.resolveMethodBinding().getExceptionTypes().length > 0)
			return true;
		
		return false;
	}

}
