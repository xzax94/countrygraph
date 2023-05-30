package gr.uoi.cse.countrygraph.classcollector;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassCollector
{
    public List<Class<?>> collectClasses(String packageName) 
    {
        final List<Class<?>> classList = new ArrayList<>();

        try 
        {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final String packagePath = packageName.replace('.', '/');
            final Enumeration<URL> resources = classLoader.getResources(packagePath);

            while (resources.hasMoreElements()) 
            {
                final URL resource = resources.nextElement();
                final File file = new File(resource.toURI());

                if (file.isDirectory()) 
                {
                    collectClassesFromDirectory(file, packageName, classList);
                }
                else if (file.getName().endsWith(".class")) 
                {
                    final String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);

                    try
                    {
                        final Class<?> clazz = Class.forName(className);
                        classList.add(clazz);
                    } 
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        } 
        catch (final Exception e) 
        {
            e.printStackTrace();
        }

        return classList;
    }

    private void collectClassesFromDirectory(File directory, String packageName, List<Class<?>> classList)
    {
        final File[] files = directory.listFiles();

        if (files != null) 
        {
            for (File file : files) 
            {
                if (file.isDirectory())
                {
                    collectClassesFromDirectory(file, packageName + '.' + file.getName(), classList);
                } 
                else if (file.getName().endsWith(".class"))
                {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);

                    try 
                    {
                        final Class<?> clazz = Class.forName(className);
                        classList.add(clazz);
                    } 
                    catch (final ClassNotFoundException e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
