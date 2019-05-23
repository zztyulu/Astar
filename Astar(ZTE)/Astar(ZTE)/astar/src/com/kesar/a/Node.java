package com.kesar.a;
/**
 * 
 * ClassName: Node 
 * 封装路径结点类，字段包含：坐标、G值、F值、父结点。实现Comparable接口，方便优先队列排序。
 * @Description: 路径结点
 * @author njust
 */
public class Node implements Comparable<Node>
{

	public Coord coord; // 坐标
	public Node parent; // 父结点
	public int G; // G：是个准确的值，是起点到当前结点的代价
	public int H; // H：是个估值，当前结点到目的结点的估计代价

	public Node(int x, int y)
	{
		this.coord = new Coord(x, y);
	}

	public Node(Coord coord, Node parent, int g, int h)
	{
		this.coord = coord;
		this.parent = parent;
		G = g;
		H = h;
	}

	@Override
	public int compareTo(Node o)
	{
		if (o == null) return -1;
		if (G + H > o.G + o.H)
			return 1;
		else if (G + H < o.G + o.H) return -1;
		return 0;
	}
}
