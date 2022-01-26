import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Snake_Xenxia extends JFrame implements KeyListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Color Snake_color = Color.black;
    Color food_color = Color.blue;
    int c = 1;
    int n = 1, i, k, xt, yt, direction, nxtdirection;
    boolean keypress = false;
    // int m[] = new int[10];
    int s = 12, sw = 0;
    javax.swing.Timer timer;
    Queue<Node> snake = new LinkedList<Node>();
    Node food = new Node(10, 32, false);
    Iterator<Node> it;
    Node pos;
    boolean game_over = false;

    public Snake_Xenxia() {
        timer = new javax.swing.Timer(50, new TimerListener());
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(998 + 30, 494 + 64);
        snake.add(check(new Node(300, 160, true)));
        direction = 101;
        // direction = 101;
        // direction = direction;
        food = check(new Node((int) (Math.random() * 944) + 10, (int) (Math.random() * 440) + 32, false));
        // Label label=new Label("Score :");
        // add(label);
        JPanel jp = new JPanel() {
            public void paintComponent(Graphics g1) {
                super.paintComponent(g1);
                // // g1.setColor(Color.blue);
                // // g1.fillOval(100,100,5,5);
                Graphics2D g = (Graphics2D) g1;
                // // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                // RenderingHints.VALUE_ANTIALIAS_ON);

                g.setColor(Color.red);
                // g.fillOval(50, 50, 5, 5);
                g.drawRect(10, 32, 976, 450);
                g.drawRect(12, 34, 972, 446);
                g.setColor(food_color);
                // s = 16;
                // sw = 0;
                // if (c % 12 <= 6) {
                // s = 12;
                // sw = 2;
                // }
                g.drawOval(food.x + sw, food.y + sw, s, s);
                g.fillOval(food.x + sw, food.y + sw, s, s);
                g.setColor(Color.pink);
                g.drawOval(food.x + s / 4 + sw, food.y + s / 4 + sw, s / 4, s / 4);
                g.fillOval(food.x + s / 4 + sw, food.y + s / 4 + sw, s / 4, s / 4);
                paintSnake(g);
            }
        };
        add(jp);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (game_over) {
            timer.stop();
            dispose();
        }
        // if (n == 1) {
        // super.paint(g);
        // g.setColor(Color.green);
        // g.fillRect(10, 32, 976, 450);
        // g.setColor(Color.yellow);
        // int i, j = 0;
        // for (i = 10; i < 980; i += 15)
        // for (j = 32; j < 470; j += 15)
        // g.drawRect(i, j, 15, 15);
        // }
        g.setColor(Color.red);
        g.drawRect(10, 32, 976, 450);
        g.drawRect(11, 33, 974, 448);
        // if(n%5==0)g.setColor(food_color);
        // else g.setColor(food_color2);
        g.setColor(food_color);
        s = 16;
        sw = 0;
        if (c % 12 <= 6) {
            s = 12;
            sw = 2;
        }
        g.fillOval(food.x + sw, food.y + sw, s, s);
        g.setColor(Color.pink);
        g.fillOval(food.x + s / 4 + sw, food.y + s / 4 + sw, s / 4, s / 4);
        paintSnake(g);

    }

    public void paintSnake(Graphics g) {
        it = snake.iterator();
        while (it.hasNext()) {
            pos = (Node) it.next();
            g.setColor(Snake_color);
            // g.drawOval(pos.x, pos.y, 16, 16);
            g.fillOval(pos.x, pos.y, 16, 16);
            if (pos.b) {
                g.setColor(Color.yellow);
                if (direction == 101 || direction == 10001) {
                    // g.drawOval(pos.x + 6, pos.y + 2, 4, 4);
                    g.fillOval(pos.x + 6, pos.y + 2, 4, 4);
                    // g.drawOval(pos.x + 6, pos.y + 10, 4, 4);
                    g.fillOval(pos.x + 6, pos.y + 10, 4, 4);
                } else {
                    // g.drawOval(pos.x + 2, pos.y + 6, 4, 4);
                    g.fillOval(pos.x + 2, pos.y + 6, 4, 4);
                    // g.drawOval(pos.x + 10, pos.y + 6, 4, 4);
                    g.fillOval(pos.x + 10, pos.y + 6, 4, 4);
                }
            }
        }
        // g.setColor(Color.green);
        // g.fillRect(pos.x - 14, pos.y - 14, 14, 14);
        // point = point > 9 ? 0 : point + 1;
        // direction= direction==0?j:;
        // // System.out.println();
        // for (int o = 0; o <= 9; o++) {
        // System.out.print(m[o]);
        // }
        // System.out.println(" " + cp + " " + point);
    }

    public Node check(Node n) {// b is true for x coordinate and false for y coordinate
        n.x -= 10;
        n.y -= 32;
        int rem;
        rem = n.x % 15;
        if (rem < 7)
            n.x -= rem;
        else
            n.x += 15 - rem;
        rem = n.y % 15;
        if (rem < 7)
            n.y -= rem;
        else
            n.y += 15 - rem;
        n.x += 10;
        n.y += 32;
        it = snake.iterator();
        while (it.hasNext()) {
            pos = (Node) it.next();
            if (pos.x == n.x && pos.y == n.y)
                return check(new Node((int) (Math.random() * 944) + 10, (int) (Math.random() * 440) + 32, false));
        }
        return n;
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
            c++;
            if (c == 12)
                c = 0;
            it = snake.iterator();
            while (it.hasNext())
                pos = (Node) it.next();
            i = pos.x;
            k = pos.y;

            it = snake.iterator();
            do {
                pos = (Node) it.next();
                if (pos.x < 10 || pos.y < 32 || pos.x > 975 || pos.y > 469
                        || (!pos.b && pos.x == i && pos.y == k)) {
                    System.out.print("Your Score:\t" + (n - 1));
                    try {
                        Scanner fin = new Scanner(new File("High.txt"));
                        int high = fin.nextInt();
                        fin.close();
                        if (high > (n - 1)) {
                            System.out.println("High Score:\t" + high);
                            // num.toWords(high);
                        } else if (high < (n - 1)) {
                            System.out.print("Congratulations!!! You have made Highest Score");
                            PrintWriter fout = new PrintWriter(new File("High.txt"));
                            fout.println(n - 1);
                            fout.close();
                        } else
                            System.out.print("Your Score is equal to the Highest Score");
                    } catch (Exception t) {
                        System.out.print(t);
                    }
                    game_over = true;
                    repaint();
                    System.exit(0);
                }
            } while (it.hasNext());
            // <eat>
            if (i == food.x && k == food.y) {
                food = check(new Node((int) (Math.random() * 944) + 10, (int) (Math.random() * 440) + 32, false));
                snake.add(new Node(i, k, false));
                n++;
            }
            // </eat>

            pos.b = false;
            xt = pos.x;
            yt = pos.y;
            if (direction / 10 == 1)
                yt = pos.y + 15;
            if (direction / 100 == 1)
                xt = pos.x + 15;
            if (direction / 1000 == 1)
                yt = pos.y - 15;
            if (direction / 10000 == 1)
                xt = pos.x - 15;
            snake.add(new Node(xt, yt, true));// true for head node
            snake.remove();
            // System.out.println(snake.contains(new Node(xt,yt,true)));
            // if (m[cp == 9 ? 0 : cp + 1] != 0) {
            // direction = 0;
            // cp++;
            // if (cp == 10)
            // cp = 0;
            // }
            keypress = false;
            if (nxtdirection != 0)
                direction = nxtdirection;
            nxtdirection = 0;
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // point++;
        // if (point > 9)
        // point = 0;
        if (keypress)
            nxtdirection = direction;
        if (keyCode == KeyEvent.VK_LEFT && direction != 101
                && direction != 10001) // Left arrow key
            direction = 10001;
        if (keyCode == KeyEvent.VK_RIGHT && direction != 10001
                && direction != 101) // Left arrow key
            direction = 101;
        if (keyCode == KeyEvent.VK_DOWN && direction != 1001 && direction != 11)
            direction = 11;
        if (keyCode == KeyEvent.VK_UP && direction != 11 && direction != 1001)
            direction = 1001;
        // if (m[point] == 0)
        // point--;
        // if (point == -1)
        // point = 9;
        if (keypress) {
            nxtdirection += direction;
            direction = nxtdirection - direction;
            nxtdirection = nxtdirection - direction;
        }
        keypress = true;
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}

class run {
    public static void main(String[] args) {
        new Snake_Xenxia();
    }
}
