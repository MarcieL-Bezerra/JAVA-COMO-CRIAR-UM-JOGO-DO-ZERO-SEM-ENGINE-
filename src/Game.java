import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, MouseListener {

    public static final int WIDTH = 640,HEIGHT = 480;
    public static int contador = 100;
    public Spanner spanner;
    public static int pontuacao = 0;
    public static int mx, my;
    public static boolean clicado = false;
    public static boolean gamerOver = false;

    public Game() {
        Dimension dimension = new Dimension(WIDTH,HEIGHT);
        this.setPreferredSize(dimension);
        this.addMouseListener(this);
        spanner = new Spanner();
    }

    public void update(){
        if (gamerOver == false){
            spanner.update();
            if (contador <= 0){
                contador=100;
                gamerOver=true;
            }
        }


    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        /*g.setColor(Color.white);

        g.setFont(new Font("Arial",Font.BOLD,23));
        g.drawString("Pontos: " + contador, WIDTH/2 - 60, 30);*/
        if (gamerOver == false){
            g.setColor(Color.green);
            g.fillRect(Game.WIDTH/2 - 100 - 70, 20, contador*3,20);

            g.setColor(Color.white);
            g.drawRect(Game.WIDTH/2 - 100 - 70, 20, 300,20);

            spanner.render(g);
        }else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,30));
            g.drawString("Game Over! " , WIDTH/2 -100 , HEIGHT/2 -50);
            g.drawString("Sua pontuação final é: " + this.pontuacao  , WIDTH/2 -200 , HEIGHT/2 +30);
        }
        bs.show();


    }

    @Override
    public void run() {
        while(true){
            update();
            render();
            try {
                Thread.sleep(1000/60);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {



    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicado = true;
        mx = e.getX();
        my = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
