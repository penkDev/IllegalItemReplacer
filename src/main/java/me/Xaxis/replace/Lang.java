package me.Xaxis.replace;

public enum Lang {

    NO_PERMISSION,
    SENDER_NOT_PLAYER,
    PREFIX;

    public String getMessage(IIR instance){

        switch (this) {
            case NO_PERMISSION -> {
                return instance.getConfig().getString("LANG.NO_PERMISSION");
            }
            case SENDER_NOT_PLAYER -> {
                return instance.getConfig().getString("LANG.SENDER_NOT_PLAYER");
            }
            case PREFIX -> {
                if(instance.getConfig().getBoolean("OPTIONS.PREFIX.USE_PREFIX")){
                    return instance.getConfig().getString("OPTIONS.PREFIX.PREFIX_TEXT");
                }
                return "";
            }
        };

        return "";
    }
}
