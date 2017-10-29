package Sort;

/**
 * Created by DELL on 2017/10/15.
 */
public class MergeSort
{
    public static int numOfMerge = 0;

    /*
     * 小数组阈值
     * 小于等于k的数组会直接采用插入排序
     * 大大减少递归深度，提高效率
     */
    private static int k = 2;

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
     * 迭代版本
     * 自底向上的归并排序版本
     */
    public static void sort2(char[] src)
    {
        int n = src.length;

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
     * 驱动函数
     * 优化过的归并排序
     * 和之前基本相同，为了统一管理
     */
    public static void sort3(char[] src)
    {
        int n = src.length;
        extra = new char[n];

        for (int i = 0; i < n; i++)
            extra[i] = src[i];

        sort3(extra, src, 0, n-1);
    }

    /*
     * 优化过的归并排序
     * 递归调用部分
     * 优化1：对小规模子数组使用插入排序
     * 优化2：测试数组是否已经有序
     * 优化3：不将元素复制到辅助数组
     */
    public static void sort3(char[] src, char[] dst, int lo, int hi)
    {
        if (hi - lo <= 0)
            insertSort(src, lo, hi);
        else
        {
            int mid = (lo + hi) / 2;

            sort3(dst, src, lo, mid);
            sort3(dst, src, mid+1, hi);

            if (src[mid] > src[mid+1])                      //测试数组是否有序
            {
                merge3(src, dst, lo, mid, hi);
                log3(src, dst, lo, mid, hi);
            }
        }
    }

    /*
     * 为了优化归并排序用到的插入排序
     */
    public static void insertSort(char[] src, int lo, int hi)
    {
        logInsertSort(src, lo, hi);
        for (int i = lo+1; i <= hi; i++)
        {
            int j;
            char dst = src[i];
            for (j = i; j > lo && dst < src[j-1]; j--)
                src[j] = src[j-1];
            src[j] = dst;
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



    /*
     * 优化后归并排序的合并部分
     * 把src归并到dst而复制到dst再归并到src
     * 节省了复制数组的时间
     * 见优化3
     */
    public static void merge3(char[] src, char[] dst, int lo, int mid, int hi)
    {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
                dst[k] = src[j++];
            else if (j > hi)
                dst[k] = src[i++];
            else if (src[i] <= src[j])
                dst[k] = src[i++];
            else
                dst[k] = src[j++];
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

    public static void logInsertSort(char[] src, int lo, int hi)
    {
        System.out.format("This is insertSort. lo: %2d, hi: %2d, array: ", lo, hi);

        for (int i = lo; i <= hi; i++)
        {
            System.out.print(src[i]);

            if (i == hi)
                System.out.println();
            else
                System.out.print(" ");
        }
    }

    public static void log3(char[] src, char[] dst, int lo, int mid, int hi)
    {
        System.out.format("Num  %2d: lo: %2d, mid: %2d, hi: %2d srcArray: ", ++numOfMerge, lo, mid, hi);

        for (int i = 0; i < src.length; i++)
        {
            System.out.print(src[i]);

                System.out.print(" ");
        }

        System.out.print("to dstArray: ");

        for (int i = 0; i < dst.length; i++)
        {
            System.out.print(dst[i]);
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

        sort3(src);

        dstString = String.valueOf(src);

        System.out.format("Sort result: %s", dstString);
    }
}
