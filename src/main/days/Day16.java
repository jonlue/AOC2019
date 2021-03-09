package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.List;

public class Day16 extends AOCRiddle {
    public Day16(String in, int part) {
        super(in, part);
        parse();
    }

    private static final int STEPS = 100;
    private static final int[] pattern = {1, 0, -1, 0};

    private int[] transmission;
    private String textTransmission;
    private int originalLength;

    @Override
    protected String solve1() {
        return calculateSteps().substring(0,8);
    }

    @Override
    protected String solve2() {
        /*
        originalLength = textTransmission.length();
        textTransmission = textTransmission.repeat(10000);
        */
        textTransmission = textTransmission.repeat(10000);
        List<Integer> in = new ArrayList<>();
        for(char c : textTransmission.toCharArray()){
            in.add(Character.getNumericValue(c));
        }

        String temp =calculateSteps();
        int pos = Integer.parseInt(temp.substring(0,7));

        return temp.substring(pos,pos+8);
    }

    private String calculateSteps() {
        String step = textTransmission;
        for(int i = 0; i < STEPS; ++i){
            step = makeStep(step);
            System.out.println(i);
        }
        return step;
    }

    private String makeStep(String input){
        return "";
    }

    /*
    private String makeStep(String input){
        StringBuilder sb = new StringBuilder();

        for(int k = 0; k < input.length(); k++){
            int sum = 0;
            int patternRepeating = k+1;
            int patternCount = 0;
            int patternIndex = 0;
            if((pattern.length*originalLength)%(k+1) != 0) {
                for (int j = k; j < textTransmission.length(); ++j) {
                    sum += Character.getNumericValue(input.charAt(j)) * pattern[patternIndex];

                    patternCount++;
                    if (patternCount == patternRepeating) {
                        patternIndex = (patternIndex+1)%pattern.length;
                        patternCount = 0;

                        if(pattern[patternIndex] == 0){
                            j += patternRepeating-1;
                            patternIndex = (patternIndex+1)%pattern.length;
                        }
                    }
                }
            }
            sb.append(Math.abs(sum % 10));
        }
        return sb.toString();
    }
    */
    /*
    private String makeStep(String input){
        StringBuilder sb = new StringBuilder();

        for(int k = 0; k<input.length(); k++){
            int sum = 0;
            int times = k+1;
            int count = 0;
            int index = 0;
            for(int j = k; j<input.length(); j++){
                if(index == pattern.length){
                    index = 0;
                }
                if(pattern[index] != 0) {
                    sum += Character.getNumericValue(input.charAt(j)) * pattern[index];
                }
                count++;
                if(count == times){
                    index++;
                    count = 0;
                }
            }
            sb.append(Math.abs(sum % 10));
        }
        return sb.toString();
    }
*/

    private void parse(){
        textTransmission = getInput();
        transmission = new int[textTransmission.length()];
        for(int i = 0; i < textTransmission.length(); i++){
            transmission[i] = Character.getNumericValue(textTransmission.charAt(i));
        }
    }
}
