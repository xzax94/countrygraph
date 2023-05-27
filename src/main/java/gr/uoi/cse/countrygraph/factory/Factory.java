package gr.uoi.cse.countrygraph.factory;

@FunctionalInterface
public interface Factory<K, V>
{
	V createNewInstance(K key);
}