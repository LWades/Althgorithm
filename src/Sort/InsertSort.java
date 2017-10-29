package Sort;

/**
 * Created by DELL on 2017/9/23.
 */
public class InsertSort {

    public static void sort(char[] src)
    {
        if (null == src)
            return ;

        int n = src.length;

        for (int i = 1; i < n; i++)
        {
            int j;
            char dst = src[i];
            //将大的元素向右移动，这样每次都交换相邻元素的方式减少了访问数组的次数
            for (j = i; j > 0 && dst < src[j-1]; j--)
                src[j] = src[j-1];

            src[j] = dst;
        }

        /*
        这种方式就是书上的方式，效率不如上面，因为每次都要交换两个元素的位置
         */
//        for (int i = 1; i < n; i++)
//        {
//            for (int j = i; j > 0 && src[j-1] > src[j]; j--)
//            {
//                char temp = src[j-1];
//                src[j-1] = src[j];
//                src[j] = temp;
//            }
//        }
    }

    public static void main (String[] args)
    {
        String srcString = "SO";
//        String srcString = "SORTEXAMPLE";
        String dstString;
        char[] src = srcString.toCharArray();

        sort(src);

        dstString = String.valueOf(src);

        System.out.printf(dstString);
    }
}
