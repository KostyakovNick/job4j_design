package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

        private static final String OUT = "закончить";
        private static final String STOP = "стоп";
        private static final String CONTINUE = "продолжить";
        private final String path;
        private final String botAnswers;

        public ConsoleChat(String path, String botAnswers) {
            this.path = path;
            this.botAnswers = botAnswers;
        }

        public void run() {
            List<String> listBot = readPhrases();
            List<String> list = new ArrayList<>();
            String s = "Bot: Привет, начнем беседу?";
            System.out.println(s);
            list.add(s);
            Scanner console = new Scanner(System.in);
            while (!OUT.equals(s)) {
                System.out.print("User: ");
                String say = console.nextLine();
                list.add("User: " + say);
                if (say.equals(OUT) || say.equals(STOP)) {
                    s = say;
                }
                if (!OUT.equals(s) && (!STOP.equals(s) || CONTINUE.equals(say))) {
                    int x = (int) (Math.random() * listBot.size());
                    System.out.println("Bot: " + listBot.get(x));
                    list.add("Bot: " + listBot.get(x));
                    s = say;
                }
            }
            saveLog(list);
        }

        private List<String> readPhrases() {
            List<String> rsl = new ArrayList<>();
            try (BufferedReader read = new BufferedReader(new FileReader(this.botAnswers))) {
                rsl = read.lines().filter(line -> !line.isBlank()).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rsl;
        }

        private void saveLog(List<String> log) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
                for (String s : log) {
                    pw.println(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\chat.log", "C:\\projects\\job4j_design\\data\\chat.txt");
            cc.run();
        }

}
