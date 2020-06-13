/*
 * Author github: https://github.com/zs-neo
 * Author Email: 2931622851@qq.com
 */
package com.zs.gateway;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhousheng
 * @version 1.0
 * @since 2020/6/8 22:09
 */
public class Solution {
	
	public boolean canMeasureWater(int x, int y, int z) {
		Pair<Integer, Integer> pair = new Pair<>(x, y);
		LinkedList<Pair> queue = new LinkedList<Pair>();
		queue.push(pair);
		Set<Pair> pairs = new HashSet<>();
		while (queue.size() > 0) {
			Pair<Integer, Integer> top = queue.poll();
			System.out.println(top.getKey() + " " + top.getValue());
			if (top.getKey().equals(z) || top.getValue().equals(z) || (top.getValue() + top.getKey()) == z) {
				return true;
			} else {
				// full
				Pair<Integer, Integer> fullx = new Pair<>(x, top.getValue());
				if (top.getKey() != x && !pairs.contains(fullx)) {
					queue.push(fullx);
					pairs.add(fullx);
				}
				Pair<Integer, Integer> fully = new Pair<>(top.getKey(), y);
				if (top.getValue() != y && !pairs.contains(fully)) {
					queue.push(fully);
					pairs.add(fully);
				}
				// clear
				Pair<Integer, Integer> clearx = new Pair<>(0, top.getValue());
				if (top.getKey() != 0 && !pairs.contains(clearx)) {
					queue.push(clearx);
					pairs.add(clearx);
				}
				Pair<Integer, Integer> cleary = new Pair<>(top.getKey(), 0);
				if (top.getValue() != 0 && !pairs.contains(cleary)) {
					pairs.add(cleary);
					queue.push(cleary);
				}
				// transfer
				int sum = top.getKey() + top.getValue();
				Pair<Integer, Integer> x2y = new Pair<>(sum - y > 0 ? sum - y : 0, sum > y ? y : sum);
				if (!pairs.contains(x2y)) {
					pairs.add(x2y);
					queue.push(x2y);
				}
				Pair<Integer, Integer> y2x = new Pair<>(sum > x ? x : sum, sum - x > 0 ? sum - x : 0);
				if (!pairs.contains(y2x)) {
					pairs.add(y2x);
					queue.push(y2x);
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.canMeasureWater(3, 5, 4));
		System.out.println(solution.canMeasureWater(2, 6, 5));
	}
	
}