package com.kesar.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.*;

     /*
        用来给地图节点随机的赋予权重
     */
public class Input {
    public static void main(String[] args) throws IOException {
        int n = 25;  //N*N数组
        int m=20;
        int wei=4;
        int[][][] arr2 = new int[n][m][wei];;  //读取出的数组

        int array[][][] = new int[n][m][wei];//插入的数组
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<m;j++)
            {
                if(j<19&&i<24)
                {
                    array[i][j][3]=array[i][j+1][1]	=(int)(Math.random()*60);//左右
                    array[i][j][2]=array[i+1][j][0]=(int)(Math.random()*60);//上下
                }
                else if(j==20&&i<24)
                {
                    array[i][j][2]=array[i+1][j][0]=(int)(Math.random()*10);
                }
                else if(j<19)
                {
                    array[i][j][3]=array[i][j+1][1]	=(int)(Math.random()*10);//左右
                }
            }

        }
        File file = new File("d:\\array.txt");  //存放数组数据的文件

        FileWriter out = new FileWriter(file);  //文件写入流

        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
        for (int k=0; k<wei; k++ )
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    out.write(array[i][j][k]+"\t");
                }
                out.write("\r\n");
            }
            // out.write("\n");
        }
        out.close();

        BufferedReader in = new BufferedReader(new FileReader(file));  //
        String line;  //一行数据
        int row=0;
        //逐行读取，并将每个数组放入到数组中
        int count=0;
        while((line = in.readLine()) !=null )
        {
            int a=count/500;
            if(a!=4)
            {
                String[] temp = line.split("\t");
                for(int j=0;j<temp.length;j++)
                {
                    System.out.println(row+"  "+j+"  "+a+"  "+count);
                    arr2[row][j][a] =  Integer.parseInt(temp[j]);
                    count++;
                }
                row++;
                if(row==25)
                    row=0;
            }

        }
        in.close();

        //显示读取出的数组
        for (int k=0; k<wei; k++ )
        {
            System.out.println(k);
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<m; j++)
                    System.out.print(arr2[i][j][k]+" ");//输出对应的值
                System.out.println();//输出换行
            }
        }
    }
}