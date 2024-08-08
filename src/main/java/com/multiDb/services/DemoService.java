package com.multiDb.services;

import com.multiDb.config.FacebookProperties;
import com.multiDb.config.Fb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Service
public class DemoService {

    @Autowired
    public FacebookProperties facebookProperties;

    @Autowired
    private Fb fb;


    public void m1() {
        System.out.println("clientId" + facebookProperties.getClientId());
        System.out.println("clientSecret" + facebookProperties.getClientSecret());
        System.out.println("clientId" + fb.clientId());
        System.out.println("clientSecret" + fb.clientSecret());
    }

    public void m2() {
        Set<Integer> s1 = new HashSet<>();

        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<Integer> integers = new LinkedList<>();

    }

    public void ListComparsion() {
        LinkedList<Integer> ll = new LinkedList<>();
        ArrayList<Integer> al = new ArrayList<>();

        Integer integer = ll.get(1);
        Integer integer1 = al.get(1);
    }
}
