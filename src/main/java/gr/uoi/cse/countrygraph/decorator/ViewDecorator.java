package gr.uoi.cse.countrygraph.decorator;

@FunctionalInterface
public interface ViewDecorator<T>
{
	void decorateView(T controller);
}