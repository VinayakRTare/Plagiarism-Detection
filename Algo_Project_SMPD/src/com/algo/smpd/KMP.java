package com.algo.smpd;

import java.io.*;
import java.util.*;

public class KMP {
	
	public double startTime = 0, endTime = 0;
	public int timeCounter = 0;
	
	public double timeTaken(){
    	return endTime - startTime;
    }
    
    public int getCounterforTime(){
 	   return timeCounter;
    }
	
	public static void main(String[] args) throws IOException {
		/*
		 * File ed = new File(args[0]); File pl = new File(args[1]); Scanner sc
		 * = new Scanner(ed); Scanner sc1 = new Scanner(pl); File outputFile =
		 * new File(args[2]);
		 */
		String text = "abcxabcdabxabcdabcdabcy";
		String pattern = "abcdabcy";
		
		KMP km = new KMP();
		
		double similarityRatio = km.KMPMatchingSentences(text,pattern);
		//int prefix[] = computePrefixarray(pattern);
		//int index = substringSearch(text, pattern, prefix);
		//if (index != 0) {
	//		System.out.println("Pattern found at the position " + index + " in the given string.");
		//} else
		//	System.out.println("Pattern is not a substring of the given text.");
		/*
		 * for (int i=0;i<prefix.length;i++) { System.out.println(prefix[i]); }
		 */
		// BufferedWriter output = new BufferedWriter(new
		// FileWriter(outputFile));
	}

	public double KMPMatchingSentences(String text, String pattern) {
		// TODO Auto-generated method stub
		int matchCount=0;
		String srcWords[] = text.split("\\s+");
		String patWords[] = pattern.split("\\s+");

		int patLength = patWords.length;

		for(int i=0;i<srcWords.length;i++){

			for(int j=0;j<patWords.length;j++){
				matchCount += KMPMatchWord(srcWords[i],patWords[j]);
			}

		}

		//System.out.println("match Count"+matchCount);
		double matchC = (double)matchCount;
		double denom = patLength*srcWords.length;
		//System.out.println("denom : "+denom);
		double ratio = matchC/denom;

		return ratio;

		
		
	}

	public int KMPMatchWord(String src, String pat) {
		// TODO Auto-generated method stub
		int prefix[] = computePrefixarray(pat);
		substringSearch(src, pat, prefix);
		return substringSearch(src, pat, prefix);
	}

	public int[] computePrefixarray(String p) {
		char pat[] = p.toCharArray();
		int pre[] = new int[pat.length];
		int j = 0;
		for (int i = 1; i < pre.length;) {
			if (pat[i] != pat[j]) {
				if (j != 0)
					j = pre[j - 1];
				else
					i++;
			} else {
				pre[i] = j + 1;
				i++;
				j++;
			}
		}
		return pre;
	}

	public int substringSearch(String txt, String pat, int pref[]) {
		int pat_pos = 0;
		char t[] = txt.toCharArray();
		char p[] = pat.toCharArray();
		startTime = System.nanoTime();
		for (int txt_pos = 0; txt_pos < txt.length();) {
			if(txt_pos>=txt.length() || pat_pos>=pat.length()){
				break;
			}
			if (t[txt_pos] == p[pat_pos]) {
				txt_pos++;
				pat_pos++;
				if(txt_pos>=txt.length() || pat_pos>=pat.length()){
					break;
				}

			}
			else{
				if (pat_pos != 0) {
					pat_pos = pref[pat_pos - 1];
				} else
					txt_pos++;
				if(txt_pos>=txt.length() || pat_pos>=pat.length()){
					break;
				}
				}
			
			if(txt_pos>=txt.length() || pat_pos>=pat.length()){
			break;
			}

			
			if (pat_pos == pat.length() && txt_pos == pat.length()) {
				//return txt_pos - pat_pos + 1;
				return 1;
			}
			++timeCounter;
		}
		endTime = System.nanoTime();
		return 0;
	}
}