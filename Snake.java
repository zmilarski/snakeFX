package org.example;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private List<Point> body;
    private Direction direction;

    public Snake(Point head, List<Point> body, Direction direction) {
        if (!body.isEmpty()) {
            if (!ifNextToEachOther(head, body.get(0))) {
                throw new IllegalArgumentException("Wąż nie ma ciągłości");
            }
        }
        if (body.size() > 1) {
            for (int i = 0; i < body.size() - 1; i++) {
                if (!ifNextToEachOther(body.get(i), body.get(i + 1))) {
                    throw new IllegalArgumentException("Wąż nie ma ciągłości");
                }
            }
        }
        this.head = head;
        this.body = new ArrayList<>(body);
        this.direction = direction;
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

/*
    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, body.get(i - 1));
        }
        body.set(0,head);
        switch (direction){
            case UP:
                head = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                head = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                head = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                head = new Point(head.getX() + 1, head.getY());
                break;
        }

    }*/

    public void move() {
        body.add(0, head);
        switch (direction) {
            case UP:
                head = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                head = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                head = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                head = new Point(head.getX() + 1, head.getY());
                break;

        }
        body.remove(body.size() - 1);
    }

    public void expand() {
        body.add(0, head);
        switch (direction) {
            case UP:
                head = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                head = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                head = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                head = new Point(head.getX() + 1, head.getY());
                break;
        }
    }

    public void cutTail() {
        body.remove(body.size() - 1);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean ifNextToEachOther(Point a, Point b) {
        if (a.getX() == b.getX()) {
            if (a.getY() == b.getY() + 1 || a.getY() == b.getY() - 1) {
                return true;
            }
        }
        if (a.getY() == b.getY()) {
            if (a.getX() == b.getX() + 1 || a.getX() == b.getX() - 1) {
                return true;
            }
        }
        return false;
    }

}
