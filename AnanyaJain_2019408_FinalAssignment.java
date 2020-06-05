
import java.io.*; 
import java.util.*; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

	 

	
class Block{
	public long[] data;

	Block(int n)
	{
		this.data=new long[(int)Math.pow(2, n)];               //Blocks in memory
		for(int i=0;i<(int)Math.pow(2, n);i++)
		     data[i]=0;
	}
}

public class AnanyaJain_2019408_FinalAssignment
{
	public static void main(String[] Args) throws IOException
	{   Scanner in=new Scanner(System.in);
		System.out.println("Please Choose The Kind Of Cache you wish to use:");
		System.out.println();
		System.out.println("Press 1 for Direct Mapped Cache");
		System.out.println("Press 2 for Fully Associative Cache");                  //introductory menu
		System.out.println("Press 3 for Set Associative Cache");
		int sel=in.nextInt();
		if(sel==1)
			dmc();
		else if(sel==2)
			fac();
		else
			sac();
	}
	
	static void dmc() throws IOException
	{   
		Reader.init(System.in);
	    System.out.println("Mention the memory size in power of 2 ");
	    int m=Reader.nextInt();
	   
	    System.out.println("Enter block size in power of 2");
	    int b=Reader.nextInt();
		System.out.println();
	    System.out.println("Enter cache lines in power of 2");
	    int cl=Reader.nextInt();
		System.out.println();
        int totblocks=m-b;//no of blocks in power of 2
        
        Block[] mainmem=new Block[(int)Math.pow(2,totblocks)]; //main memory
        for(int i=0;i<(int)Math.pow(2,totblocks);i++)
        	{
        	   mainmem[i]=new Block(b);
        	}
        
        int[] tagarr=new int[(int)Math.pow(2,cl)];
                                                          //direct mapped cache inputs and breaking the inputs in part for access of memory
        Block[] dataarr=new Block[(int)Math.pow(2,cl)];
        for(int i=0;i<(int)Math.pow(2,cl);i++)
        	dataarr[i]=new Block(b);
        
       /* for(int i=0;i<(int)Math.pow(2,cl);i++)
        	for(int j=0;j<(int)Math.pow(2, b);j++)
        		System.out.println(mainmem[i].data[j]);
	    */
	    System.out.println("Address of the form ");
		System.out.println();
	    System.out.println("TAG CACHE_LINE OFFSET");
	    System.out.println("  "+(totblocks-cl)+"       "+cl+"       "+b +" bits");
	    		
		System.out.println();
	   
	    
		System.out.println("Enter the no of commands you wish to enter");
		int no=Reader.nextInt();
		System.out.println();
		System.out.println("to read enter 0 address");
		System.out.println("to write enter 1 content address ");//add in binary content in decimals
		System.out.println();
		for(int i=0;i<no;i++)
		{
			int mode=Reader.nextInt();
			if(mode==1)
			{   long info=Reader.nextLong();
			//System.out.println(info);
			String binadd=Reader.next();
			String mainmemstr=binadd.substring(0,binadd.length()-b);
			//System.out.println(mainmemstr);
			String incache=binadd.substring(totblocks-cl,totblocks);
			//System.out.println(incache);
			String tag=binadd.substring(0,totblocks-cl);
			//System.out.println(tag);
			String offset=binadd.substring(totblocks,binadd.length());
			//System.out.println(offset);
				
				int mmadd=Integer.parseInt(mainmemstr,2);  
				int cacheadd=Integer.parseInt(incache,2);  
				int tagadd=Integer.parseInt(tag,2);  
				int badd=Integer.parseInt(offset,2);                                         //data write in cache 
				
				mainmem[mmadd].data[badd]=info;
				Block temp=mainmem[mmadd];
				if(tagarr[cacheadd]==tagadd)
				{   
					dataarr[cacheadd].data[badd]=info;      //in case of cache hit
				}
				else
				{  
					tagarr[cacheadd]=tagadd;
					dataarr[cacheadd]=temp;
					System.out.println("CACHE MISS!!");
					System.out.println("BLOCK BROUGHT TO CACHE!!  "+"Block :"+mainmemstr); // in case of cache miss
				}
				System.out.println("Data Stored!");
				System.out.println();
			}
			else 
			{
				String binadd=Reader.next();
				String mainmemstr=binadd.substring(0,binadd.length()-b);
				//System.out.println(mainmemstr);
				String incache=binadd.substring(totblocks-cl,totblocks);
				//System.out.println(incache);
				String tag=binadd.substring(0,totblocks-cl);
				//System.out.println(tag);
				String offset=binadd.substring(totblocks,binadd.length());
				//System.out.println(offset);
					
					int mmadd=Integer.parseInt(mainmemstr,2);  
					int cacheadd=Integer.parseInt(incache,2);  
					int tagadd=Integer.parseInt(tag,2);  
					int badd=Integer.parseInt(offset,2);  
					
				if(tagarr[cacheadd]==tagadd)//cache hit
				{
					System.out.println("Data: "+dataarr[cacheadd].data[badd]);            //  reading a direct mapped cache 
					System.out.println();
				}
				else// cache miss
				{    System.out.println("Data: "+mainmem[mmadd].data[badd]);
				System.out.println();
					  tagarr[cacheadd]=tagadd;
					  dataarr[cacheadd]=mainmem[mmadd];
					  System.out.println("CACHE MISS!!");
				      System.out.println("BLOCK BROUGHT TO CACHE!!  "+"Block :"+mainmemstr);
				}
			}
			
			System.out.println("Cache Status: Blocks in Cache");  //Display of Cache
			System.out.println();
			System.out.println("TAG CACHE_LINE   Data");
			for(int j=0;j<(int)Math.pow(2,cl);j++)
			{
				System.out.print(Integer.toBinaryString(tagarr[j])+"        "+Integer.toBinaryString(j)+"->");
				for(int mm=0;mm<Math.pow(2, b);mm++)
				{System.out.print(dataarr[j].data[mm]+" ");
					
				}
				System.out.println();
			}
			
		}
		
		
	}
	
	static void fac() throws IOException
	{   
		Reader.init(System.in);
	    System.out.println("Mention the memory size in power of 2 ");
	    int m=Reader.nextInt();
	   
	    System.out.println("Enter block size in power of 2");
	    int b=Reader.nextInt();
        
	    System.out.println("Enter cache lines in power of 2");
	    int cl=Reader.nextInt();
	    
        int totblocks=m-b;//no of blocks in power of 2
        
        Block[] mainmem=new Block[(int)Math.pow(2,totblocks)]; //main memory
        for(int i=0;i<(int)Math.pow(2,totblocks);i++)
        	{
        	   mainmem[i]=new Block(b);
        	}
        
        int[][] tagarr=new int[(int)Math.pow(2,cl)][2];
        
        Block[] dataarr=new Block[(int)Math.pow(2,cl)];
        for(int i=0;i<(int)Math.pow(2,cl);i++)
        	dataarr[i]=new Block(b);
                                                                               //reading inputs in a fully associative cache
        System.out.println("Address of the form ");
    	System.out.println();
	    System.out.println("BLOCK OFFSET");
	    System.out.println((totblocks)+"     "+b+" bits");
	    		
	    
	   
		System.out.println();
		System.out.println("Enter the no of commands you wish to enter");
		int no=Reader.nextInt();
		System.out.println();
		System.out.println("to read enter 0 address");
		System.out.println("to write enter 1 content address ");//add in binary content in decimals
		System.out.println();
		for(int i=0;i<no;i++)
		{
			int mode=Reader.nextInt();
			if(mode==1)
			{   long info=Reader.nextLong();
			//System.out.println(info);
			String binadd=Reader.next();
			String mainmemstr=binadd.substring(0,binadd.length()-b);
			//System.out.println(mainmemstr);
			String offset=binadd.substring(totblocks,binadd.length());
			//System.out.println(offset);
				
				int mmadd=Integer.parseInt(mainmemstr,2);  // 
				int badd=Integer.parseInt(offset,2);  
				boolean miss=true;
				mainmem[mmadd].data[badd]=info;
				Block temp=mainmem[mmadd];
				int minoccur=10000000;                                                //writing in a fully associative cache
				int indexmin=-1;
				for(int j=0;j<(int)Math.pow(2,cl);j++)//traversing through all cache lines
				{	if(tagarr[j][0]==mmadd) //cache hit
				{  dataarr[j].data[badd]=info;
					temp=dataarr[j];
					miss=false;
					tagarr[j][1]+=1;
					System.out.println("Data Stored");
					System.out.println();
					break;
					}
				else//cache miss
				{  if(tagarr[j][1]<minoccur)   //apt for replacement using LRU
						{minoccur=tagarr[j][1]; 
						 indexmin=j;
						}
				}
		       }
				if(miss==true)  //replacement and eviction
				{   miss=false;
					tagarr[indexmin][0]=mmadd;   
					tagarr[indexmin][1]=1;
					dataarr[indexmin]=temp;
					System.out.println("CACHE MISS!!");
					System.out.println("BLOCK BROUGHT TO CACHE!!  "+"Block :"+mainmemstr);
					System.out.println("Data Stored");
					System.out.println();
				}
			}
			else 
			{   int index=-1;;
			    int min=1000000;
				String binadd=Reader.next();
				String mainmemstr=binadd.substring(0,binadd.length()-b);
				//System.out.println(mainmemstr);
				String offset=binadd.substring(totblocks,binadd.length());
				//System.out.println(offset);
					boolean miss=true;
					int mmadd=Integer.parseInt(mainmemstr,2);  
					int badd=Integer.parseInt(offset,2);  
					
					for(int j=0;j<(int)Math.pow(2,cl);j++)
					{	
					if(tagarr[j][0]==mmadd)//cache hit
					{
						
						miss=false;
						tagarr[j][1]+=1;
						System.out.println("Data: "+dataarr[j].data[badd]);   //reading a fully associative cache
						System.out.println();
						break;
					}
					else//cache miss and finding apt element for replacement
					{
						if(tagarr[j][1]<min)
							{min=tagarr[j][1];
							 index=j;
							}
					}
				    }
					
					if(miss==true)//replacement and eviction
					{
						miss=false;
						tagarr[index][0]=mmadd;  
						tagarr[index][1]=1;   //chage this for lru
						dataarr[index]=mainmem[mmadd];
						System.out.println("CACHE MISS!!");
						System.out.println("BLOCK BROUGHT TO CACHE!!  "+"Block :"+mainmemstr);
						System.out.println("Data: "+dataarr[index].data[badd]);
						System.out.println();
					}
				}			
	
			
			System.out.println("Cache Status: Blocks in Cache");  //Printing Cache
			System.out.println();
			System.out.println("BLOCKS  DATA ");
			for(int j=0;j<(int)Math.pow(2,cl);j++)
			{
				System.out.print(Integer.toBinaryString(tagarr[j][0])+"->");
				for(int mm=0;mm<Math.pow(2, b);mm++)
				{System.out.print(dataarr[j].data[mm]+" ");
					
				}
				System.out.println();
			}
		}
		
		
	}
	
	
	static void sac() throws IOException
	{   
		Reader.init(System.in);
	    System.out.println("Mention the memory size in power of 2 ");
	    int m=Reader.nextInt();
	   
	    System.out.println("Enter block size in power of 2");
	    int b=Reader.nextInt();
        
	    System.out.println("Enter cache lines in power of 2");
	    int cl=Reader.nextInt();
	    
	    System.out.println("Enter number of elements you want in a set in power of 2");
	    int k=Reader.nextInt();
	    
        int totblocks=m-b;//no of blocks in power of 2
        Block[] mainmem=new Block[(int)Math.pow(2,totblocks)]; //main memory
        for(int i=0;i<(int)Math.pow(2,totblocks);i++)
        	{
        	   mainmem[i]=new Block(b);
        	}
        
        int[][] tagarr=new int[(int)Math.pow(2,cl)][2];
        Block[] dataarr=new Block[(int)Math.pow(2,cl)];
        for(int i=0;i<(int)Math.pow(2,cl);i++)                         //taking inputs in a k-set associative cache
        	dataarr[i]=new Block(b);
        System.out.println("Address of the form ");
    	System.out.println();
    	int paticular=cl-k;//in power of2;
	    System.out.println("TAG INDEX OFFSET");
	    System.out.println((totblocks-paticular)+"   "+paticular+"     "+b+" bits");
	    		
	
		System.out.println();
		
		System.out.println("Enter the no of commands you wish to enter");
		int no=Reader.nextInt();
		System.out.println();
		System.out.println("to read enter 0 address");
		System.out.println("to write enter 1 content address ");//add in binary content in decimals
		System.out.println();
		
		for(int i=0;i<no;i++)
		{   int mode=Reader.nextInt();
			if(mode==1)
			{   long info=Reader.nextLong();
			String binadd=Reader.next();
			String mainmemstr=binadd.substring(0,binadd.length()-b);
			String tags=binadd.substring(0,totblocks-paticular);
			String pati=binadd.substring(totblocks-paticular,binadd.length()-b);
			String offset=binadd.substring(totblocks,binadd.length());
				int mmadd=Integer.parseInt(mainmemstr,2);  // 
				int badd=Integer.parseInt(offset,2);  
				int cachestart=(int)Math.pow(2, k)*Integer.parseInt(pati,2);
				int tag=Integer.parseInt(tags,2);
				int steps=(int)Math.pow(2, k);
				boolean miss=true;
				mainmem[mmadd].data[badd]=info;//accessing from main memory
				Block temp=mainmem[mmadd];
				int minoccur=10000000;                            //writing in a k-set associative cache
				int indexmin=-1;
				int j=cachestart;
				while(steps!=0)  //finding among the k elements
				{	if(tagarr[j][0]==tag)//cache hit
				{   dataarr[j].data[badd]=info;
					temp=dataarr[j];
					miss=false;
					tagarr[j][1]+=1;
					System.out.println("Data Stored");
					System.out.println();
					break;}
				else//cache miss
				{if(tagarr[j][1]<minoccur)   //apt for replacement
						{minoccur=tagarr[j][1];
						 indexmin=j;
			    }}
				j++;steps--;}
				if(miss==true)//LRU replacement and eviction
				{miss=false;
					tagarr[indexmin][0]=tag;   
					tagarr[indexmin][1]=1;//chage this for lru
					dataarr[indexmin]=temp;
					System.out.println("CACHE MISS!!");
					System.out.println("BLOCK BROUGHT TO CACHE!!  "+"Block :"+mainmemstr);
					System.out.println("Data Stored");
					System.out.println();
				}
			}
			else 
			{   int index=-1;;
			    int min=1000000;
			    int steps=(int)Math.pow(2, k);
			    boolean miss=true;
			    String binadd=Reader.next();
				String mainmemstr=binadd.substring(0,binadd.length()-b);
				String tags=binadd.substring(0,totblocks-paticular);
				String pati=binadd.substring(totblocks-paticular,binadd.length()-b);
				//System.out.println(mainmemstr);
				String offset=binadd.substring(totblocks,binadd.length());
				//System.out.println(offset);
					int mmadd=Integer.parseInt(mainmemstr,2);           // k-set associative cache read
					int badd=Integer.parseInt(offset,2);  
					int cachestart=(int)Math.pow(2, k)*Integer.parseInt(pati,2);
					int tag=Integer.parseInt(tags,2);
					int j=cachestart;
					while(steps!=0)//finding within set
					{	if(tagarr[j][0]==tag)//cache hit
					{miss=false;
						tagarr[j][1]+=1;
						System.out.println("Data: "+dataarr[j].data[badd]);
						System.out.println();
						break;
					}
					else//cache miss
					{if(tagarr[j][1]<min)//finding apt for replacement
							{min=tagarr[j][1];
							 index=j;
							}
					}
					j++;
					steps--;
				    }
					if(miss==true)//replacement and eviction corresponding to LRU
					{
						miss=false;
						tagarr[index][0]=tag;  
						tagarr[index][1]=1;   //chage this for lru
						dataarr[index]=mainmem[mmadd];
						System.out.println("CACHE MISS!!");
						System.out.println("BLOCK BROUGHT TO CACHE!! "+"Block :"+mainmemstr);
						System.out.println("Data: "+dataarr[index].data[badd]);
						System.out.println();
					}
				}			
	
			
			System.out.println("Cache Status: Blocks in Cache");   //k-set associative cache print
			System.out.println();
			System.out.println("TAG INDEX  DATA");
			int set=0;
			int elements=0;
			for(int j=0;j<(int)Math.pow(2,cl);j++)
			{   
				System.out.print(Integer.toBinaryString(tagarr[j][0])+"    "+Integer.toBinaryString(set)+"->");
				for(int mm=0;mm<Math.pow(2, b);mm++)
				{System.out.print(dataarr[j].data[mm]+" ");
					
				}
				System.out.println();
				elements++;
				if(elements==(int)Math.pow(2, k))
				{
					elements=0;
					set++;
					System.out.println();
				}
			}
		}
		
		
	}

}
