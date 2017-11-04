package Sort.MergeSort;

/**
 * Created by DELL on 2017/11/1.
 */
public class MergeSortDownToUp
{

    /*
     * 将归并排序所需要的额外数组设置为全局
     * 有利于
     */
    private static char[] extra;

    /*
     * 迭代版本
     * 自底向上的归并排序版本
     */
    public static void sort(char[] src)
    {
        int n = src.length;
        extra = new char[n];

        for (int size = 2; size / 2 < n; size *= 2)
        {
            for (int i = 0; i < n; i += size)
            {
                int hi = Math.min(n - 1, i + size - 1);
                int mid = Math.min(n - 1, i + size / 2 - 1);
                merge(src, i, mid, hi);
            }
        }
    }

    /*
     * 归并排序的核心
     * 将src数组的[lo...mid]和[mid+1...hi]归并起来
     */
    public static void merge(char[] src, int lo, int mid, int hi)
    {
        /*
         * 用i, j分别跟踪数组两个部分的没有比较的最左下标
         */
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)
            extra[k] = src[k];

        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
                src[k] = extra[j++];
            else if (j > hi)
                src[k] = extra[i++];
            else if (extra[i] <= extra[j])                  //这里一定要是<=，虽然结果相同但是，这样可以保证稳定性
                src[k] = extra[i++];
            else
                src[k] = extra[j++];
        }
    }

    public static void main (String[] args)
    {
        String srcString = "MERGESORTEXAMPLE";
        String dstString;
        char[] src = srcString.toCharArray();

        sort(src);

        dstString = String.valueOf(src);

        System.out.format("Sort result: %s", dstString);
    }
}
