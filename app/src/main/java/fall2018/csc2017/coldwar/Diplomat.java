package fall2018.csc2017.coldwar;

import fall2018.csc2017.slidingtiles.R;

public class Diplomat extends Agent {
    Diplomat(String owner){
        this.setOwner(owner);
    }

    @Override
    public int getPicture(){
        return R.drawable.diplomat;
    }
}
