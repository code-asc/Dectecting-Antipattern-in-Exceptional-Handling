package tools;

import org.eclipse.jdt.core.dom.ASTNode;

/**
 * This method is used to retrieve the parent of block of code.
 * @author sandeepchowdaryannabathuni
 *
 */
public class Parent {
	
	/**
	 * This method takes ASTNode object as input and return the
	 * parent of particular block of code.
	 * @param node
	 * @return ASTNode object.
	 */
	public static ASTNode getParent(ASTNode node) {
		
		if(node.getParent().getNodeType() == ASTNode.METHOD_DECLARATION)
			return node.getParent();
		return getParent(node.getParent());
	}
}
