package com.marcinseweryn.algorithms.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class TreeNode<T> {
    List<TreeNode<T>> children;
    T value;

    public TreeNode(T value) {
        this.value = value;
        children = new ArrayList<>();
    }

    /**
     * Return String that represent the Tree hierarchy of
     * this TreeNode
     *
     * @return String that represent the Tree hierarchy
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(this, 0, sb);
        return sb.toString();
    }

    public void print(TreeNode<T> root, int level, StringBuilder sb) {
        sb.append(" ".repeat(level) + root.value + "\n");
        for (TreeNode<T> child : root.children) {
            print(child, level + 1, sb);
        }
    }

    public static void main(String[] args) {
        TreeNode<String> drinks = new TreeNode<>("Drinks"); // root
        TreeNode<String> hot = new TreeNode<>("Hot");
        TreeNode<String> cold = new TreeNode<>("Cold");
        TreeNode<String> nonAlcoholic = new TreeNode<>("Non alcoholic");
        TreeNode<String> alcoholic = new TreeNode<>("Alcoholic");

        TreeNode<String> tea = new TreeNode<>("Tea");
        TreeNode<String> coffee = new TreeNode<>("Coffee");
        TreeNode<String> green = new TreeNode<>("Green");
        TreeNode<String> black = new TreeNode<>("Black");
        TreeNode<String> americano = new TreeNode<>("Americano");
        TreeNode<String> latte = new TreeNode<>("Latte");
        TreeNode<String> beer = new TreeNode<>("Beer");
        TreeNode<String> wine = new TreeNode<>("Wine");
        TreeNode<String> soda = new TreeNode<>("Soda");
        TreeNode<String> fanta = new TreeNode<>("Fanta");

        hot.children.add(tea);
        hot.children.add(coffee);
        coffee.children.add(black);
        coffee.children.add(americano);
        coffee.children.add(latte);
        tea.children.add(green);
        tea.children.add(black);
        nonAlcoholic.children.add(fanta);
        nonAlcoholic.children.add(soda);
        alcoholic.children.add(wine);
        alcoholic.children.add(beer);
        cold.children.add(alcoholic);
        cold.children.add(nonAlcoholic);
        drinks.children.add(hot);
        drinks.children.add(cold);

        out.println(drinks);

        /* OUTPUT
                    Drinks
                     Hot
                      Tea
                       Green
                       Black
                      Coffee
                       Black
                       Americano
                       Latte
                     Cold
                      Alcoholic
                       Wine
                       Beer
                      Non alcoholic
                       Fanta
                       Soda
         */
    }
}
