package com.ub.cse574.ir.IRF18P4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class SentimentAnalysis {

	protected  JSONObject getEvaluation(List<String> tweets) throws IOException{
		if(tweets==null || tweets.isEmpty())
			return new JSONObject();
		JSONObject jo = null;
		try{
			float p=0;
			float n=0;
			float nt=0;
			int count=0;
			float positive = 0;
			float negative = 0;
			float neutral = 0;

		

			  ArrayList<String> stopwords= new ArrayList<String>();
			  
//			  FileInputStream f = new FileInputStream(new File("C:\\Users\\Soumi\\SA\\stopwords.docx"));
//			  BufferedReader stop = new BufferedReader(new InputStreamReader(f, "UTF8")); 
//			  
//			  FileReader fr = new FileReader("C:\\Users\\Soumi\\SA\\stopwords.docx","UTF-8");
//			  BufferedReader stop = new BufferedReader(fr);
			  InputStream is = getClass().getClassLoader().getResourceAsStream("stopwords.txt");
			  BufferedReader stop = new BufferedReader(new InputStreamReader(is));
			 // BufferedReader stop = new BufferedReader(new FileReader("stopwords.txt"));

			  String line = "";

			  while ((line = stop.readLine()) != null)

			  {

				  stopwords.add(line);

			  }
		

			  Map<String, String> map = new HashMap<String, String>();
			  InputStream is1 = getClass().getClassLoader().getResourceAsStream("AFINN1.txt");
			  BufferedReader in = new BufferedReader(new InputStreamReader(is1,"UTF-8"));
			 // FileInputStream f1 = new FileInputStream(new File("AFINN1.txt"));
			//  BufferedReader in = new BufferedReader(new InputStreamReader(f1, "UTF-8"));
		    //  BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Soumi\\SA\\AFINN"));
	       

		        line="";

		        while ((line = in.readLine()) != null) {

		            String parts[] = line.split("\t");
		            if(parts.length>2) {
		            	for(int i=1;i<parts.length-1;i++) {
		            		parts[0]+=parts[i];
		            	}
		            }
		           // System.out.println(parts[0]);
		            map.put(parts[0], parts[parts.length-1]);

		            count++;

		        }

		        in.close();

		     //   System.out.println(map.toString());
			
//		        FileInputStream f2 = new FileInputStream(new File("C:\\Users\\Soumi\\SA\\TestTweets.txt"));
//				  BufferedReader inputStream = new BufferedReader(new InputStreamReader(f2, "UTF8"));
		        
//			Scanner inputStream= new Scanner(new FileReader("C:\\Users\\Soumi\\SA\\TestTweets.csv"));
//				  while (inputStream.hasNext())
				 // while((line = inputStream.readLine()) != null)
		        for(String tweet:tweets)
			{

				float tweetscore=0;

			//tweet= inputStream.nextLine();
				//tweet = line;
			String[] word=tweet.split(" ");
			for(int i=0; i<word.length;i++)
			{

					if(stopwords.contains(word[i].toLowerCase()))
					{
				
					}

					else{

					if(map.get(word[i].toLowerCase())!=null && !map.get(word[i].toLowerCase()).isEmpty())
					{

					String wordscore= map.get(word[i].toLowerCase());

					tweetscore=(float) tweetscore + Integer.parseInt(wordscore);
					//System.out.println(tweetscore);
					}
							
					}
			}

				Map<String, Float> sentiment= new HashMap<String, Float>();

				sentiment.put(tweet, tweetscore);

				System.out.println(sentiment.toString()); 
				if(tweetscore >0)
				{
					p++;
					//System.out.println("Positive Sentiment\n");
				}
				else if(tweetscore<0)
				{
					n++;
					//System.out.println("Negative Sentiment\n");
				}
				else
				{
					nt++;
					//System.out.println("Neutral Sentiment\n");
				}

			}
			float a =p+n+nt;
			//int b=100;
			
			positive = (p/a)*100;
			negative = (n/a)*100;
			neutral = (nt/a)*100;
			
			
			System.out.println(a);
			System.out.println("Fraction of Positive sentiments = "+ positive);
			System.out.println("Fraction of Negative sentiments = "+ negative);
			System.out.println("Fraction of Neutral sentiments = "+ neutral);
			
			jo = new JSONObject();
			
			jo.put("postive", positive);
			jo.put("negative", negative);
			jo.put("neutral", neutral);
			
	}

		
		catch(FileNotFoundException e)

		{

			e.printStackTrace();

	}

		return jo;

	}
	public static void main(String args[]) throws IOException
	{
		
	}
	
	}