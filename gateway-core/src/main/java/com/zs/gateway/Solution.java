/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/8 22:09
 */
public class Solution {
	
	private static int res = 0;
	
	public int get(int a) {
//		System.out.println(a);
		int sum = 0;
		while (a > 0) {
			sum += a % 10;
			a /= 10;
		}
//		System.out.println(sum);
		return sum;
	}
	
	public void find(int[][] visit, int i, int j, int max, int rows, int cols) {
		if (get(i) + get(j) > max) {
			return;
		}
		res++;
		visit[i][j] = 1;
		if (i - 1 >= 0 && visit[i - 1][j] == 0) {
			find(visit, i - 1, j, max, rows, cols);
		}
		if (j - 1 >= 0 && visit[i][j - 1] == 0) {
			find(visit, i, j - 1, max, rows, cols);
		}
		if (j + 1 < cols && visit[i][j + 1] == 0) {
			find(visit, i, j + 1, max, rows, cols);
		}
		if (i + 1 < rows && visit[i + 1][j] == 0) {
			find(visit, i + 1, j, max, rows, cols);
		}
	}
	
	public int movingCount(int threshold, int rows, int cols) {
		res = 0;
		int[][] visit = new int[rows][cols];
		find(visit, 0, 0, threshold, rows, cols);
		return res;
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.movingCount(38, 10, 20));
	}
	
}