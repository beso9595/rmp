package com.beso9595.rmp;


public interface Message {

    String VERSION_TEXT = "Version";
    String WRONG_NUMBER_FORMAT = "Wrong number format";
    String FILE_DOESNT_EXIST = "File doesn't exists";
    String INVALID_PATH = "Invalid path";
    String SWITCHED_TO_NEW_PATH = "Switched to new path";
    String CURRENT_PATH = "Current path";
    String INVALID_DEFAULT_PATH = "Invalid default path";
    String LIST_IS_OVER = "List is over, choose new path or repeat the current";
    String MUST_BE_LESS = "Must be less then non-played songs quantity";
    String EMPTY_DIR = "Not a single *.mp3 format file was found in directory";

    String VERSION = "1.3";
    String WELCOME = "\n" +
            "  ___ __  __ ___ \n" +
            " | _ \\  \\/  | _ \\\n" +
            " |   / |\\/| |  _/\n" +
            " |_|_\\_|  |_|_|  \n" +
            "                 \n";

}
