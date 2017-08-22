package com.beso9595.rmp;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    private static File folder = new File("/home/beso9595/Music");
    private static ArrayList<File> musicList;
    private static boolean[] played;
    private static int countPlayed;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        init();
        String query;
        while ((query = in.nextLine()) != null) {
            if (query.length() > 1) {
                String command = query.substring(0, 2);

                if (command.equals(":q")) {
                    break;
                } else if (command.equals(":i")) {
                    String[] querySplitted = query.split(" ");
                    if (querySplitted.length > 1) {
                        try {
                            play(Integer.parseInt(querySplitted[1]), true);
                        } catch (NumberFormatException e) {
                            System.out.println(inColor(Message.WRONG_ID_FORMAT, 1));
                        }
                    }
                } else if (command.equals(":s")) {
                    String[] querySplitted = query.split(" ");
                    if (querySplitted.length > 1) {
                        String str = querySplitted[1];
                        if (!str.isEmpty()) {
                            for (File m : musicList) {
                                if (m.getName().toLowerCase().contains(str.toLowerCase())) {
                                    print(m.getName(), musicList.indexOf(m), true);
                                }
                            }
                        }
                    }
                } else if (command.equals(":l")) {
                    String[] querySplitted = query.split(" ");
                    ArrayList<File> sortedList = (ArrayList) musicList.clone();
                    if (querySplitted.length > 1) {
                        String str = querySplitted[1];
                        if (!str.isEmpty()) {
                            if (str.equals("-n")) {
                                sortedList.sort(Comparator.comparing(File::getName));
                            }
                        }
                    }
                    for (File m : sortedList) {
                        print(m.getName(), musicList.indexOf(m), true);
                    }
                } else if (command.equals(":p")) {
                    String[] querySplitted = query.split(" ");
                    if (querySplitted.length > 1) {
                        String str = querySplitted[1];
                        if (!str.isEmpty()) {
                            File newDir = new File(str);
                            if (newDir.isDirectory()) {
                                folder = newDir;
                                init();
                                System.out.println(inColor(Message.SWITCHED_TO_NEW_PATH, 5) + ": " + str);
                            } else {
                                System.out.println(inColor(Message.INVALID_PATH, 1) + ": " + str);
                            }
                        }
                    }
                }
            } else {
                next();
            }
        }
    }

    private static void next() throws IOException {
        if (isOver()) {
            init();
            countPlayed = 0;
        }
        Random rand = new Random();
        while (true) {
            int n = rand.nextInt(musicList.size()) + 1;
            if (!isPlayed(n)) {
                makePlayed(n);
                play(n, false);
                break;
            }
        }
    }

    private static void makePlayed(int n) {
        played[n - 1] = true;
        countPlayed++;
    }

    private static boolean isOver() {
        for (boolean bl : played) {
            if (!bl) {
                return false;
            }
        }
        return true;
    }

    private static void play(int n, boolean count) throws IOException {
        int id = n - 1;
        File file = new File(musicList.get(id).getAbsolutePath());
        print(file.getName(), id, count);
        if (Desktop.isDesktopSupported()) {
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println(inColor(Message.FILE_DOESNT_EXIST, 1));
            }
        }
    }

    private static void print(String filename, int id, boolean count) {
        filename = filename.substring(0, filename.length() - 4);
        if (!count) {
            System.out.println(inColor("(", 5) + inColor(Integer.toString(countPlayed), 3) + "/" + inColor(Integer.toString(musicList.size()), 4) + inColor(")", 5));
        }
        System.out.println(inColor(Integer.toString(id + 1), 6) + ": " + inColor(filename, 2));
    }

    private static boolean isPlayed(int n) {
        return played[n - 1];
    }

    private static void init() {
        folder = new File(System.getProperty("user.home") + "/Music");
        if (folder.isDirectory()) {
            countPlayed = 0;
            musicList = new ArrayList<>();

            if (folder.listFiles() != null) {
                for (File listOfFile : folder.listFiles()) {
                    String filename = listOfFile.getName();
                    if (listOfFile.isFile() && (filename.length() > 4) && (filename.substring(filename.length() - (4)).equalsIgnoreCase(".mp3"))) {
                        musicList.add(listOfFile);
                    }
                }
            }
            played = new boolean[musicList.size()];
        } else {
            System.out.println(inColor(Message.INVALID_DEFAULT_DIRECTORY, 1) + ": " + folder.getAbsolutePath());
        }

    }

    private static String inColor(String str, int color) {
        //1 - red
        //2 - green
        //3 - yellow
        //4 - blue
        //5 - purple
        //6 - cyan
        return "\u001B[3" + color + "m" + str + "\u001B[0m";
    }
}
