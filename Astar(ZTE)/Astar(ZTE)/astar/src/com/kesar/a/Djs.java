package com.kesar.a;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.xml.stream.events.EndDocument;

/**
 * 
 * ClassName: AStar 
 * @Description: DJS算法来进行计算地标节点到各个节点的最短路径
 * @author njust
 */
public class Djs
{
	public final static int BAR = 1; //     0是可走的，1表示障碍物不可走
	public final static int PATH = 2; //    2是路线
	public final static int max = -1; //    2是路线
	public int number=0;
	Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
	List<Node> closeList = new ArrayList<Node>();
	
	//開始算法，循环移动结点寻找路径，设定循环结束条件。Open表为空或者终于结点在Close表
	public int start(MapInfo mapInfo)
	{
		if(mapInfo==null) return -1;
		// clean
		openList.clear();
		closeList.clear();
		  // 開始搜索
		openList.add(mapInfo.start);
		moveNodes(mapInfo);
		  // 返回距离
		return dis(mapInfo.maps, mapInfo.end);
	}

	/**
	 * 移动当前结点
	 */
	private void moveNodes(MapInfo mapInfo)
	{
		while (!openList.isEmpty())
		{
			if (isCoordInClose(mapInfo.end.coord))
			{
				//System.out.println(number+"          "+mapInfo.end.coord.x+" ，"+ mapInfo.end.coord.y);
				//drawPath(mapInfo.maps, mapInfo.end);
				break;
			}
			Node current = openList.poll();
			closeList.add(current);
			number++;
			addNeighborNodeInOpen(mapInfo,current);
		}
	}
	/*计算距离
	 * 
	 *  */
	 private int dis(int[][] maps, Node end)
	{
		if(end==null||maps==null) return  -1;
		{
		//System.out.println("总代价" + end.G);
		return end.G;
		}
	}

	private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
	{
		int x = current.coord.x;
		int y = current.coord.y;
		//System.out.print(x+"   "+y+"   ");
		//System.out.print((x-1)+"   "+(y)+"   ");
		 // 左
		if(canAddNodeToOpen(mapInfo, x-1,y))   //* 推断结点是否能放入Open列表，判断数组是否越界
		   addNeighborNodeInOpen(mapInfo,current, x - 1, y,mapInfo.array[y][x-1][1] );
		
		//System.out.print((x)+"   "+(y-1)+"   ");
		   // 上
		if(canAddNodeToOpen(mapInfo, x, y - 1))
			addNeighborNodeInOpen(mapInfo,current, x, y - 1, mapInfo.array[y-1][x][0]);
		 
		//System.out.print((x+1)+"   "+(y)+"   ");
		// 右
		if(canAddNodeToOpen(mapInfo, x + 1, y))
		{
			//System.out.println(canAddNodeToOpen(mapInfo, x + 1, y));
			addNeighborNodeInOpen(mapInfo,current, x + 1, y, mapInfo.array[y][x + 1][3]);
		}
		 // 下
		//System.out.println((x)+"   "+(y+1)+"   ");
		if(canAddNodeToOpen(mapInfo,x, y + 1))
		addNeighborNodeInOpen(mapInfo,current, x, y + 1, mapInfo.array[ y + 1][x][2]);
		
	}

	  /**
    * 增加一个邻结点到open表，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，改动距离在这里改
     */
	private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
	{
		if (canAddNodeToOpen(mapInfo,x, y))  //* 推断结点是否能放入Open列表
		{
			Node end=mapInfo.end;
			Coord coord = new Coord(x, y);
			int G = current.G + value; // 计算邻结点的G值
			Node child = findNodeInOpen(coord);   //从Open列表中查找指定结点，如果没有返回null
			
	  //如果节点在Open表中则重新计算G值并更新，如果不在就计算H值
			if (child == null)
			{
				int H=calcH(end.coord,coord); // 计算H值
				if(isEndNode(end.coord,coord))  //推断结点是否是终于结点
				{
					child=end;
					child.parent=current;
					child.G=G;
					child.H=H;
				}
				else
				{
					child = new Node(coord, current, G, H);
				}
				openList.add(child);
			}
			else if (child.G > G)
			{
				child.G = G;
				child.parent = current;
				openList.add(child);
			}
		}
	}

	/**
	从Open列表中查找结点
	 */
	private Node findNodeInOpen(Coord coord)
	{
		if (coord == null || openList.isEmpty()) return null;
		for (Node node : openList)
		{
			if (node.coord.equals(coord))
			{
				return node;
			}
		}
		return null;
	}


	/**
	计算H值，“曼哈顿” 法。坐标分别取差值相加，。。。。。。。。。。。。。。。。。。。。。。。。。。。。H值在这里改
	 */
	private int calcH(Coord end,Coord coord)
	{
		return 0;
	}
	
	 /**
     * 推断结点是否是终于结点
     */
	private boolean isEndNode(Coord end,Coord coord)
	{
		return coord != null && end.equals(coord);
	}

	 /**
     * 推断结点是否能放入Open列表
     */
	private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
	{
		 // 是否在地图中
		if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
		// 推断是否是不可通过的结点
		if (mapInfo.maps[y][x] == BAR) return false;
	    // 推断结点是否存在close表
		if (isCoordInClose(x, y)) return false;

		return true;
	}

	 /**
     * 推断坐标是否在close表中
     */
	private boolean isCoordInClose(Coord coord)
	{
		return coord!=null&&isCoordInClose(coord.x, coord.y);
	}
	  /**
     * 推断坐标是否在close表中
     */
	private boolean isCoordInClose(int x, int y)
	{
		if (closeList.isEmpty()) return false;
		for (Node node : closeList)
		{
			if (node.coord.x == x && node.coord.y == y)
			{
				return true;
			}
		}
		return false;
	}
}
