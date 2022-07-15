package me.Xaxis.replace;

public enum Permission {

    ADMIN,
    BYPASS,
    REPLACE_ITEMS;

    public String permission(){

        return switch (this) {
            case ADMIN -> "IllegalItemReplacer.*";
            case BYPASS -> "IllegalItemReplacer.bypass";
            case REPLACE_ITEMS -> "IllegalItemReplacer.replaceItems";
        };

    }

}
