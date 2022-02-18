package com.smoothstack.streams.menu;

/**
 * The allMatch method works similarly to anyMatch but will check to see if all the ele- ments of the
 * stream match the given predicate. For example, you can use it to find out whether the menu is
 * healthy (that is, all dishes are below 1000 calories):
 * These three operations, anyMatch, allMatch, and noneMatch, make use of what we call short-circuiting,
 * a stream version of the familiar Java short-circuiting && and || operators.
 * Short-circuiting evaluation
 * Some operations don’t need to process the whole stream to produce a result. For example, say you
 * need to evaluate a large boolean expression chained with and oper- ators. You need only find out
 * that one expression is false to deduce that the whole expression will return false, no matter
 * how long the expression is; there’s no need to evaluate the entire expression. This is what
 * short-circuiting refers to.
 * In relation to streams, certain operations such as allMatch, noneMatch, find- First, and
 * findAny don’t need to process the whole stream to produce a result. As soon as an element is
 * found, a result can be produced. Similarly, limit is also a short-circuiting operation: the
 * operation only needs to create a stream of a given size without processing all the elements
 * in the stream. Such operations are useful, for example, when you need to deal with streams of
 * infinite size, because they can turn an infinite stream into a stream of finite size.
 */
public class N_Pred_Match_All_None_Elements {
    public static void main(String[] args) {
        new N_Pred_Match_All_None_Elements();
    }

    public N_Pred_Match_All_None_Elements() {
        n_Pred_Match_All_Elements();
        n_Pred_Match_None_Elements();
    }

    /**
     *
     The opposite of allMatch is noneMatch. It ensures that no elements in the stream match the given predicate. For example, you could rewrite the previous example as follows using noneMatch:
     */
    public void n_Pred_Match_All_Elements() {
        boolean isHealthy = Dishes.menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);
    }

    public void n_Pred_Match_None_Elements() {
        boolean isHealthy = Dishes.menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy);
    }

}
