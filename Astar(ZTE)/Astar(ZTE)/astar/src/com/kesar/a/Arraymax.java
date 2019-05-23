package com.kesar.a;

public class Arraymax
{
	  public  Coord locateLargest(int[][] a)  //返回二维数组最大值时候的x和y坐标
	  {
	        Coord coord = new Coord();
	        int row = 0;
	        int column = 0;
	        int maxValue = 0;
	        for (int i = 0; i < a.length; i++)
	        {
	            for (int j = 0; j < a[i].length; j++)
	            {
	                if(a[i][j]>maxValue)
	                {
	                    maxValue=a[i][j];
	                    row =i;
	                    column=j;
	                }

	            }

	        }
	        coord.x = row;    //行
	        coord.y = column; //列
	        coord.max =maxValue;
	        return coord;//实例
	}
	  public  int[][] arrayadd(int[][] list1 ,int[][] list2)   //二维数组相加
	  {
		  int result[][]=new int[25][20];
		  for(int i=0; i<result.length; i++) 
		  {
		    for(int j=0; j<result[i].length; j++)
		    {
		        result[i][j] = list1[i][j] + list2[i][j];
		    }
		  } 
		  return result;
	  }
	  public  int[][][] weidu(int[][] list1 ,int[][] list2,int[][] list3 ,int[][] list4)//三个二维数组转化为三维数组
	  
	  {
		  int result[][][]=new int[25][20][4];
		  
		  for(int i=0; i<result.length; i++) 
		  {
		    for(int j=0; j<result[i].length; j++)
		    {
		        result[i][j][0] = list1[i][j];
		    }
		  } 
		  for(int i=0; i<result.length; i++) 
		  {
		    for(int j=0; j<result[i].length; j++)
		    {
		        result[i][j][1] = list2[i][j];
		    }
		  } 
		  for(int i=0; i<result.length; i++) 
		  {
		    for(int j=0; j<result[i].length; j++)
		    {
		        result[i][j][2] = list3[i][j];
		    }
		  } 
		  for(int i=0; i<result.length; i++) 
		  {
		    for(int j=0; j<result[i].length; j++)
		    {
		        result[i][j][3] = list4[i][j];
		    }
		  } 
		  return result;
	  }
	 
	
}
