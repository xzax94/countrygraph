package gr.uoi.cse.countrygraph.form;

import gr.uoi.cse.countrygraph.GraphController;

@FunctionalInterface
public interface FormCreator
{
	void createFormWindow(GraphController graphController, String measureName);
}