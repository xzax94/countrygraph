package gr.uoi.cse.countrygraph.exception.handler;

import gr.uoi.cse.countrygraph.dialogue.DialogueDisplayer;

public final class BarChartMeasureExceptionHandler implements ExceptionHandler
{
	private static final String DIALOGUE_MESSAGE = "Bar Chart requires at least 1 measure!";
	
	@Override
	public void handleException(Throwable exception) 
	{
		final DialogueDisplayer dialogueDisplayer = DialogueDisplayer.getInstance();
		dialogueDisplayer.displayDialogue(DIALOGUE_MESSAGE);
	}
}