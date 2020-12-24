package main.day4;

import main.AOCRiddle;

import java.util.HashMap;

public class SecureContainer extends AOCRiddle {
    public SecureContainer(String in, int part) {
        super(in, part);
        parse();
    }

    private int from;
    private int to;

    @Override
    protected String solve1() {
        return Integer.toString(countPasswords(false));
    }

    @Override
    protected String solve2() {
        return Integer.toString(countPasswords(true));
    }

    private int countPasswords(boolean part2){
        int passwords = 0;
        for(;from<to;from++){
            if(checkRules(from,part2)){
                passwords++;
            }
        }
        return passwords;
    }
    private boolean checkRules(int password, boolean part2) {
        String p = Integer.toString(password);
        boolean temp = checkDouble(p) && checkDecreasing(p);
        if(part2){
            return temp && checkTriple(p);
        }
        return temp;
    }

    private boolean checkTriple(String password) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i< password.length();i++){
            map.putIfAbsent(password.charAt(i),0);
            map.put(password.charAt(i),map.get(password.charAt(i))+1);
        }
        return map.values().contains(new Integer(2));
    }

    private boolean checkDecreasing(String password) {
        for(int i = 1; i< password.length();i++){
            if(password.charAt(i)<password.charAt(i-1)){
                return false;
            }
        }
        return true;
    }

    private boolean checkDouble(String password) {
        for(int i = 1; i < password.length();i++) {
            if (password.charAt(i - 1) == password.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public void parse(){
        String[] textNumbers =  getInput().split("-");
        from = Integer.parseInt(textNumbers[0]);
        to = Integer.parseInt(textNumbers[1]);
    }
}
