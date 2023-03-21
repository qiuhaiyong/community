private static <T extends Number> double add(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
        }
        ------
        著作权归@pdai所有
原文链接：https://pdai.tech/md/java/basic/java-basic-x-generic.html