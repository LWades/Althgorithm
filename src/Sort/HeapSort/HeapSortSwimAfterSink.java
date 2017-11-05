package Sort.HeapSort;

/**
 * Created by DELL on 2017/11/5.
 * 堆排序的一种改进方式
 * 但是会牺牲空间
 * 是一种空间换时间的方法
 * 适用于比较的操作代价较大的情况(以长字符串为键值等等)
 */
public class HeapSortSwimAfterSink
{
    public static int countSink = 0;
    public static int countSwim = 0;

    /*
     * 先下沉后上浮
     */
    public static void sort (char[] src)
    {
        int n = src.length;

        for (int i = n / 2; i > 0; i--)
        {
            sink(src, i, n);
            logSink(src, i, n);
        }

        for (int i = n; i > 0; i--)
        {
            exchange(src, 1, i);
            int k= sinkDirectly(src, 1, i - 1);
            logSink(src, 1, i - 1);

            swim(src, k);
            logSwim(src, k);
        }
    }

    /*
     * 上浮
     * 元素从当前位置上浮到满足堆结构的正确位置上
     */
    public static void swim(char[] src, int k)
    {
        int i = k;
        while (i / 2 > 0)
        {
            if (less(src, i/2, i))
            {
                exchange(src, i/2, i);
                i /= 2;
            }
            else
                return ;
        }
    }

    /*
     * 下沉
     */
    public static void sink(char[] src, int k, int n)
    {
        int i = k;
        while (2 * i <= n)
        {
            /*
             * j用来跟踪子节点的较大者
             */
            int j = 2 * i;
            if (j + 1 <= n && less(src, j, j+1))
                j = 2 * i + 1;

            if (less(src, i, j))
            {
                exchange(src, i, j);
                i = j;
            }
            else
                return ;
        }
    }

    /*
     * 下沉2.0
     * 这里相较于HeapSort原版做了改动
     * 不进行元素是否在正确位置的比较
     * 这也是这次改进的目的
     */
    public static int sinkDirectly(char[] src, int k, int n)
    {
        int i = k;

        while (2 * i <= n)
        {
            int j = 2 * i;

            if (j + 1 <= n && less(src, j, j+1))
                j = 2 * i + 1;

            exchange(src, i, j);
            i = j;
        }
        return i;
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

    public static void logSink (char[] src, int k, int n)
    {
        System.out.format("Sink num: %2d, sink index: %2d, heap size: %2d, array: ", ++countSink, k, n);

        for (int i = 0; i < src.length; i++)
        {
            System.out.print(src[i]);

            if (i == src.length - 1)
                System.out.println();
            else
                System.out.print(" ");
        }
    }

    public static void logSwim (char[] src, int k)
    {
        System.out.format("Swim num: %2d, swim index: %2d, array: ", ++countSwim, k);

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
    }}
