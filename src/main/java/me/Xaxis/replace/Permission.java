package me.Xaxis.replace;

public enum Permission {

    ADMIN,
    BYPASS,
    REPLACE_ITEMS,
    PANIC_CHEST_LOCATION;

    public String permission(){

        return switch (this) {
            case ADMIN -> "IllegalItemReplacer.*";
            case BYPASS -> "IllegalItemReplacer.bypass";
            case REPLACE_ITEMS -> "IllegalItemReplacer.replaceItems";
            case PANIC_CHEST_LOCATION -> "IllegalItemReplacer.setPanicChestLocation";
        };

    }

}
