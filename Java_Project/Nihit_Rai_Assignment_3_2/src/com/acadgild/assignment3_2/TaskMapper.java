package com.acadgild.assignment3_2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*; 

public class TaskMapper extends Mapper<LongWritable, Text, Text, Text>{

	Text key;
	Text value;
	
	@Override
	public void setup(Context context) {
		key = new Text();
		value = new Text();
	}
	
	@Override
	public void map(LongWritable mkey, Text mvalue, Context context) 
			throws IOException, InterruptedException {
		String[] lineArray = mvalue.toString().split("|");
		StringTokenizer st = new StringTokenizer(mvalue.toString(),"|");
		String compName = st.nextToken();
		String prodCd = st.nextToken();
				
		if(!"NA".equals(compName))
		{
			if(!"NA".equals(prodCd))
			{
			key.set(mkey.toString());
			value.set(mvalue);
			
			context.write(key, value);
			
			}
		}
		else
		{
			System.out.println("contains NA -"+mvalue);
		}
	}
}
