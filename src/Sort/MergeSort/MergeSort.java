package Sort.MergeSort;

/**
 * Created by DELL on 2017/10/15.
 */
public class MergeSort
{
    public static int numOfMerge = 0;

    /*
     * 将归并排序所需要的额外数组设置为全局
     * 有利于
     */
    private static char[] extra;

    /*
     * 驱动函数
     */
    public static void sort(char[] src)
    {
        int n = src.length;
        extra = new char[n];

        sort(src, 0, n-1);
    }

    /*
     * 递归调用
     * 排序src由lo到hi的数组
     * 从中间二分，递归排序左部分和右部分
     * special case：直到这一部分中只有一个元素
     * 自顶向下的归并排序版本
     */
    public static void sort(char[] src, int lo, int hi)
    {
        if (lo >= hi)
            return ;
        else
        {
            int mid = (lo + hi) / 2;

            sort(src, lo, mid);
            sort(src, mid+1, hi);
            merge(src, lo, mid, hi);
            log(src, lo, mid, hi);
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

    public static void log(char[] src, int lo, int mid, int hi)
    {
        System.out.format("Num  %2d: lo: %2d, mid: %2d, hi: %2d array: ", ++numOfMerge, lo, mid, hi);
        for (int i = 0; i < src.length; i++)
        {
            System.out.print(src[i]);

            if (i == src.length - 1)
                System.out.println();
            else
                System.out.print(" ");
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
