package com.hz.huawei;

import java.util.*;

/**
 * @authod: pp_lan on 2020/3/13.
 */
public class CaseOne {

    /**
     * 转换为M
     * @param a
     * @return
     */
    public static int changeToM(String a) {
        int memory; //单位为M
        if (a.contains("M")) {
            memory = Integer.parseInt(a.replace("M", "").trim());
        } else if (a.contains("G")) {
            memory = Integer.parseInt(a.replace("G", "").trim()) * 1024;
        } else if (a.contains("T")) {
            memory = Integer.parseInt(a.replace("T", "").trim()) * 1024 * 1024;
        } else {
            throw new RuntimeException("单位不符合");
        }
        return memory;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            List<Disk> diskList = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Disk disk = new Disk(sc.next());
                //反设m大小
                disk.setMemory(changeToM(disk.getName()));
                diskList.add(disk);
            }
            Collections.sort(diskList, new Comparator<Disk>() {
                @Override
                public int compare(Disk o1, Disk o2) {
                    return o1.getMemory() - o2.getMemory();
                }
            });
            for (Disk disk : diskList) {
                System.out.println(disk.getName());
            }

        }
    }

    static class Disk {

        /**
         * 大小，单位为M
         */
        int memory;

        String name;

        public Disk(String name) {
            this.name = name;
        }

        public int getMemory() {
            return memory;
        }

        public void setMemory(int memory) {
            this.memory = memory;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Disk disk = (Disk) o;

            if (memory != disk.memory) return false;
            return name != null ? name.equals(disk.name) : disk.name == null;
        }

        @Override
        public int hashCode() {
            int result = memory;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}
