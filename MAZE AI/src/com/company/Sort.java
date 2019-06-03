package com.company;

import java.util.Comparator;

public class Sort implements Comparator<Node> {

    @Override
    public int compare(Node firstNode, Node secondNode) {
        return (firstNode.distance - secondNode.distance);
    }
}