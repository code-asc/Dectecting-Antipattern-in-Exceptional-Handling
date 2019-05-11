package antipattern.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import beans.PatternInfo;
import patterns.AbortCatchBlock;
import patterns.CatchReturnNull;
import patterns.FinallyWithThrows;
import patterns.GenericCatch;
import patterns.GetCauseInCatch;
import patterns.IncompleteImpl;
import patterns.NestedTry;
import patterns.NullCatchBlock;
import patterns.ThrownGeneric;
import patterns.ThrowsKitchen;
import tools.Parser;


public class SampleHandler extends AbstractHandler {
	
	private String globalAccess;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			
			FileWriter fw = new FileWriter(new File("/Users/sandeepchowdaryannabathuni/eclipse-workspace/antipattern/data.csv"));
			fw.append("file");
			fw.append(",");
			fw.append("nullcatch");
			fw.append(",");
			fw.append("throwskitchen");
			fw.append(",");
			fw.append("finallywiththrow");
			fw.append(",");
			fw.append("nestedtry");
			fw.append(",");
			fw.append("getcause");
			fw.append(",");
			fw.append("genericcatch");
			fw.append(",");
			fw.append("abortincatch");
			fw.append(",");
			fw.append("throwngeneric");
			fw.append(",");
			fw.append("incompleteimpl");
			fw.append(",");
			fw.append("catchreturnnull");
			fw.append("\n");
			
			
			
			
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			
			IProject[] projects = root.getProjects();
			
			for(IProject project : projects) {
				IJavaProject javaProject = JavaCore.create(project);
				
				try {
					IPackageFragment[] fragments = javaProject.getPackageFragments();
					
					for(IPackageFragment fragment : fragments) {
						ICompilationUnit[] iCompilationUnits = fragment.getCompilationUnits();
						
						for(ICompilationUnit iCompilationUnit : iCompilationUnits) {
							
							
							CompilationUnit compilationUnit = Parser.parse(iCompilationUnit);
							
							PatternInfo info = new PatternInfo();
							processAntiPatterns(compilationUnit, info, iCompilationUnit);
							//PatternInfo info = PatternInfo.getInstance();
							
							
							
							IResource resource = iCompilationUnit.getUnderlyingResource();
							if (resource.getType() == IResource.FILE) {
								IFile ifile = (IFile) resource;
							    String path = ifile.getRawLocation().toString()
							    					.replace("/Users/sandeepchowdaryannabathuni/eclipse-workspace/hadoop/hadoop/", "");
							    globalAccess = path;
							    //trackFileName = path;
							  //  System.out.println("Path : " + path);
							   // bw.write("Path: " + path + "\n");
							}
							//System.out.println("Path : " + iCompilationUnit.getPath().makeRelative().toString());
							
							System.out.println("Null Catch : " + info.numOfNullCatch);
							
							
							System.out.println("ThrowsKitchen : " + info.numOfThrowKitchen);
						
							
							System.out.println("Finally with throws: " + info.numOfFinallyBlockWithThrows);		
							
							
							System.out.println("Nested try : " + info.numOfNestedTry);
							
							
							System.out.println("GetCause : " + info.numOfGetCause);
						
							
							System.out.println("GenericCatch : " + info.numOfGenericCatch);
							
							
							System.out.println("AbortInCatch : " + info.numOfAbortInCatch);
							
							
							System.out.println("ThrownGeneric : " + info.numThrownGeneric);
							
							
							System.out.println("IncompleteImpl : " + info.numIncompleteImpl);
						
							
							System.out.println("CatchReturnNull : " + info.numCatchReturnNull);
							
							
							List<String> row = Arrays.asList(globalAccess,
													Integer.toString(info.numOfNullCatch), 
													Integer.toString(info.numOfThrowKitchen),
													Integer.toString(info.numOfFinallyBlockWithThrows),
													Integer.toString(info.numOfNestedTry),
													Integer.toString(info.numOfGetCause),
													Integer.toString(info.numOfGenericCatch),
													Integer.toString(info.numOfAbortInCatch),
													Integer.toString(info.numThrownGeneric),
													Integer.toString(info.numIncompleteImpl),
													Integer.toString(info.numCatchReturnNull));
							
							System.out.println("*".repeat(20));
							
							fw.append(String.join(",", row));
							fw.append("\n");
							
							fw.flush();
							
						}
					}
					
				} catch (JavaModelException e) {
	
					List<String> row = Arrays.asList(globalAccess,
							"0", 
							"0",
							"0",
							"0",
							"0",
							"0",
							"0",
							"0",
							"0",
							"0");
					
					fw.append(String.join(",", row));
					fw.append("\n");
					fw.flush();
				}
				
			}
			
			fw.close();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		return null;
	}
	
	
	private void processAntiPatterns(CompilationUnit compilationUnit, PatternInfo info, ICompilationUnit icompilationInit) throws JavaModelException {
		CompilationUnit unit = compilationUnit;
		
		NestedTry patternOne = new NestedTry(info);
		FinallyWithThrows patternTwo = new FinallyWithThrows(info);
		GetCauseInCatch patternThree = new GetCauseInCatch(info);
		ThrowsKitchen patternFour = new ThrowsKitchen(info);
		NullCatchBlock patternFive = new NullCatchBlock(info);
		AbortCatchBlock patternSix = new AbortCatchBlock(info);
		CatchReturnNull patternSeven = new CatchReturnNull(info);
		GenericCatch patternEight = new GenericCatch(info);
		ThrownGeneric patternNine = new ThrownGeneric(info);
		IncompleteImpl patternTen = new IncompleteImpl(icompilationInit.getSource(), info);
		
		unit.accept(patternOne);
		unit.accept(patternTwo);
		unit.accept(patternThree);
		unit.accept(patternFour);
		unit.accept(patternFive);
		unit.accept(patternSix);
		unit.accept(patternSeven);
		unit.accept(patternEight);
		unit.accept(patternNine);
		
		for(Comment comment : (List<Comment>) unit.getCommentList())
			unit.accept(patternTen);
		
		
	}
	
	
}
