import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spanner {
    public int timer = 0;
    public List<ReactObj> rectangles = new ArrayList<ReactObj>();
    public List<Particula> particula = new ArrayList<Particula>();

    public void update(){
        timer++;
        if (timer % 60 == 0){
            rectangles.add(new ReactObj(0, new Random().nextInt(480-40),40,40));
        }
        for(int i = 0; i < rectangles.size(); i++){
            ReactObj current = rectangles.get(i);
            rectangles.get(i).update();

            if (current.x > Game.WIDTH){
                rectangles.remove(current);
                Game.contador--;
            }
            if (Game.clicado){
                if (Game.mx >= current.x && Game.mx < current.x + current.width){
                    if (Game.my >= current.y && Game.my < current.y + current.height){
                        rectangles.remove(current);
                        Game.pontuacao++;
                        Game.clicado = false;

                        for (int n = 0; n<50; n++){
                            particula.add(new Particula(current.x,current.y,8,8,current.color));
                        }

                    }
                }
            }
        }

        for (int i = 0; i < particula.size(); i++){
            particula.get(i).update();
            Particula part = particula.get(i);
            if (part.timer >= 60){
                particula.remove(part);
            }
        }


    }

    public void render(Graphics g){

        for(int i = 0; i < rectangles.size();i++){
            ReactObj current = rectangles.get(i);
            Graphics2D g2 = (Graphics2D) g;
            g2.rotate(Math.toRadians(current.rotation),current.x+current.width/2,current.y+current.height/2);
            g.setColor(current.color);
            g.fillRect(current.x, current.y, current.width,current.height);
            g2.rotate(Math.toRadians(-current.rotation),current.x+current.width/2,current.y+current.height/2);


        }
        for (int i = 0; i < particula.size(); i++){
            particula.get(i).render(g);
        }

    }
}
