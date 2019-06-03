package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Matrix {
    private ArrayList<ArrayList<Tile>> matrix = new ArrayList<ArrayList<Tile>>(); //2d tomb amiben tarolunk
    int items; //targyak szama
    int  rows;
    int columns;

    Matrix() throws IOException //amikor letrejon a matrix akkor meg is adjuk az ertekeit neki bemeneten keresztul
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //ezzel tudunk beolvasni
        String input = null; //ebbol a stringbol fogjuk majd olvasni

        while((input=br.readLine()).contains(" ")) //ha van benne szokoz akkor a benne levo szamokat ki kell szedni.
        {
            matrix.add(new ArrayList<>()); //mindig ha uj sor van, akkor azt letre kell hozni.
            while(input.contains(" ")) //amig van szokoz addig kiszedjuk a szamokat
            {
                matrix.get(rows).add(new Tile(Integer.parseInt(input.substring(0,input.indexOf(" ")))));
                input = input.substring(input.indexOf(" ")+1);
            }
            matrix.get(rows).add(new Tile(Integer.parseInt(input)));
            rows++;
        }
        items=Integer.parseInt(input); //a vegen pedig megmondjuk a targyak szamat.
        columns = matrix.get(0).size(); //az oszlopok szama barmely sor elemeinek a szama.

        for(int i=0;i<rows;i++) //a szelso elemeknel "eltuntetjuk" a bejaratot
        {
            for(int j=0;j<columns;j++)
            {
                if(i == 0) get(i,j).North=false;
                if(i == rows-1) get(i,j).South=false;
                if(j == 0) get(i,j).West=false;
                if(j == columns-1) get(i,j).East=false;
            }
        }

    }

    Tile get(int row,int coloumn)
    {
        return matrix.get(row).get(coloumn);
    }

}
