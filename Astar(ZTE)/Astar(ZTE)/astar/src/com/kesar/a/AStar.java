package com.kesar.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * ClassName: AStar 
 * @Description: Astar算法
 * @author njust
 */
public class AStar
{
	public final static int BAR = 1; //     0是可走的，1表示障碍物不可走
	public final static int PATH = 2; //    2是路线
	public final static int max = -1; //    2是路线
	public int number=0;
	Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
	List<Node> closeList = new ArrayList<Node>();
	
	//開始算法，循环移动结点寻找路径，设定循环结束条件。Open表为空或者终于结点在Close表
	public int[] start(MapInfo mapInfo)
	{int[] b={0,0};
		if(mapInfo==null) return b;
		// clean
		openList.clear();
		closeList.clear();
		  // 開始搜索
		openList.add(mapInfo.start);
        b[0]=moveNodes(mapInfo);
        b[1]=Adis(mapInfo.maps, mapInfo.end);
        return b;
	}

	/**
	 * 移动当前结点
	 */
	private int moveNodes(MapInfo mapInfo)
	{
		while (!openList.isEmpty())
		{
			if (isCoordInClose(mapInfo.end.coord))
			{
                //System.out.println("A*遍历节点个数为  "+number);
				drawPath(mapInfo.maps, mapInfo.end);
				break;
			}
			Node current = openList.poll();
			closeList.add(current);
			 number++;
			// System.out.println(number);
			addNeighborNodeInOpen(mapInfo,current);
		}
		return number;
	}
	
	/**
	 回溯法绘制路径
	 */
	private void drawPath(int[][] maps, Node end)
	{
		if(end==null||maps==null) return ;
		//System.out.println("A*算法的最优路径总代价为" + end.G);
   
   while (end != null)
		{
			Coord c = end.coord;
			maps[c.y][c.x] = PATH;                                    //绘制路径
			end = end.parent;
		}
	}

    /**
     *计算A*算法计算得到的最优代价值，并返回该值
     */
    private int Adis(int[][] maps, Node end)
    {
        if(end==null||maps==null) return  0;
        return end.G;
    }
	
	/**
	 * 增加全部邻结点到open表，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，改动距离在这里改
	 */
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
				int H=calcH(end.coord,coord,mapInfo); // 计算H值
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
	计算H值，利用从该节点到地标节点以及地标节点到终点的差值的最大值作为评估函数值
	 */
	private int calcH(Coord end,Coord coord,MapInfo mapInfo)
	{     int number=mapInfo.dis[0][0].length;
	      int a[]=new int[number];
	      //System.out.println("搜索的当前节点为 "+coord.x+","+coord.y);
          for(int i=0;i<number;i++){
	            a[i]=Math.abs(mapInfo.dis[coord.y][coord.x][i]-mapInfo.dis[end.y][end.x][i]);
			  //System.out.println("根据第"+i+"个骨干节点计算得到的评估函数预选值为： "+a[i]);
			  // System.out.println(a[i]);
	        }
          Arrays.sort(a);
          int cost=a[number-1];
		 // System.out.println("最终的评估函数值为： "+cost);
          return cost;
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
