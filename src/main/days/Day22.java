package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day22 extends AOCRiddle {
    public Day22(String in, int part) {
        super(in, part);
        parse();
    }

    private void parse() {
        instructions = new ArrayList<>();
        instructions.addAll(Arrays.asList(getInput().split("\n")));
    }

    private final int MAX_CARDS = 10006;
    private final long MAX_CARDS_TWO = 119315717514047L;
    private final long REPETITIONS = 101741582076661L;

    private List<String> instructions;

    @Override
    protected String solve1() {
        return String.valueOf(runInstructions());

    }

    private String runInstructions() {
        List<String> deck = newDeck(MAX_CARDS);
        int n = -1;
        for (String instruction : instructions) {
            int type = getShuffle(instruction);

            if (type != 0) {
                n = getNumber(instruction);
            }

            switch (type) {
                case (0):
                    deck = dealIntoNewStack(deck);
                    break;
                case (1):
                    deck = cutCards(deck, n);
                    break;
                case (2):
                    deck = dealWithIncrement(deck, n);
                    break;
            }
        }
        return deck.get(2020);
    }

    @Override
    protected String solve2() {
        return null;
    }

    private List<String> dealIntoNewStack(List<String> deck){
        List<String> newDeck = new ArrayList<>();
        for(int j = deck.size()-1; j > -1; j--){
            newDeck.add(deck.get(j));
        }
        return newDeck;
    }

    private List<String> cutCards(List<String> deck, int n){
        List<String> temp = new ArrayList<>();
        List<String> newDeck = new ArrayList<>();
        if(n>0){
            for(int j = 0; j < n; j++){
                temp.add(deck.get(j));
            }
            for(int k = n; k < deck.size(); k++){
                newDeck.add(deck.get(k));
            }
        }else{
            for(int j = deck.size() + n; j < deck.size(); j++){
                newDeck.add(deck.get(j));
            }
            for(int k = 0; k < deck.size() + n; k++){
                temp.add(deck.get(k));
            }
        }
        newDeck.addAll(temp);
        return newDeck;
    }

    private List<String> dealWithIncrement(List<String> deck, int n){
        List<String> newDeck = new ArrayList<>();
        for(int k = 0; k<deck.size(); k++){
            newDeck.add("");
        }
        int pos = 0;
        for(int j = 0; j< deck.size(); j++){
            newDeck.remove(pos);
            newDeck.add(pos,deck.get(j));
            pos += n;
            if(pos>deck.size()){
                pos = pos % deck.size();
            }
        }

        return newDeck;
    }

    private int getShuffle(String instruction) {
        if(instruction.startsWith("deal into")){
            return 0;
        }else if(instruction.startsWith("cut")){
            return 1;
        }else{
            return 2;
        }
    }

    private int getNumber(String instruction) {
        boolean isNegativ = false;
        for(int i = 0; i < instruction.length(); ++i){
            if(instruction.charAt(i) == '-'){
                isNegativ = true;
                break;
            }
        }
        if(isNegativ){
            return Integer.parseInt(instruction.replaceAll("\\D","")) * -1 ;
        }
        return Integer.parseInt(instruction.replaceAll("\\D",""));
    }

    private List<String> newDeck(long numberCards) {
        List<String> deck = new ArrayList<>();
        for(long i =0; i <= numberCards; i++){
            deck.add(Long.toString(i));
        }
        return deck;
    }
}
