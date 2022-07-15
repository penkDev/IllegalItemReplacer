package me.Xaxis.replace;

public enum GUI {

    EMPTY_GUI,
    REPLACEMENT_GUI;

    public String getPath(){

        switch (this){

            case EMPTY_GUI -> {
                return "GUI.PLACE_ITEMS_GUI";
            }
            case REPLACEMENT_GUI -> {
                return "GUI.REPLACEMENT_ITEMS_GUI";
            }

        }
        return "";

    }

    public String getTitle(IIR instance){
        switch (this){

            case EMPTY_GUI -> {
                return instance.getConfig().getString("GUI.PLACE_ITEMS_GUI.TITLE");
            }
            case REPLACEMENT_GUI -> {
                return instance.getConfig().getString("GUI.REPLACEMENT_ITEMS_GUI.TITLE");
            }

        }
        return "";
    }

    public int getSize(IIR instance){

        switch (this){

            case EMPTY_GUI -> {
                return instance.getConfig().getInt("GUI.PLACE_ITEMS_GUI.SIZE");
            }

            case REPLACEMENT_GUI -> {
                return instance.getConfig().getInt("GUI.REPLACEMENT_ITEMS_GUI.SIZE");
            }

        }
        return 0;
    }

    public String getPathName(){
        switch (this){

            case REPLACEMENT_GUI -> {
                return "REPLACEMENT_ITEMS_GUI";
            }
            case EMPTY_GUI -> {
                return "PLACE_ITEMS_GUI";
            }

        }
        return "";
    }
}
