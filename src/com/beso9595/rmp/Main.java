package com.beso9595.rmp;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    private static File folder = new File("/home/beso9595/Music");
    private static ArrayList<File> musicList;
    private static boolean[] played;
    private static int countPlayed = 0;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        load();
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
                            play(Integer.parseInt(querySplitted[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("\u001B[31mWrong id format\u001B[0m");
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
                    ArrayList<File> sortedList = (ArrayList)musicList.clone();
                    if (querySplitted.length > 1) {
                        String str = querySplitted[1];
                        if (!str.isEmpty()) {
                            if (str.equals("-n")) {
                                sortedList.sort(Comparator.comparing(File::getName));
                            }
                        }
                    }
                    for (File m : sortedList) {
                        print(m.getName(), sortedList.indexOf(m), true);
                    }
                }
            } else {
                next();
            }
        }
    }

    private static void next() throws IOException {
        if (isOver()) {
            load();
            countPlayed = 0;
        }
        Random rand = new Random();
        while (true) {
            int n = rand.nextInt(musicList.size()) + 1;
            if (!isPlayed(n)) {
                makePlayed(n);
                play(n);
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

    private static void play(int n) throws IOException {
        int id = n - 1;
        File file = new File(musicList.get(id).getAbsolutePath());
        print(file.getName(), id, false);
        if (Desktop.isDesktopSupported()) {
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("\u001B[31mFile doesn't exists\u001B[0m");
            }
        }
    }

    private static void print(String filename, int id, boolean search) {
        filename = filename.substring(0, filename.length() - 4);
        if (!search) {
            System.out.println("\u001B[35m(\u001B[0m\u001B[33m" + countPlayed + "\u001B[0m/\u001B[34m" + musicList.size() + "\u001B[0m\u001B[35m)\u001B[0m");
        }
        System.out.println("\u001B[36m" + (id + 1) + ": \u001B[0m\u001B[32m" + filename + "\u001B[0m");
    }

    private static boolean isPlayed(int n) {
        return played[n - 1];
    }

    private static void load() {
        musicList = new ArrayList<>();
        //
        if (folder.listFiles() != null) {
            for (File listOfFile : folder.listFiles()) {
                String filename = listOfFile.getName();
                if (listOfFile.isFile() && (filename.length() > 4) && (filename.substring(filename.length() - (4)).equalsIgnoreCase(".mp3"))) {
                    musicList.add(listOfFile);
                }
            }
        }
        //
        played = new boolean[musicList.size()];
    }
}
