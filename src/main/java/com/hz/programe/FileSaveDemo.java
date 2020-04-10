package com.hz.programe;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @authod: pp_lan on 2020/3/13.
 */
public class FileSaveDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Record> map = new HashMap<>();
        while (sc.hasNext()) {
            String[] info = sc.nextLine().split(" ");
            String fileName = info[0].substring(info[0].lastIndexOf("\\") + 1);

            Record r = map.get(info[0] + " " + info[1]);
            if (r == null) {
                Record record = new Record(fileName, info[1]);
                record.setCount(1);
                map.put(info[0] + " " + info[1], record);
            } else {
                r.addCount();
            }
            Record nr = map.get(info[0] + " " + info[1]);
            System.out.println(nr.getFileName() + " " + nr.getLineNum() + " " + nr.getCount());
        }

    }

    static class Record {
        private String fileName;
        private String lineNum;
        private int count;

        public Record(String fileName, String lineNum) {
            this.fileName = fileName;
            this.lineNum = lineNum;
        }

        public Record(String fileName, String lineNum, int count) {
            this.fileName = fileName;
            this.lineNum = lineNum;
            this.count = count;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setLineNum(String lineNum) {
            this.lineNum = lineNum;
        }

        public String getLineNum() {
            return this.lineNum;
        }

        public int getCount() {
            return count;
        }

        public void addCount() {
            this.count++;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
