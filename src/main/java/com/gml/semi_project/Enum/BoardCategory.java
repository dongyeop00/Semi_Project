package com.gml.semi_project.Enum;

public enum BoardCategory {
    ANNOUNCEMENT, LEVELUP, FREE, FILE;
    public static BoardCategory of(String category){
        if(category.equalsIgnoreCase("announcement"))
            return BoardCategory.ANNOUNCEMENT;
        else if(category.equalsIgnoreCase("levelup"))
            return BoardCategory.LEVELUP;
        else if(category.equalsIgnoreCase("free"))
            return BoardCategory.FREE;
        else if(category.equalsIgnoreCase("file"))
            return BoardCategory.FILE;
        else
            return null;
    }
}
