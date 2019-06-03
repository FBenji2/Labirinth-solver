package com.company;

public class Node {

    int row; //melyik sorban es oszlopban van a node
    int column;
    Node North,South,East,West; //eltaroljuk a szomszed nodeokat
    boolean hasitem;
    Node via; //honnan erkezunk ide, hogy rovid legyen az utvonal
    int distance; //milyen messze van a kezdettol

    Node(int row,int column, boolean item)
    {
        this.row=row;
        this.column=column;
        hasitem=item;
        distance=999999; //ez most azt szimbolizalja, hogy a dijkstra algotirmus vegtelen tavolsagot hasznal.
    }
}
