package com.hz.programe;

/**
 * @author: pp_lan on 2020/3/26.
 */
public class Hello {

    public void test() {
        int i = 8;
        while ((i -= 3) > 0) {
            System.out.println("i = " + i);
        }
    }

    public static void change(String str) {
        str = "change";
    }
    public static void change(User user) {
        user = new User(2);
    }

    static class User {
        private int age;

        public User(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("123", "123");
//
//        ArrayList<String> list = new ArrayList<>();
//        list.add("1");
//
//        List<Object> list2 = Collections.synchronizedList(new ArrayList<>());
//
//        CopyOnWriteArrayList<Integer> list3 = new CopyOnWriteArrayList<>();
//        list3.add(1);
//
//        Hello hello = new Hello();
//        for (int i = 0; i < 5000; i++) {
//            hello.test();
//
//        }

//        String str = "accv";
//        change(str);
//        System.out.println(str);

        User user = new User(1);
        change(user);
        System.out.println(user);
    }

}
