import javax.swing.JFrame;
//import java.awt.Panel;


public class Frame extends JFrame{

    Frame(){
        this.add(new Panel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ensuring same experiancs across different devices
        this.setResizable(false);
//        gets the preferred size from the computer
        this.pack();
        this.setVisible(true);
//        to spooning in the center to the screen
        this.setLocationRelativeTo(null);
//        if i initialize as null it will appear in teh center of the screen
    }
}
