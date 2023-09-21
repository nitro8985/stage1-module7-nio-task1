package com.epam.mjc.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
        List<String> lines = new ArrayList<>(4);
        try (RandomAccessFile accessFile = new RandomAccessFile(file.getAbsolutePath(), "r");
             FileChannel channel = accessFile.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(512);
            channel.read(buffer);
            buffer.flip();
            lines = separateIntoLines(buffer);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(lines.get(0), Integer.valueOf(lines.get(1)), lines.get(2), Long.valueOf(lines.get(3)));
    }

    private List<String> separateIntoLines(ByteBuffer buffer) {
        List<String> lines = new ArrayList<>();
        while (buffer.hasRemaining()) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < buffer.limit(); j++) {
                char c = (char) buffer.get();
                if (c == '\n') {
                    break;
                }
                line.append(c);
            }
            lines.add(line.substring(line.indexOf(" ") + 1).trim());
        }
        return lines;
    }
}
