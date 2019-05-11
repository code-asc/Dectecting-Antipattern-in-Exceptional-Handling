package patterns;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.LineComment;

import beans.PatternInfo;

public class IncompleteImpl extends ASTVisitor {
	
	private String src;
	private final PatternInfo info;
	
	public IncompleteImpl(String src, PatternInfo info) {
		super();
		this.src = src;
		this.info = info;
	}

	
	@Override
	public boolean visit(LineComment node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		
		if(src.substring(start, end).contains("TODO") || 
				src.substring(start, end).contains("todo") || 
				src.substring(start, end).contains("TO DO")||
				src.substring(start, end).contains("to do") || 
				src.substring(start, end).contains("FIXME") || 
				src.substring(start, end).contains("fixme")||
				src.substring(start, end).contains("fix me")){
			
			info.numIncompleteImpl++;
		}
			
		return super.visit(node);
	}
	
	@Override 
	public boolean visit(BlockComment node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		
		if(src.substring(start, end).contains("TODO") || 
				src.substring(start, end).contains("todo") || 
				src.substring(start, end).contains("TO DO")||
				src.substring(start, end).contains("to do") || 
				src.substring(start, end).contains("FIXME") || 
				src.substring(start, end).contains("fixme")||
				src.substring(start, end).contains("fix me")){
			
			info.numIncompleteImpl++;
		}
		return super.visit(node);
	}
}
