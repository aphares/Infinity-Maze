package aphares.dev.theMaze;

import java.util.ArrayList;
import java.util.Random;

public class Lines {

    private Block[][] map;
    private ArrayList<Block> stack;
    private Block temp;
    private int displacement;


    //This variable decreases the larger the level gets.
    private int bufferHeight;


    public Lines(int x, Block oldBlock, int height) {

        temp = oldBlock;
        displacement = x;
        map = new Block[34][68];
        stack = new ArrayList<Block>();
        bufferHeight = height;
        makeWalls();
        makeMap();



        //removeRazorEdges();
    }

    public void makeWalls() {
        for (int i = 0; i < 34; i++) {
            for (int j = 0; j < 68; j++) {
                map[i][j] = new Block((j * 16), i * 16,false);
            }
        }
    }

    public boolean[] pathOpen(Block b) {

        //0 is right, 1 is top, 2 is left, 3 is bottom
        boolean[] allSides = {false, false, false, false};
        //Is one side at least two squares away from a white square? Then return true. If it is within 2 squares from the edge, returns false.
        if ((b.getX() < map[0].length - 2) && (b.getY() > bufferHeight && (b.getY() < map.length - bufferHeight))) {
            //CHECK RIGHT: Above makes sure there's space to the right, bottom, and top.
            if (!map[b.getY() - 1][b.getX() + 2].getWay() && !map[b.getY()][b.getX() + 2].getWay() && !map[b.getY() + 1][b.getX() + 2].getWay()
                    && !map[b.getY() - 1][b.getX() + 1].getWay() && !map[b.getY()][b.getX() + 1].getWay() && !map[b.getY() + 1][b.getX() + 1].getWay()) {
                allSides[0] = true;
            }
        }
        if ((b.getY() < map.length - bufferHeight) && (b.getX() > 2 && (b.getX() < map[0].length - 2))) {
            //CHECK TOP: Above makes sure there's space to the top, left, and right.
            if (!map[b.getY() + 2][b.getX() - 1].getWay() && !map[b.getY() + 2][b.getX()].getWay() && !map[b.getY() + 2][b.getX() + 1].getWay()
                    && !map[b.getY() + 1][b.getX() - 1].getWay() && !map[b.getY() + 1][b.getX()].getWay() && !map[b.getY() + 1][b.getX() + 1].getWay()) {
                allSides[1] = true;
            }
        }
        if (b.getX() > 2 && (b.getY() > bufferHeight && (b.getY() < map.length - bufferHeight))) {
            //CHECK LEFT: Above makes sure there's space to the left, bottom, and top.
            if (!map[b.getY() - 1][b.getX() - 2].getWay() && !map[b.getY()][b.getX() - 2].getWay() && !map[b.getY() + 1][b.getX() - 2].getWay()
                    && !map[b.getY() - 1][b.getX() - 1].getWay() && !map[b.getY()][b.getX() - 1].getWay() && !map[b.getY() + 1][b.getX() - 1].getWay()) {
                allSides[2] = true;
            }
        }
        if (b.getY() > bufferHeight && (b.getX() > 2 && (b.getX() < map[0].length - 2))) {
            //CHECK BOTTOM: Above makes sure there's space to the bottom, left, and right.
            if (!map[b.getY() - 2][b.getX() - 1].getWay() && !map[b.getY() - 2][b.getX()].getWay() && !map[b.getY() - 2][b.getX() + 1].getWay()
                    && !map[b.getY() - 1][b.getX() - 1].getWay() && !map[b.getY() - 1][b.getX()].getWay() && !map[b.getY() - 1][b.getX() + 1].getWay()) {
                allSides[3] = true;
            }
        }
        return allSides;
    }

    //This method is responsible for drawing another block based on a given previous block.
    public Block drawLines(Block block) {
        // SpacetoRight, SpacetoTop, SpacetoLeft, SpacetoBottom
        boolean[] openSides = pathOpen(block);
        Random rand = new Random();
        int c = rand.nextInt(100);


        //60% chance to continue a run
        if (c < 75) {
            //toTop
            if (openSides[1] && map[block.getY() - 1][block.getX()].getWay()) {
                map[block.getY() + 1][block.getX()].setColor(true);
                stack.add(map[block.getY() + 1][block.getX()]);
                return drawLines(map[block.getY() + 1][block.getX()]);
            }
            //toBottom
            else if (openSides[3] && map[block.getY() + 1][block.getX()].getWay()) {
                map[block.getY() - 1][block.getX()].setColor(true);
                stack.add(map[block.getY() - 1][block.getX()]);
                return drawLines(map[block.getY() - 1][block.getX()]);
            }
            //toRight
            else if (openSides[0] && map[block.getY()][block.getX() - 1].getWay()) {
                map[block.getY()][block.getX() + 1].setColor(true);
                stack.add(map[block.getY()][block.getX() + 1]);
                return drawLines(map[block.getY()][block.getX() + 1]);
            }
            //toLeft
            else if (openSides[2] && map[block.getY()][block.getX() + 1].getWay()) {
                    map[block.getY()][block.getX() - 1].setColor(true);
                    stack.add(map[block.getY()][block.getX() - 1]);
                    return drawLines(map[block.getY()][block.getX() - 1]);
            }
        }
        //if any spots are open
        if (openSides[0] || openSides[1] || openSides[2] || openSides[3]) {
            c = rand.nextInt(4);
            if (c == 1 && openSides[0]) {
                map[block.getY()][block.getX() + 1].setColor(true);
                stack.add(map[block.getY()][block.getX() + 1]);
                return drawLines(map[block.getY()][block.getX() + 1]);
            }
            if (c == 2 && openSides[1]) {
                map[block.getY() + 1][block.getX()].setColor(true);
                stack.add(map[block.getY() + 1][block.getX()]);
                return drawLines(map[block.getY() + 1][block.getX()]);
            }
            if (c == 4 && openSides[2]) {
                map[block.getY()][block.getX() - 1].setColor(true);
                stack.add(map[block.getY()][block.getX() - 1]);
                return drawLines(map[block.getY()][block.getX() - 1]);
            }
            if (c == 3 && openSides[3]) {
                map[block.getY() - 1][block.getX()].setColor(true);
                stack.add(map[block.getY() - 1][block.getX()]);
                return drawLines(map[block.getY() - 1][block.getX()]);
            } else {
                if (openSides[0]) {
                    map[block.getY()][block.getX() + 1].setColor(true);
                    stack.add(map[block.getY()][block.getX() + 1]);
                    return drawLines(map[block.getY()][block.getX() + 1]);
                }
                if (openSides[1]) {
                    map[block.getY() + 1][block.getX()].setColor(true);
                    stack.add(map[block.getY() + 1][block.getX()]);
                    return drawLines(map[block.getY() + 1][block.getX()]);
                }
                if (openSides[2]) {
                    map[block.getY()][block.getX() - 1].setColor(true);
                    stack.add(map[block.getY()][block.getX() - 1]);
                    return drawLines(map[block.getY()][block.getX() - 1]);
                }
                if (openSides[3]) {
                    map[block.getY() - 1][block.getX()].setColor(true);
                    stack.add(map[block.getY() - 1][block.getX()]);
                    return drawLines(map[block.getY() - 1][block.getX()]);
                }
            }
        }
        stack.remove(map[block.getY()][block.getX()]);
        return null;
    }

    public void makeMap() {

        map[temp.getY()][temp.getX()].setColor(true);
        stack.add(map[temp.getY()][temp.getX()]);
        temp = drawLines(map[temp.getY()][temp.getX()]);

        while (temp == null) {
            if (stack.size() != 0) {
                temp = drawLines(stack.get(stack.size() - 1));
            }
            else {

                for (int i = 33; i >= 0; i--) {
                    if (map[i][66].getWay()) {
                        temp = map[i][66];
                    }
                }
            }
        }
        for (int i = 0; i < 34; i++) {
            map[i][2].setColor(true);
        }
    }

    public void removeRazorEdges() {
        int c = 4;
        for (int i = 1; i < 33; i++) {
            for (int j = 1; j < 67; j++) {
                if (!map[i + 1][j].getWay()) {
                    c++;
                }
                if (!map[i - 1][j].getWay()) {
                    c++;
                }
                if (!map[i][j + 1].getWay()) {
                    c++;
                }
                if (!map[i][j - 1].getWay()) {
                    c++;
                }
                if (map[i + 1][j + 1].getWay()) {
                    c++;
                }
                if (map[i - 1][j - 1].getWay()) {
                    c++;
                }
                if (map[i + 1][j - 1].getWay()) {
                    c++;
                }
                if (map[i - 1][j + 1].getWay()) {
                    c++;
                }
                if (c > 4) {
                    map[i][j].setColor(false);

                }
                c = 0;

            }
        }
    }
    public void setDisplacement(int newDisplacment) {
        displacement = newDisplacment;
    }

    public int getDisplacement() {
        return displacement;
    }

    public Block[][] getMap() {
        return map;
    }

    public Block getTemp() {
        return temp;
    }

}