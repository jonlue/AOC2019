package main.day8;

import main.AOCRiddle;

public class SpaceImageFormat extends AOCRiddle {
    public SpaceImageFormat(String in, int part) {
        super(in, part);
        getRows();
    }

    private static final int PICTURE_WIDTH = 25;
    private static final int PICTURE_HEIGHT = 6;
    private String picture;
    private String[] rows;

    @Override
    protected String solve1() {
        return String.valueOf(transmissionUncorrupted());
    }

    @Override
    protected String solve2() {
        return decodePicture();
    }

    private String decodePicture() {
        String[] picture = getPicture(rows);
        StringBuilder pic = new StringBuilder();
        for(String s : picture){
            pic.append(s.replaceAll("0"," ").replaceAll("1","#"));
            pic.append("\n");
        }
        return pic.toString();
    }

    private static String[] getPicture(String[] layers) {
        String[] picCompl = new String[6];
        String pic ="";
        for(int j = layers.length-1; j> -1; j--){
            String layer = layers[j];
            if(j==layers.length-1){
                pic = layers[j];
            }else{
                String temp ="";
                for(int i = 0; i < layer.length(); i++){
                    if(layer.charAt(i)=='0'){
                        temp = temp.concat("0");
                    }else if(layer.charAt(i)=='1'){
                        temp = temp.concat("1");
                    }else{
                        temp = temp.concat(Character.toString(pic.charAt(i)));
                    }
                }
                pic = temp;
            }
        }
        for(int k = 0; k < PICTURE_HEIGHT; k++){
            picCompl[k]=pic.substring(0,PICTURE_WIDTH);
            pic = pic.substring(PICTURE_WIDTH);
        }

        return picCompl;
    }

    private void getRows() {
        picture = getInput();
        int len = picture.length();
        int totalPixel = PICTURE_WIDTH*PICTURE_HEIGHT;
        int numberOfLayer = len/totalPixel;
        rows = new String[numberOfLayer];
        for(int i = 0; i<numberOfLayer; i++) {
            rows[i] = picture.substring(0,totalPixel);
            picture = picture.substring(totalPixel);
        }
    }

    private int transmissionUncorrupted() {
        int layerFewestZero = getLayerWithFewest();
        int ones = countNumber(rows[layerFewestZero],'1');
        int twos = countNumber(rows[layerFewestZero],'2');
        return ones * (twos+1);
    }

    private int getLayerWithFewest() {
        int min = Integer.MAX_VALUE;
        int lay = -1;
        for(int j = 0; j < rows.length-1; j++){
            String layer = rows[j];
            int count = 0;
            for(int i=0; i<layer.length()-1;i++){
                if(layer.charAt(i) ==  '0'){
                    count++;
                }
            }
            if(count<min){
                min = count;
                lay = j;
            }
        }
        return lay;
    }

    private int countNumber(String layer, char number) {
        int count = 0;
        for(int i = 0; i<layer.length()-1; i++){
            if(layer.charAt(i)==number){
                count++;
            }
        }
        return count;
    }


}
