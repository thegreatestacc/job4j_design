package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void getWorkingPeriod() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.write("200 10:56:01\n" +
                    "\n" +
                    "500 10:57:01\n" +
                    "\n" +
                    "400 10:58:01\n" +
                    "\n" +
                    "200 10:59:01\n" +
                    "\n" +
                    "500 11:01:02\n" +
                    "\n" +
                    "200 11:02:02");
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }

        assertThat(result.toString(), is("начало периода 400\n " +
                "конец периода 200\n " +
                "начало периода 500\n " +
                "конец периода 200\n "));
    }
}
