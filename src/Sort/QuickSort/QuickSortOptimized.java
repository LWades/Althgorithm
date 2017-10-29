package Sort.QuickSort;

/**
 * Created by LWade on 2017/10/29.
 * 优化后的快速排序
 * 优化1：保持随机性
 *  a. 在排序前随机打乱数组顺序
 *  b. partition开始时随机指定一个元素与第一个元素进行交换
 * 优化2：去掉冗余的边界测试
 * 优化3：切换到插入排序
 */
public class QuickSortOptimized
{
    public static int count = 0;

    /*
     * 切换到插入排序的阈值
     */
    public static int M = 3;

    /*
     * 优化1
     * 加入随机性
     */
    public static void sort(char[] src)
    {
        int n = src.length;
        StdRandom.shuffle(src);
        sort(src, 0, n - 1);
    }

    /*
     * 优化3
     * 加入插入排序
     */
    public static void sort(char[] src, int lo, int hi)
    {
        if (lo + M>= hi)
        {
            insertSort(src, lo, hi);
            return ;
        }

        int splitIndex = partition(src, lo, hi);
        log(src, lo, hi, splitIndex);
        sort(src, lo, splitIndex - 1);
        sort(src, splitIndex + 1, hi);
    }

    /*
     * 优化2
     * 这里进行了优化
     * 去掉了多余的边界测试(h == lo)，因为a[lo]可以作为一个哨兵
     * 它不会比自己还要小
     * 这里要用--h而不是h--因为要先做减法在遇到a[lo]的时候提早结束循环
     * 要不然在遇到a[lo]之后在做减法就会出现越界的问题
     */
    public static int partition(char[] src, int lo, int hi)
    {
        int base = src[lo];
        int l = lo, h = hi+1;
        while (true)
        {
            while (src[++l] <= base)
                if (l == hi)
                    break;
            while (src[--h] > base)
                ;

            if (l < h)
                exchange(src, l, h);
            else
                break;
        }
        exchange(src, lo, h);

        return h;
    }

    public static void exchange(char[] src, int i, int j)
    {
        char temp = src[i];
        src[i] = src[j];
        src[j] = temp;
    }

    /*
     * 为了优化快速排序用到的插入排序
     */
    public static void insertSort(char[] src, int lo, int hi)
    {
        for (int i = lo+1; i <= hi; i++)
        {
            int j;
            char dst = src[i];
            for (j = i; j > lo && dst < src[j-1]; j--)
                src[j] = src[j-1];
            src[j] = dst;
        }
    }

    public static void log (char[] src, int lo, int hi, int partition)
    {
        System.out.format("Num: %2d, The partition: %2d %c, lo: %2d, hi: %2d, Array: ", ++count, partition, src[partition], lo, hi);

        for (int i = 0; i < src.length; i++)
        {
            System.out.print(src[i]);
            if (i != src.length - 1)
                System.out.print(" ");
            else
                System.out.println();
        }
    }


    public static void main (String[] args)
    {
        String src = "QUICKSORTEXAMPLE";
        char[] srcArray = src.toCharArray();

        sort(srcArray);

        System.out.format("The sort result: %s", String.valueOf(srcArray));
    }
}

