

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


 //  array for tracking the snake

 import java.util.Arrays;

//random food provide

 import java.util.Random;



//inheriting the panel class from JPanel and adding actionListner
public class Panel extends JPanel implements ActionListener {
//   declaring the size of the panel
    static int width=1200;
    static int height=600;
//    size of  each unit
    static int unit=50;
//    for checking the game at regular interval
    Timer timer;
    static int delay = 160;
//   for food spawns(snakes food)
    Random random;
    int body=3;
    int score=0;
    char dir='R';
//    false cause the game has not started yet
    boolean flag=false;

//    x and y veriable to spawn food
    int fx,fy;
    static  int size=(width*height)/(unit*unit);
   public int [] xsnake = new int[size];
   public int [] ysnake = new int[size];



    Panel(){
//        for the panel dimention
         this.setPreferredSize(new Dimension(width,height));
//      background colour
         this.setBackground(Color.BLACK);
//      keyboard will go directly to the game
         this.setFocusable(true);
         random=new Random();
         this.addKeyListener(new Key());
         game_start();
    }

    public void game_start(){
    spawnfood();
  // setting the flag to true when the game starts
//game has started
    flag=true;
  // starting the timer
//to keep checking the state of the game
    timer =new Timer(delay,this);
    timer.start();
    }
    public void spawnfood(){

//  its stores the random integer between the provided range
        fx=random.nextInt((int)(width/unit))*unit;
        fy=random.nextInt((int)(height/unit))*unit;
    }

    public void checkHit(){
      for(int i=body;i>0;i--){
// x co-ordinate is the xsnake[0];
//          ysnake[0] is the snake head in the y co-ordinate
          if((xsnake[0]==xsnake[i]) && (ysnake[0]==ysnake[i])){
              flag =false;
          }
      }
//      if snake hit the wall
      if(xsnake[0]<0){
          flag=false;
      }
      if(xsnake[0]>width){
          flag=false;
      }
        if(ysnake[0]<0){
            flag=false;
        }
        if(ysnake[0]>height){
            flag=false;
        }
//
        if(!flag){
            timer.stop();
        }
    }
/// contains all the colour shapes and size
       public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        draw(graphic);
       }
      public void draw(Graphics graphic){
//        2 senerio if the game ended or the snake hhit the wall
//          flag=true    snake and the food
//          flag=fasle   game end
          if(flag==true){
//              need to paint snake and the food
              graphic.setColor(Color.red);
//              fro oval fx,fy lagbe then th ehihgt and the breth cause pix 50
              graphic.fillOval(fx,fy,unit,unit);
//              setting pram fro the snake
              for(int i=0;i<body;i++){
//                  for the head colour of the snake
                  if(i==0){
                      graphic.setColor(Color.GREEN);
                      graphic.fillRect(xsnake[i], ysnake[i],unit,unit);
                  }
//                  for the other part
                  else{
                      graphic.setColor(Color.orange);
                      graphic.fillRect(xsnake[i], ysnake[i], unit, unit);

                  }
              }
              graphic.setColor(Color.BLUE);
              graphic.setFont(new Font("Comic Sans",Font.BOLD,40));
              FontMetrics f=getFontMetrics(graphic.getFont());
//              starting position in x and the starting position in y
              graphic.drawString("SCORE:"+score,(width - f.stringWidth("SCORE:"+score))/2,graphic.getFont().getSize());
          }
          else{
              gameOver(graphic);
          }
      }




      public void gameOver(Graphics graphic){
          graphic.setColor(Color.RED);
          graphic.setFont(new Font("Comic Sans",Font.BOLD,40));
          FontMetrics f=getFontMetrics(graphic.getFont());
//              starting position in x and the starting position in y
          graphic.drawString("SCORE:"+score,(width - f.stringWidth("SCORE:"+score))/2,graphic.getFont().getSize());
//        will draw the graphic for game over text
          graphic.setColor(Color.BLUE);
          graphic.setFont(new Font("Comic Sans",Font.BOLD,80));
          FontMetrics f2=getFontMetrics(graphic.getFont());
//              starting position in x and the starting position in y
          graphic.drawString("Game Over",(width - f2.stringWidth("Game Over"))/2,height/2);



//          graphic for the replay font

          graphic.setColor(Color.BLUE);
          graphic.setFont(new Font("Comic Sans",Font.BOLD,40));
          FontMetrics f3=getFontMetrics(graphic.getFont());
//              starting position in x and the starting position in y
          graphic.drawString("Press R to replay",(width - f3.stringWidth("Press R to replay"))/2,height/3);
      }


      public void move(){
//        loop for updating the body part except the head
        for(int i = body;i>0;i--){
//            body part will move to the head   last body part move to the head
            xsnake[i]=xsnake[i-1];
            ysnake[i]=ysnake[i-1];
        }
//        to get the head will creat a switch case
          switch(dir){
              case 'U':
                  ysnake[0]=ysnake[0]-unit;
                  break;
              case 'D':
                  ysnake[0]=ysnake[0]+unit;
                  break;
              case 'L':
                  xsnake[0]=xsnake[0]-unit;
                  break;
              case 'R':
                  xsnake[0]=xsnake[0]+unit;
                  break;
          }
      }

      public void checkScore(){
        if(fx==xsnake[0] && fy==ysnake[0]){
            body++;
            score++;
            spawnfood();
        }
      }


    public class Key extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(dir!='R'){
                        dir='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(dir!='L'){
                        dir='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(dir!='D'){
                        dir='U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(dir!='U'){
                        dir='D';
                    }
                    break;
                case KeyEvent.VK_R:
//                    game is running so the flag is true
//                    to restart we have to make the falg false and we will chexk if the falg is not ture
                    if(!flag){
//                        changing every thing to the initian value like reseting
//                        and starting from the beginning

                        score=0;
                        body=3;
                        dir='R';
                        Arrays.fill(xsnake,0);
                        Arrays.fill(ysnake,0);
                        game_start();
                    }
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
      if(flag){
          move();
          checkScore();
          checkHit();
      }
      repaint();
    }
}
