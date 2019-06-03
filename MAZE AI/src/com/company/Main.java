package com.company;

/*
standard bemeneten erkezo string alapjan fel kell epiteni a labirintust.
a labirintus jobb felso sarkabol el kell menni a bal also sarkaba.
kozben vannak targyak amiket fel kell venni, ezeket bele kell ejteni az utvonalba
a kiirasban szerepelnie kell, hogy merre lepunk es ha az adott mezon felveszunk-e valamit
15 labirintust kell megcsinalni, anelkul, hogy megbaszodna a rendszer.
A bemenet megadja, hogy az adott mezohoz kepest milyen iranyban (eszak,del,kelet,nyugat) vannak falak
es azt is, hogy van-e rajta targy. Ez ugy van megoldva, hogy minden mezonek van egy osszege, amelybol visszafejtheto
az adott mezo tulajdonsaga. Az egesz vegen csak egy szamot fogunk kapni amely megmondja, hogy hany targy van.

eszaki fal: 1,
Keleti fal: 2,
Deli fal: 4,
Nyugati fal: 8,
Targy: 16,

10 9 7
8 0 19
12 4 2
1
*/

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Matrix maze = new Matrix(); //ezzel meg is tortenik a labirintus letrehozasa a szamokbol DONE
        Graph graph = new Graph(maze); //itt alakitjuk graffa a labirintust DONE
        graph.solve(0,0);
    }
}
