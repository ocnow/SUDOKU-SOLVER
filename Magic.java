package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.CancellationException;
import javax.swing.*;

//class for each entry in given problem
class Entry
{
 //array holding list of all possible entries at position
 ArrayList list;
 //Current index in the list
 int try_index;
 
 Entry()
 {
  list = new ArrayList<>();
  
  //Currently fill list with all posibilities
  for(int i=0;i<9;i++){
   list.add(i+1);
  }
  try_index = -1;
 }
 
 
 int getNext()
 {
  try_index++;
  try{
   return list.get(try_index);
  }
  catch (Exception e) {
   try_index=-1;
   return -1;
  }
  
 }
}

public class MAGIC {
 
 //Entry object array for each element in array
 Entry[][] ent;
 static GUI F;
 
 //Integer array holding problem
 int[][] arr;
 
 MAGIC()
 {
  ent=new Entry[9][9];
  arr =new int[9][9];
 }
 
 
 public static void main(String[] args)
 {
  F = new GUI();
 }
 
 
 
 static void start()
 {
  MAGIC l1 =new MAGIC();
  
  for(int i=0;i<9;i++){
   for(int j=0;j<9;j++){
    
     l1.arr[i][j]=F.get_element(i, j);
     System.out.print(l1.arr[i][j]+" ");
     if(l1.arr[i][j]==0)
      l1.ent[i][j] = new Entry();
   }
   System.out.println();
   
  }
  
  l1.solve();
 }
 
 
 //Method to fill the possible number list of given position
 void fill_arrays(int i,int j){
  
   System.out.println(ent[i][j]);
   if(ent[i][j]!=null){
    
     for(int k=0;k<9;k++)
     {
      
      if(arr[i][k]!=0){
        
        ent[i][j].list.remove(new Integer(arr[i][k]));
      }
      if(arr[k][j]!=0){
       ent[i][j].list.remove(new Integer(arr[k][j]));
       
      }
     }
     
     int box_x=i/3;
     int box_y=j/3;
     
     
     for(int k=3*box_x;k<3*box_x+3;k++)
     {
      for(int l=3*box_y;l<3*box_y+3;l++)
      {
       
       if(ent[k][l] != null){
        ent[i][j].list.remove(new Integer(arr[k][l]));
       }
      }
     }
    }
   
 }
 
 void print_Arr(int i,int j)
 {
  if(ent[i][j].list!=null)
   for(Integer k:ent[i][j].list)
    System.out.print(k+" ");
 }
 
 void printSudoku()
 {
  for(int i=0;i<9;i++)
  {
   for(int j=0;j<9;j++)
   {
    F.set_element(i, j, arr[i][j]);
   }
  }
 }
 
 void solve()
 {
  int i=0;
  int j=0;
  while(i<=8 && j<=8 && ent[i][j]==null)   {    j++;    i=i+(j/9);    j=j%9;   }           if(i>8 || j>8)
  {
   System.out.println("sudoku all filled");
   System.exit(0);
  }
  
  
  fill_arrays(i, j);
  
 
  while(i<=8 && j<=8)
  {
   int x=ent[i][j].getNext();
  
   if(x==-1)
   {
    arr[i][j]=0;
    if(i==0 && j==0)
    {
     
     System.exit(1);
    }
    do{
    if(j==0){
     i=i-1;
     j=8;
    }else{
     j=j-1;
    }
    }while(i<=8 && j<=8 && ent[i][j]==null);
    
    continue;
   }
   
   arr[i][j] = x;
   
   do{
   j++;
   i=i+(j/9);
   j=j%9;
   }while(i<=8 && j<=8 && ent[i][j]==null);    if(i>8 || j>8)
   {
    continue;
   }
   
   ent[i][j] = new Entry();
   fill_arrays(i, j);
   
  }
  printSudoku();
 }
 
}
