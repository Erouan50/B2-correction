package com.supinfo.bitmaptransformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Main {

    public static void main(String[] args) {
        try {
            List<Integer> integers = readFile(new FileInputStream("bitmap.bmp"));

            List<Integer> subList = integers.subList(138, integers.size());
            addToPixel(subList, 200);

            writeToFile(new FileOutputStream("bitmap_new.bmp"), subList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> readFile(FileInputStream inputStream) {
        List<Integer> integers = new ArrayList<Integer>();
        try {
            int buff;
            while (-1 != (buff = inputStream.read())) {
                integers.add(buff);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
        }
        return integers;
    }

    public static void addToPixel(List<Integer> pixels, int value) {
        for (int i = 0; i < pixels.size(); i++) {
            int pixel = pixels.get(i);
            pixel = (pixel + value) % 255;
            pixels.set(i, pixel);
        }
    }

    public static void writeToFile(FileOutputStream fileOutputStream, List<Integer> pixels) {

        try {
            for (Integer pixel : pixels) {
                fileOutputStream.write(pixel);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {

                }
            }
        }

    }
}
