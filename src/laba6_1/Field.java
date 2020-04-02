package laba6_1;

import java.awt.Color;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*; 
 

@SuppressWarnings("serial")
 public class Field extends JPanel {   
// ���� ������������������ �������� 
 private boolean paused; 
 private boolean movev =true;
 private int counter=0;
 private int coun;
 private boolean slowly;
// ������������ ������ �������� ����� 
 private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10); 
 
  // ����� ������ �������� �� ���������� ��������� ������� ActionEvent  
//��� �������� ��� ���������� ������������ ��������� �����,
 // ����������� ��������� ActionListener
 private Timer repaintTimer = new Timer(10, new ActionListener() { 
     public void actionPerformed(ActionEvent ev) { 
  // ������ ����������� ������� ActionEvent - ����������� ����
         repaint();   
     }  
  });  
 // ����������� ������ BouncingBall
   public Field() {  
// ���������� ���� ������� ���� �����   
     setBackground(Color.WHITE);  
// ��������� ������ 
     repaintTimer.start(); 
   }   
// �������������� �� JPanel ����� ����������� ����������
  public void paintComponent(Graphics g) {
  // ������� ������ ������, �������������� �� ������ 
     super.paintComponent(g);  
     Graphics2D canvas = (Graphics2D) g;
  // ��������������� ��������� ���������� �� ���� ����� �� ������
     for (BouncingBall ball: balls) {  
           ball.paint(canvas); 
     }  
  }   
  
// ����� ���������� ������ ���� � ������ 
    public void addBall() {  
//����������� � ���������� � ������ ������ ���������� BouncingBall   
//��� ������������� ���������, ��������, �������, �����
 // BouncingBall ��������� ��� � ������������
    	counter++;
         balls.add(new BouncingBall(this));
    }
//����� ������������������, �.�. ������ ���� ����� ����� 
// ������������ ���� ������ 
      public synchronized void pause() {  
// �������� ����� �����
        paused = true;  
      }  
// ����� ������������������, �.�. ������ ���� ����� ����� 
// ������������ ���� ������
      public synchronized void resume() {  
// ��������� ����� �����  
        paused = false;   
//����� ��� ��������� ����������� ������ 
        notifyAll();
      }    
//������������������ ����� ��������, ����� �� ��� ���������
// (�� ������� �� ����� �����?)  
public synchronized void canMove(BouncingBall ball ) throws InterruptedException {  
	     if (paused && ball.getSpeed() < 5 ) {
 // ���� ����� ����� �������, �� �����, �������� 
// ������ ������� ������, ��������    
	    	
             wait(1200);  
         }  
     }

public void motion() {
	// TODO Auto-generated method stub
	
	 movev = true;  
	 coun=0;
}
	       
public synchronized void canAcc(BouncingBall ball ) throws InterruptedException{  
	if(movev) {
		coun++;
    ball.acceleration();
    if(coun ==counter) {
    	movev=false;
    }
   
	} 	
}

public void slowd() {
	// TODO Auto-generated method stub
	slowly = true;
	coun=0;
}
public synchronized void canslow(BouncingBall ball)  throws InterruptedException{  
	if(slowly) {
		coun++;
    ball.slow();
    if(coun==counter){
    	slowly=false;
    }
	}
}



} 
