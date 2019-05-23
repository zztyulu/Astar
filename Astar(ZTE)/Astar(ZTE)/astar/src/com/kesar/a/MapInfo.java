package com.kesar.a;

/**
 * 
 * ClassName: MapInfo 
 * @Description: 包含地图所需的所有输入数据
 * @author njust
 */
public class MapInfo
{
	public int[][] maps; // 二维数组的地图     坐标原点在左上角。所以y是高，x是宽，y向下递增，x向右递增
	public int width; // 地图的宽
	public int hight; // 地图的高
	public Node start; // 起始结点
	public Node end; // 最终结点
	public int[][][] array; // 三维数组，存储每个节点到上下左右四个节点的代价值（代价值随机获取）
	public int[][][] dis; // 三维数组，存储不同的地标节点到各个节点的djs算法的最短路径值
	
	public MapInfo(int[][] maps, int width, int hight, Node start, Node end,int[][][] array)
	{
		this.maps = maps;
		this.width = width;
		this.hight = hight;
		this.start = start;
		this.end = end;
		this.array=array;
		
	}
	
	public MapInfo(int[][] maps, int width, int hight, Node start, Node end,int[][][] array,int[][][] dis)
	{
		this.maps = maps;
		this.width = width;
		this.hight = hight;
		this.start = start;
		this.end = end;
		this.array=array;
		this.dis=dis;
		
	}
}
