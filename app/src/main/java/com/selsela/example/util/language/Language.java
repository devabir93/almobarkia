package com.selsela.example.util.language;

public enum Language {
    KEY("lang"), ARABIC("ar"), ENGLISH("en");

    private String language;

    private Language(String language) {
        this.language = language;
    }

    public static String KEY() {
        return KEY.toString();
    }

    public static String ARABIC() {
        return ARABIC.toString();
    }

    public static String ENGLISH() {
        return ENGLISH.toString();
    }

    @Override
    public String toString() {
        return language.toString();
    }
}
