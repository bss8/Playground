package algorithms;

public class DivideAndConquer implements DivideAndConquerAlgorithms {
    private int crossLeft, crossRight;

    @Override
    public int findMaxCrossingSubarray(int[] array, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int maxLeft = 0, maxRight = 0;
        int sum = 0;

        for (int i = mid; i > low; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        for (int j = mid + 1; j < high; j++) {
            sum += array[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        setCrossLeft(maxLeft);
        setCrossRight(maxRight);
        return leftSum + rightSum;
    }

    @Override
    public int findMaxSubarray(int[] array, int low, int high) {
        int mid, leftSum, crossSum, rightSum;

        if (high == low) {
            return array[low];
        } else {
            mid = (low + high) / 2;
            leftSum = findMaxSubarray(array, low, mid);
            rightSum = findMaxSubarray(array, mid + 1, high);
            crossSum = findMaxCrossingSubarray(array, low, mid, high);

            if (leftSum >= rightSum && leftSum >= crossSum) {
                return leftSum;
            } else if (rightSum >= leftSum && rightSum >= crossSum) {
                return rightSum;
            } else {
                return crossSum;
            }
        }
    }

    @Override
    public int linearTimeMaxSubarray(int[] array) {
        int sum = Integer.MIN_VALUE;
        int lowM = 0;
        int highM = 0;
        int Mr = 0;
        int lowR = 1;
        for (int i = 0; i < array.length; i++) {
            Mr += array[i];
            if (Mr > sum) {
                lowM = lowR;
                highM = i;
                sum = Mr;
            }
            if (Mr < 0) {
                Mr = 0;
                lowR = i + 1;
            }
        }
        setCrossLeft(lowM);
        setCrossRight(highM);
        return sum;
    }


    private void setCrossLeft(int subarrayMaxLeft) {
        this.crossLeft = subarrayMaxLeft;
    }

    private void setCrossRight(int subarrayMaxRight) {
        this.crossRight = subarrayMaxRight;
    }

    int getCrossRight() {
        return crossRight;
    }

    int getCrossLeft() {
        return crossLeft;
    }
}
