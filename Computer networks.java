import java.io.*; 
class DV 
{ 
 public static void main(String args[]) throws Exception 
 { 
 BufferedReader b = new BufferedReader(new InputStreamReader(System.in)); 
 String filename=""; 
 boolean menu=true; 
 int[][] cost=new int[1][1];
 int[][][] olda=new int[1][1][2]; 
 int[][][] newa=new int[1][1][2]; 
 int[][][] routeredge=new int[1][1][2];
 int rcount=0; 
 try 
 { 
 System.out.println("Distance Vector Algorithm Using Bellman Ford"); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 System.out.println(); 
 while(menu) 
 { 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); System.out.println(" MENU "); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); System.out.println("1.Load file");
 System.out.println("2.Output Final Table for a Selected Router"); 
 System.out.println("3.Compute and Outpt Final Routing Table"); 
 System.out.println("4.Link Failure"); 
 System.out.println("5.Output Optimal Path and Minimum Cost"); 
 System.out.println("6.EXIT");
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 System.out.println("Enter the option"); 
try 
 { 
 filename=b.readLine(); 
 } 
 catch (IOException e) 
 { 
 System.out.println("IOError"); 
 } 
 int optn=Integer.parseInt(filename); 
 boolean flag=false; 
 switch(optn) 
 { 
    //Loads the file

     case 1:
         System.out.println("Enter the file name of cost matrix"); 
         filename=b.readLine(); 
    
         FileInputStream fstream = new FileInputStream(filename); 
        
         DataInputStream in = new DataInputStream(fstream);
        
         BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
         String strLine;
     
         rcount=0;
      

 while ((strLine = br.readLine()) != null) 
 { 
 rcount++;
 } 
 in.close(); 

 cost=new int[rcount][rcount];
 olda=new int[rcount][rcount][2]; 
 newa=new int[rcount][rcount][2]; 

 routeredge=new int[rcount][rcount][2];
 int j=-1; 
 int k=0; 


 FileInputStream fstream1 = new FileInputStream(filename); 
 DataInputStream in1 = new DataInputStream(fstream1); 

 BufferedReader br1 = new BufferedReader(new InputStreamReader(in1)); 


/*
The file is read until no more values exists and each value is read using split function and is stored in an array named sarray and its respective cost is stored in cost[][]
*/

 while ((strLine = br1.readLine()) != null) 
 { 
 j++; 
 String[] sarray= new String[rcount]; 
 sarray=strLine.trim().split("\\s+");
 for(k=0;k<rcount;k++) 
 { 
 cost[j][k]=Integer.parseInt(sarray[k]); 
 } 
 } 
 in1.close(); 
 System.out.println("The Input Table is"); 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 System.out.print(cost[j][k] + " "); //Prints the initial cost table
 } 
 System.out.println(); 
 } 
     break ;
     case 2: 
      try { 
 while(true) 
 { 
 System.out.println("Enter the router number"); 
 filename=b.readLine(); 

//Checks if the entered router number is the valid router number

 if(Integer.parseInt(filename) > rcount) 
 { 
 System.out.println("Router number does not exist. Retry!"); 
 continue; 
 } 
 else 
 break; 
 } 
 } 
 catch (IOException e) 
 { 
 System.out.println("IOError"); 
 } 
 int rno=Integer.parseInt(filename); 
 int i=0; 
 j=0; 
 k=0; 
 int T=0; 
 flag=false; 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 newa[j][k][0]=cost[j][k]; 
 newa[j][k][1]=k; 
 } 
 } 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
System.out.println("Iteration T=0"); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
System.out.println("The Cost Matrix at router <" + rno + "> is:"); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 rno--; 

 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 if(j==rno) 
 System.out.print(cost[rno][k] + " "); 
 else 
 System.out.print("-1 "); 
 } 
 System.out.println(); 
 } 
 while(!flag) 
 { 
 T++; 
 flag=true; 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 

// Updates old matrix values (olda[][][])  with new matrix values (newa[][][])

 olda[j][k][0]=newa[j][k][0]; 
 olda[j][k][1]=newa[j][k][1]; 
 } 
 } 
 for(i=0;i<rcount;i++) 
 { 
 for(j=0;j<rcount;j++) 
 { 

/* If there exists an edge between two nodes then update routeredge matrix (routeredge[][][])  with old array matrix value (olda[][][]) */

 if(cost[i][j]!=-1) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=olda[j][k][0]; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 else 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=-1; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 } 
 if(rno==i) 
 { 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 System.out.println("Iteration T="+T); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 System.out.println("Matrix in next level"); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 

/* For the requested router number prints the updated cost after each level */
 
 System.out.print(routeredge[j][k][0] + " "); 
 } 
 System.out.println(); 
 } 
 } 
 for(k=0;k<rcount;k++) 
 { 
 int x=routeredge[i][k][0]; 
 
/* 
If there exists an edge between i to k 
then 
it finds an intermediate router j that has an edge to routers i and k then it checks for the condition whether the cost from i to j and cost from j to k is less than cost from i to k then update the minimum cost in the new array (newa[][][0]) and stores the intermediate router number in the new array (newa[][][1]) 
else if
there does not exists an edge between i to k
then
it compares the costs of all possible intermediate routers from i to k and updates the new array according
*/

if(x!= -1) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(x>routeredge[j][k][0] && 
x>(routeredge[j][k][0]+routeredge[i][j][0])) 
 { 
newa[i][k][0]=routeredge[j][k][0]+routeredge[i][j][0]; 
 x=newa[i][k][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 } 
 } 
 } 
 else 
 { 
 int y=0; 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(y==0) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 else 
 { 
if(y>routeredge[j][k][0]+routeredge[i][j][0]) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 flag=false; 
 newa[i][k][1]=j; 
 } 
 } 
 } 
 } 
 newa[i][k][0]=y; 
 } 
 } 
 if(rno==i) 
 { 
 System.out.println("New Matrix after recalculating is"); 
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 if(j==i) 
 System.out.print(newa[j][k][0] + " "); 

 else 
 System.out.print(routeredge[j][k][0] + " "); 
 } 
 System.out.println(); 
 } 
 } 
 } 
 } 
 break; 
 case 3: 
/* Follows the same logic as of case 2 until there are no more updations */
 
for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 newa[j][k][0]=cost[j][k]; 
 newa[j][k][1]=k; 
 } 
 } 
 while(!flag) 
 { 
 flag=true; 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 olda[j][k][0]=newa[j][k][0]; 
 olda[j][k][1]=newa[j][k][1]; 
 } 
 } 
 for(i=0;i<rcount;i++) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(cost[i][j]!=-1) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=olda[j][k][0]; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 else 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=-1; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 } 
 for(k=0;k<rcount;k++) 
 { 
 int x=routeredge[i][k][0]; 
 if(x!= -1) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(x>routeredge[j][k][0] && 
x>(routeredge[j][k][0]+routeredge[i][j][0])) 
 { 
newa[i][k][0]=routeredge[j][k][0]+routeredge[i][j][0]; 
 x=newa[i][k][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 } 
 } 
 } 
 else 
 { 
 int y=0; 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(y==0) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 else 
 { 
if(y>routeredge[j][k][0]+routeredge[i][j][0]) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 flag=false; 
 newa[i][k][1]=j; 
 } 
 } 
 } 
 } 
 newa[i][k][0]=y; 
 } 
 } 
 } 
 } 
 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 System.out.println("Final table is"); 
 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 System.out.print(newa[j][k][0] + " "); 
 } 
 System.out.println(); 
 } 
 break; 
case 4: 

/* User enters the failed router number for which all the neighboring edges are set to -1 and updates the matrix */

 System.out.println("Enter the failed router number"); 
 try 
 { 
 filename=b.readLine(); 
 String[] sarray; 
 sarray=filename.split(" "); 
  { 
 int src=Integer.parseInt(sarray[0]); 
 if(src >rcount) 
 { 
 System.out.println("Router no. is not appropiate"); 
 break; 
 } 
 src--; 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=src) 
{
 cost[j][src]=-1; 
 cost[src][j]=-1; 
 }
 } 
 } 
 } 
 catch(IOException e) 
 { 
 System.out.println("IOError"); 
 } 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 newa[j][k][0]=cost[j][k]; 
 newa[j][k][1]=k; 
 } 
 } 
 while(!flag) 
 { 
 flag=true; 
 for(j=0;j<rcount;j++) 

 { 
 for(k=0;k<rcount;k++) 
 { 
 olda[j][k][0]=newa[j][k][0]; 
 olda[j][k][1]=newa[j][k][1]; 
 } 
 } 
 for(i=0;i<rcount;i++) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(cost[i][j]!=-1) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=olda[j][k][0]; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 else 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=-1; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 } 
 for(k=0;k<rcount;k++) 
 { 
 int x=routeredge[i][k][0]; 
 if(x!= -1) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(x>routeredge[j][k][0] && 
x>(routeredge[j][k][0]+routeredge[i][j][0])) 
{ 
 newa[i][k][0]=routeredge[j][k][0]+routeredge[i][j][0]; 
 x=newa[i][k][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 } 
 } 
 } 
 else 
 { 
 int y=0; 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(y==0) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 else 
 { 
 
if(y>routeredge[j][k][0]+routeredge[i][j][0]) 

 { 

 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 flag=false; 
 newa[i][k][1]=j; 
 } 
 } 
 } 
 } 
 newa[i][k][0]=y; 

 } 
 } 
 } 
 } 
 System.out.println("Matrix Updated"); 
 break; 
 case 5: 
/* Using the similar logic calculate and output the optimal path between source and destination using newa[][][0] and prints the respective router numbers for that path using newa[][][1] */
 
for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 newa[j][k][0]=cost[j][k]; 
 newa[j][k][1]=k; 
 } 
 } 
 while(!flag) 
 { 
 flag=true; 
 for(j=0;j<rcount;j++) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 olda[j][k][0]=newa[j][k][0]; 
 olda[j][k][1]=newa[j][k][1]; 
 } 
 } 
 for(i=0;i<rcount;i++) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(cost[i][j]!=-1) 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=olda[j][k][0]; 
 } 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 else 
 { 
 for(k=0;k<rcount;k++) 
 { 
 routeredge[j][k][0]=-1; 
} 
 routeredge[i][j][1]=olda[i][j][1]; 
 } 
 } 
 for(k=0;k<rcount;k++) 
 { 
 int x=routeredge[i][k][0]; 
 if(x!= -1) 
 { 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(x>routeredge[j][k][0] && 
x>(routeredge[j][k][0]+routeredge[i][j][0])) 
 { 
newa[i][k][0]=routeredge[j][k][0]+routeredge[i][j][0]; 
 x=newa[i][k][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 } 
 } 
 } 
 else 
 { 
 int y=0; 
 for(j=0;j<rcount;j++) 
 { 
 if(j!=i && routeredge[j][k][0]!=-1) 
 { 
 if(y==0) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 newa[i][k][1]=j; 
 flag=false; 
 } 
 else 
 { 
 if(y>routeredge[j][k][0]+routeredge[i][j][0]) 
 { 
y=routeredge[j][k][0]+routeredge[i][j][0]; 
 flag=false; 
 newa[i][k][1]=j; 
 } 
 } 
 } 
 } 
 newa[i][k][0]=y; 
 } 
 } 
 } 
 } 
 try 
 { 
 System.out.println("Enter Source and Destination router number"); 
 filename=b.readLine(); 
 int sr,dr; 
 { 
 String[] sarray; 
 sarray=filename.split(" "); 
 sr=Integer.parseInt(sarray[0]); 
 dr=Integer.parseInt(sarray[1]); 
 if(sr >rcount) 
 { 
 System.out.println("Source Router no. is not appropiate"); 
 break; 
 } 
 if(dr > rcount) 
 { 
 System.out.println("The Destination Router no. is not appropriate"); 
 break; 
 } 
 } 
 System.out.println("Source Router Number is: " + sr); 
 System.out.println("Destination Router Number is: " + dr); 
 String result; 
 result=Integer.toString(sr); 
 sr--; 
 dr--; 
 int vr=sr; 
 while(olda[vr][dr][1]!=dr) 
 { 
 int v=olda[vr][dr][1]; 
 v++; 
 result=result.concat("-"); 
 result=result.concat(Integer.toString(v)); 
 vr=olda[vr][dr][1]; 
 } 
 result=result.concat("-"); 
 result=result.concat(Integer.toString((dr+1))); 
 System.out.println("Optimal path is: " + result); 
 System.out.println("Cost of the Optimal path is: " + 
olda[sr][dr][0]); 
 } 
 catch (IOException e) 
 { 
 System.out.println("IOError"); 
 } 
 break; 
 case 6: 
menu=false; 
 break; 
 default: 
 System.out.println("Enter correct choice"); 
 break; 
 }
} 
}
 catch (Exception e){//Catch exception if any 
 System.err.println("Error: " + e.getMessage()); 
 return; 
} 
 }
 }

