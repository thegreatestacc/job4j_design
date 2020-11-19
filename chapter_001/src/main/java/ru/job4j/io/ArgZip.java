package ru.job4j.io;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length < 6) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public String directory() {
        if (valid() && args[0].equals("-d")) {
            return args[1];
        }
        return "";
    }

    public String exclude() {
        if (valid() && args[0].equals("-e")) {
            return args[1];
        }
        return "";
    }

    public String output() {
        if (valid() && args[0].equals("-o")) {
            return args[1];
        }
        return "";
    }
}
