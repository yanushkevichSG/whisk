package utilities;

import com.github.javafaker.Faker;

public class Fake {

    private static final Faker FAKER = new Faker();

    private Fake() {
    }

    public static String shoppingList() {
        return FAKER.company().name();
    }
}
