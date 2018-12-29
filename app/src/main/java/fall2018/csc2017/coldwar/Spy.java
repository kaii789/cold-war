package fall2018.csc2017.coldwar;

import fall2018.csc2017.slidingtiles.R;

public class Spy extends Agent {

    Spy(String owner){
        this.setOwner(owner);
    }

    @Override
    public int getPicture(){
        return R.drawable.spy;
    }
}
