package constants;

import java.util.Arrays;

public enum NavMenu {
    EXPLORE("Explore", "explore-nav-link"),
    SAVED("Saved", "recipe-box-nav-link"),
    COMMUNITIES("Communities", "5baeaaa4-dd14-d060-8f1f-037f12662889"),
    PLANNER("Planner", "meal-plan-nav-link"),
    SHOPPING("Shopping", "shopping-list-nav-link");

    private final String navMenuName;
    private final String navMenuTestId;

    NavMenu(String navMenuName, String navMenuTestId) {
        this.navMenuName = navMenuName;
        this.navMenuTestId = navMenuTestId;
    }

    public String getNavMenuName() {
        return navMenuName;
    }

    public String getNavMenuTestId() {
        return navMenuTestId;
    }

    public static String getDataTestIdByName(String dataTestId) {
        NavMenu foundResult = Arrays.stream(NavMenu.values())
                .filter(p -> p.getNavMenuName().equals(dataTestId))
                .findFirst()
                .orElse(null);
        return foundResult.getNavMenuTestId();
    }
}
