package com.company;

import java.util.ArrayList;



public class Graph {

    Matrix matrix;
    ArrayList<Node> graph = new ArrayList<>();
    int items;

    Graph(Matrix m) //konstruktorban graffa alakitjuk a labirintust DONE
    {
        matrix = m;
        items = matrix.items;
        for(int i=0;i<matrix.rows;i++)
        {
            for(int j=0;j<matrix.columns;j++)
            {
                if(matrix.get(i,j).hasitem) //ha van targy akkor az egy uj node
                {
                    graph.add(new Node(i,j,true));
                    setNeighbours(getNode(i,j));
                    continue;
                }
                if(!(matrix.get(i,j).North && matrix.get(i,j).South &&
                    !matrix.get(i,j).East  && !matrix.get(i,j).West ||
                     matrix.get(i,j).East && matrix.get(i,j).West &&
                    !matrix.get(i,j).North && !matrix.get(i,j).South))
                {
                    graph.add(new Node(i,j,false));
                    setNeighbours(getNode(i,j));
                    continue;
                }
            }
        }
    }

    void setNeighbours(Node node) //fuggveny amivel a szomszedokat lehet beallitani DONE
    {
        int i = node.row;
        int j = node.column;
        int k = 0;
        while(matrix.get(i-k,j).North)
        {
            k++;
            if(getNode(i-k,j) != null) break;
        }
        if(k != 0)
        {
            node.North = getNode(i-k,j);
            node.North.South = node;
        }

        k=0;
        while(matrix.get(i,j-k).West)
        {
            k++;
            if(getNode(i,j-k) != null) break;;
        }
        if(k != 0)
        {
            node.West = getNode(i,j-k);
            node.West.East = node;
        }
    }

    Node getNode(int row,int column) //visszater nodedal DONE
    {
        for(int i=0;i<graph.size();i++)
        {
            if(graph.get(i).row == row && graph.get(i).column == column) return graph.get(i);
        }
        return null;
    }

    void solve(int row,int column) //adott pontbol nezve tavolsagok kiszamolasa DONE
    {
        int s = items;
        for(int m = 0;m<s+1;m++)
        {
            ArrayList<String> path = new ArrayList<>(); //ezzel irjuk ki majd a megoldast
            ArrayList<Node> queue = new ArrayList<>(graph); //uj lista ami a graf masolata
            queue.remove(getNode(row, column));
            queue.add(0, getNode(row, column));

            queue.get(0).distance = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (queue.get(0).North != null && queue.get(0).via != queue.get(0).North) //ha van eszaki szomszed es nem rajtunk keresztul jut el hozzank
                {
                    int d = 0; //segedvaltozo amivel megmondjuk az adott szomszed tavolsagat ha letezik a szomszed
                    d += queue.get(0).distance; //hozza kell eloszor adni a mi tavolsagunk a kezdettol
                    d += queue.get(0).row - queue.get(0).North.row; //utana hozza kell adni a tavolsagunk magatol a szomszedtol
                    //ilyenkor a d megadja azt, hogy az eppen vizsgalt pontbol hany lepesben jutunk el az eszaki szomszedhoz
                    if (queue.get(0).North.distance > d) //ha ez a tavolsag ami kijott ez kisebb mint ami eddig volt
                    {
                        queue.get(0).North.distance = d; //akkor kicsereljuk es az eredeti listaban is
                        getNode(queue.get(0).North.row, queue.get(0).North.column).distance = d;
                        queue.get(0).North.via = queue.get(0); //es mostantol rajtunk keresztul megy.
                        getNode(queue.get(0).North.row, queue.get(0).North.column).via = queue.get(0);
                    }
                }
                if (queue.get(0).South != null && queue.get(0).via != queue.get(0).South) {
                    int d = 0;
                    d += queue.get(0).distance;
                    d += queue.get(0).South.row - queue.get(0).row;

                    if (queue.get(0).South.distance > d) {
                        queue.get(0).South.distance = d;
                        getNode(queue.get(0).South.row, queue.get(0).South.column).distance = d;
                        queue.get(0).South.via = queue.get(0);
                        getNode(queue.get(0).South.row, queue.get(0).South.column).via = queue.get(0);
                    }
                }
                if (queue.get(0).East != null && queue.get(0).via != queue.get(0).East) {
                    int d = 0;
                    d += queue.get(0).distance;
                    d += queue.get(0).East.column - queue.get(0).column;

                    if (queue.get(0).East.distance > d) {
                        queue.get(0).East.distance = d;
                        getNode(queue.get(0).East.row, queue.get(0).East.column).distance = d;
                        queue.get(0).East.via = queue.get(0);
                        getNode(queue.get(0).East.row, queue.get(0).East.column).via = queue.get(0);
                    }
                }
                if (queue.get(0).West != null && queue.get(0).via != queue.get(0).West) {
                    int d = 0;
                    d += queue.get(0).distance;
                    d += queue.get(0).column - queue.get(0).West.column;

                    if (queue.get(0).West.distance > d) {
                        queue.get(0).West.distance = d;
                        getNode(queue.get(0).West.row, queue.get(0).West.column).distance = d;
                        queue.get(0).West.via = queue.get(0);
                        getNode(queue.get(0).West.row, queue.get(0).West.column).via = queue.get(0);
                    }
                }
                //miutan az adott pont szomszedait megneztuk, utana szortirozni kell oket tavolsag szerint.
                queue.remove(0); //eltavolitjuk az elso elemet
                queue.sort(new Sort());
            }
            graph.sort(new Sort());
            if (items != 0) //ha meg van targy akkor megyunk a kovetkezohoz
            {
                for (int i = 0; i < graph.size(); i++) //megkeressuk a legkozelebb levo targyat es onnan nezve ujra futtatunk mindent.
                {
                    if (graph.get(i).hasitem) {
                        row = graph.get(i).row;
                        column = graph.get(i).column;
                        break;
                    }
                }
                path.add(0, "felvesz"); //felvesszuk a targyat
                getNode(row, column).hasitem = false; //ki is vesszuk a targyat a csucsbol
                items--;
            } else { //ha mar nincs targy, akkor megyunk a kijarathoz
                row = matrix.rows - 1;
                column = matrix.columns - 1;
            }
            Node iter = getNode(row, column); //itt pedig berakjuk az utvonalat
            while (iter.via != null) {
                if (iter.via == iter.North) //ha ahonnan jovunk az folottunk van akkor
                {
                    for (int i = 0; i < iter.distance - iter.via.distance; i++) //kiirjuk a megfelelo lepeseket az elozo pontig
                    {
                        path.add(0, (iter.row - i) + " " + iter.column);
                    }
                    iter = iter.via; //majd belepunk abba a pontba
                    continue; //es folytatjuk a while ciklust
                }
                if (iter.via == iter.South) //ha ahonnan jovunk az folottunk van akkor
                {
                    for (int i = 0; i < iter.distance - iter.via.distance; i++) //kiirjuk a megfelelo lepeseket az elozo pontig
                    {
                        path.add(0, (iter.row + i) + " " + iter.column);
                    }
                    iter = iter.via; //majd belepunk abba a pontba
                    continue; //es folytatjuk a while ciklust
                }
                if (iter.via == iter.East) //ha ahonnan jovunk az folottunk van akkor
                {
                    for (int i = 0; i < iter.distance - iter.via.distance; i++) //kiirjuk a megfelelo lepeseket az elozo pontig
                    {
                        path.add(0, iter.row + " " + (iter.column + i));
                    }
                    iter = iter.via; //majd belepunk abba a pontba
                    continue; //es folytatjuk a while ciklust
                }
                if (iter.via == iter.West) //ha ahonnan jovunk az folottunk van akkor
                {
                    for (int i = 0; i < iter.distance - iter.via.distance; i++) //kiirjuk a megfelelo lepeseket az elozo pontig
                    {
                        path.add(0, iter.row + " " + (iter.column - i));
                    }
                    iter = iter.via; //majd belepunk abba a pontba
                    continue; //es folytatjuk a while ciklust
                }
            }
            for(int i=0;i<path.size();i++) System.out.println(path.get(i));
            reset();
        }
    }

    void reset()
    {
        for(int i=0;i<graph.size();i++)
        {
            graph.get(i).distance=999999;
            graph.get(i).via = null;
        }
    }

    void printDistance()
    {
        for(int i=0;i<graph.size();i++)
        {
            System.out.println("Row: " + graph.get(i).row + " Column: " + graph.get(i).column + " Distance: " +graph.get(i).distance);
        }
    }


}
