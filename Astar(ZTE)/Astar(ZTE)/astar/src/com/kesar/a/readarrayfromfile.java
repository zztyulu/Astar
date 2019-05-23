package com.kesar.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
   读取地图文件
 */
public class readarrayfromfile {

	public int[][][] Threedimensionalarray(String file0) throws IOException   //从文件中读取一个三维数组，必须是25*20*4  目前是距离矩阵
	{
		int array[][][] = new int[25][20][4];//声明
		File file = new File(file0);   //存放数组数据的文件
		BufferedReader in = new BufferedReader(new FileReader(file));  //  读取文件
		String line;  //一行数据
		int row=0;
		int count=0; 
			while((line = in.readLine()) !=null) //逐行读取，并将每个数组放入到数组中
			{  
				int a=count/500;   //25*20 分第三维
				if(a!=4)
				{
					String[] temp = line.split("\t");   
					for(int j=0;j<temp.length;j++)
					{  
						// System.out.println(row+"  "+j+"  "+a+"  "+count);
						array[row][j][a] =  Integer.parseInt(temp[j]);  
						count++;
					}  
					row++;
					if(row==25)
						row=0;
				}
			}
		in.close();
		return array;
		}
	public int[][] Twodimensionalarray(String file0) throws IOException   //从文件中读取一个二维数组，必须是25*20 目前是地图矩阵
	{
		int array[][] = new int[25][20];//声明
		File file = new File(file0);   //存放数组数据的文件
		BufferedReader in = new BufferedReader(new FileReader(file));  //  读取文件
		String line;  //一行数据
		int row=0;
		int count=0; 
			while((line = in.readLine()) !=null) //逐行读取，并将每个数组放入到数组中
			{  
				int a=count/500;
				if(a!=4)
				{
					String[] temp = line.split("\t");   
					for(int j=0;j<temp.length;j++)
					{  
						// System.out.println(row+"  "+j+"  "+a+"  "+count);
						array[row][j] =  Integer.parseInt(temp[j]);  
						count++;
					}  
					row++;
				}
			}
		in.close();
		return array;
	
	}
}
