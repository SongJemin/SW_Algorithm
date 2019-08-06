package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static StringBuilder sb;
	public static Node head;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputLength;
		int inputOperation;
		int selectedIndex, inputNums;
		StringTokenizer st;

		Solution sol;
		
		for(int a = 1; a <= 10; a++) {
			sol = new Solution();
			sb = new StringBuilder();
			head = null;
			sb.append("#" + a);
			
			inputLength = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < inputLength; i++) {
				sol.add(Integer.parseInt(st.nextToken()));
			}
			inputOperation = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());

			// 연산 횟수만큼 for문 반복
			for (int i = 0; i < inputOperation; i++) {
				st.nextToken();
				selectedIndex = Integer.parseInt(st.nextToken());
				inputNums = Integer.parseInt(st.nextToken());
				// 추가되는 숫자만큼 for문 반복
				for(int j = 0; j < inputNums; j++) {
					sol.addToIndex(selectedIndex + j, Integer.parseInt(st.nextToken()));
				}
			}
			sol.printNode();
			System.out.println(sb);
		}	
	}
	
	/** 연결리스트에 값 추가하는 메서드 */
	private void add(int data) {
		if(head == null) {
			firstAdd(data);
		} else {
			Node n = head;
			while(n.link != null) {
				n = n.link;
			}
			Node newNode = new Node(data);
			n.link = newNode;
		}
		
	}
	
	/** 연결리스트 특정 index에 값을 저장하는 메서드 */
	private void addToIndex(int index, int data) {
		if(index == 0) {
			firstAdd(data);
			return;
		}
		Node newNode = new Node(data);
		Node pre = head;
		for (int i = 0; i < index - 1; i++) {
			pre = pre.link;
		}
		Node next = pre.link;
		pre.link = newNode;
		newNode.link = next;
	}
	
	/** 연결리스트 첫 index에 값을 저장하는 메서드 */
	private void firstAdd(int data) {
		Node newNode = new Node(data);
		newNode.link = head;
		head = newNode;
	}

	/** 리스트에 저장된 앞의 10개의 데이터 출력하는 메서드 */
	private void printNode() {
		int count = 0;
		Node n = head;
		while(count < 10) {
			sb.append(" " + n.data);
			n = n.link;
			count++;
		}
	}

	/** 노드 데이터를 저장하는 클래스 */
	static class Node{
		int data;
		Node link;
		
		Node(int data){
			this.data = data;
		}
	}
}
