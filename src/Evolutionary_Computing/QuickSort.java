package Evolutionary_Computing;

public class QuickSort {

	private double array[];
	private BinaryTree array2[];
	private int length;

	public void sort(double[] inputArr) {
		
		if (inputArr == null || inputArr.length == 0) {
			return;
		}
		this.array = inputArr;
		quickSort(0, this.array.length - 1);
	}
	
	public void sort(double[] inputArr, BinaryTree[] treeArr) {
		
		if (inputArr == null || inputArr.length == 0 || treeArr == null || treeArr.length == 0) {
			return;
		}
		
		this.array = inputArr;
		this.array2 = treeArr;
		
		quickSortTree(0, this.array.length - 1);
	}

	private void quickSort(int lowerIndex, int higherIndex) {
		
		int i = lowerIndex;
		int j = higherIndex;
		double pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(i, j);
				i++;
				j--;
			}
		}
		if (lowerIndex < j)
			quickSort(lowerIndex, j);
		if (i < higherIndex)
			quickSort(i, higherIndex);
	}
	
	private void quickSortTree(int lowerIndex, int higherIndex) {
		
		int i = lowerIndex;
		int j = higherIndex;
		double pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				exchangeData(i, j);
				i++;
				j--;
			}
		}
		if (lowerIndex < j)
			quickSortTree(lowerIndex, j);
		if (i < higherIndex)
			quickSortTree(i, higherIndex);
	}

	private void exchangeNumbers(int i, int j) {
		double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	private void exchangeData(int i, int j) {
		double temp = array[i];
		BinaryTree temptree = array2[i].copyTree();
		array[i] = array[j];
		//array2[i] = null;
		array2[i] = array2[j].copyTree();
		array[j] = temp;
		//array2[j] = null;
		array2[j] = temptree.copyTree();
	}
	
}
