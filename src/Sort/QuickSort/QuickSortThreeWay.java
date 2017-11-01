package Sort.QuickSort;

/**
 * Created by LWade on 2017/10/31.
 * 三路切分的快速排序
 * 关注点是排序中的重复元素方面
 */
public class QuickSortThreeWay
{
    static int count = 0;

    /*
     * 驱动函数
     */
    public static void sort(char[] src)
    {
        int n = src.length;
        sort(src, 0, n - 1);
    }

    /*
     * 这里是和普通快速排序不一样的地方
     * 这里维护几个索引
     * 用i跟踪当前待比较的那一个元素
     * 通过不断地递归将数组切分成三个部分
     * src[lo...lt-1]：小于部分
     * src[lt...gt]：等于部分
     * src[gt+1, hi]：大于部分
     */
    public static void sort(char[] src, int lo, int hi)
    {
        if (lo >= hi)
            return ;
        int lt = lo, gt = hi, i = lt + 1;
        char base = src[lo];

        while (i <= gt)
        {
            if (src[i] < base)
                exchange(src, lt++, i++);
            else if (src[i] > base)
                exchange(src, gt--, i);
            else
                i++;
        }

        log(src, lo, hi, lt, gt);
        sort(src, lo, lt - 1);
        sort(src, gt + 1, hi);
    }

    public static void log (char[] src, int lo, int hi, int lt, int gt)
    {
        System.out.format("Num %2d: lo: %2d, lt: %2d, gt: %2d, hi: %2d, array: ", ++count, lo, lt, gt, hi);

        for (int i = 0; i < src.length; i++)
        {
            System.out.print(src[i]);

            if (i == src.length - 1)
                System.out.println();
            else
                System.out.print(" ");
        }

    }

    public static void exchange(char[] src, int x, int y)
    {
        char temp = src[x];
        src[x] = src[y];
        src[y] = temp;
    }

    public static void main (String[] args)
    {
//        String src = "QUICKSORTEXAMPLE";
        String src = "QUABCQUAQUAQUAQUA";
        char[] srcArray = src.toCharArray();

        sort(srcArray);

        System.out.format("The sort result: %s", String.valueOf(srcArray));
    }
}

