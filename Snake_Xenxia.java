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
    int n = 1, i, k, xt, yt, high = 0, point = 0, cp = 0;
    int m[] = new int[10];
    boolean a = true;
    int s = 12, sw = 0;
    javax.swing.Timer timer;
    Queue<Node> snake = new LinkedList<Node>();
    Node food = new Node(10, 32, false);
    Iterator<Node> it;
    Node pos;
    Scanner fin = null;
    PrintWriter fout = null;
    File file = new File("High.txt");

    public Snake_Xenxia() {
        timer = new javax.swing.Timer(100, new TimerListener());
        timer.start();
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(998, 494);
        snake.add(new Node(300, 160, true));
        m[point] = 101;
        m[cp] = m[point];
        food.x = check((int) (Math.random() * 944) + 10, true);
        food.y = check((int) (Math.random() * 440) + 32, false);
    }

    public void paint(Graphics g) {
        super.paint(g);
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
        // if(n%5==0)
        g.setColor(food_color);
        // else g.setColor(food_color2);
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
            g.fillOval(pos.x, pos.y, 16, 16);
            if (pos.b) {
                g.setColor(Color.yellow);
                if (m[cp] == 101 || m[cp] == 10001) {
                    g.fillOval(pos.x + 6, pos.y + 2, 4, 4);
                    g.fillOval(pos.x + 6, pos.y + 10, 4, 4);
                } else {
                    g.fillOval(pos.x + 2, pos.y + 6, 4, 4);
                    g.fillOval(pos.x + 10, pos.y + 6, 4, 4);
                }
            }
        }
        // g.setColor(Color.green);
        // g.fillRect(pos.x - 14, pos.y - 14, 14, 14);
        // point = point > 9 ? 0 : point + 1;
        // m[cp]= m[point]==0?j:;
        // // System.out.println();
        // for (int o = 0; o <= 9; o++) {
        // System.out.print(m[o]);
        // }
        // System.out.println(" " + cp + " " + point);
    }

    static int check(int i, boolean b) {// b is true for x coordinate and false for y coordinate
        if (b)
            i -= 10;
        else
            i -= 32;
        int rem;
        rem = i % 15;
        if (rem < 7)
            i -= rem;
        else
            i += 15 - rem;
        if (b)
            i += 10;
        else
            i += 32;
        return i;
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            it = snake.iterator();
            while (it.hasNext())
                pos = (Node) it.next();
            i = pos.x;
            k = pos.y;
            c++;
            if (c == 12)
                c = 0;
            it = snake.iterator();
            pos = (Node) it.next();
            do {
                if (pos.x < 10 || pos.y < 32 || pos.x > 975 || pos.y > 469
                        || (!a && (pos.x == i && pos.y == k && !pos.b))) {
                    System.out.print("Your Score:\t");
                    num.toWords(n - 1);
                    try {
                        fin = new Scanner(file);
                        while (fin.hasNext())
                            high = fin.nextInt();
                        fin.close();
                        if (high > (n - 1)) {
                            System.out.print("High Score:\t");
                            num.toWords(high);
                        } else if (high < (n - 1)) {
                            System.out.print("Congratulations!!! You have made Highest Score");
                            fout = new PrintWriter(file);
                            fout.println((n - 1));
                            fout.close();
                        } else if (high == (n - 1))
                            System.out.print("Your Score is equal to the Highest Score");
                    } catch (Exception t) {
                        System.out.print(t);
                    }
                    System.exit(0);
                }
                a = false;
                if (it.hasNext())
                    pos = (Node) it.next();
                else
                    break;
            } while (true);
            // eat
            it = snake.iterator();
            while (it.hasNext())
                pos = (Node) it.next();
            if (pos.x == food.x && pos.y == food.y) {
                food.x = check((int) (Math.random() * 944) + 10, true);
                food.y = check((int) (Math.random() * 440) + 32, false);
                while (it.hasNext())
                    pos = (Node) it.next();
                snake.add(new Node(pos.x, pos.y, false));
                n++;
                c++;
            } // eat
            a = true;
            it = snake.iterator();
            while (it.hasNext()) {
                pos = (Node) it.next();
                if (pos.b == true) {
                    pos.b = false;
                    break;
                }
            }
            xt = pos.x;
            yt = pos.y;
            if (m[cp] / 10 == 1)
                yt = pos.y + 15;
            if (m[cp] / 100 == 1)
                xt = pos.x + 15;
            if (m[cp] / 1000 == 1)
                yt = pos.y - 15;
            if (m[cp] / 10000 == 1)
                xt = pos.x - 15;
            snake.add(new Node(xt, yt, true));
            snake.remove();
            if (m[cp == 9 ? 0 : cp + 1] != 0) {
                m[cp] = 0;
                cp++;
                if (cp == 10)
                    cp = 0;
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        point++;
        if (point > 9)
            point = 0;
        if (keyCode == KeyEvent.VK_LEFT && m[point == 0 ? 9 : point - 1] != 101
                && m[point == 0 ? 9 : point - 1] != 10001) // Left arrow key
            m[point] = 10001;
        if (keyCode == KeyEvent.VK_RIGHT && m[point == 0 ? 9 : point - 1] != 10001
                && m[point == 0 ? 9 : point - 1] != 101) // Left arrow key
            m[point] = 101;
        if (keyCode == KeyEvent.VK_DOWN && m[point == 0 ? 9 : point - 1] != 1001 && m[point == 0 ? 9 : point - 1] != 11)
            m[point] = 11;
        if (keyCode == KeyEvent.VK_UP && m[point == 0 ? 9 : point - 1] != 11 && m[point == 0 ? 9 : point - 1] != 1001)
            m[point] = 1001;
        if (m[point] == 0)
            point--;
        if (point == -1)
            point = 9;
        // repaint();
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