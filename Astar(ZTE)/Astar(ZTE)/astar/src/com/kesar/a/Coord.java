package com.kesar.a;
/**
 * 
 * ClassName: Coord
 * 依照二维数组的特点，坐标原点在左上角。所以y是高，x是宽，y向下递增，x向右递增，我们将x和y封装成一个类，好传參，重写equals方法比較坐标(x,y)是不是同一个。
 * @Description: 坐标
 * @author njust
 */
public class Coord
{

	public int x;
	public int y;
	public int max;
	public Coord()
	{

	}
	public Coord(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (obj instanceof Coord)
		{
			Coord c = (Coord) obj;
			return x == c.x && y == c.y;
		}
		return false;
	}
}
