
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class tp1 {
    
    
    private String[] literale=new String[41]; 
    private int[] satisfait=new int[100];
    private int totale;

    public tp1() {
      for(int i=0;i<this.getLiterale().length;i++) literale[i]="";
      this.totale=0;
    }
    
    
    public int getTotale() {
        return totale;
    }

    public void setTotale() {
       int[] a= this.getSatisfait();
       for(int i=0;i<a.length;i++)
           if(a[i] != 0) totale++;
         
    }
    
    
    public String[] getLiterale() {
        return literale;
    }

    public void setLiterale(int a) {
        String b=a+"";
        int l=0;
        while(literale[l].equals("") == false)l++;    
        this.literale[l] = b;
       
    }

    public int[] getSatisfait() {
        return satisfait;
    }

    public void setSatisfait(int a) {
        int l=0;
        while(satisfait[l] != 0)l++;
        this.satisfait[l] = a;
    }
     
    
    public String getlastelementLiterale() {
        
        int l=0;
        while(literale[l].equals("") == false && l<40)l++;
        return literale[l-1];
    }
    
    public boolean existeclause(int a){
    
        boolean existe=false;
        int i=0;
        while(existe == false && i<this.getSatisfait().length){         
            if(satisfait[i] == a) existe =true;else i++;         
        }
        return existe;
    }
    
      public boolean existeliterale(int a){
        
        String s=""+a;
        boolean existe=false;
        String[] lit=this.getLiterale();
        int i=0;
        while(existe == false && i < this.getLiterale().length){         
            if(lit[i].equals(s) == true) existe=true; else i++;          
        }
        return existe;
    }
      
    public void  printlietralelist(){
        String[] list=this.getLiterale();
        
        System.out.print("\n liste des  literaux:: ");
        for(int i=0;i<list.length;i++){
            System.out.print(list[i]+" ");
        }
  
        
    }
    
    public void printclauselist(){
        int[] list=this.getSatisfait();
        
        System.out.print("\n Les clauses satisfaits:: ");
        for(int i=0;i<list.length;i++){
            if(list[i] != 0)System.out.print(list[i]+" ");
        }
     
    }
    
    public void printtotale(){
     this.setTotale();
     System.out.println(" \n l'union ::"+getTotale());
    }
    
    
    // tirer un chiffre aleatoire sans remise
     public int aleatoir(){
     
          boolean exist=true;
          int rand = 0;
          while(exist == true){
            rand = ThreadLocalRandom.current().nextInt(-20,20);
            exist = this.existeliterale(rand);
          }  
            this.setLiterale(rand);
            return rand;
            
    }
     
    
     public void satisfaction() throws IOException{
      
            String a=this.getlastelementLiterale(); 


            File temp =new File("C:\\Users\\user\\Desktop\\Manel_M1_S2\\Meta_Heuristique\\tp\\uf20-01.cnf");
            Scanner file= new Scanner(temp);
            String line =file.nextLine();
            int linenumber=0;

            try{
                     while(line != null ){
                     String[] c=line.split(" ");
                     for(int j=0;j<c.length;j++){
                          
                              if (a.equals(c[j])){
                                              
                                  if(existeclause(linenumber) == false) this.setSatisfait(linenumber);                                  
                                  System.out.println("\t \t \t "+a+" satifait la clause:: "+line+" num:: "+linenumber);}
                          }
                     
                    line =file.nextLine();
                    linenumber++;
                    }
                       
                 }catch(Exception e){
                      System.out.println("you can't learn more, sorry! ");
            }


         }

      
    // calcule la sataisfasibilite de chaque literale dans tableau par apport au ficheir .cnf donné
    public int[] occurence(String[] table) throws IOException{
        
        int l=table.length;
        int[] result=new int[l];
        boolean b;
        
        // initialisation du tableau resultat qui conteint les occurence de chaque literale
        for(int i=0;i<l;i++)result[i]=0;
        
        File temp =new File("C:\\Users\\user\\Desktop\\Manel_M1_S2\\Meta_Heuristique\\tp\\uf20-01.cnf");
        Scanner file= new Scanner(temp);
        String line =file.nextLine();
        List<Integer> satisfait = new ArrayList<Integer>();
        
        try{
                 while(line != null ){
                 System.out.println(line);
                 
                  for(int i=0;i<l;i++){  
                      
                    String[] c=line.split(" ");
                    for(int j=0;j<c.length;j++){
                          boolean x=table[i].equals(c[j]);
                          if (x == true){
                          result[i]++;
                          System.out.println("=>> "+table[i]+" AND =>>"+c[j]+" =>>>>>>>>>> ::::: "+x);}
                      }
                
                }
                    line =file.nextLine();
                  
                 
             }
                 
             }catch(Exception e){
                  System.out.println("you can't learn more, sorry! ");
        }

       
    
       return result;
    }
    
    
    // affciher tout le fichier .cnf
    public void show_all() throws IOException{
        File temp =new File("C:\\Users\\user\\Desktop\\Manel_M1_S2\\Meta_Heuristique\\tp\\uf20-01.cnf");
        Scanner file= new Scanner(temp);
        String a=file.nextLine();
        
        try{
                 while(a != null ){
                 System.out.println(a);
                 a=file.nextLine();
             }
                 
             }catch(Exception e){
                  System.out.println("you can't learn more, sorry! ");
        }
    }
    
    public static void main(String[] args) throws IOException{
        
       tp1 o =new tp1();
     
       for(int i=0;i<40;i++){
       o.aleatoir(); 

       System.out.println("\n Iteration "+i+" =======> "+o.getlastelementLiterale()+" -----------------------------------------------------------------------------------------------------------");       
       o.satisfaction();
       o.printlietralelist();
       o.printclauselist(); 
       }
       
       o.printtotale();
       
       

       
       
      
     
      
      
      

   }
}
