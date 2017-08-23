package com.beso9595.rmp;


public interface Message {

    public static final String VERSION_TEXT = "Version";
    public static final String WRONG_ID_FORMAT = "Wrong id format";
    public static final String FILE_DOESNT_EXIST = "File doesn't exists";
    public static final String INVALID_PATH = "Invalid path";
    public static final String SWITCHED_TO_NEW_PATH = "Switched to new path";
    public static final String CURRENT_PATH = "Current path";
    public static final String INVALID_DEFAULT_PATH = "Invalid default path";
    public static final String OVER = "List is over, choose new path or repeat the current";

    public static final double VERSION = 1.0;
    public static final String WELCOME = "\n" +
            "  ___ __  __ ___ \n" +
            " | _ \\  \\/  | _ \\\n" +
            " |   / |\\/| |  _/\n" +
            " |_|_\\_|  |_|_|  \n" +
            "                 \n";

}
