package exam2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class kakao_5_��ã����� {
	
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

		// ��Ʈ ��� ����
		TreeNode root = treeNodes.get(0);

		// ��� ����
		for (int i = 1; i < treeNodes.size(); i++) {
			add(root, treeNodes.get(i));
		}
		int[][] answer = new int[2][treeNodes.size()];

		// ���� ��ȸ
		preOrder(root, answer);
		count = 0;
		// ���� ��ȸ
		postOrder(root, answer);
		
		return answer;
	}

	// ���� ��ȸ(�� -> �� -> ��)
	public static void preOrder(TreeNode node, int[][] answer) {
		answer[0][count++] = node.index;

		// ���� ��� ���� ���
		if(node.left != null) {
			preOrder(node.left, answer);
		}
		// �� ���� ����������
		if(node.right != null) {
			preOrder(node.right, answer);
		}
	}
	
	// ���� ��ȸ(�� -> �� -> ��)
	public static void postOrder(TreeNode node, int[][] answer) {
		
		// ���� ��� ���� ���
		if(node.left != null) {
			postOrder(node.left, answer);
		}
		// ���� ������
		if(node.right != null) {
			postOrder(node.right, answer);
		}
		answer[1][count++] = node.index;
	}

	public static void add(TreeNode root, TreeNode inputNode) {
		// ����
		if(inputNode.x < root.x) {
			// �̹� ��尡 ���� ���
			if(root.left != null) {
				// ���
				add(root.left, inputNode);
			}
			// ���� ��� ��� ����
			else {
				root.left = inputNode;
			}
		}
		// ������
		else {
			if(root.right != null) {
				// ���
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
