package com.mtech.parttimeone.photolearn.dummyModel;

import java.util.ArrayList;

/**
 * Created by Zhikai on 17/3/2018.
 */

public class dummyDao {

    public ArrayList<Title> GetTitleList(String SessionID){

        ArrayList<Title> lsl = new ArrayList<Title>();

        String FixedID = new String("MTECH-ORO-002");

        if (FixedID.equalsIgnoreCase(SessionID) == true) {


            //test
            Title ls1 = new Title("MTECH-ORO-002", "All about IOT", "Lim Zhi Kai", Title.TitleType.TITLE);
            Title ls2 = new Title("MTECH-ORO-002", "How to retrack the items", "Lim Zhi Kai", Title.TitleType.TITLE);
            Title ls3 = new Title("MTECH-ORO-002", "History of the topic", "Liu Changling", Title.TitleType.TITLE);
            Title ls4 = new Title("MTECH-ORO-002", "Overview", "Darryl Ng", Title.TitleType.TITLE);
            Title ls5 = new Title("MTECH-ORO-002", "How to get full marks", "Lim Zhi Kai", Title.TitleType.TITLE);
            Title ls6 = new Title("MTECH-ORO-002", "How to go back in time", "Lim Zhi Kai", Title.TitleType.TITLE);
            Title ls7 = new Title("MTECH-ORO-002", "History of the NUS", "Liu Changling", Title.TitleType.TITLE);
            Title ls8 = new Title("MTECH-ORO-002", "Background", "Darryl Ng", Title.TitleType.TITLE);

            lsl.add(ls1);
            lsl.add(ls2);
            lsl.add(ls3);
            lsl.add(ls4);
            lsl.add(ls5);
            lsl.add(ls6);
            lsl.add(ls7);
            lsl.add(ls8);
        }

        return lsl;

//        lsl.add(ls5);
//        lsl.add(ls6);
//        lsl.add(ls7);
//        lsl.add(ls8);
//        lsl.add(ls9);
//        lsl.add(ls10);
//        lsl.add(ls11);
//        lsl.add(ls12);
//        lsl.add(ls13);
//        lsl.add(ls14);

    }

    public ArrayList<Title> GetQuizList(String SessionID){

        ArrayList<Title> lsl = new ArrayList<Title>();

        String FixedID = new String("MTECH-ORO-002");

        if (FixedID.equalsIgnoreCase(SessionID) == true) {


            //test
            Title ls1 = new Title("MTECH-ORO-002", "Formula Quiz", "Lim Zhi Kai", Title.TitleType.QUIZ);
            Title ls2 = new Title("MTECH-ORO-002", "Chapter 1 Quiz", "Lim Zhi Kai", Title.TitleType.QUIZ);
            Title ls3 = new Title("MTECH-ORO-002", "Final Test", "Liu Changling", Title.TitleType.QUIZ);
            Title ls4 = new Title("MTECH-ORO-002", "Overview Quiz", "Darryl Ng", Title.TitleType.QUIZ);
            Title ls5 = new Title("MTECH-ORO-002", "Test Chapter 1", "Lim Zhi Kai", Title.TitleType.QUIZ);
            Title ls6 = new Title("MTECH-ORO-002", "Test Chapter 2", "Lim Zhi Kai", Title.TitleType.QUIZ);
            Title ls7 = new Title("MTECH-ORO-002", "Test Chapter 3", "Liu Changling", Title.TitleType.QUIZ);
            Title ls8 = new Title("MTECH-ORO-002", "Test Chapter 4", "Darryl Ng", Title.TitleType.QUIZ);

            lsl.add(ls1);
            lsl.add(ls2);
            lsl.add(ls3);
            lsl.add(ls4);
            lsl.add(ls5);
            lsl.add(ls6);
            lsl.add(ls7);
            lsl.add(ls8);
        }

        return lsl;
    }

    public ArrayList<Item> GetLearningItemList(String SessionID,String Title){

        ArrayList<Item> lsl = new ArrayList<Item>();

        String FixedID = new String("MTECH-ORO-002");
        String FixedTitle = new String("All about IOT");

        if ((FixedID.equalsIgnoreCase(SessionID) == true) && (FixedTitle.equalsIgnoreCase(Title) == true)) {


            //test
            Item ls1 = new Item(FixedID,"What is Internet of things?","Lim Zhi Kai","pic1","The Internet of things is the network of physical devices, vehicles, home appliances and other items embedded with electronics, software, sensors, actuators, and connectivity which enables these objects to connect and exchange data","Serangoon North 12345","2018/07/23",Item.ItemType.LEARNING);
            Item ls2 = new Item(FixedID,"How does IOT work","Lim Zhi Kai","pic2","IoT in transport. Having been woken by your smart alarm, you're now driving to work. On comes the engine light. You'd rather not head straight to the garage, but what if it's something urgent? In a connected car, the sensor that triggered the check engine light would communicate with others in the car. A component called the diagnostic bus collects data from these sensors and passes it to a gateway in the car, which sends the most relevant information to","Singapore","2018/07/23",Item.ItemType.LEARNING);
            Item ls3 = new Item(FixedID,"Top countries for IOT","Lim Zhi Kai","pic3","Countries with the most IoT devices. Korea Demark Swizerland United States Netherlands Germany Sweden Spain Francce Portugal Belgium United Kingdom 37.9 32.7 29 24.9 24.7 22.4 21.9 19.9 17.6 16.2 15.6 13 Devices online per 100 ... Chart Appears In. Coming soon: The internet of clothes — Quartz(qz.com). The top-country early-adopters of the Internet of Things, ranked - Quartz(qz.com). The top-country early-adopters of the Internet of Things","Bukit Timah Hill","2018/07/23",Item.ItemType.LEARNING);
            Item ls4 = new Item(FixedID,"Top skills for IOT","Lim Zhi Kai","pic4","One of the top factors slowing down IoT implementations is the talent gap: Existing market supply just cannot keep up with the demand for engineers with IoT skills. Some 68% of companies are struggling to hire developers for their IoT projects, according to research from Canonical. But the flipside to the skills shortage is that career prospects are bright for professionals with in-demand skills.","Orchard Road","2018/07/23",Item.ItemType.LEARNING);

            lsl.add(ls1);
            lsl.add(ls2);
            lsl.add(ls3);
            lsl.add(ls4);

        }

        return lsl;
    }
}
