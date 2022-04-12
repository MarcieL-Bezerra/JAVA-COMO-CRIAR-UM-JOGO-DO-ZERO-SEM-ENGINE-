import javax.swing.*;

public class Inicial {
    public static void main(String[] args) {
        Game game = new Game();
        JFrame jframe =  new JFrame("Meu Jogo");
        jframe.add(game);
        jframe.setLocationRelativeTo(null);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.setVisible(true);

        new Thread(game).start();

    }
}
