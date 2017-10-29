package Sort;

/**
 * Created by DELL on 2017/9/23.
 */
public class SelectionSort
{
    public static void sort(char[] src)
    {
        if (null == src)
            return ;

        int n = src.length;

        for (int i = 0; i < n; i++)
        {
            //索引不断往右走，左边都是有序的
            int min = i;

            //找到索引右边最小的值
            for (int j = i+1; j < n; j++)
            {
                if (src[j] < src[min])
                    min = j;
            }

            //最小值换到索引右边的最左边
            exch(src, i, min);
        }
        //索引到达了数组的末端，排序结束
    }

    private static void exch(char[] a, int i, int j) {
        char swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main (String[] args)
    {
        String srcString = "SORTEXAMPLE";
        String dstString;
        char[] src = srcString.toCharArray();

        sort(src);

        dstString = String.valueOf(src);

        System.out.printf(dstString);
    }
}
