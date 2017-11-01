package Sort.QuickSort;

import java.util.Random;

/**
 * Created by LWade on 2017/10/29.
 */
public class QuickSortThreeSample
{
    public static int count = 0;

    public static void sort(char[] src)
    {
        int n = src.length;
        sort(src, 0, n - 1);
    }

    public static void sort(char[] src, int lo, int hi)
    {
        if (lo >= hi)
            return ;

        int splitIndex = partition(src, lo, hi);
        log(src, lo, hi, splitIndex);
        sort(src, lo, splitIndex - 1);
        sort(src, splitIndex + 1, hi);
    }

    /*
     * 为了改进快速排序性能
     * 进行三取样优化
     * 再给定数组中随机取三个元素（这里指定的可重复）
     * 取三个数的大小中位数的那个元素
     */
    public static int partition(char[] src, int lo, int hi)
    {
        int m = getSample(src, lo, hi);
        int base = src[m];
        exchange(src, lo, m);
        logSample(src, lo, hi, m);
        int l = lo, h = hi;
        while (true)
        {
            while (src[l] <= base)
                if (++l >= h)
                    break;
            while (src[h] > base)
                if (--h <= l)
                    break;

            if (l <= h)
                exchange(src, l, h);
            else
                break;
        }
        exchange(src, lo, h);

        return h;
    }

    /*
     * 三取样
     * 随机进行三次有放回的抽样
     * 选取三次结果的大小中位数作为返回值
     * 供partition的初值
     */
    public static int getSample(char[] src, int lo, int hi)
    {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        int[] sampleIndex = new int[3];
        char[] sample = new char[3];

        for (int i = 0; i < 3; i++)
        {
            sampleIndex[i] = random.nextInt(hi - lo + 1) + lo;
            sample[i] = src[sampleIndex[i]];
            for (int j = i; j > 0 && sample[j] < sample[j-1]; j--)
                exchange(sample, j, j-1);
        }

        for (int i = 0; i < 3; i++)
        {
            if (src[sampleIndex[i]] == sample[1])
                return sampleIndex[i];
        }

        return -1;
    }

    /*
     * 为了将取样样本排序用到的插入排序
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

    public static void exchange(char[] src, int i, int j)
    {
        char temp = src[i];
        src[i] = src[j];
        src[j] = temp;
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

    public static void logSample(char[] src, int lo, int hi, int m)
    {
        System.out.format("This is sample, lo: %2d, hi: %2d, m: %2d, arrayAfterExch: ", lo, hi, m);

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
