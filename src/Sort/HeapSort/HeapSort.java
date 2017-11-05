package Sort.HeapSort;

/**
 * Created by DELL on 2017/11/4.
 * 堆排序的实现
 * 这里需要注意，为了方便堆排序是不用数组中的第一个元素的
 * 堆排序是我们所知的唯一能够同时最优地利用空间和时间的方法
 * 但是现代系统许多应用却很少使用它
 * 因为它没有办法利用缓存
 * 数组元素很少与其相邻的元素进行比较
 */
public class HeapSort
{
    public static int count = 0;

    public static void sort(char[] src)
    {
        int n = src.length;

        /*
         * 用一个for循环构造堆
         * 从倒数第二层开始
         * 一个一个下沉
         */
        for (int i = n / 2; i > 0; i--)
        {
            sink(src, i, n);
            log(src, i, n);
        }

        for (int i = n; i > 0; i--)
        {
            /*
             * 将第一个元素和堆的最后一个元素进行交换
             * 堆的大小不断减小
             * 这样从末尾到头就逐渐将数组排序了
             */
            exchange(src, 1, i);
            sink(src, 1, i-1);
            log(src, 1, i);
        }
    }

    /*
     * 堆排序的核心
     * 从k位置的元素开始
     * 和子节点的较大者进行交换
     * 知道比它们都大，或者到了堆的底部
     */
    public static void sink(char[] src, int k, int n)
    {
        int i = k;
        while (2 * i <= n)
        {
            /*
             * j用来跟踪子节点的较大者
             */
            int j = 2*i;
            if (j + 1 <= n && less(src, j, j+1))
                j = 2*i + 1;

            if (less(src, i, j))
            {
                exchange(src, i, j);
                i = j;
            }
            else
                return ;
        }
    }

    public static boolean less (char[] src, int a, int b)
    {
        return src[a-1] < src[b-1];
    }

    public static void exchange(char[] src, int a, int b)
    {
        char temp = src[a-1];
        src[a-1] = src[b-1];
        src[b-1] = temp;
    }

    public static void log (char[] src, int k, int n)
    {
        System.out.format("Sink num: %2d, sink index: %2d, heap size: %2d, array: ", ++count, k, n);

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
        String src = "HEAPSORTEXAMPLE";
        char[] srcArray = src.toCharArray();

        sort(srcArray);

        System.out.format("The sort result: %s", String.valueOf(srcArray));
    }
}
