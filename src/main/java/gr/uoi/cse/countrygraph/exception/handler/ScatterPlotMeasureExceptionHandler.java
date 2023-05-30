package gr.uoi.cse.countrygraph.exception.handler;

import gr.uoi.cse.countrygraph.dialogue.DialogueDisplayer;

public final class ScatterPlotMeasureExceptionHandler implements ExceptionHandler
{
	private static final String DIALOGUE_MESSAGE = "Scatter Plot requires exactly 2 measures!";
	
	@Override
	public void handleException(Throwable exception) 
	{
		final DialogueDisplayer dialogueDisplayer = DialogueDisplayer.getInstance();
		dialogueDisplayer.displayDialogue(DIALOGUE_MESSAGE);
	}
}