package constants;

import java.util.Arrays;
import java.util.List;

public class Items {
    public final static String MILK = "Milk";
    public final static String EGGS = "Eggs";
    public final static String BREAD = "Bread";
    public final static String BUTTER = "Butter";
    public final static String CHEESE = "Cheese";

    public final static List<String> allItems = Arrays.asList(MILK, EGGS, BREAD, BUTTER, CHEESE);
    public final static Object[][] allDataProviderItems = { {MILK}, {EGGS},{BREAD}, {BUTTER}, {CHEESE} };
}
