package com.efgh.avraelayout;

public class Modifiers {
    private static Boolean IS_ADVANTAGE = Boolean.FALSE;
    private static Boolean IS_DISADVANTAGE = Boolean.FALSE;

    public static boolean switchAdvantage(){
        IS_ADVANTAGE = !IS_ADVANTAGE;
        return IS_ADVANTAGE;
    }

    public static boolean hasAdvantage(){
        return IS_ADVANTAGE.booleanValue();
    }

    public static boolean switchDisadvantage(){
        IS_DISADVANTAGE = !IS_DISADVANTAGE;
        return IS_DISADVANTAGE;
    }

    public static boolean hasDisadvantage(){
        return IS_DISADVANTAGE.booleanValue();
    }
}
