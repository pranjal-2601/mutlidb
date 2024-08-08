package com.multiDb.test;

public class Finally {

    public static int getInt() {
        try  {
            return 3;
        } catch (Exception ex) {
            System.out.println("ex");
        } finally {
            System.out.println("finally");
        }
        return 3;
    }

    public static void main(String[] args) {
        System.out.println("result" + getInt());
    }
}
