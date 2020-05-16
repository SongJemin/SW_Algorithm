package exam2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class kakao_5_길찾기게임 {
	
	static int count = 0;
	
	public static void main(String[] args) {
		int[][] input = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		int[][] answer = solution(input);
		
		for (int i = 0; i < answer.length; i++) {
			System.out.println(Arrays.toString(answer[i]));
		}
	}

	public static int[][] solution(int[][] nodeinfo) {

		ArrayList<TreeNode> treeNodes = new ArrayList<>();

		for (int i = 0; i < nodeinfo.length; i++) {
			treeNodes.add(new TreeNode(nodeinfo[i][0], nodeinfo[i][1], (i+1)));
		}

		Collections.sort(treeNodes);

		// 루트 노드 설정
		TreeNode root = treeNodes.get(0);

		// 노드 연결
		for (int i = 1; i < treeNodes.size(); i++) {
			add(root, treeNodes.get(i));
		}
		int[][] answer = new int[2][treeNodes.size()];

		// 전위 순회
		preOrder(root, answer);
		count = 0;
		// 후위 순회
		postOrder(root, answer);
		
		return answer;
	}

	// 전위 순회(중 -> 왼 -> 오)
	public static void preOrder(TreeNode node, int[][] answer) {
		answer[0][count++] = node.index;

		// 왼쪽 노드 있을 경우
		if(node.left != null) {
			preOrder(node.left, answer);
		}
		// 다 돌면 오른쪽으로
		if(node.right != null) {
			preOrder(node.right, answer);
		}
	}
	
	// 후위 순회(왼 -> 오 -> 중)
	public static void postOrder(TreeNode node, int[][] answer) {
		
		// 왼쪽 노드 있을 경우
		if(node.left != null) {
			postOrder(node.left, answer);
		}
		// 다음 오른쪽
		if(node.right != null) {
			postOrder(node.right, answer);
		}
		answer[1][count++] = node.index;
	}

	public static void add(TreeNode root, TreeNode inputNode) {
		// 왼쪽
		if(inputNode.x < root.x) {
			// 이미 노드가 있을 경우
			if(root.left != null) {
				// 재귀
				add(root.left, inputNode);
			}
			// 없을 경우 노드 연결
			else {
				root.left = inputNode;
			}
		}
		// 오른쪽
		else {
			if(root.right != null) {
				// 재귀
				add(root.right, inputNode);
			}
			else {
				root.right = inputNode;
			}
		}
	}

	public static class TreeNode implements Comparable<TreeNode> {
		int x;
		int y;
		int index;

		TreeNode left;
		TreeNode right;

		public TreeNode(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}

		@Override
		public int compareTo(TreeNode o) {
			if(this.y > o.y) return -1;
			else if(this.y < o.y) return 1;
			else {
				if(this.x < o.x) return -1;
				else return 1;
			}
		}
	}
}
