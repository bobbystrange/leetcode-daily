package org.dreamcat.daily.juc.on2007;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Create by tuke on 2020/7/18
 */
public class On18_piped {

    public static void main(String[] args) throws IOException {
        var in = new PipedInputStream();
        var out = new PipedOutputStream();
        out.connect(in);

        new Thread(() -> {
            int i;
            try (in) {
                while ((i = in.read()) != -1) {
                    System.out.print((char) i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, "Print").start();

        try (out) {
            for (char c : "Hello word".toCharArray()) {
                out.write(c);
            }
        }

    }
}
