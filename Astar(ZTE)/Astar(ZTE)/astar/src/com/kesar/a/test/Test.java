package com.kesar.a.test;

import java.io.IOException;
import com.kesar.a.*;

public class Test
{

	public static void main(String[] args) throws IOException 
	{
	    /*
		   定义一个通路矩阵，0代表通路，1代表阻碍  从map.txt里面读取网格地图，从array.txt读取预先设置好的随机权重（在INPUT.java中设置）
		 */
		String file0="\\maps.txt";
		int maps[][] = new readarrayfromfile().Twodimensionalarray(file0);//声明
		String file="\\array.txt";//    需重新配置下路径，文件见test文件夹下
		int array[][][] = new readarrayfromfile().Threedimensionalarray(file);//声明
        System.out.println("----利用A*算法进行寻路（目标函数ALT）与DJS算法比较-----NJUST");
        System.out.println("");

        /*
		   定义一个距离矩阵array通过DJS求节点之间的最短距离，定义一个三维数组，用来存储一个节点到其他节点的最短距离
		 */
		long startTime0 = System.currentTimeMillis();
		int x=0;
		int y=0;
		int [][][]dis=new int[25][20][5];           //   最终距离数组矩阵
        int number=dis[0][0].length;  //三维的长度
		for(int k=0;k<number;k++)	{               //计算节点到其他节点的最短距离
			Coord node=new Coord(x,y);
			int num=k+1;
			System.out.println("选取的第"+num+"landmark节点为"+"("+x+","+y+")：");
            //System.out.println("搜索节点数"+"代价      "+"目标节点");
			for(int i=0;i<25;i++){
				for(int j=0;j<20;j++) {
					MapInfo info=new MapInfo(maps,maps[0].length, maps.length,new Node(node.x, node.y), new Node(j, i),array);   //j是行，i是列
					//	System.out.println(i+"   "+j);
					//int a[]=;
					dis[i][j][k]=new Djs().start(info);
				}
			}
			x=x+4;
			y=y+5;
		}
		long endTime0 = System.currentTimeMillis();
		System.out.println("预计算程序运行时间：" + (endTime0 - startTime0) + "ms");
		System.out.println("");

		/*
		初始化
		 */
	    MapInfo info=new MapInfo(maps,maps[0].length, maps.length,new Node(4,4), new Node(18, 20),array,dis);
	    System.out.print("需搜索路径的起始节点坐标为 "+info.start.coord.x+","+ info.start.coord.y);
	    System.out.println(" ，目标节点坐标为 "+info.end.coord.x+","+ info.end.coord.y);
	    System.out.println("");

	    int num=1;              //   规定业务数量

        /*
		算法计算最短路径
		 */
       long startTime = System.currentTimeMillis();
       for(int i=0;i<num;i++){
		   int djs[]=new DJS2().start(info);
	   }
		int djs[]=new DJS2().start(info);
       long endTime = System.currentTimeMillis(); //获取结束时间
       System.out.println("----DJS算法的搜索路径（用9表示）----");
	   printMap(maps);
	   System.out.println("DJS算法程序运行时间：" + (endTime - startTime) + "ms");
	   System.out.println("");

       long startTime1 = System.currentTimeMillis();
		for(int i=0;i<num;i++){
			int acost[]=new AStar().start(info);
		}
  	   int acost[]=new AStar().start(info);
		//int bcost[]=new AStar().start(info);
		//int ccost[]=new AStar().start(info);
		//int dcost[]=new AStar().start(info);
		//int ecost[]=new AStar().start(info);
		//int fcost[]=new AStar().start(info);
       long endTime1 = System.currentTimeMillis(); //获取结束时间
	   System.out.println("----Astar算法的搜索路径（用2表示）----");
	   printMap(maps);
		//System.out.println("当该网络需要处理的总业务数为："+num+"时，各算法所需时间");
		System.out.println("DJS算法程序运行时间：" + (endTime - startTime) + "ms");
	   //System.out.println("Astar程序总运行时间（包括预计算）：" + ((endTime1 - startTime1)+(endTime0 - startTime0)) + "ms");
	   System.out.println("Astar程序运行时间：" + (endTime1 - startTime1) + "ms");
	   System.out.println("");


	   System.out.println("DJS算法搜索节点数为"+djs[0]);
       System.out.println("Astar算法搜索节点数为"+acost[0]);
       System.out.println("");
       System.out.println("DJS算法搜索代价为"+djs[1]);
	   System.out.println("Astar算法搜索代价为"+acost[1]);
	//System.out.println("...."+acost[1]);
	//printMap(maps);
	}

	/**
	 * 打印地图
	 */
	public static void printMap(int[][] maps)
	{
		for (int i = 0; i < maps.length; i++)
		{
			for (int j = 0; j < maps[i].length; j++)
			{
				System.out.print(maps[i][j] + " ");
			}
			System.out.println();
		}
	}
	/**
	 * 更新地图
	 */
	public static void updateMap(int[][] maps)
	{   int path=2;
		for (int i = 0; i < maps.length; i++)
		{
			for (int j = 0; j < maps[i].length; j++)
			{
				if(maps[i][j] == path){
					maps[i][j]=1;
				}
			}
		}
		maps[4][12]=0;     //与初始化的坐标x，y相反
		maps[4][19]=0;    //将起始节点还原为可通过
	}

}
