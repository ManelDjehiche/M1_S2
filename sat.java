
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */



public class sat {
    
    private boolean[] solution=new boolean[20];
    private int[] literaux=new int[20];

    public int[] getLiteraux() {
        return literaux;
    }

    public void setLiteraux(int indice,int val) {
        literaux[indice] = val;
    }
    
    
 
    public boolean[] getSolution() {
        return solution;
    }

    public void setSolution(int indice,int a) {
        boolean b;
        if( a == 0) b =false; else b =true;   
        this.solution[indice] = b;
    }
    
      public void aleatoir(){
    
          int rand = 0;
            for (int i=0;i<20;i++){
            rand = ThreadLocalRandom.current().nextInt(0,2);
            this.setSolution(i,rand);
            }
            
    }
      
      public void solutionaleatoir(){
          
          this.aleatoir();
          boolean[] sol=this.getSolution();
          for(int i=0;i<20;i++){
             if(sol[i] == true) this.setLiteraux(i,i+1);else this.setLiteraux(i,-(i+1));
          }
          
      }
      
      public boolean oneclause(String[] table,int linenumber){
          
          boolean b=false;
          int indice;
          int[] liter=this.getLiteraux();
         
          //transmettre clause de string vers array of int
          int[] clause=new int[4];
          for(int i=0;i<4;i++) clause[i]=Integer.parseInt(table[i]); 
          
          System.out.print("\n\nclause numméro ("+linenumber+"):\t");
          for(int i=0;i<table.length;i++) System.out.print(clause[i]+"\t");
          
          // verification
          for(int i=0;i<3;i++){
              if(clause[i] < 0)indice= -(clause[i]);else indice=clause[i];
              if(liter[indice-1] == clause[i]){ b = true; System.out.println("\n => "+clause[i]+" satifsait cette clause");}
                             
          }
          
        
          
          return b;
          
      }
      
      
       public void satisfaction() throws IOException{
      
      
            File temp =new File("C:\\Users\\user\\Desktop\\Manel_M1_S2\\Meta_Heuristique\\tp\\uf20-01.cnf");
            Scanner file= new Scanner(temp);
            String line =file.nextLine();
            boolean sat=true;
            int linenumber=0;
            
         
            try{
                     while(line != null  && sat == true){
                     linenumber++;
                     String[] clause=line.split(" ");   
                     
                     if(linenumber > 8)
                     sat=this.oneclause(clause,linenumber);            
                     line =file.nextLine();
               
                    }
                     
                     
                     if(sat == true) System.out.println(" \n\n ********* Cette solution est Satisfiable *********");
                     else System.out.println(" \n\n ********* Cette solution est Non-Satisfiable *********");
                       
                 }catch(Exception e){
                      System.out.println("you can't learn more, sorry! ");
            }


         }

              
      public void printbooleansolution(){
          System.out.println("\n\n Solution booelan :: ");
      for(int i=0;i<20;i++) System.out.print(this.getSolution()[i] +"\t  ");
      }
      
      public void printintsolutiont(){
          System.out.println("\n\n Solution Les literaux :: ");
      for(int i=0;i<20;i++) System.out.print(this.getLiteraux()[i] +"\t ");
          
      }
      
    
    public static void main (String[]args)  throws IOException{
    
        String myString = "-15";
       /* int a=Integer.parseInt(myString);
        int foo = Integer.parseInt(myString); 
        System.out.println(foo);
        
        for(int i=0;i<100;i++){
        int rand = ThreadLocalRandom.current().nextInt(0,2);
        System.out.println(rand);  */

 
 int a=Integer.parseInt(myString.replace("-", ""));
 
 int c=12;
 int b=-(c+1);
         System.out.println(b); 
         
         sat o =new sat();
         o.solutionaleatoir();
         o.printbooleansolution();
         o.printintsolutiont();
         o.satisfaction();         
         

    
    
    }
    
}
