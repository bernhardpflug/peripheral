/*
 * Class that searches for subclasses of given class
 * in given package
 *
 * uses the spring framework
 */

package peripheral.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

/**
 *
 * @author Berni
 */
public class SubclassLoader {

    /**
     * Creates a List with all subclasses as class instances of given class
     * in given package
     *
     * packageName has to fit following formate: org/example/package
     *
     * @throws ClassNotFoundException if found class definitions can't be instanced
     * @return List with Class instances of all subclasses
     */
    public static List getSubclasses(Class forClass, String packageName) throws ClassNotFoundException{

        ArrayList subClassList = new ArrayList();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(forClass));

        Set<BeanDefinition> components = provider.findCandidateComponents(packageName);
        for (BeanDefinition component : components) {

            Class cls = Class.forName(component.getBeanClassName());
            subClassList.add(cls);
        }

        return subClassList;
    }
}
