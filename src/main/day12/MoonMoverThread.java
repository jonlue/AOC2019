package main.day12;

import java.util.ArrayList;
import java.util.List;

public class MoonMoverThread extends Thread {

    private final List<Moon> moons;
    private final List<Moon> original;
    private final int mode;
    private long result;

    public MoonMoverThread(List<Moon> moons, int mode){
        super();
        this.moons = moons;
        result = -1;
        this. mode = mode;
        original = new ArrayList<>();
        for (Moon moon : moons) {
            original.add(new Moon(moon));
        }
    }

    @Override
    public void run() {
        long count = 0;
        boolean found = false;
        while(!found){
            ++count;
            found = true;
            compareMoons(moons);
            for(int i = 0; i < moons.size(); ++i){
                moons.get(i).applyVelocity();
                int pOriginal, pThis, vThis;
                switch (mode){
                    case 0:
                        pOriginal = original.get(i).getX();
                        pThis = moons.get(i).getX();
                        vThis = moons.get(i).getVelocityX();
                        break;
                    case 1:
                        pOriginal = original.get(i).getY();
                        pThis = moons.get(i).getY();
                        vThis = moons.get(i).getVelocityY();
                        break;
                    default:
                        pOriginal = original.get(i).getZ();
                        pThis = moons.get(i).getZ();
                        vThis = moons.get(i).getVelocityZ();
                        break;
                }
                if(found){
                    if(vThis != 0){
                        found = false;
                    }else{
                        if(pOriginal != pThis){
                            found = false;
                        }
                    }
                }
            }
        }
        result = count;
    }

    public long getResult(){
        return result;
    }

    private void compareMoons(List<Moon> moons) {
        for (int i = 0; i <moons.size(); i++) {
            for (int j = i+1; j < moons.size(); j++){
                moons.get(i).compareMoon(moons.get(j),mode);
            }
        }
    }
}
