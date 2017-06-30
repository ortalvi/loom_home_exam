package com.ortal.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Maor on 29/06/2017.
 */
public class Main {
    public static void main(String[] args) {
        Investigator investigator = new Investigator();
        List<String> file = new ArrayList<String>();
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                file.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("output.txt"));

            Collection<Group> groups = investigator.process(file);
            for (Group cur : groups) {
                out.println(cur.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
