package Sort;

/**
 * Created by DELL on 2017/10/19.
 */
public class QuickSort
{
    public static int count = 0;

    /*
     * 快速排序的驱动函数
     */
    public static void sort(char[] src)
    {
        int n = src.length;
        sort(src, 0, n - 1);
    }

    /*
     * 递归调用
     * lo, hi用于跟踪递归进度
     */
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
     * 快速排序的核心
     * 用段落的第一个元素作为切分的标准
     * 将小于它的都放在左部分，大于它的都放在右部分
     * 从左到右找大的，从右到左找小的，做交换
     * 最后找到最靠右的小于标准元素的元素与标准元素进行交换
     */
    public static int partition(char[] src, int lo, int hi)
    {
        int base = src[lo];
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

        /*
         * 这里的h换成l-1都可以，表示左侧部分最右的一个元素
         */
        exchange(src, lo, h);

        return l;
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

    public static void main (String[] args)
    {
        String src = "QUICKSORTEXAMPLE";
        char[] srcArray = src.toCharArray();

        sort(srcArray);

        System.out.format("The sort result: %s", String.valueOf(srcArray));
    }
}
