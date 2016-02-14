package com.woestmanBrian.bWoestmanLab2_1.fragments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian Woestman on 2/6/16.
 * Android Programming
 * We 5:30p - 9:20p
 */
public class SingletonIpsum {

    private static SingletonIpsum singletonIpsum;
    private static List<Ipsum> ipsums;

    private static String[] Headlines = {
            "Article One",
            "Article Two"
    };

    private static String[] Articles = {
            "Excepteur pour-over occaecat squid biodiesel umami" +
                    " gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco " +
                    "culpa retro ea, trust fund excepteur eiusmod direct trade banksy " +
                    "nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a " +
                    "bird on it gluten-free, wes anderson ut trust fund twee occupy viral. " +
                    "Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip " +
                    "yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut" +
                    " ethnic, authentic brunch. Esse single-origin coffee banksy do next level " +
                    "tempor. Velit synth dreamcatcher, magna shoreditch in american apparel " +
                    "messenger bag narwhal PBR ennui farm-to-table.",

            "Vinyl williamsburg non velit, master cleanse four loko banh mi. " +
                    "Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter " +
                    "dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua " +
                    "pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo " +
                    "booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum " +
                    "stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, " +
                    "vegan carles odd future."
    };

    private SingletonIpsum() {

    }

    public static SingletonIpsum getSingletonIpsum() {
        if (null == singletonIpsum) {
            ipsums = new ArrayList();
            singletonIpsum = new SingletonIpsum();
            ipsums.add(new Ipsum(Headlines[0], Articles[0]));
            ipsums.add(new Ipsum(Headlines[1], Articles[1]));
        }
        return singletonIpsum;
    }

    public static String[] getHeadlines() {
        return Headlines;
    }

    public static String[] getArticles() {
        return Articles;
    }

    public static List<Ipsum> getIpsums() {
        return ipsums;
    }
}
