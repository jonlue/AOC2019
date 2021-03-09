package main.days;

import main.AOCRiddle;
import main.util.Tree.SortedBinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 extends AOCRiddle {
    public Day6(String in, int part) {
        super(in, part);
        parse();
        createTree();
    }

    private void createTree() {
        orbitalMap = new SortedBinaryTree<>();
        orbitalMap.add(root.substring(0,3));

        for(String i : sortedInput){
            String[] temp = i.split("\\)");
            orbitalMap.add(temp[0],temp[1]);
        }
    }

    private  String root;
    private List<String> sortedInput;
    private SortedBinaryTree<String> orbitalMap;

    @Override
    protected String solve1() {
        return String.valueOf(orbitalMap.getSumHeight());
    }

    @Override
    protected String solve2() {
        return String.valueOf(orbitalMap.getDistance("YOU","SAN"));
    }

    private void parse(){
        sortedInput = new ArrayList<>();
        List<String> ins = Arrays.stream(getInput().split("\n")).collect(Collectors.toList());
        getRoot(ins);
        sortedInput.add(root);

        while(sortedInput.size() != ins.size()){
            List<String> temp = new ArrayList<>();
            for(String t : sortedInput) {
                String parent = t.split("\\)")[1];
                for (String s : ins) {
                    if (s.startsWith(parent)) {
                        temp.add(s);
                    }
                }
            }
            sortedInput.addAll(temp);
            sortedInput = sortedInput.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        }
    }


    private void getRoot(List<String> inputs) {
        List<String> parents = new ArrayList<>();
        List<String> orbiters = new ArrayList<>();
        for (String s : inputs) {
            String[] temp = s.split("\\)");
            parents.add(temp[0]);
            orbiters.add(temp[1]);
        }
        List<String> uniqueP = getUnique(parents);
        String res = "";
        for(String i : uniqueP){
            boolean in = false;
            for(String j : orbiters){
                if (j.equals(i)) {
                    in = true;
                    break;
                }
            }
            if(!in){
                res= i;
                break;
            }
        }
        for(String i : inputs){
            if(i.startsWith(res)){
                res = i;
                break;
            }
        }
        root = res;
    }
    private List<String> getUnique(List<String> list) {
        List<String> res = new ArrayList<>();
        for (String s : list) {
            boolean contained = false;

            for (String t : res) {
                if (t.equals(s)) {
                    contained = true;
                    break;
                }
            }
            if (!contained) {
                res.add(s);
            }
        }
        return res;
    }
}
