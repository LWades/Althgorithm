package Sort.InsertSort;

/**
 * Created by DELL on 2017/9/24.
 * 希尔排序(插入排序的改进)
 */
public class ShellSort
{
    public static void sort(char[] src)
    {
        if (null == src)
            return ;

        int n = src.length;

        /*
        初始化增量
        这里采用了书中的增量，应该效果还不错
        3*h
         */
        int h = 1;
        while (h < n/3)
            h = 3 * h + 1;

        while (h > 0)
        {
            for (int i = h; i < n; i++)
            {
                /*
                 * 第一次的移动会覆盖下标为i的元素，要将其存储起来
                 */
                char dst = src[i];
                int j;
                for (j = i; j >= h && src[j-h] > dst; j -= h)
                {
                    src[j] = src[j-h];
                }

                src[j] = dst;
            }

            h = h / 3;
        }
    }

    public static void main(String[] args)
    {
        String srcString = "SORTEXAMPLE";
        String dstString;
        char[] src = srcString.toCharArray();

        sort(src);

        dstString = String.valueOf(src);

        System.out.printf(dstString);
    }
}
