package utils;

import com.google.common.collect.Lists;

import java.util.List;

public class ListDivider {

    private ListDivider() {
    }

    public static <T> List<List<T>> divideListIntoSubLists(List<T> list, int subListsNumber) {
        int subListSize = getSubListSize(list, subListsNumber);
        return Lists.partition(list, subListSize);
    }

    private static <T> int getSubListSize(List<T> list, int subListsNumber) {
        return (int) Math.ceil((double) list.size() / (double) subListsNumber);
    }

}
