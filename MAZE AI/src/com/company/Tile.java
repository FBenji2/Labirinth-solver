package com.company;

public class Tile {
    Boolean North,South,East,West; //megnezzuk, hogy melyik iranyokba tudunk menni
    Boolean hasitem; //megnezzuk, van-e rajta targy

    Tile(int input) //konstruktorban a bemenet alapjan letrehozzuk a mezot.
    {
        hasitem=false;
        North=true;
        South=true;
        East=true;
        West=true;
        if(input>=16) //egyszer kivonjuk a megfelelo ertekeket es bebillentjuk a biteket a helyukre.
        {
            input=input-16;
            hasitem=true;
        }
        if(input>=8)
        {
            input=input-8;
            West=false;
        }
        if(input>=4)
        {
            input=input-4;
            South=false;
        }
        if(input>=2)
        {
            input=input-2;
            East=false;
        }
        if(input>=1)
        {
            North=false;
        }
    }

    void println()
    {
        System.out.println("North: " + North);
        System.out.println("East: " + East);
        System.out.println("South: " + South);
        System.out.println("West: " + West);
        System.out.println("Has item: " + hasitem);
    }
}
